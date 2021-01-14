package com.vanced.faq.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.vanced.faq.*
import com.vanced.faq.adapter.VancedViewpagerAdapter
import com.vanced.faq.databinding.ActivityMainBinding
import com.vanced.faq.ui.core.currentListItem
import com.vanced.faq.ui.core.currentPagePosition

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetch()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)
            vancedViewpager.currentItem = 0
            isFetching.observe(this@MainActivity) { fetching ->
                if (!fetching) {
                    vancedLoading.isVisible = false
                    vancedViewpager.isVisible = true
                    vancedViewpager.adapter = VancedViewpagerAdapter(this@MainActivity, hashMapOf("Bugreport" to bugreport.value, "FAQ" to faq.value, "Troubleshooting" to troubleshooting.value))
                    vancedViewpager.isUserInputEnabled = false
                    currentPagePosition.observe(this@MainActivity) {
                        vancedViewpager.setCurrentItem(it, true)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        currentListItem.value = 0
        val currentPos = currentPagePosition.value
        if (currentPos != null && currentPos != 0) {
            currentPagePosition.value = currentPos - 1
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}