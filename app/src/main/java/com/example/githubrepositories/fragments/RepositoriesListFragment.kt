package com.example.githubrepositories.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubrepositories.R
import com.example.githubrepositories.activities.MainActivity
import com.example.githubrepositories.adapters.RepoAdapter
import com.example.githubrepositories.repositories.GitHubRepository
import com.example.githubrepositories.response.GithubRepositoryOwner
import com.example.githubrepositories.response.UserRepository
import com.example.githubrepositories.viewmodels.GitHubViewModel
import com.example.githubrepositories.viewmodels.GitHubViewModelFactory
import kotlinx.android.synthetic.main.fragment_repositories_list.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class RepositoriesListFragment : Fragment() {

    private var user: GithubRepositoryOwner? = null
    private var repositoriesList: List<UserRepository>? = emptyList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        user = arguments?.getParcelable("user")
        repositoriesList = arguments?.getParcelableArrayList("list")
        return inflater.inflate(R.layout.fragment_repositories_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            Glide.with(this@RepositoriesListFragment)
                .load(user?.avatarUrl)
                .into(avatar_img)

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = if (repositoriesList == null) RepoAdapter(emptyList())
        else RepoAdapter(repositoriesList!!)
        }
    }

    companion object{
        fun newInstance(repositories: List<UserRepository>, user: GithubRepositoryOwner?): Fragment = RepositoriesListFragment().apply {
            arguments = bundleOf("list" to repositories, "user" to user)
        }
    }
}
