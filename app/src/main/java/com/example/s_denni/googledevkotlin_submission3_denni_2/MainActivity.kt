package com.example.s_denni.googledevkotlin_submission3_denni_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.s_denni.googledevkotlin_submission3_denni_2.R.id.*
import com.example.s_denni.googledevkotlin_submission3_denni_2.R.layout.activity_beranda
import com.example.s_denni.googledevkotlin_submission3_denni_2.fragments.KaresepFragments
import com.example.s_denni.googledevkotlin_submission3_denni_2.fragments.LagaEnjingFragment
import com.example.s_denni.googledevkotlin_submission3_denni_2.fragments.LagaKamariFragment
import kotlinx.android.synthetic.main.activity_beranda.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_beranda)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                kamari -> {
                    loadKamariFragment(savedInstanceState)
                }
                enjing -> {
                    loadEnjingFragment(savedInstanceState)
                }
                kareseps -> {
                    loadKaresepFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = kamari
    }


    private fun loadKamariFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LagaKamariFragment(), LagaKamariFragment::class.simpleName)
                .commit()
        }
    }

    private fun loadEnjingFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LagaEnjingFragment(), LagaEnjingFragment::class.simpleName)
                .commit()
        }
    }

    private fun loadKaresepFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, KaresepFragments(), KaresepFragments::class.simpleName)
                .commit()
        }
    }
}
