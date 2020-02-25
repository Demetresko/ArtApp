package org.com.art.application.artist_activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

import org.WheelViewAlternative
import org.wangjie.wheelview.R
import org.com.art.application.game_activity.SixStep

import java.util.ArrayList

class ArtistActivity : Activity() {
    lateinit var progressBar: ProgressBar
    lateinit var mRecyclerViewPicture: RecyclerView
    lateinit var mTitle: TextView
    lateinit var mDates: TextView
    lateinit var mBiography: TextView
    lateinit var mTitleDate: TextView
    lateinit var mTitleAbout: TextView
    lateinit var mImageArtist: ImageView
    lateinit var mArtistList: ArrayList<Artist>
    lateinit var layoutManager: LinearLayoutManager

    internal var numberOfArtist: Int = 0
    internal var mCondition: Int = 0

    lateinit var mFirebaseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_artistbiography)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK)

        val item3 = SecondaryDrawerItem().withIdentifier(2).withName(R.string.malevichGame).withSelectable(false).withTextColor(Color.BLACK)

        // Create the AccountHeader
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.i_007)
                .build()

        DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        //new DividerDrawerItem(),
                        // item2,
                        item3
                        // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener { view, position, drawerItem ->
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


        //        String imgpath = "/mnt/sdcard/joke.png";
        //
        //        String result = imgpath.substring(imgpath.lastIndexOf("/") + 1);
        //        System.out.println("Image name " + result);

        val targetView = findViewById<View>(R.id.mDateOfBirth)
        targetView.parent.requestChildFocus(targetView, targetView)

        mArtistList = ArrayList()

        mRecyclerViewPicture = findViewById(R.id.horizontal_artist_list)
        mTitle = findViewById(R.id.artist_title)
        mDates = findViewById(R.id.mDateOfBirth)
        mBiography = findViewById(R.id.mBiography)
        mImageArtist = findViewById(R.id.artist_portreit)
        mTitleAbout = findViewById(R.id.textAbout)
        mTitleDate = findViewById(R.id.textDate)
        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE
        mTitleDate.visibility = View.INVISIBLE
        mTitleAbout.visibility = View.INVISIBLE


        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        layoutManager.scrollToPositionWithOffset(1, 20)
        //recycler_artist.setHasFixedSize(false);
        mRecyclerViewPicture.layoutManager = layoutManager

        // ref = new Firebase("https://fir-data-976bd.firebaseio.com/users/Da Vinci/artists"); //Root URL

        val database = FirebaseDatabase.getInstance()
        val databaseReference = database.reference

        val srt = intent.getStringExtra("Number")// Получаю из Направления номер активити

        val parts = srt.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        numberOfArtist = Integer.parseInt(parts[0])// Номер художника;

        mCondition = Integer.parseInt(parts[1])// Номер моего направления

        mFirebaseUrl = when (mCondition) {
            0 -> "Направления/A/artists"
            1 -> "Направления/B/artists"
            2 -> "Направления/C/artists"
            3 -> "Направления/D/artists"
            4 -> "Направления/E/artists"
            5 -> "Направления/F/artists"
            6 -> "Направления/G/artists"
            7 -> "Направления/H/artists"
            else -> "Направления/I/artists"
        }// Беру художников по направлению из таймлайна передаю число в направление, оттуда в адаптер из адаптера в активиту Художника

        val childReference = databaseReference.child(mFirebaseUrl)

        childReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0

                val children = dataSnapshot.children

                for (child in children) {
                    count++
                    if (count >= dataSnapshot.childrenCount) {
                        //stop progress bar here
                        progressBar.visibility = View.INVISIBLE
                        mTitleDate.visibility = View.VISIBLE
                        mTitleAbout.visibility = View.VISIBLE
                    }

                    val user = child.getValue(Artist::class.java)
                    mArtistList.add(user)
                }

                val parts = mArtistList[numberOfArtist].pictures.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                mRecyclerViewPicture.adapter = ArtistBiographyAdapter(parts, numberOfArtist, applicationContext)// Список с картинами , беру массив из строк (там урлы на картинки) вместо массива классов

                mTitle.text = mArtistList[numberOfArtist].name
                mDates.text = mArtistList[numberOfArtist].dates
                mBiography.text = mArtistList[numberOfArtist].biography

                Glide.with(applicationContext)
                        .load(mArtistList[numberOfArtist].iconPicture)
                        .placeholder(R.drawable.rama)
                        .into(object : SimpleTarget<GlideDrawable>() {
                            override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                                mImageArtist.setImageDrawable(resource)
                            }
                        })
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}

