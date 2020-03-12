package org.com.art.application.sample

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import org.com.art.application.WheelView
import org.com.art.application.WheelView.OnWheelViewListener
import org.com.art.application.game_activity.SixStep
import org.wangjie.wheelview.R
import java.util.*

class TimelineActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_line_activity)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val item4 = SecondaryDrawerItem().withIdentifier(2).withName("Малевич Game").withSelectable(false).withTextColor(Color.BLACK)
        // Create the AccountHeader
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.i_007)
                .build()
        DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item4 // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener { view, position, drawerItem ->
                    if (position == 1) {
                        val intent = Intent(view.context, SixStep::class.java)
                        startActivity(intent)
                    }
                    false
                }.withSelectedItem(-1) //эта штука селекшн дефаултный убирает
                .build()
        //  result.updateItem(item2);
        val wva = findViewById<View>(R.id.main_wv) as WheelView
        wva.setSeletion(4)
        wva.offset = 3
        wva.setItems(Arrays.asList(*PLANETS))
        wva.onWheelViewListener = object : OnWheelViewListener() {
            override fun onSelected(selectedIndex: Int, item: String?) {
                Log.d(TAG, "selectedIndex: $selectedIndex, item1: $item")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item1 clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {}

    companion object {
        private val TAG = TimelineActivity::class.java.simpleName
        private val PLANETS = arrayOf("", "1850   Реализм", "1860   Импрессионизм", "1880   Пост" +
                "\n" + "        -импрессионизм",
                "1910   Кубизм", "1915   Экспрессионизм", "1920   Сюрреализм",
                "1930   Абстракционизм", "1950   Абстрактная" +
                "\n" + "            Живопись", "1960   Поп арт")
    }
}
