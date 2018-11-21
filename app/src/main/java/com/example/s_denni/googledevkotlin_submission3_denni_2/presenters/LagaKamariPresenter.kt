package com.example.s_denni.googledevkotlin_submission3_denni_2.presenters

import com.example.s_denni.googledevkotlin_submission3_denni_2.interfaces.MyLagaView
import com.example.s_denni.googledevkotlin_submission3_denni_2.models.ListOfMyLaga
import com.example.s_denni.googledevkotlin_submission3_denni_2.networks.DataRepository
import com.example.s_denni.googledevkotlin_submission3_denni_2.networks.TheSportDBApi
import com.example.s_denni.googledevkotlin_submission3_denni_2.tools.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LagaKamariPresenter (private val view: MyLagaView,
                           private val apiRepository: DataRepository,
                           private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

        fun nimmLagaKamari() {
            view.showLoading()

            GlobalScope.launch (context.main){
                val data = gson.fromJson(apiRepository
                    .machenRequest(TheSportDBApi.nimmLastMatch()).await(),
                    ListOfMyLaga::class.java)

                view.showEventList(data.events)
                view.hideLoading()
            }
        }

}