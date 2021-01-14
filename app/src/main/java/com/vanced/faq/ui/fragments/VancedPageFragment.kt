package com.vanced.faq.ui.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beust.klaxon.JsonObject
import com.vanced.faq.databinding.FragmentPageBinding
import com.vanced.faq.ui.core.currentItems
import com.vanced.faq.ui.core.currentListItem

class VancedPageFragment : Fragment() {

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        currentListItem.observe(viewLifecycleOwner) { currentItem ->
            currentItems.observe(viewLifecycleOwner) { items ->
                //for whatever the fuck reason currentItem
                //automatically changes to 22, in order to prevent
                //crashes i had to add a trycatch block
                val item = try {
                    items[currentItem]
                } catch (e: IndexOutOfBoundsException) {
                    items[0]
                }
                val fields = item.array<JsonObject>("fields")
                with (binding.pageLinear) {
                    removeAllViews()
                    addView(
                        TextView(context).apply {
                            setPadding(0, 8, 0, 0)
                            text = item.string("title")
                            setTextColor(Color.WHITE)
                            textSize = 24f
                        }
                    )
                    addView(
                        TextView(context).apply {
                            setPadding(0, 4, 0, 0)
                            setTextColor(Color.WHITE)
                            autoLinkMask = Linkify.WEB_URLS
                            text = item.string("description")
                        }
                    )
                    fields?.forEach {
                        addView(
                            TextView(context).apply {
                                setTextColor(Color.WHITE)
                                setPadding(0, 8, 0, 8)
                                text = it.string("title")
                                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                                textSize = 18f
                            }
                        )
                        addView(
                            TextView(context).apply {
                                setPadding(0, 8, 0, 8)
                                setTextColor(Color.WHITE)
                                autoLinkMask = Linkify.WEB_URLS
                                text = it.string("content")
                            }
                        )
                    }
                }
            }
        }
    }

}