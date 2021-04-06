package com.skillbox.github.ui.repository_list.adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skilbox.networking.data.network.adapter.inflate
import com.skillbox.github.R
import com.skillbox.github.data.Project
import java.util.*

class RepositoryDelegateAdapter(val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Project, Project, RepositoryDelegateAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ProjectViewHolder {
        return ProjectViewHolder(parent.inflate(R.layout.repository_view_for_list), onItemClick)
    }

    class ProjectViewHolder(view: View, onItemClick: (position: Int) -> Unit) : BaseHolder(view, onItemClick) {

        fun bind(repository: Project) {
            bindMainInfo(
                id = repository.id.toString(),
                repository_name = repository.name,
                html_url = repository.html_url.toString(),
                user = repository.owner,
                language = repository.language.toString(),
                created_at = repository.created_at,
                updated_at = repository.updated_at,
                stargazers_count = repository.stargazers_count

            )
        }
        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: Project,
        items: MutableList<Project>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: Project,
        holder: ProjectViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
