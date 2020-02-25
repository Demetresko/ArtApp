package org.com.art.application

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import org.com.art.application.WheelView
import org.com.art.application.direction.ActivityMainDirection
import java.util.*

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 7/1/14.
 */
class WheelView : ScrollView {
    var i = 0

    open class OnWheelViewListener {
        open fun onSelected(selectedIndex: Int, item: String?) {}
    }

    private var views: LinearLayout? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private lateinit var items: MutableList<String>

    private fun getItems(): List<String>? {
        return items
    }

    fun setItems(list: List<String?>?) {
        if (null == items) {
            items = ArrayList()
        }
        items!!.clear()
        items.addAll(list as Collection<String>)
        // 前面和后面补全 передняя и задняя комплементаё
        for (i in 0 until offset - 1) {
            items!!.add(0, "")
            items!!.add("")
        }
        initData()
    }

    //сколько итемов будет видно - установить смещение
    var offset = OFF_SET_DEFAULT // 偏移量（需要在最前面和最后面补全） Смещение (комплемент требуется в передней и дальней)

    private var displayItemCount = 0 // 每页显示的数量 выводить по = 0
    var selectedIndex = 1 // не влияет на первый клик итема
    private fun init(context: Context) {
        //        scrollView = ((ScrollView)this.getParent());
//        Log.d(TAG, "scrollview: " + scrollView);
        Log.d(TAG, "parent: " + this.parent)
        //        this.setOrientation(VERTICAL);
        this.isVerticalScrollBarEnabled = false
        views = LinearLayout(context)
        views!!.orientation = LinearLayout.VERTICAL
        views!!.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        this.addView(views)
        scrollerTask = Runnable {
            val newY = scrollY
            if (initialY - newY == 0) { // stopped
                val remainder = initialY % itemHeight
                val divided = initialY / itemHeight
                //                    Log.d(TAG, "initialY: " + initialY);
//                    Log.d(TAG, "remainder: " + remainder + ", divided: " + divided);
                if (remainder == 0) {
                    selectedIndex = divided + offset //Выбранный индекс
                    onSeletedCallBack()
                } else {
                    if (remainder > itemHeight / 2) {
                        post {
                            smoothScrollTo(0, initialY - remainder + itemHeight)
                            selectedIndex = divided + offset + 1
                            onSeletedCallBack()
                        }
                    } else {
                        post {
                            smoothScrollTo(0, initialY - remainder)
                            selectedIndex = divided + offset
                            onSeletedCallBack()
                        }
                    }
                }
            } else {
                initialY = scrollY
                postDelayed(scrollerTask, newCheck.toLong())
            }
        }
    }

    var initialY = 0
    var scrollerTask: Runnable? = null
    var newCheck = 50
    fun startScrollerTask() {
        initialY = scrollY
        postDelayed(scrollerTask, newCheck.toLong())
    }

    private fun initData() {
        displayItemCount = offset * 2 + 1
        for (item in items!!) {
            views!!.addView(createView(item))
        }
        refreshItemView(0)
    }

    var itemHeight = 0
    private fun createView(item: String): TextView { //Textview создаю вьюшку в списке
        val tv = TextView(context)
        views!!.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (item.length != 0) {
            val ss1 = SpannableString(item)
            ss1.setSpan(RelativeSizeSpan(0.6f), 0, 5, 0) // set size
            //ss1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 5, 0);// set color
            views!!.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            tv.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            //            tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.isSingleLine = false
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 27f)
            tv.setTypeface(null, Typeface.BOLD)
            tv.text = ss1
            tv.gravity = Gravity.LEFT
        } else {
            tv.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            tv.isSingleLine = false
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 27f)
            tv.setTypeface(null, Typeface.ITALIC)
            tv.text = item
            tv.gravity = Gravity.CENTER
        }
        views!!.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        val padding = dip2px(20f)
        tv.setPadding(padding, padding, padding, padding)
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(tv)
            Log.d(TAG, "itemHeight: $itemHeight")
            views!!.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            //            views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
            val lp = this.layoutParams as LinearLayout.LayoutParams
            this.layoutParams = LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount)
            //            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.getLayoutParams();
