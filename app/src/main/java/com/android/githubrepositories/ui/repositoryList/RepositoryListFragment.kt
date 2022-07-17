package com.android.githubrepositories.ui.repositoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.githubrepositories.R
import com.android.githubrepositories.databinding.FragmentRepositoryListBinding
import com.android.githubrepositories.domain.model.RepositoryModel
import com.android.githubrepositories.ui.ViewState.Failure
import com.android.githubrepositories.ui.ViewState.Loading
import com.android.githubrepositories.ui.ViewState.Success
import com.android.githubrepositories.ui.repositoryList.adapter.RepositoryListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class RepositoryListFragment : Fragment() {

    companion object {
        private const val LIST_BUNDLE_KEY = "LIST_BUNDLE_KEY"
        private const val REQUEST_PAGE_KEY = "REQUEST_PAGE_KEY"
    }

    private var requestPage = 1

    private var _binding: FragmentRepositoryListBinding? = null
    private val binding get() = _binding!!

    private val repositoryViewModel: RepositoryListViewModel by viewModel()

    private var repositoryList: ArrayList<RepositoryModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSavedInstances(savedInstanceState)
        setupFields()
        setupListeners()
    }

    private fun handleSavedInstances(savedInstanceState: Bundle?) {
        savedInstanceState?.getParcelableArrayList<RepositoryModel>(LIST_BUNDLE_KEY)?.let {
            repositoryList = it
        } ?: run {
            repositoryViewModel.getRepositories(requestPage)
        }
        requestPage = savedInstanceState?.getInt(REQUEST_PAGE_KEY) ?: requestPage
    }

    private fun setupFields() {
        binding.rvRepositoryList.adapter =
            RepositoryListAdapter(
                repositoryList,
                { repositoryViewModel.getRepositories(requestPage) }
            ) {
                findNavController().navigate(
                    RepositoryListFragmentDirections.actionRepositoryListFragmentToPullRequestFragment(it)
                )
            }
        addListDivider(binding.rvRepositoryList)
    }

    private fun addListDivider(list: RecyclerView) {
        ResourcesCompat.getDrawable(
            resources, R.drawable.list_item_divider,
            resources.newTheme()
        )?.let { drawable ->
            val decorator = DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
            decorator.setDrawable(drawable)
            list.addItemDecoration(decorator)
        }
    }

    private fun setupListeners() {
        repositoryViewModel.repositoryList.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                is Failure -> {
                    handleRepositoryListError(it)
                }
                is Success -> {
                    requestPage += 1
                    handleRepositoryListSuccess(it.data)
                }
            }
        }
    }

    private fun handleRepositoryListError(failure: Failure) {
        var errorMessage = ""
        failure.message?.let { text ->
            errorMessage = text
        }
        failure.resId?.let { res ->
            errorMessage = getString(res)
        }
        Toast.makeText(
            context,
            errorMessage,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun handleRepositoryListSuccess(list: List<RepositoryModel>) {
        val lastIndex = repositoryList.lastIndex
        repositoryList.addAll(list)
        if (list.isNotEmpty()) {
            binding.rvRepositoryList.adapter?.notifyItemRangeInserted(
                lastIndex + 1,
                list.size
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(LIST_BUNDLE_KEY, repositoryList)
        outState.putInt(REQUEST_PAGE_KEY, requestPage)
        super.onSaveInstanceState(outState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}