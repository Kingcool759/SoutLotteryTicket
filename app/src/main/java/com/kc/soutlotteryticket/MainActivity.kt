package com.kc.soutlotteryticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var numbersInArray : IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //前区号码
        val numbersIntArrayFront = IntRange(1, 35).step(1).toList().toIntArray()
        hitFront.setOnClickListener {
            frontCode.text = shakeLotteryCode(numbersIntArrayFront,5).toString()
        }

        //后区号码
        val numbersIntArrayBehind = IntRange(1, 12).step(1).toList().toIntArray()
        hitBehind.setOnClickListener {
            behindCode.text = shakeLotteryCode(numbersIntArrayBehind,2).toString()
        }

        titleWord.text = Html.fromHtml("<font color=\"#E9A214\">自</font> <font color=\"#108EE9\">主</font> <font color=\"#EC3E3E\">摇</font> <font color=\"#7AD80C\">号</font> ")

    }

    //乐透摇号
    fun shakeLotteryCode(numbersIntArray: IntArray,count:Int):ArrayList<Int>{
        numbersInArray = numbersIntArray
        val choosedNumbers = ArrayList<Int>() // "选中数"列表
        for (index in 1..count){  //号码个数：前区5个、后区2个
            val currentNum = numbersInArray.random()
            choosedNumbers.add(currentNum)  //将选中的随机数加入"选中数"列表
            numbersInArray = numbersInArray.filter { it != currentNum }.toIntArray()  //从数组中移除已经选过的数，更新数组
        }
        choosedNumbers.sort()  //"选中数"重排序：从小到大
        return choosedNumbers
    }
}