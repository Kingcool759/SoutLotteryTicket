package com.kc.soutlotteryticket

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 *  超级大乐透
 *
 *  设计规则：
 *  超级大乐透基本投注是指从前区号码中任选五个号码，
 *  并从后区号码中任选两个号码的组合进行投注。
 *  其中，前区号码由01—35共三十五个号码组成，
 *  后区号码由01—12共十二个号码组成。每注基本投注金额人民币2元。
 *
 *  形式：红 5 ，蓝 2。
 */

class BlankFragment : Fragment() {
    private val calculateTools by lazy {
        CalculateTools()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //描述
        titleWord.text = Html.fromHtml("<font color=\"#E9A214\">自</font> <font color=\"#108EE9\">主</font> <font color=\"#EC3E3E\">摇</font> <font color=\"#7AD80C\">号</font> ")
        //前区号码
        val numbersIntArrayFront = IntRange(1, 35).step(1).toList().toIntArray()
        hitFront.setOnClickListener {
            frontCode.text = calculateTools.shakeLotteryCode(numbersIntArrayFront,5,"frontShake").toString()
            calculateTools.calculateCount(false,frontCode,behindCode,frontCountNum,behindCountNum)
        }

        //后区号码
        val numbersIntArrayBehind = IntRange(1, 12).step(1).toList().toIntArray()
        hitBehind.setOnClickListener {
            behindCode.text = calculateTools.shakeLotteryCode(numbersIntArrayBehind,2,"behindShake").toString()
            calculateTools.calculateCount(false,frontCode,behindCode,frontCountNum,behindCountNum)
        }

        //全区摇号
        shakeCodeAll.setOnClickListener {
            frontCode.text = calculateTools.shakeLotteryCode(numbersIntArrayFront,5,"frontShake").toString()
            behindCode.text = calculateTools.shakeLotteryCode(numbersIntArrayBehind,2,"behindShake").toString()
            calculateTools.calculateCount(false,frontCode,behindCode,frontCountNum,behindCountNum)
        }

        //重置统计次数
        reCountbtn.setOnClickListener {
            calculateTools.calculateCount(true,frontCode,behindCode,frontCountNum,behindCountNum)
        }
    }
}