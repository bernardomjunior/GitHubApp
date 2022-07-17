package com.android.githubrepositories.ui.pullRequest


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.githubrepositories.R
import com.android.githubrepositories.domain.ResultWrapper.Success
import com.android.githubrepositories.domain.ResultWrapper.GenericError
import com.android.githubrepositories.domain.ResultWrapper.NetworkError
import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.domain.usecase.ListRepositoryPullRequestsUseCase
import com.android.githubrepositories.ui.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

internal class PullRequestViewModel(
    private val useCase: ListRepositoryPullRequestsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _pullRequestList = MutableLiveData<ViewState<List<PullRequestModel>>>()
    val pullRequestList: LiveData<ViewState<List<PullRequestModel>>> = _pullRequestList

    fun getPullRequests(creator: String, repositoryName: String) {
        viewModelScope.launch(dispatcher) {
            _pullRequestList.postValue(ViewState.Loading)
            when (val response = useCase.execute(creator, repositoryName)) {
                is Success -> _pullRequestList.postValue(ViewState.Success(response.value))
                is GenericError -> {
                    if (response.message.isNullOrEmpty()) {
                        _pullRequestList.postValue(
                            ViewState.Failure(resId = R.string.unknown_error)
                        )
                    } else {
                        _pullRequestList.postValue(ViewState.Failure(response.message))
                    }
                }
                NetworkError -> _pullRequestList.postValue(
                    ViewState.Failure(resId = R.string.connection_problem)
                )
            }
        }
    }

}