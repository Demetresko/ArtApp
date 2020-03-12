package org.com.art.application

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crash.FirebaseCrash
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import org.com.art.application.direction.ActivityMainDirection
import org.com.art.application.game_activity.SixStep
import org.com.art.application.sample.CenterZoomLayoutManager
import org.wangjie.wheelview.R
import java.util.*

class WheelViewAlternative : AppCompatActivity() {

    private var prevCenterPos = 0

    var firstItemWidthDate = 0f
    var paddingDate = 0f
    var itemWidthDate = 0f
    var allPixelsDate = 0
    var finalWidthDate = 0
    private var dateAdapter: DateAdapter? = null
    private var labelerDates = ArrayList<LabelerDate>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alternative_timeline)
        getRecyclerviewDate()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val item4 = SecondaryDrawerItem().withIdentifier(2).withName("Малевич Game").withSelectable(false).withTextColor(Color.BLACK)
        FirebaseCrash.logcat(Log.INFO, "WheelView", "  WTF  ")
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.i_007)
                .build()
        val result = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item4)
                .withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener { view, position, drawerItem ->
                    if (position == 1) {
                        val intent = Intent(view.context, SixStep::class.java)
                        startActivity(intent)
                    }
                    false
                }.withSelectedItem(-1) //эта штука селекшн дефаултный убирает
                .build()
    }

    fun getRecyclerviewDate() {
        val recyclerViewDate = findViewById<RecyclerView>(R.id.rv_tasks_date) // RecyclerView installing
        val vtoDate = recyclerViewDate.viewTreeObserver

        vtoDate.addOnPreDrawListener {
//            recyclerViewDate.viewTreeObserver.removeOnPreDrawListener()
            finalWidthDate = recyclerViewDate.measuredHeight;
            itemWidthDate = resources.getDimension(R.dimen.item_dob);
            paddingDate = (finalWidthDate - itemWidthDate) / 3; // Отступы просчитываем
            firstItemWidthDate = paddingDate;
            allPixelsDate = 0;

            val dateLayoutManager = CenterZoomLayoutManager(applicationContext)
            dateLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerViewDate.layoutManager = dateLayoutManager //  Manager adding


            recyclerViewDate.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val center = recyclerViewDate.height / 2
                    val centerView = recyclerViewDate.findChildViewUnder(center.toFloat(), recyclerViewDate.right.toFloat())
                    val centerPos = recyclerViewDate.getChildAdapterPosition(centerView!!) // номер центрального итема
                    if (prevCenterPos != centerPos) { // dehighlight the previously highlighted view
                        val prevView = recyclerViewDate.layoutManager!!.findViewByPosition(prevCenterPos) // 0
                        //    View prevView = recyclerViewDate.getLayoutManager().findViewByPosition(prevCenterPos);//
                        if (prevView != null) {
                            val button = prevView.findViewById<TextView>(R.id.txt_date)
                            button.setTextColor(Color.GRAY)
                            val mDateTextView = prevView.findViewById<TextView>(R.id.textDate)
                            mDateTextView.setTextColor(Color.GRAY)
                        }
                        // highlight view in the middle
                        if (centerView != null) {
                            val button = centerView.findViewById<TextView>(R.id.txt_date)
                            button.setTextColor(Color.BLACK)
                            val mDateTextView = centerView.findViewById<TextView>(R.id.textDate)
                            mDateTextView.setTextColor(Color.BLACK)
                        }
                        prevCenterPos = centerPos
                    }
                }
            })
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                Handler().postDelayed(
                        { recyclerViewDate.scrollToPosition(dateAdapter!!.itemCount - 4) }, 1)
            } else {
                Handler().postDelayed(
                        { recyclerViewDate.smoothScrollToPosition(dateAdapter!!.itemCount - 4) }, 1)
            }
            if (labelerDates == null) {
                labelerDates = ArrayList()
            }
            genLabelerDate()
            dateAdapter = DateAdapter(labelerDates, firstItemWidthDate.toInt(),
                    action =
                    {   val intent = Intent(this, ActivityMainDirection::class.java)
                        intent.putExtra(it[0], it[1])
                        startActivity(intent)
                    }
            )
            recyclerViewDate.adapter = dateAdapter
            dateAdapter!!.setSelecteditem(dateAdapter!!.itemCount - 1)

            true
        }

    }

    private fun genLabelerDate() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            for (i in 0..12) {
                val labelerDate: LabelerDate = LabelerDate()
                labelerDates.add(labelerDate)
                if (i == 3 || i == 12) {
                    labelerDate.type = DateAdapter.VIEW_TYPE_PADDING
                } else {
                    labelerDate.type = DateAdapter.VIEW_TYPE_ITEM
                }
            }
        } else { //заполнение
            for (i in 0..13) {
                val labelerDate = LabelerDate()
                labelerDates.add(labelerDate)
                if (i == 3 || i == 13) {
                    labelerDate.type = DateAdapter.VIEW_TYPE_PADDING
                } else {
                    labelerDate.type = DateAdapter.VIEW_TYPE_ITEM
                }
            }
        }
    }

    private fun scrollListToPositionDate(recyclerView: RecyclerView, expectedPositionDate: Int) {
        val targetScrollPosDate = expectedPositionDate * itemWidthDate + firstItemWidthDate - paddingDate
        val missingPxDate = targetScrollPosDate - allPixelsDate
        if (missingPxDate != 0f) {
            recyclerView.smoothScrollBy(missingPxDate.toInt(), 0)
        }
        setDateValue()
    }

    private fun setDateValue() {
        val expectedPositionDateColor = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate)
        val setColorDate = expectedPositionDateColor + 1
        //        set color here
        dateAdapter!!.setSelecteditem(setColorDate)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
