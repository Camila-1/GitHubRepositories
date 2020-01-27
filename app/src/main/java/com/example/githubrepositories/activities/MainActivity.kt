package com.example.githubrepositories.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.R
import com.example.githubrepositories.fragments.GitHubLogoFragment
import com.example.githubrepositories.fragments.RepositoriesListFragment
import com.example.githubrepositories.fragments.RepositoryDetailsFragment
import com.example.githubrepositories.repositories.GitHubRepository
import com.example.githubrepositories.viewmodels.GitHubViewModel
import com.example.githubrepositories.viewmodels.GitHubViewModelFactory
import kotlinx.android.synthetic.main.edit_text.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val login by lazy { login_edit_text.text.toString() }

    private val viewModel by lazy {
        ViewModelProvider(this, GitHubViewModelFactory(GitHubRepository(this))).get(
            GitHubViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_edit_text.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && view.text.isNotEmpty()) {
                replaceFragments()
                true
            } else false
        }
    }

    override fun onResume() {
        super.onResume()
        if (login_edit_text.text.isEmpty()) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, GitHubLogoFragment()).commit()
        } else replaceFragments()
    }

    private fun replaceFragments() {
        GlobalScope.launch(Dispatchers.Main) {
            val transaction = supportFragmentManager.beginTransaction()
            val orientation: Int = resources.configuration.orientation
            when(orientation) {
                Configuration.ORIENTATION_PORTRAIT -> transaction.replace(R.id.fragment, RepositoriesListFragment.newInstance(viewModel.getRepositories(login), viewModel.getUser(login))).commit()
                Configuration.ORIENTATION_LANDSCAPE -> transaction.replace(R.id.fragment_list, RepositoriesListFragment.newInstance(viewModel.getRepositories(login), viewModel.getUser(login)))
                    .replace(R.id.details_fragment, RepositoryDetailsFragment()).commit()
            }
        }

    }
}