//            this.setLayoutParams(new RelativeLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
        }
        return tv
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        refreshItemView(t)
        scrollDirection = if (t > oldt) { //            Log.d(TAG, "向下滚动");
            SCROLL_DIRECTION_DOWN
        } else { //            Log.d(TAG, "向上滚动");
            SCROLL_DIRECTION_UP
        }
    }

    private fun refreshItemView(y: Int) {
        var position = y / itemHeight + offset
        val remainder = y % itemHeight
        val divided = y / itemHeight
        if (remainder == 0) {
            position = divided + offset
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1
            }
        }
        val childSize = views!!.childCount
        i = 0
        while (i < childSize) {
            val itemView = views!!.getChildAt(i) as TextView //Надпись
            itemView.setOnClickListener {
                var intent: Intent? = null
                val s = seletedItem
                if (s == "1850   Реализм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "0")
                } else if (s == "1860   Импрессионизм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "1")
                } else if (s == "1880   Пост" + "\n" + "        -импрессионизм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "2")
                } else if (s == "1910   Кубизм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "3")
                } else if (s == "1915   Экспрессионизм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "4")
                } else if (s == "1920   Сюрреализм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "5")
                } else if (s == "1930   Абстракционизм") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "6")
                } else if (s == "1950   Абстрактная" + "\n" + "            Живопись") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "7")
                } else if (s == "1960   Поп арт") {
                    intent = Intent(context, ActivityMainDirection::class.java)
                    intent.putExtra("Number_in_list", "8")
                }
                context!!.startActivity(intent)
            }
            if (null == itemView) {
                return
            }
            if (position == i) {
                itemView.setTextColor(Color.parseColor("#000000")) // Текущий цвет Черныыыйййй
            } else {
                itemView.setTextColor(Color.parseColor("#bbbbbb")) //Цвет остальных Серый
            }
            i++
        }
    }

    /**
     * 获取选中区域的边界
     */
    private lateinit var selectedAreaBorder: IntArray

    private fun obtainSelectedAreaBorder(): IntArray {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = IntArray(2)
            selectedAreaBorder!![0] = itemHeight * offset
            selectedAreaBorder!![1] = itemHeight * (offset + 1)
        }
        return selectedAreaBorder
    }

    private var scrollDirection = -1
    var paint: Paint? = null
    var viewWidth = 0
    override fun setBackgroundDrawable(background: Drawable) {
        var background: Drawable? = background
        if (viewWidth == 0) {
            viewWidth = (context as Activity?)!!.windowManager.defaultDisplay.width
            Log.d(TAG, "viewWidth: $viewWidth")
        }
        if (null == paint) {
            paint = Paint()
            paint!!.color = Color.parseColor("#000000")
            paint!!.strokeWidth = dip2px(1f).toFloat()
        }
        background = object : Drawable() {
            override fun draw(canvas: Canvas) {
                canvas.drawLine(viewWidth * 1 / 6.toFloat(), obtainSelectedAreaBorder()[0].toFloat(), viewWidth * 5 / 6.toFloat(), obtainSelectedAreaBorder()[0].toFloat(), paint!!)
                canvas.drawLine(viewWidth * 1 / 6.toFloat(), obtainSelectedAreaBorder()[1].toFloat(), viewWidth * 5 / 6.toFloat(), obtainSelectedAreaBorder()[1].toFloat(), paint!!)
            }

            override fun setAlpha(alpha: Int) {}
            override fun setColorFilter(cf: ColorFilter?) {}
            override fun getOpacity(): Int {
                return PixelFormat.UNKNOWN
            }
        }
        super.setBackgroundDrawable(background)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d(TAG, "w: $w, h: $h, oldw: $oldw, oldh: $oldh")
        viewWidth = w
        //setBackgroundDrawable(null);
    }

    /**
     * 选中回调
     */
    private fun onSeletedCallBack() {
        if (null != onWheelViewListener) {
            onWheelViewListener!!.onSelected(selectedIndex, items!![selectedIndex])
        }
    }

    fun setSeletion(position: Int) {
        selectedIndex = position + offset + 2 // Вот тут я выбранный индекс увеличил на 2 так как после SetOffset меняется SelectedIndex
        post { smoothScrollTo(0, position * itemHeight) }
    }

    val seletedItem: String
        get() = items!![selectedIndex]

    val seletedIndex: Int
        get() = selectedIndex - offset

    override fun fling(velocityY: Int) {
        super.fling(velocityY / 3)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            startScrollerTask()
        }
        return super.onTouchEvent(ev)
    }

    var onWheelViewListener: OnWheelViewListener? = null

    private fun dip2px(dpValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun getViewMeasuredHeight(view: View): Int {
        val width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val expandSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        view.measure(width, expandSpec)
        return view.measuredHeight
    }

    companion object {
        val TAG = WheelView::class.java.simpleName
        const val OFF_SET_DEFAULT = 1
        private const val SCROLL_DIRECTION_UP = 0
        private const val SCROLL_DIRECTION_DOWN = 1
    }
}