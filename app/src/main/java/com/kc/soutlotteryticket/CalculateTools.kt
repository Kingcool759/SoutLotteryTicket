package com.kc.soutlotteryticket

import android.widget.TextView

/**
 * @data on 4/23/21 9:46 PM
 * @auther KC
 * @describe 计算工具类
 */
class CalculateTools {
    lateinit var numbersInArray : IntArray
    private var frontCount = 0  //统计前区摇号次数
    private var behindCount = 0  //统计后区摇号次数
    /**
     *    摇号
     * param1: numbersIntArray 数组区间数
     * param2: count 号码个数：前区5个、后区2个
     * param3: type 前后区区分
     * return: ArrayList "选中数"列表
     */
    fun shakeLotteryCode(numbersIntArray: IntArray,
                         count:Int,
                         type:String):ArrayList<Int>{
        numbersInArray = numbersIntArray
        val choosedNumbers = ArrayList<Int>()
        for (index in 1..count){
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

    /**
     *     统计前后区摇号次数
     *  param1 : reCount  是否重置
     *  param2 : tv1  前区数据
     *  param3 : tv2  后区数据
     *  param4 : tv3  前区统计次数
     *  param5 : tv4  后区统计次数
     */

    fun calculateCount(reCount:Boolean,
                       tv1:TextView,
                       tv2:TextView,
                       tv3:TextView,
                       tv4:TextView
    ) {
        if (reCount){
            frontCount = 0
            behindCount = 0
            tv1.text = "数据已重置"
            tv2.text = "数据已重置"
        }
        tv3.text = frontCount.toString()
        tv4.text = behindCount.toString()
    }
}