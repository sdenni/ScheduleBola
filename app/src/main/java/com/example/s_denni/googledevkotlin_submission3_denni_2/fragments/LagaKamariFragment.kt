package com.example.s_denni.googledevkotlin_submission3_denni_2.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.s_denni.googledevkotlin_submission3_denni_2.R
import com.example.s_denni.googledevkotlin_submission3_denni_2.activities.LagaDetailActivity
import com.example.s_denni.googledevkotlin_submission3_denni_2.adapters.LagaKamariAdapter
import com.example.s_denni.googledevkotlin_submission3_denni_2.interfaces.MyLagaView
import com.example.s_denni.googledevkotlin_submission3_denni_2.models.MyLaga
import com.example.s_denni.googledevkotlin_submission3_denni_2.networks.DataRepository
import com.example.s_denni.googledevkotlin_submission3_denni_2.presenters.LagaKamariPresenter
import com.example.s_denni.googledevkotlin_submission3_denni_2.tools.invisible
import com.example.s_denni.googledevkotlin_submission3_denni_2.tools.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LagaKamariFragment : Fragment(), AnkoComponent<Context>, MyLagaView {

    private var lagaKamaris: MutableList<MyLaga> = mutableListOf()
    private lateinit var presenter: LagaKamariPresenter
    private lateinit var adapterLastMatch: LagaKamariAdapter
    private lateinit var reusableView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val leagueName: String = "German Bundesliga"
    private lateinit var textView: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterLastMatch = LagaKamariAdapter(lagaKamaris) {
            context?.startActivity<LagaDetailActivity>("id_schedule" to "${it.scheduleId}")
        }

        reusableView.adapter = adapterLastMatch
        val request = DataRepository()
        val gson = Gson()
        presenter = LagaKamariPresenter(this, request, gson)
        presenter.nimmLagaKamari()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = android.widget.LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            textView = textView()

            swipeRefresh = swipeRefreshLayout() {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    reusableView = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = android.support.v7.widget.LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(dataKamari: List<MyLaga>) {
        Log.d("TRACE", "showEventListKamariFragment laga "+lagaKamaris.size)
        Log.d("TRACE", "showEventListKamariFragment data "+dataKamari.size)
        swipeRefresh.isRefreshing = false
        lagaKamaris.clear()
        lagaKamaris.addAll(dataKamari)
        adapterLastMatch.notifyDataSetChanged()
    }
}