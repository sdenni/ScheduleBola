package com.example.s_denni.googledevkotlin_submission3_denni_2.networks

import android.net.Uri
import com.example.s_denni.googledevkotlin_submission3_denni_2.BuildConfig

class TheSportDBApi {

//    DIRAPIHKAN
    companion object {

        fun getTeams(league: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("search_all_teams.php")
                    .appendQueryParameter("l", league)
                    .build()
                    .toString()
        }

        fun nimmTeams(league: String = "German Bundesliga"): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("search_all_teams.php")
                    .appendQueryParameter("l", league)
                    .build()
                    .toString()
        }

        fun nimmMatch(id_schedule: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", id_schedule)
                .build()
                .toString()
        }

        fun nimmNextMatch(): String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("eventsnextleague.php")
                    .appendQueryParameter("id", "4328")
                    .build()
                    .toString()
        }

        fun nimmLastMatch(): String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("eventspastleague.php")
                    .appendQueryParameter("id", "4328")
                    .build()
                    .toString()
        }

        fun nimmKlubInfo(data: String?): String {
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("api")
                    .appendPath("v1")
                    .appendPath("json")
                    .appendPath(BuildConfig.TSDB_API_KEY)
                    .appendPath("lookupteam.php")
                    .appendQueryParameter("id", data)
                    .build()
                    .toString()
        }
    }
}