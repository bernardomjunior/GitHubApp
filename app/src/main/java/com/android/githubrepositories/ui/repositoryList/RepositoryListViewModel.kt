package com.android.githubrepositories.ui.repositoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.githubrepositories.R
import com.android.githubrepositories.domain.ResultWrapper.GenericError
import com.android.githubrepositories.domain.ResultWrapper.NetworkError
import com.android.githubrepositories.domain.ResultWrapper.Success
import com.android.githubrepositories.domain.model.RepositoryModel
import com.android.githubrepositories.domain.usecase.GetKotlinRepositoriesUseCase
import com.android.githubrepositories.ui.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

internal class RepositoryListViewModel(
    private val useCase: GetKotlinRepositoriesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _repositoryList = MutableLiveData<ViewState<List<RepositoryModel>>>()
    val repositoryList: LiveData<ViewState<List<RepositoryModel>>> = _repositoryList

    fun getRepositories(page: Int) {
        viewModelScope.launch(dispatcher) {
            if (_repositoryList.value == ViewState.Loading) return@launch
            _repositoryList.postValue(ViewState.Loading)
            when (val response = useCase.execute(page)) {
                is Success -> {
                    _repositoryList.postValue(ViewState.Success(response.value.items))
                }
                is GenericError -> {
                    if (response.message.isNullOrEmpty()) {
                        _repositoryList.postValue(
                            ViewState.Failure(resId = R.string.unknown_error)
                        )
                    } else {
                        _repositoryList.postValue(ViewState.Failure(response.message))
                    }
                }
                NetworkError -> _repositoryList.postValue(
                    ViewState.Failure(resId = R.string.connection_problem)
                )
            }
        }
    }


}