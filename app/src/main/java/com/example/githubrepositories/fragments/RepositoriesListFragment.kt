package com.example.githubrepositories.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepositories.R
import com.example.githubrepositories.adapters.RepoAdapter
import com.example.githubrepositories.response.UserRepository
import kotlinx.android.synthetic.main.fragment_repositories_list.*

/**
 * A simple [Fragment] subclass.
 */
class RepositoriesListFragment : Fragment() {

    private var repositoriesList: List<UserRepository> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repositories_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = RepoAdapter(repositoriesList)
    }

    companion object{
        fun newInstance(response: List<UserRepository>): Fragment = RepositoriesListFragment().apply {
            repositoriesList = response
        }
    }
}
