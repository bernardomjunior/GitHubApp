package com.android.githubrepositories.ui.pullRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.githubrepositories.databinding.FragmentPullRequestBinding
import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.ui.ViewState.Failure
import com.android.githubrepositories.ui.ViewState.Loading
import com.android.githubrepositories.ui.ViewState.Success
import com.android.githubrepositories.ui.pullRequest.adapter.PullRequestListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestFragment : Fragment() {

    private var _binding: FragmentPullRequestBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PullRequestViewModel by viewModel()
    private val args: PullRequestFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPullRequestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        val repository = args.repository
        viewModel.getPullRequests(
            creator = repository.owner.nickName,
            repositoryName = repository.name
        )
    }

    private fun setupListeners() {
        viewModel.pullRequestList.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                is Failure -> handlePullRequestListError(it)
                is Success -> setupFields(it.data)
            }
        }
    }

    private fun setupFields(list: List<PullRequestModel>){
        binding.pullRequestList.adapter = PullRequestListAdapter(list) {

        }
    }

    private fun handlePullRequestListError(failure: Failure) {
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
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}