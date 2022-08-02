package com.android.githubrepositories.ui.repositoryList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.githubrepositories.R
import org.koin.test.KoinTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoryListFragmentTest: KoinTest {

    private lateinit var navController: TestNavHostController
    private lateinit var repositoryScenario: FragmentScenario<RepositoryListFragment>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

        @Before
    fun setup(){
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        repositoryScenario = launchFragmentInContainer()
        repositoryScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun simpleActivityTest() {
        onView(withId(R.id.rvRepositoryList)).check(matches(isDisplayed()))
    }

//    @Test
//    fun navigateToDetailPullRequestScreen(){
//        onView(withId(R.id.rvRepositoryList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
//        assertEquals(navController.currentDestination?.id, R.id.pullRequestFragment)
//    }

}