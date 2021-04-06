package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import com.skillbox.github.ui.repository_list.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.repository_list_fragment.*

class RepositoryListFragment : Fragment(R.layout.repository_list_fragment) {

    private var repositoryAdapter: RepositoryListAdapter? = null

    private val viewModel: ProjectViewModel by navGraphViewModels(R.id.repositories) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadProgectList()
        bindViewModel()
        initList()
    }

    private fun bindViewModel() {
        error_text_repository.isVisible = false
        viewModel.isLoadingProject.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.projectList.observe(viewLifecycleOwner) { repositoryAdapter?.items = it }
        viewModel.serverError.observe(viewLifecycleOwner, ::postErrorMassage)
    }

    private fun postErrorMassage(errorServerMassage: String) {
        if (errorServerMassage.length> 1) {
            error_text_repository.text = errorServerMassage
            error_text_repository.isVisible = true
        }
    }

    private fun initList() {
        repositoryAdapter = RepositoryListAdapter { position -> detailInfo(position) }

        Log.e("addflap", "initList")

        with(repository_list) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun detailInfo(position: Int) {
        val action = RepositoryListFragmentDirections.actionRepositoryListFragmentToDetailRepositoryInfo(position = position)
        findNavController().navigate(action)
    }

    private fun updateLoadingState(isLoading: Boolean) {
        Log.e("updateLoadingState", "$isLoading")
        progressBarRepository.isVisible = isLoading
        repository_list.isVisible = isLoading.not()
    }
}
