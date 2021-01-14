package com.vanced.faq.ui.core

import androidx.lifecycle.MutableLiveData
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

val currentPagePosition = MutableLiveData<Int>()
val currentItems = MutableLiveData<JsonArray<JsonObject>>()
val currentListItem = MutableLiveData<Int>()