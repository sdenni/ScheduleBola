package com.example.s_denni.googledevkotlin_submission3_denni_2.interfaces

import com.example.s_denni.googledevkotlin_submission3_denni_2.models.MyLaga

interface MyLagaView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(myLaga: List<MyLaga>)
}