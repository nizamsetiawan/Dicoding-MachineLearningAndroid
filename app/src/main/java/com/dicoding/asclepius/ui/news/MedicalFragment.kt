package com.dicoding.asclepius.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.databinding.FragmentMedicalBinding
import com.dicoding.asclepius.core.data.source.model.MedicalNewsModel
import com.dicoding.asclepius.ui.adapter.MedicalNewsAdapter

class MedicalFragment : Fragment() {

    private lateinit var viewModel: MedicalViewModel
    private lateinit var adapter: MedicalNewsAdapter

    private var _binding: FragmentMedicalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MedicalViewModel::class.java)
        adapter = MedicalNewsAdapter()
        binding.rvMedicalNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMedicalNews.adapter = adapter

        viewModel.medicalNews.observe(viewLifecycleOwner, Observer {
            displayNews(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })

        viewModel.fetchMedicalNews()
    }

    private fun displayNews(newsList: List<MedicalNewsModel>) {
        adapter.submitList(newsList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
