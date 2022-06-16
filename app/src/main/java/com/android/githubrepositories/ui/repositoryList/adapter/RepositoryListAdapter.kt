package com.android.githubrepositories.ui.repositoryList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.githubrepositories.databinding.ListItemRepositoryBinding
import com.android.githubrepositories.domain.model.RepositoryModel
import com.android.githubrepositories.ui.repositoryList.adapter.RepositoryListAdapter.RepositoryViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

internal class RepositoryListAdapter(
    private val repositoryList: List<RepositoryModel>,
    private val loadMore: () -> Unit
): Adapter<RepositoryViewHolder>() {

    private val visibleThreshold = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ListItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        if (position > repositoryList.lastIndex - visibleThreshold){
            loadMore()
        }
        holder.bind(repositoryList[position])
    }

    override fun getItemCount(): Int =
        repositoryList.size

    inner class RepositoryViewHolder(
        private val binding: ListItemRepositoryBinding
    ) : ViewHolder(binding.root){
        fun bind(repository: RepositoryModel){
            binding.textviewRepositoryName.text = repository.name
            binding.textviewRepositoryDescription.text = repository.description
            binding.textviewRepositoryForkCounter.text = repository.forksCount.toString()
            binding.textviewRepositoryStarCounter.text = repository.starCount.toString()
            binding.textviewRepositoryOwnerUsername.text = repository.owner.nickName
            Glide.with(binding.root)
                .load(repository.owner.avatar)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.repositoryOwnerAvatar)
        }
    }
}