package com.example.s_denni.googledevkotlin_submission3_denni_2.interfaces

import com.example.s_denni.googledevkotlin_submission3_denni_2.models.KlubSepakBolaResponse
import com.example.s_denni.googledevkotlin_submission3_denni_2.models.ListOfMyLaga

interface LagaDetailView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(
        myLagaInfo: ListOfMyLaga,
        kencaKlubData: KlubSepakBolaResponse,
        katuhuKlubData: KlubSepakBolaResponse
    )
}