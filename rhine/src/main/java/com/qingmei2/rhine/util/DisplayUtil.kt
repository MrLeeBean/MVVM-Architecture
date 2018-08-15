package com.qingmei2.rhine.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast

import com.qingmei2.rhine.base.BaseApplication

import java.lang.reflect.Field

object DisplayUtil {

    private val screenHeight: Int
        get() = BaseApplication.instance!!.resources.displayMetrics.heightPixels

    private val screenWidth: Int
        get() = BaseApplication.instance!!.resources.displayMetrics.widthPixels

    fun layoutInflater(context: Context): LayoutInflater {
        return context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    @SuppressLint("PrivateApi")
    fun getStatusbarHeight(context: Context): Int {
        var statusBarHeight = 0
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val o = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = field.get(o) as Int
            statusBarHeight = context.resources.getDimensionPixelSize(x)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusBarHeight
    }

    fun getScreenHeightExcludeStatusbar(context: Context): Int {
        return screenHeight - getStatusbarHeight(context)
    }

    fun px2dp(context: Context, pxValue: Float): Int {
        val density = context.resources.displayMetrics.density
        return (pxValue / density + 0.5f).toInt()
    }

    fun dp2px(context: Context, dpValue: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dpValue * density + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        val scaleDensity = context.resources.displayMetrics.scaledDensity
        return (pxValue / scaleDensity + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val scaleDensity = context.resources.displayMetrics.scaledDensity
        return (spValue * scaleDensity + 0.5f).toInt()
    }
}