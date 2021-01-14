package com.vanced.faq.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanced.faq.adapter.VancedPagesListAdapter
import com.vanced.faq.databinding.FragmentPagesListBinding
import com.vanced.faq.ui.core.currentItems

class VancedPagesFragment : Fragment() {

    private lateinit var binding: FragmentPagesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        currentItems.observe(viewLifecycleOwner) { array ->
            binding.categoryRecylcer.apply {
                adapter = VancedPagesListAdapter(requireActivity(), array.map { it.string("title") })
                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            }
        }

    }

}