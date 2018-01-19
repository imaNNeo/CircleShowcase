package com.neo.circleshowcase

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
class CircleShowcaseView @JvmOverloads constructor(mContext : Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(mContext,attrs, defStyleAttr){

    private val overlayColor = Color.BLACK
    private val overlayAlpha = 210 //between 0 - 255
    private var bmp : Bitmap? = null
    private var overlayPaint = Paint()
    private var tmpCanvas : Canvas? = null
    private var tmpRect = Rect()
    private var tmpRectF = RectF()

    private var clearPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = dp2px(100f)

    private var strokePaint= Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        overlayPaint.apply {
            color = overlayColor
            alpha = overlayAlpha
        }

        clearPaint.apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
            color = Color.TRANSPARENT
        }

        strokePaint.apply {
            strokeWidth = dp2px(3f)
            color = Color.WHITE
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(bmp==null) {
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            tmpCanvas = Canvas(bmp)
        }else {
            bmp?.eraseColor(Color.TRANSPARENT)
        }

        //Draw background transparent
        tmpRect.set(0,0,width,height)
        tmpCanvas?.drawRect(tmpRect,overlayPaint)


        //Clear Circle in center
        val centerX = (width/2).toFloat()
        val centerY = (height/2).toFloat()
        tmpCanvas?.drawCircle(centerX,centerY,radius,clearPaint)

        //Draw Stroke
        tmpRectF.set(centerX - radius - dp2px(4f),
                centerY - radius - dp2px(4f),
                centerX + radius + dp2px(4f),
                centerY + radius + dp2px(4f))
        tmpCanvas?.drawArc(tmpRectF,180f,120f,false,strokePaint)




        canvas?.drawBitmap(bmp,0f,0f,null)
    }


    private fun dp2px(dp: Float): Float {
        return SizeConverter.dpToPx(context, dp)
    }

    private fun sp2px(sp: Float): Float {
        return SizeConverter.spToPx(context, sp)
    }

}