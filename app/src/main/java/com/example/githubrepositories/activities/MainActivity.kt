package com.example.githubrepositories.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.R
import com.example.githubrepositories.fragments.RepositoriesListFragment
import com.example.githubrepositories.repositories.GitHubRepository
import com.example.githubrepositories.services.githubrepositoriesservice.GitHubAPIRequest
import com.example.githubrepositories.services.githubrepositoriesservice.GitHubServiceBuilder
import com.example.githubrepositories.viewmodels.GitHubViewModel
import com.example.githubrepositories.viewmodels.GitHubViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, GitHubViewModelFactory(GitHubRepository(this))).get(GitHubViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        GlobalScope.launch(Dispatchers.Main) {
            val test = viewModel.getRepositories()
            transaction.replace(R.id.fragment, RepositoriesListFragment.newInstance(test!!)).commit()
        }
    }
}

