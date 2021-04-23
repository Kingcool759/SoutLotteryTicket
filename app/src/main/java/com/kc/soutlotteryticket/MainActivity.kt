package com.kc.soutlotteryticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragments = ArrayList<Fragment>()
    val titles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTabLayout()
    }

    //设置TabLayout和ViewPager
    fun setTabLayout(){
        setTitleData()
        titles.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
        fragments.add(MainFragment())
        fragments.add(BlankFragment())
        viewPager.adapter = PublicTabAdapter(supportFragmentManager,fragments,titles)
        tabLayout.setupWithViewPager(viewPager)
    }

    //设置tabTitles数据
    fun setTitleData(){
        titles.add("双色球")
        titles.add("超级大乐透")
    }
}