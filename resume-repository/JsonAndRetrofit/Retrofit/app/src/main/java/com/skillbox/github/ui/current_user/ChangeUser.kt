package com.skillbox.github.ui.current_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.skillbox.github.R
import com.skillbox.github.data.User
import com.skillbox.github.data.UserForChange
import kotlinx.android.synthetic.main.current_user_fragment.*
import kotlinx.android.synthetic.main.fragment_change_user.*

class ChangeUser : Fragment(R.layout.fragment_change_user) {

    private val viewModel: UserViewModel by navGraphViewModels(R.id.user) {
        defaultViewModelProviderFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bindViewModel()

        cancellation.setOnClickListener {
            findNavController().popBackStack()
        }

        confirm.setOnClickListener {
            updateUser()
            findNavController().popBackStack()
        }
    }

    private fun bindViewModel() {

        viewModel.userViewModel.observe(viewLifecycleOwner, ::loadUserInfo)
    }

    private fun updateUser() {
        val newUser = UserForChange(
            name = if (change_name.text.isEmpty()) { "${viewModel.userViewModel.value!!.name}" } else { change_name.text.toString() },
            email = if (change_user_email.text.isEmpty()) { "" } else { change_user_email.text.toString() },
            location_user = if (change_user_location.text.isEmpty()) { "${viewModel.userViewModel.value!!.location_user}" } else { change_user_location.text.toString() },
            blog = if (change_user_blog.text.isEmpty()) {
                viewModel.userViewModel.value!!.blog.orEmpty()
            } else { change_user_blog.text.toString() },
            twitter_username = if (change_user_twitter_username.text.isEmpty()) { "${viewModel.userViewModel.value!!.twitter_username}" } else { change_user_twitter_username.text.toString() },
            company = if (change_user_company.text.isEmpty()) { "${viewModel.userViewModel.value!!.company}" } else { change_user_company.text.toString() },
            hireable = change_user_hireable.isChecked
        )

        viewModel.changeUser(newUser)
    }

    fun loadUserInfo(user: User) {
        change_name.hint = user.name
        change_user_email.hint = user.email
        change_user_location.hint = user.location_user
        change_user_blog.hint = user.blog
        change_user_twitter_username.hint = user.twitter_username
        change_user_company.hint = user.company
        change_user_hireable.isChecked = user.hireable==true
    }
}
