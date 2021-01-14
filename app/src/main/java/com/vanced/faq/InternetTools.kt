package com.vanced.faq

import androidx.lifecycle.MutableLiveData
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

val isFetching = MutableLiveData<Boolean>()
val faq = MutableLiveData<JsonArray<JsonObject>?>()
val troubleshooting = MutableLiveData<JsonArray<JsonObject>?>()
val bugreport = MutableLiveData<JsonArray<JsonObject>?>()

fun String.getJson(): JsonObject? {
    return try{
        Parser.default().parse(
            StringBuilder(URL(this).readText().trimIndent())
        ) as JsonObject?
    } catch (e: Exception) {
        null
    }
}

fun fetch() = CoroutineScope(Dispatchers.IO).launch {
    isFetching.postValue(true)
    val baseUrl = "https://vancedapp.com/webapi/strings/en/"
    bugreport.postValue("$baseUrl/bugreport.json".getJson()?.array("bugreport"))
    faq.postValue("$baseUrl/faq.json".getJson()?.array("faq"))
    troubleshooting.postValue("$baseUrl/troubleshooting.json".getJson()?.array("troubleshooting"))
    isFetching.postValue(false)
}