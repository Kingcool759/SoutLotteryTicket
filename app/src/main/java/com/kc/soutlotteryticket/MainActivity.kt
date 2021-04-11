package com.kc.soutlotteryticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var numbersInArray : IntArray
    private var frontCount = 0  //统计前区摇号次数
    private var behindCount = 0  //统计后区摇号次数

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleWord.text = Html.fromHtml("<font color=\"#E9A214\">自</font> <font color=\"#108EE9\">主</font> <font color=\"#EC3E3E\">摇</font> <font color=\"#7AD80C\">号</font> ")

        //前区号码
        val numbersIntArrayFront = IntRange(1, 35).step(1).toList().toIntArray()
        hitFront.setOnClickListener {
            frontCode.text = shakeLotteryCode(numbersIntArrayFront,5,"frontShake").toString()
            calculateCount(false)
        }

        //后区号码
        val numbersIntArrayBehind = IntRange(1, 12).step(1).toList().toIntArray()
        hitBehind.setOnClickListener {
            behindCode.text = shakeLotteryCode(numbersIntArrayBehind,2,"behindShake").toString()
            calculateCount(false)
        }

        //全区摇号
        shakeCodeAll.setOnClickListener {
            frontCode.text = shakeLotteryCode(numbersIntArrayFront,5,"frontShake").toString()
            behindCode.text = shakeLotteryCode(numbersIntArrayBehind,2,"behindShake").toString()
            calculateCount(false)
        }

        //重置统计次数
        reCountbtn.setOnClickListener {
            calculateCount(true)
        }

    }

    //乐透摇号
    fun shakeLotteryCode(numbersIntArray: IntArray,count:Int,type:String):ArrayList<Int>{
        numbersInArray = numbersIntArray
        val choosedNumbers = ArrayList<Int>() // "选中数"列表
        for (index in 1..count){  //号码个数：前区5个、后区2个
            val currentNum = numbersInArray.random()
            choosedNumbers.add(currentNum)  //将选中的随机数加入"选中数"列表
            numbersInArray = numbersInArray.filter { it != currentNum }.toIntArray()  //从数组中移除已经选过的数，更新数组
        }
        choosedNumbers.sort()  //"选中数"重排序：从小到大
        //做统计次数处理
        if (type=="frontShake"){
            frontCount++
        }else{
            behindCount++
        }
        return choosedNumbers
    }

    //统计前后区摇号次数
    fun calculateCount(reCount:Boolean){  //参数传是否重置
        if (reCount){
            frontCount = 0
            behindCount = 0
            frontCode.text = "数据已重置"
            behindCode.text = "数据已重置"
        }
        frontCountNum.text = frontCount.toString()
        behindCountNum.text = behindCount.toString()
    }
}