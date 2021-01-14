package com.vanced.faq.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vanced.faq.databinding.ViewMenuitemIndexesBinding
import com.vanced.faq.ui.core.currentListItem
import com.vanced.faq.ui.core.currentPagePosition

class VancedPagesListAdapter(
    private val context: Context,
    private val pages: List<String?>
) : RecyclerView.Adapter<VancedPagesListAdapter.VancedCategoryListViewHolder>() {

    class VancedCategoryListViewHolder(binding: ViewMenuitemIndexesBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.menuitemText
        val index = binding.menuitemPagenum
        val view = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VancedCategoryListViewHolder {
        val binding = ViewMenuitemIndexesBinding.inflate(LayoutInflater.from(context), parent, false)
        return VancedCategoryListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VancedCategoryListViewHolder, position: Int) {
        holder.title.text = pages[position]
        holder.index.text = (position + 1).toString() + "."
        holder.view.setOnClickListener {
            currentListItem.value = position
            currentPagePosition.value = 2
        }
    }

    override fun getItemCount(): Int = pages.size

}