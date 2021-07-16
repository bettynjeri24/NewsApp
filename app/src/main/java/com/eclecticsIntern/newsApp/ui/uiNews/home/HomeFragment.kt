package com.eclecticsIntern.newsApp.ui.uiNews.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclecticsIntern.newsApp.R
import com.eclecticsIntern.newsApp.databinding.FragmentHomeBinding
import com.eclecticsIntern.newsApp.ui.adapter.NewsRecyclerViewAdapter
import com.eclecticsIntern.newsApp.utils.ApiException
import com.eclecticsIntern.newsApp.utils.Coroutines
import com.eclecticsIntern.newsApp.utils.NetWorkException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.progressBar.isVisible = true
        Coroutines.main {
            try {
                viewModel.article.await().observe(viewLifecycleOwner, {
                    binding.progressBar.isVisible = false
                    newsRecyclerViewAdapter = NewsRecyclerViewAdapter(it)
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = newsRecyclerViewAdapter
                        setHasFixedSize(true)
                    }


                    Log.e("Data", it.size.toString())
                    Log.e("Data", it.toString())
                    //binding.textHome.text = it.toString()
                })
            } catch (e: NetWorkException) {
                Log.e("NetworkProblem", e.message.toString())
                binding.textHome.text = e.message.toString()
            } catch (e: ApiException) {
                Log.e("ApiException", e.message.toString())
                binding.textHome.text = e.message.toString()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}