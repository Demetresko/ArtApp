package org.com.art.application.direction

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crash.FirebaseCrash
import com.google.firebase.database.*
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import org.com.art.application.WheelViewAlternative
import org.com.art.application.game_activity.SixStep
import org.wangjie.wheelview.R
import java.util.*

class ActivityMainDirection : AppCompatActivity() {
    private lateinit  var gArray: ArrayList<Direction>
    private lateinit  var mConception: TextView
    private lateinit  var mDescription: TextView
    private lateinit  var mTools: TextView
    private lateinit  var mDirectionTitle: TextView
    private lateinit  var mTitleDirection: TextView
    private lateinit  var mTitleConception: TextView
    private lateinit  var mTitleMethods: TextView
    private lateinit  var progressBar: ProgressBar
    private lateinit  var mFirstImage: ImageView
    private lateinit  var layoutManager: LinearLayoutManager
    private lateinit  var layoutManager2: LinearLayoutManager

    private var number = 0
    private lateinit  var recycler_artist: RecyclerView
    private lateinit  var recycler_pictures: RecyclerView

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_main_direction)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar!!.visibility = View.VISIBLE
        val item1 = PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK)
        val item3 = SecondaryDrawerItem().withIdentifier(2).withName(R.string.malevichGame).withSelectable(false).withTextColor(Color.BLACK)

        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.i_007)
                .build()

        DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .addDrawerItems(
                        item1,  //new DividerDrawerItem(),
                        item3 // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withActionBarDrawerToggle(true).withActionBarDrawerToggleAnimated(true).withOnDrawerItemClickListener { view, position, drawerItem ->
                    // do something with the clicked item :D
                    if (position == 1) {
                        val intent = Intent(view.context, WheelViewAlternative::class.java)
                        startActivity(intent)
                    }
                    if (position == 2) {
                        val intent = Intent(view.context, SixStep::class.java)
                        startActivity(intent)
                    }
                    false
                }.withSelectedItem(-1)
                .build()


        gArray = ArrayList()
        recycler_artist = findViewById(R.id.horizontal_artist_list)
        recycler_pictures = findViewById(R.id.horizontal_list)
        mDescription = findViewById(R.id.mDescription)
        mConception = findViewById<View>(R.id.mConception) as TextView
        mTools = findViewById<View>(R.id.mToolsText) as TextView
        mTitleConception = findViewById<View>(R.id.titleConception) as TextView
        mTitleDirection = findViewById<View>(R.id.titleDescription) as TextView
        mTitleMethods = findViewById<View>(R.id.titleMethods) as TextView
        mFirstImage = findViewById<View>(R.id.first_image) as ImageView
        mDirectionTitle = findViewById<View>(R.id.main_title) as TextView
        val targetView = findViewById<View>(R.id.mConceptionLayout)
        targetView.parent.requestChildFocus(targetView, targetView)
        mTitleConception.visibility = View.INVISIBLE
        mTitleDirection.visibility = View.INVISIBLE
        mTitleMethods.visibility = View.INVISIBLE
        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
        //recycler_artist.setHasFixedSize(false);
        recycler_artist!!.layoutManager = layoutManager
        layoutManager2 = LinearLayoutManager(this)
        layoutManager2!!.scrollToPositionWithOffset(2, 20)
        layoutManager2!!.orientation = LinearLayoutManager.HORIZONTAL
        //recycler_pictures.setHasFixedSize(true);
        layoutManager2!!.scrollToPositionWithOffset(2, 20)
        recycler_pictures!!.layoutManager = layoutManager2
        FirebaseCrash.logcat(Log.INFO, "Activity Direction", "  WTF  ")

        FirebaseDatabase.getInstance().reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                var count = 0
                Log.e("children tag", dataSnapshot.toString())
                val children = dataSnapshot.children
                for (child in children) {
                    count++
                    if (count >= dataSnapshot.childrenCount) { //stop progress bar here
                        progressBar!!.visibility = View.INVISIBLE
                        mTitleConception!!.visibility = View.VISIBLE
                        mTitleDirection!!.visibility = View.VISIBLE
                        mTitleMethods!!.visibility = View.VISIBLE
                    }
                    //Log.e("child tag",child.toString());
                    val direction = child.getValue(Direction::class.java)
                    gArray!!.add(direction)
                    Log.e("new_dir_added", direction.toString())
                }

                val srt = intent.getStringExtra("Number_in_list")?.let {
                    number = it.toInt()
                }

                Log.e("arrSize", gArray!!.size.toString() + "   " + number)
                Log.e("url image erre", gArray!![number].toString())
                Log.e("image_url", gArray!![number].mUrlImage)
                val mPictureList = gArray!![number].mUrlImage.split(",").toTypedArray()
                val mArtistListImage = gArray!![number].mArtistListImage?.split(",")?.toTypedArray()

                recycler_artist!!.adapter = mArtistListImage?.let { ArtistAdapter(it, number) }
                recycler_pictures!!.adapter = HorizontalRVAdapter(mPictureList, number)
                mConception!!.text = gArray!![number].concept
                mDescription!!.text = gArray!![number].desc
                mTools!!.text = gArray!![number].technique
                mDirectionTitle!!.text = gArray!![number].name
                Glide.with(applicationContext)
                        .load(gArray!![number].image)
                        .placeholder(R.drawable.rama)
                        .into(mFirstImage)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
