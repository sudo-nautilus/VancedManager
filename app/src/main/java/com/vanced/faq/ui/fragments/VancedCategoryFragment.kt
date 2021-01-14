package com.vanced.faq.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.vanced.faq.adapter.VancedCategoryListAdapter
import com.vanced.faq.databinding.FragmentCategoryListBinding

class VancedCategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryListBinding

    companion object {

        private const val CATEGORIES = "categories"

        fun createInstance(categories: HashMap<String, JsonArray<JsonObject>?>): VancedCategoryFragment {
            return VancedCategoryFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CATEGORIES, categories)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.categoryRecylcer.apply {
            adapter = arguments?.getSerializable(CATEGORIES)?.let { VancedCategoryListAdapter(requireActivity(), it as HashMap<String, JsonArray<JsonObject>?>) }
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

}