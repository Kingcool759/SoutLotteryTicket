package com.kc.soutlotteryticket

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * 双色球
 *
 * 设计规则：
 * 双色球投注区分为红色球号码区和蓝色球号码区，
 * 红色球号码区由1-33共三十三个号码组成，
 * 蓝色球号码区由1-16共十六个号码组成。
 * 投注时选择6个红色球号码和1个蓝色球号码组成一注进行单式投注，每注金额人民币2元。
 *
 * 形式：红 6， 蓝 1
 */
class MainFragment : Fragment() {

    private val calculateTools by lazy {
        CalculateTools()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //描述
        titleWord.text = Html.fromHtml("<font color=\"#E9A214\">自</font> <font color=\"#108EE9\">主</font> <font color=\"#EC3E3E\">摇</font> <font color=\"#7AD80C\">号</font> ")

        //前区号码
        val numbersIntArrayFront = IntRange(1, 33).step(1).toList().toIntArray()
        hitFront.setOnClickListener {
            frontCode.text = calculateTools.shakeLotteryCode(numbersIntArrayFront,5,"frontShake").toString()
            calculateTools.calculateCount(false,frontCode,behindCode,frontCountNum,behindCountNum)
        }

        //后区号码
        val numbersIntArrayBehind = IntRange(1, 16).step(1).toList().toIntArray()
        hitBehind.setOnClickListener {
            behindCode.text = calculateTools.shakeLotteryCode(numbersIntArrayBehind,2,"behindShake").toString()
            calculateTools.calculateCount(false,frontCode,behindCode,frontCountNum,behindCountNum)
        }

        //全区摇号
        shakeCodeAll.setOnClickListener {
            frontCode.text = calculateTools.shakeLotteryCode(numbersIntArrayFront,6,"frontShake").toString()
            behindCode.text = calculateTools.shakeLotteryCode(numbersIntArrayBehind,1,"behindShake").toString()
            calculateTools.calculateCount(false,frontCode,behindCode,frontCountNum,behindCountNum)
        }

        //重置统计次数
        reCountbtn.setOnClickListener {
            calculateTools.calculateCount(true,frontCode,behindCode,frontCountNum,behindCountNum)
        }
    }
}