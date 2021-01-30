package com.skilbox.a02_toolbar

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

private val users = listOf(
    "User1",
    "User2",
    "User3",
    "Null"
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_toolbar.title = "My TOLLBAR "

        initToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initToolBar() {
        my_toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_contact -> {
                    showToast("Контакт")
                    Log.e("initToolBar", "Контакт")
                    true
                }
                R.id.menu_profil -> {
                    showToast("Профиль-")
                    Log.e("initToolBar", "Контакт")
                    true
                }
                R.id.menu_progect -> {
                    showToast("Проект")
                    Log.e("initToolBar", "Проект")
                    true
                }
                else -> false
            }
        }

        val searchValue = my_toolbar.menu.findItem(R.id.menu_search)
        searchValue.setOnActionExpandListener(
            object : MenuItem.OnActionExpandListener {

                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    Log.e("onMenuItemActionExpand", "Serch")
                    expandTextView.text = "Serch"
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    Log.e("onMenuItemActionColla", "Serch")
                    expandTextView.text = "Serch Expand"
                    return true
                }
            }
        )

        (searchValue.actionView as SearchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    users.filter { it.contains(newText ?: "", true) }.joinToString().let { searchResultTextView.text = it }
                    return true
                }
            }
        )
    }
}
