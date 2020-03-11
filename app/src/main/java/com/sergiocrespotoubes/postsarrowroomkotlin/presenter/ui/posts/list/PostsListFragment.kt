package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts.list

/**
 * Created by Sergio Crespo Toubes on 14/01/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import arrow.fx.extensions.io.applicativeError.handleError
import arrow.fx.extensions.io.async.effectMap
import com.sergiocrespotoubes.postsarrowroomkotlin.R
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.use_case.FindPosts
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.Resource
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_posts_list.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

class PostsListViewModel(
	private val findPosts: FindPosts
) : ViewModel() {

    val postsResourceObs = MutableLiveData<Resource<List<PostDb>, Throwable>>()

    fun onResume() {
        loadPosts()
    }

    private fun loadPosts() {
		postsResourceObs.value = Resource.loading()
        findPosts.invoke().continueOn(Dispatchers.Main).effectMap { posts ->
				//postsResourceObs.value
            }.handleError {
				postsResourceObs.value = Resource.error(it)
            }.unsafeRunAsync {}
    }

    fun onPostSelect(post: PostDb) {

    }
}

class PostsListFragment : Fragment() {

    private val vModel: PostsListViewModel by inject()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadViews()
        loadObservers()
    }

    private fun loadViews() {
        rv_posts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
    }

    private fun loadObservers() {
        vModel.postsResourceObs.observe(requireActivity(), Observer { resource ->
            resource?.let {
                loadSurgerySpecialtyResource(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        vModel.onResume()
    }

    private fun loadSurgerySpecialtyResource(servicesGroupSpecialtyResource: Resource<List<PostDb>, Throwable>) {
        when (servicesGroupSpecialtyResource) {
            is Resource.Success -> {
                loadPosts(servicesGroupSpecialtyResource.value)
            }
            is Resource.Error -> {
                vss_posts.error()
            }
            is Resource.Loading -> {
				vss_posts.loading()
            }
        }
    }

    private fun loadPosts(posts: List<PostDb>) {
        groupAdapter.clear()
        val specialtiesItems = posts.map { post ->
            PostItem(post) {
                vModel.onPostSelect(post)
            }
        }
        groupAdapter.addAll(specialtiesItems)

        if (specialtiesItems.isEmpty()) {
			vss_posts.empty()
        } else {
			vss_posts.success()
        }
    }

    /*private fun laodPost(postModel: PostModel) {

    }*/
}
