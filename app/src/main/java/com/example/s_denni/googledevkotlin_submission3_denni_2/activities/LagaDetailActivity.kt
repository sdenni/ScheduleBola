package com.example.s_denni.googledevkotlin_submission3_denni_2.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.s_denni.googledevkotlin_submission3_denni_2.interfaces.LagaDetailView
import com.example.s_denni.googledevkotlin_submission3_denni_2.models.KlubSepakBolaResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.example.s_denni.googledevkotlin_submission3_denni_2.R
import com.example.s_denni.googledevkotlin_submission3_denni_2.models.ListOfMyLaga
import com.example.s_denni.googledevkotlin_submission3_denni_2.networks.DataRepository
import com.example.s_denni.googledevkotlin_submission3_denni_2.presenters.DetailKlubPresenter
import com.example.s_denni.googledevkotlin_submission3_denni_2.tools.invisible
import com.example.s_denni.googledevkotlin_submission3_denni_2.tools.visible
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

class LagaDetailActivity : AppCompatActivity(), LagaDetailView {

    private lateinit var detailPresenter: DetailKlubPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var tv_tanggalmaen: TextView

    private lateinit var iv_klub_c: ImageView
    private lateinit var iv_klub_k: ImageView
    private lateinit var tv_kenca: TextView
    private lateinit var tv_katuhu: TextView

    private lateinit var tvGoalDetailsC: TextView
    private lateinit var tvGoalDetailsK: TextView
    private lateinit var tvShotsC: TextView
    private lateinit var tvShotsK: TextView
    private lateinit var tvKeeperC: TextView
    private lateinit var tvKeeperK: TextView
    private lateinit var tvDefenseC: TextView
    private lateinit var tvDefenseK: TextView
    private lateinit var tvMidfieldC: TextView
    private lateinit var tvMidfieldK: TextView
    private lateinit var tvForwardC: TextView
    private lateinit var tvForwardK: TextView
    private lateinit var tvSubstituteC: TextView
    private lateinit var tvSubstituteK: TextView

    private lateinit var idSchedule: String

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        Log.d("TRACE", "onCreate DetActivity TEST")
        Log.d("TRACE", "onCreate DetActivity BEFORE "+idSchedule)

        val intent = intent
        idSchedule = intent.getStringExtra("id_schedule")
        supportActionBar?.title = "Laga Saja"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Log.d("TRACE", "onCreate DetActivity AFTER "+idSchedule)

        verticalLayout(){

            textView {
                text = "Tanggal"
                id = R.id.tanggalMaen
                textSize = 20f
                textAlignment = left
            }.lparams{
                margin = dip(3)
            }

            swipeRefresh = swipeRefreshLayout {
                //                setColorSchemeResources(
//                    colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light
//                )

                scrollView {
                    isVerticalScrollBarEnabled = false

                    verticalLayout () {
                        lparams(width = matchParent, height = wrapContent)

                        //LAYOUT
                        linearLayout(){
                            verticalLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                imageView{
                                    id = R.id.image_klub_c
                                }.lparams(width= dip(75), height = dip(75))

                                textView {
                                    text = "Kenca Club"
                                    id = R.id.kenca_club
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "VS"
                                    textSize = 20f
                                }

                            }
                            verticalLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                imageView{
                                    id = R.id.image_klub_k
                                }.lparams(width= dip(75), height = dip(75))

                                textView {
                                    text = "Katuhu Klub"
                                    id = R.id.katuhu_club
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        /// #1--- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goals_details_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Goals"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goals_details_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.shots_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Shots"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.shots_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        textView {
                            text = "Informasi Tambahan"
                            textSize = 16f
                        }.lparams{
                            margin = dip(3)
                        }

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goal_keeper_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Goal Keeper"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.goal_keeper_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.defense_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Defense"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.defense_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.midfield_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Midfield"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.midfield_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.forward_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Forward"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.forward_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }

                        // --- //

                        linearLayout(){
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.substitute_c
                                    textSize = 16f
                                    textAlignment = left
                                }.lparams{
                                    margin = dip(3)
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.2F)
                                padding = dip(1)

                                textView(){
                                    text = "Substitute"
                                    textSize = 20f
                                }

                            }
                            linearLayout(){
                                lparams(width = 0, height = wrapContent, weight = 0.4F)
                                padding = dip(1)

                                textView {
                                    text = ""
                                    id = R.id.substitute_k
                                    textSize = 16f
                                    textAlignment = right
                                }.lparams{
                                    margin = dip(5)
                                }

                            }
                        }
                    }
                }

            }



            // --- //
        }

        favoriteState()
        val request = DataRepository()
        val gson = Gson()
        detailPresenter = DetailKlubPresenter(this, request, gson)
        detailPresenter.nimmKlubDetail(idSchedule)

        swipeRefresh.onRefresh {
            detailPresenter.nimmKlubDetail(idSchedule)
        }

    }

    private fun favoriteState(){
//        database.use {
//            val result = select(Favorite.TABLE_FAVORITE)
//                .whereArgs("(TEAM_ID = {id})",
//                    "id" to id)
//            val favorite = result.parseList(classParser<Favorite>())
//            if (!favorite.isEmpty()) isFavorite = true
//        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(
        myLagaInfo: ListOfMyLaga,
        kencaKlubData: KlubSepakBolaResponse,
        katuhuKlubData: KlubSepakBolaResponse
    ) {
        Log.d("TRACE", "SOMETHING "+myLagaInfo.events[0].lagaHasilKenca)

        tvGoalDetailsC.text = myLagaInfo.events[0].goalDetailC
        tvGoalDetailsK.text = myLagaInfo.events[0].goalDetailK
        tvShotsC.text = myLagaInfo.events[0].lagaHasilKenca
        tvShotsK.text = myLagaInfo.events[0].lagaHasilKatuhu
        tvKeeperC.text = myLagaInfo.events[0].goalKeeperC
        tvKeeperK.text = myLagaInfo.events[0].goalKeeperK
        tvDefenseC.text = myLagaInfo.events[0].defenseC
        tvDefenseK.text = myLagaInfo.events[0].defenseK
        tvMidfieldC.text = myLagaInfo.events[0].midFieldC
        tvMidfieldK.text = myLagaInfo.events[0].midFieldK
        tvForwardC.text = myLagaInfo.events[0].forwardC
        tvForwardK.text = myLagaInfo.events[0].forwardK
        tvSubstituteC.text = myLagaInfo.events[0].substituteC
        tvSubstituteK.text = myLagaInfo.events[0].substituteK
    }
}