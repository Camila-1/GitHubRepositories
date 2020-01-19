package com.example.githubrepositories.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.R
import com.example.githubrepositories.response.UserRepository
import kotlinx.android.synthetic.main.list_item.view.*

class RepoAdapter(private val items: List<UserRepository>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: UserRepository) = with(itemView){
            project_name.text = item.name
            language.text = item.language
            stargazersCount.text = item.stargazersCount.toString()
            forksCount.text = item.forksCount.toString()
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) = holder.bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder = with(parent){
        return RepoViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, this, false))
    }
}