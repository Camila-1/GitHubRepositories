package com.example.githubrepositories.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.R
import com.example.githubrepositories.fragments.GitHubLogoFragment
import com.example.githubrepositories.fragments.RepositoriesListFragment
import com.example.githubrepositories.repositories.GitHubRepository
import com.example.githubrepositories.viewmodels.GitHubViewModel
import com.example.githubrepositories.viewmodels.GitHubViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, GitHubViewModelFactory(GitHubRepository(this))).get(GitHubViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (login_edit_text.text.isEmpty()) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, GitHubLogoFragment()).commit()
        }
        
        login_edit_text.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && view.text.isNotEmpty()) {
                loadRepositories()
                view.focusable = View.NOT_FOCUSABLE
                true
            } else false
        }
    }

    fun loadRepositories() {
        val transaction = supportFragmentManager.beginTransaction()
        GlobalScope.launch(Dispatchers.Main) {
            val test = viewModel.getRepositories(login_edit_text.text.toString())
            transaction.replace(R.id.fragment, RepositoriesListFragment.newInstance(test!!)).commit()
        }
    }
}

