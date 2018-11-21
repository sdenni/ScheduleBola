package com.example.s_denni.googledevkotlin_submission3_denni_2.networks

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class DataRepository {
//    fun machenRequest(url: String): String {
//        return URL(url).readText()
//    }
    fun machenRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}