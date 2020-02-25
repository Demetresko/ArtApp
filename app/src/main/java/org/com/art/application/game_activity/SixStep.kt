package org.com.art.application.game_activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import org.com.art.application.sample.TimelineActivity
import org.wangjie.wheelview.R
import java.util.*

/**
 * Created by Wonka on 14.08.2017.
 */
class SixStep : AppCompatActivity() {
    var mQuantOfTries = 0
    var mMainArray = arrayOf(intArrayOf(R.drawable.ic_image101, R.drawable.ic_image102, R.drawable.ic_image103, R.drawable.ic_image104, R.drawable.ic_image105, R.drawable.ic_image106,
            R.drawable.ic_image201, R.drawable.ic_image202, R.drawable.ic_image203, R.drawable.ic_image204, R.drawable.ic_image205, R.drawable.ic_image206
    ), intArrayOf(R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12), intArrayOf(R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6,
            R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10, R.drawable.i11, R.drawable.i12))

    var iv_11: ImageView? = null
    var iv_12: ImageView? = null
    var iv_13: ImageView? = null
    var iv_14: ImageView? = null
    var iv_21: ImageView? = null
    var iv_23: ImageView? = null
    var iv_22: ImageView? = null
    var iv_24: ImageView? = null
    var iv_31: ImageView? = null
    var iv_32: ImageView? = null
    var iv_33: ImageView? = null
    var iv_34: ImageView? = null
    //array for the images
    var cardArray = arrayOf(101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206)
    var mQuant: TextView? = null
    //actual images то куда дровербл пихается
    var image101 = 0
    var image102 = 0
    var image103 = 0
    var image104 = 0
    var image105 = 0
    var image106 = 0
    var image201 = 0
    var image202 = 0
    var image203 = 0
    var image204 = 0
    var image205 = 0
    var image206 = 0
    var firstCard = 0
    var secondCard = 0
    var clickedFirst = 0
    var clickedSecond = 0
    var cardNumber = 1
    var turn = 1
    var playerPoints = 0
    var cpuPoints = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val item1 = PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK)
        val item3 = SecondaryDrawerItem().withIdentifier(2).withName(R.string.malevichGame).withSelectable(false).withTextColor(Color.BLACK)
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.i_007)
                .build()
        //Now create your drawer and pass the AccountHeader.Result
//additional Drawer setup as shown above
        val result = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,  //new DividerDrawerItem(),
//      item2,
                        item3 // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener { view, position, drawerItem ->
                    // do something with the clicked item :D
                    if (position == 1) {
                        val intent = Intent(view.context, TimelineActivity::class.java)
                        startActivity(intent)
                    }
                    if (position == 2) {
                        val intent = Intent(view.context, SixStep::class.java)
                        startActivity(intent)
                    }
                    false
                }.withSelectedItem(-1)
                .build()
        mQuantOfTries = 0
        //        tv_p1= (TextView) findViewById(R.id.tv_p1);
