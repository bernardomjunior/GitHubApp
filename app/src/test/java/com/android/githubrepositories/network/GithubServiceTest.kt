package com.android.githubrepositories.network

import com.android.githubrepositories.enqueueResponse
import com.android.githubrepositories.network.exception.RequestError
import com.android.githubrepositories.network.model.RepositoryResponse
import com.android.githubrepositories.network.model.UserResponse
import junit.framework.Assert.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class GithubServiceTest {

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var service: GithubService

    private val mockWebserver = MockWebServer()


    @Before
    fun setup() {
        val api = Retrofit.Builder()
            .baseUrl(mockWebserver.url("/").toString())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(GithubApi::class.java)

        service = GithubService(api, dispatcher)
    }

    @After
    fun tearDown() {
        mockWebserver.shutdown()
    }

    @Test
    fun `givenValidResponse whenRequestForRepositories shouldTransformTheDataProperly`() =
        runBlocking {
            // arrange
            mockWebserver.enqueueResponse("200.json", 200)

            // act
            val actual = service.getRepositories("language:Kotlin", "stars", 1)
            val expected = RepositoryResponse(
                name = "okhttp",
                description = "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
                starCount = 42324,
                forksCount = 8866,
                owner = UserResponse(
                    avatar = "https://avatars.githubusercontent.com/u/82592?v=4",
                    nickName = "square"
                )
            )

            // assert
            assertEquals(30, actual.items.size)
            assertEquals(expected, actual.items[0])
        }

    @Test
    fun `givenErrorResponse whenRequestForRepositories shouldThrowException`(): Unit = runBlocking {
        // arrange
        mockWebserver.enqueueResponse("422.json", 422)

        // act
        try {
            service.getRepositories("language:asd", "stars", 1)
            fail("Did not throw a Exception")
        } catch (err: Exception) {

            // assert
            val expected = "Validation Failed"
            assertTrue { err is RequestError }
            assertEquals(expected, err.message)
        }
    }
}