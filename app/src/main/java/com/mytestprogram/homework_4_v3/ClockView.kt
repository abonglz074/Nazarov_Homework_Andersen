package com.mytestprogram.homework_4_v3

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View

class ClockView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet!!, 0)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        init(context, attributeSet)
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val scalePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val hourPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val minPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val secondPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var circleColor = Color.BLACK
    private var scaleColor = Color.BLACK
    private var hourColor = Color.BLACK
    private var minColor = Color.RED
    private var secondColor = Color.BLUE


    // Ширина линий
    private val circlestroke = 10f
    private val scaleStroke = 10f
    private val bigScaleLen = 40f
    private val hourStroke = 15f
    private val minStroke = 12f
    private val secondStroke = 9f

    private var phoneWidth = 0
    private var viewHeight = 0
    private var radius = 0f
    private var space = 100
    private var centerX = 0f
    private var centerY = 0f

    private var hourHandLength = 180
    private var minHandLength = 150
    private var secondHandLength = 120

    private fun init(context: Context, attributeSet: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.ClockView)
        circlePaint.color = typeArray.getColor(R.styleable.ClockView_clock_ring_color, circleColor)
        circlePaint.style = Paint.Style.STROKE
        scalePaint.color = typeArray.getColor(R.styleable.ClockView_clock_scale_color, scaleColor)
        hourPaint.color = typeArray.getColor(R.styleable.ClockView_clock_hour_color, hourColor)
        minPaint.color = typeArray.getColor(R.styleable.ClockView_clock_min_color, minColor)
        secondPaint.color = typeArray.getColor(R.styleable.ClockView_clock_second_color, secondColor)
        hourHandLength = typeArray.getInt(R.styleable.ClockView_hour_hand_length, hourHandLength)
        minHandLength = typeArray.getInt(R.styleable.ClockView_min_hand_length, minHandLength)
        secondHandLength = typeArray.getInt(R.styleable.ClockView_second_hand_length, secondHandLength)


        typeArray.recycle()

        circlePaint.strokeWidth = circlestroke

        scalePaint.style = Paint.Style.STROKE
        scalePaint.strokeWidth = scaleStroke

        hourPaint.style = Paint.Style.STROKE
        hourPaint.strokeWidth = hourStroke

        minPaint.style = Paint.Style.STROKE
        minPaint.strokeWidth = minStroke

        secondPaint.style = Paint.Style.STROKE
        secondPaint.strokeWidth = secondStroke

        val displayMetrics = context.resources.displayMetrics
        phoneWidth = displayMetrics.widthPixels

        radius = phoneWidth / 2f - space
        centerX = phoneWidth / 2f
        centerY = radius + space

        viewHeight = (centerY * 2).toInt()

        startAnimator()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
        drawScale(canvas)
        drawHour(canvas)
        drawMin(canvas)
        drawSecond(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(phoneWidth, viewHeight)

    }

    // Draw the watch circle

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(centerX, centerY, radius, circlePaint)
    }

    //Draw watch scales

    private fun drawScale(canvas: Canvas) {
        canvas.save()
        canvas.rotate(360 / 12f, centerX, centerY)
        val angle = 360 / 12f
        for (i in 0 until 12) {
                canvas.drawLine(
                    phoneWidth / 2f,
                    space - scaleStroke / 2 + circlestroke,
                    phoneWidth / 2f,
                    space + bigScaleLen,
                    scalePaint
                )
            canvas.rotate(angle, centerX, centerY)
        }
        canvas.restore()
    }

    // Draw the second hand

    private fun drawSecond(canvas: Canvas) {
        canvas.save()
        canvas.rotate(secondDegrees, centerX, centerY)
        canvas.drawLine(centerX, centerY + secondHandLength / 3, centerX, centerY - secondHandLength, secondPaint)
        canvas.restore()
    }

    // Draw the minute hand

    private fun drawMin(canvas: Canvas) {
        canvas.save()
        canvas.rotate(minDegrees, centerX, centerY)
        canvas.drawLine(centerX, centerY + minHandLength / 3, centerX, centerY - minHandLength, minPaint)
        canvas.restore()
    }

    //Draw the hour hand

    private fun drawHour(canvas: Canvas) {
        canvas.save()
        canvas.rotate(hourDegrees, centerX, centerY)
        canvas.drawLine(centerX + hourHandLength / 3, centerY, centerX - hourHandLength, centerY, hourPaint)
        canvas.restore()
    }

    var secondDegrees = 0f
        set(value) {
            field = value
            invalidate()
        }

    var minDegrees = 0f

    var hourDegrees = -30f
    lateinit var animatorHandler: Handler
    private fun startAnimator() {
        animatorHandler = Handler()
        val runnable = object : Runnable {
            override fun run() {

                if (secondDegrees >= 360) {
                    secondDegrees = 360 / 60f

                    if (hourDegrees >= 360) {
                        hourDegrees = 0f
                    } else {
                        hourDegrees += 0.5f
                    }
                } else {
                    secondDegrees += 360 / 60f
                }

                if (minDegrees >= 360) {
                    minDegrees = 360 / 60 / 60f
                } else {
                    minDegrees += 360 / 60 / 60f
                }

                animatorHandler.postDelayed(this, 1000)
            }
        }
        animatorHandler.post(runnable)
    }

    fun setCurrentTime(hour: Int, min: Int, second: Int) {
        var currentHour = hour
        if (currentHour > 12) {
            currentHour -= 12
        }
        hourDegrees = (9 - hour) * (-30).toFloat()
        minDegrees = min * 6f
        secondDegrees = second * 6f

        val secondAnimator = ValueAnimator.ofFloat(0f, secondDegrees)
        secondAnimator.addUpdateListener {
            secondDegrees = it.animatedValue as Float
        }
        val minAnimator = ValueAnimator.ofFloat(0f, minDegrees)
        minAnimator.addUpdateListener {
            minDegrees = it.animatedValue as Float
        }
        val hourAnimator = ValueAnimator.ofFloat(-30f, hourDegrees)
        hourAnimator.addUpdateListener {
            hourDegrees = it.animatedValue as Float
        }
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(secondAnimator, minAnimator, hourAnimator)
        animatorSet.duration = 1000
        animatorSet.start()
    }

    fun stop() {
        animatorHandler.removeCallbacksAndMessages(null)
    }
}