//        tv_p2= (TextView) findViewById(R.id.tv_p2);
        mQuant = findViewById<View>(R.id.mQuantity) as TextView
        mQuant!!.text = mQuantOfTries.toString()
        iv_11 = findViewById<View>(R.id.iv_11) as ImageView
        iv_12 = findViewById<View>(R.id.iv_12) as ImageView
        iv_13 = findViewById<View>(R.id.iv_13) as ImageView
        iv_14 = findViewById<View>(R.id.iv_14) as ImageView
        iv_21 = findViewById<View>(R.id.iv_21) as ImageView
        iv_22 = findViewById<View>(R.id.iv_22) as ImageView
        iv_23 = findViewById<View>(R.id.iv_23) as ImageView
        iv_24 = findViewById<View>(R.id.iv_24) as ImageView
        iv_31 = findViewById<View>(R.id.iv_31) as ImageView
        iv_32 = findViewById<View>(R.id.iv_32) as ImageView
        iv_33 = findViewById<View>(R.id.iv_33) as ImageView
        iv_34 = findViewById<View>(R.id.iv_34) as ImageView
        iv_11!!.tag = "0"
        iv_12!!.tag = "1"
        iv_13!!.tag = "2"
        iv_14!!.tag = "3"
        iv_21!!.tag = "4"
        iv_22!!.tag = "5"
        iv_23!!.tag = "6"
        iv_24!!.tag = "7"
        iv_31!!.tag = "8"
        iv_32!!.tag = "9"
        iv_33!!.tag = "10"
        iv_34!!.tag = "11"
        //load card images
        frontOfCardsResources()
        //shuffle images
        mutableListOf(cardArray).shuffle()
        //        tv_p2.setTextColor(Color.GRAY);
        iv_11!!.setOnClickListener { view ->
            val theCard= (view.tag as String).toInt()
            doStuff(iv_11, theCard)
        }
        iv_12!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_12, theCard)
        }
        iv_13!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_13, theCard)
        }
        iv_14!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_14, theCard)
        }
        iv_21!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_21, theCard)
        }
        iv_22!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_22, theCard)
        }
        iv_23!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_23, theCard)
        }
        iv_24!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_24, theCard)
        }
        iv_31!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_31, theCard)
        }
        iv_32!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_32, theCard)
        }
        iv_33!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_33, theCard)
        }
        iv_34!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            doStuff(iv_34, theCard)
        }
    }

    private fun doStuff(iv: ImageView?, card: Int) { //set the correct image to the imageview//check if selected images are equal
        // вписываем количество попыток
        when {
            cardArray[card] == 101 -> {
                iv!!.setImageResource(image101)
            }
            cardArray[card] == 102 -> {
                iv!!.setImageResource(image102)
            }
            cardArray[card] == 103 -> {
                iv!!.setImageResource(image103)
            }
            cardArray[card] == 104 -> {
                iv!!.setImageResource(image104)
            }
            cardArray[card] == 105 -> {
                iv!!.setImageResource(image105)
            }
            cardArray[card] == 106 -> {
                iv!!.setImageResource(image106)
            }
            cardArray[card] == 201 -> {
                iv!!.setImageResource(image201)
            }
            cardArray[card] == 202 -> {
                iv!!.setImageResource(image202)
            }
            cardArray[card] == 203 -> {
                iv!!.setImageResource(image203)
            }
            cardArray[card] == 204 -> {
                iv!!.setImageResource(image204)
            }
            cardArray[card] == 205 -> {
                iv!!.setImageResource(image205)
            }
            cardArray[card] == 206 -> {
                iv!!.setImageResource(image206)
            }
            //check which image is selected and save it to temporarily variable
        }
        //check which image is selected and save it to temporarily variable
        if (cardNumber == 1) {
            firstCard = cardArray[card]
            if (firstCard > 200) {
                firstCard = firstCard - 100
            }
            cardNumber = 2
            clickedFirst = card
            iv!!.isEnabled = false
        } else if (cardNumber == 2) {
            secondCard = cardArray[card]
            if (secondCard > 200) {
                secondCard = secondCard - 100
            }
            // вписываем количество попыток
            cardNumber = 1
            clickedSecond = card
            iv_11!!.isEnabled = false
            iv_12!!.isEnabled = false
            iv_13!!.isEnabled = false
            iv_14!!.isEnabled = false
            iv_21!!.isEnabled = false
            iv_22!!.isEnabled = false
            iv_23!!.isEnabled = false
            iv_24!!.isEnabled = false
            iv_31!!.isEnabled = false
            iv_32!!.isEnabled = false
            iv_33!!.isEnabled = false
            iv_34!!.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                //check if selected images are equal
                calculate()
            }, 1100)
        }
    }

    private fun calculate() { // if images are equal remove them and add point
        mQuantOfTries++
        mQuant!!.text = mQuantOfTries.toString()
        if (firstCard == secondCard) {// tv_p2.setText("p2  "+cpuPoints);
            //  tv_p1.setText("p1  "+playerPoints);
            when (clickedFirst) {
                0 -> {
                    iv_11!!.visibility = View.INVISIBLE
                }
                1 -> {
                    iv_12!!.visibility = View.INVISIBLE
                }
                2 -> {
                    iv_13!!.visibility = View.INVISIBLE
                }
                3 -> {
                    iv_14!!.visibility = View.INVISIBLE
                }
                4 -> {
                    iv_21!!.visibility = View.INVISIBLE
                }
                5 -> {
                    iv_22!!.visibility = View.INVISIBLE
                }
                6 -> {
                    iv_23!!.visibility = View.INVISIBLE
                }
                7 -> {
                    iv_24!!.visibility = View.INVISIBLE
                }
                8 -> {
                    iv_31!!.visibility = View.INVISIBLE
                }
                9 -> {
                    iv_32!!.visibility = View.INVISIBLE
                }
                10 -> {
                    iv_33!!.visibility = View.INVISIBLE
                }
                11 -> {
                    iv_34!!.visibility = View.INVISIBLE
                }
                //add   points
            }// tv_p2.setText("p2  "+cpuPoints);
            //  tv_p1.setText("p1  "+playerPoints);
            when (clickedSecond) {
                0 -> {
                    iv_11!!.visibility = View.INVISIBLE
                }
                1 -> {
                    iv_12!!.visibility = View.INVISIBLE
                }
                2 -> {
                    iv_13!!.visibility = View.INVISIBLE
                }
                3 -> {
                    iv_14!!.visibility = View.INVISIBLE
                }
                4 -> {
                    iv_21!!.visibility = View.INVISIBLE
                }
                5 -> {
                    iv_22!!.visibility = View.INVISIBLE
                }
                6 -> {
                    iv_23!!.visibility = View.INVISIBLE
                }
                7 -> {
                    iv_24!!.visibility = View.INVISIBLE
                }
                8 -> {
                    iv_31!!.visibility = View.INVISIBLE
                }
                9 -> {
                    iv_32!!.visibility = View.INVISIBLE
                }
                10 -> {
                    iv_33!!.visibility = View.INVISIBLE
                }
                11 -> {
                    iv_34!!.visibility = View.INVISIBLE
                }
                //add   points
            }
            //add   points
            if (turn == 1) {
                playerPoints++
                //  tv_p1.setText("p1  "+playerPoints);
            } else if (turn == 2) {
                cpuPoints++
                // tv_p2.setText("p2  "+cpuPoints);
            }
        } else {
            iv_11!!.setImageResource(R.drawable.card_cover)
            iv_12!!.setImageResource(R.drawable.card_cover)
            iv_13!!.setImageResource(R.drawable.card_cover)
            iv_14!!.setImageResource(R.drawable.card_cover)
            iv_21!!.setImageResource(R.drawable.card_cover)
            iv_22!!.setImageResource(R.drawable.card_cover)
            iv_23!!.setImageResource(R.drawable.card_cover)
            iv_24!!.setImageResource(R.drawable.card_cover)
            iv_31!!.setImageResource(R.drawable.card_cover)
            iv_32!!.setImageResource(R.drawable.card_cover)
            iv_33!!.setImageResource(R.drawable.card_cover)
            iv_34!!.setImageResource(R.drawable.card_cover)
            //change the player turn
            if (turn == 1) {
                turn = 2
                //  tv_p1.setTextColor(Color.GRAY);
//tv_p2.setTextColor(Color.BLACK);
            } else if (turn == 2) {
                turn = 1
                // tv_p2.setTextColor(Color.GRAY);
//tv_p1.setTextColor(Color.BLACK);
            }
        }
        iv_11!!.isEnabled = true
        iv_12!!.isEnabled = true
        iv_13!!.isEnabled = true
        iv_14!!.isEnabled = true
        iv_21!!.isEnabled = true
        iv_22!!.isEnabled = true
        iv_23!!.isEnabled = true
        iv_24!!.isEnabled = true
        iv_31!!.isEnabled = true
        iv_32!!.isEnabled = true
        iv_33!!.isEnabled = true
        iv_34!!.isEnabled = true
        checkEnd()
    }

    private fun checkEnd() {
        if (iv_11!!.visibility == View.INVISIBLE && iv_12!!.visibility == View.INVISIBLE && iv_13!!.visibility == View.INVISIBLE && iv_14!!.visibility == View.INVISIBLE && iv_21!!.visibility == View.INVISIBLE && iv_22!!.visibility == View.INVISIBLE && iv_23!!.visibility == View.INVISIBLE && iv_24!!.visibility == View.INVISIBLE && iv_31!!.visibility == View.INVISIBLE && iv_32!!.visibility == View.INVISIBLE && iv_33!!.visibility == View.INVISIBLE && iv_34!!.visibility == View.INVISIBLE) {
            val alertDialog = AlertDialog.Builder(this@SixStep)
            alertDialog.setMessage("Игра закончена ").setCancelable(false)
                    .setPositiveButton("Новая") { dialogInterface, i ->
                        val intent = Intent(applicationContext, SixStep::class.java)
                        startActivity(intent)
                        finish()
                    }.setNegativeButton("Выйти") { dialogInterface, i -> finish() }
            val alertDialog1 = alertDialog.create()
            alertDialog1.show()
            val buttonPositive = alertDialog1.getButton(DialogInterface.BUTTON_POSITIVE)
            buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.black))
            val buttonNegative = alertDialog1.getButton(DialogInterface.BUTTON_NEGATIVE)
            buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    //Определяются картины и художники
    private fun frontOfCardsResources() {
        val random = Random()
        val number = random.nextInt(2 - 0 + 1) + 0
        image101 = mMainArray[number][0]
        image102 = mMainArray[number][1]
        image103 = mMainArray[number][2]
        image104 = mMainArray[number][3]
        image105 = mMainArray[number][4]
        image106 = mMainArray[number][5]
        image201 = mMainArray[number][6]
        image202 = mMainArray[number][7]
        image203 = mMainArray[number][8]
        image204 = mMainArray[number][9]
        image205 = mMainArray[number][10]
        image206 = mMainArray[number][11]
    }
}