package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.skillbox.github.R
import com.skillbox.github.data.Project
import kotlinx.android.synthetic.main.fragment_detail_repository_info.*
import java.text.SimpleDateFormat

class DetailRepositoryInfo : Fragment(R.layout.fragment_detail_repository_info) {

    private val viewModel: ProjectViewModel by navGraphViewModels(R.id.repositories) {
        defaultViewModelProviderFactory
    }

    private val args: DetailRepositoryInfoArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        checkStar()

        repository_star_detail.setOnClickListener {
            changeStatusStar()
        }
    }

    private fun changeStatusStar() {
        val project = viewModel.projectList.value!![args.position]
        val owner: String = project.owner.user_name
        val repo: String = project.name
        Log.e("changeStatusStar", "${viewModel.starStatus.value}")

        viewModel.changeStarStatus(owner, repo)
    }

    private fun bindViewModel() {
        viewModel.projectList.observe(viewLifecycleOwner, ::repositoryInfo)
        viewModel.starStatus.observe(viewLifecycleOwner, ::starStatus)
    }

    private fun repositoryInfo(projects: List<Project>) {
        Log.e("repositoryInfo", "${args.position}")
        Log.e("repositoryInfo", viewModel.projectList.value.toString())
        val project = projects[args.position]
        if (project.stargazers_count> 0) {
            stars_count_detail.text = project.stargazers_count.toString()
        }

        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")

        repository_name_detail.text = project.name
        view_repository_id_detail.text = project.id.toString()
        view_html_url_detail.text = project.html_url
        user_name_for_repository_detail.text = project.owner.user_name
        view_language_detail.text = project.language
        view_created_at_detail.text = formatter.format(project.created_at)
        view_updated_at_detail.text = formatter.format(project.updated_at)

        Glide.with(user_avatar_for_repository_detail)
            .load(project.owner.avatar_url)
            .placeholder(R.drawable.load_imege)
            .into(user_avatar_for_repository_detail)
            .view
    }

    private fun checkStar() {
        val project = viewModel.projectList.value!![args.position]
        val owner: String = project.owner.user_name
        val repo: String = project.name
        viewModel.checkStarStatus(owner = owner, repo = repo)
    }

    private fun starStatus(status: Boolean) {
        if (status) {
            repository_star_detail.setImageResource(R.drawable.star_thrue)
        } else {
            repository_star_detail.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
    }
}
