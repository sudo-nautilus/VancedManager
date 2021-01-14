package com.vanced.faq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.vanced.faq.databinding.ViewMenuitemBinding
import com.vanced.faq.ui.core.currentItems
import com.vanced.faq.ui.core.currentPagePosition

class VancedCategoryListAdapter(
    private val context: Context,
    private val categories: HashMap<String, JsonArray<JsonObject>?>
) : RecyclerView.Adapter<VancedCategoryListAdapter.VancedCategoryListViewHolder>() {

    class VancedCategoryListViewHolder(binding: ViewMenuitemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.menuitemText
        val view = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VancedCategoryListViewHolder {
        val binding = ViewMenuitemBinding.inflate(LayoutInflater.from(context), parent, false)
        return VancedCategoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VancedCategoryListViewHolder, position: Int) {
        holder.title.text = categories.map { it.key }[position]
        holder.view.setOnClickListener {
            val arrays = categories.map { it.value }[position]
            if (arrays != null) {
                currentItems.value = arrays
                currentPagePosition.value = 1
            }
        }
    }

    override fun getItemCount(): Int = categories.size

}