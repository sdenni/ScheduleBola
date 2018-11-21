package com.example.s_denni.googledevkotlin_submission3_denni_2.tools

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}