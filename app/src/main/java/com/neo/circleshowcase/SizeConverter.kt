package com.neo.circleshowcase

import android.content.Context

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
object SizeConverter {
    fun spToPx(ctx: Context, sp: Float): Float {
        return sp * ctx.resources.displayMetrics.scaledDensity
    }

    fun pxToDp(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    fun dpToPx(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}
