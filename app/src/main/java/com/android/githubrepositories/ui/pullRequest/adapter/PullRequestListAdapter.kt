package com.android.githubrepositories.ui.pullRequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.githubrepositories.databinding.ListItemPullRequestBinding
import com.android.githubrepositories.domain.model.PullRequestModel
import com.android.githubrepositories.ui.pullRequest.adapter.PullRequestListAdapter.PullRequestViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat

internal class PullRequestListAdapter(
    private val pullRequestList: List<PullRequestModel>,
    private val onItemClick: (PullRequestModel) -> Unit
): Adapter<PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            ListItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(pullRequestList[position])
    }

    override fun getItemCount() = pullRequestList.size


    inner class PullRequestViewHolder(
        private val binding: ListItemPullRequestBinding
    ): ViewHolder(binding.root) {
        fun bind(pullRequest: PullRequestModel){
            val formatter = SimpleDateFormat.getDateInstance()
            binding.root.setOnClickListener { onItemClick(pullRequest) }
            binding.textviewPullRequestBody.text = pullRequest.body
            binding.textviewPullRequestDate.text = formatter.format(pullRequest.createdAt)
            binding.textviewPullRequestTitle.text = pullRequest.title
            binding.textviewPullRequestAuthorUsername.text = pullRequest.user.nickName
            Glide.with(binding.root)
                .load(pullRequest.user.avatar)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.pullRequestAuthorAvatar)
        }
    }

}