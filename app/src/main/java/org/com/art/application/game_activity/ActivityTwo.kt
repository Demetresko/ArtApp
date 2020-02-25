package org.com.art.application.game_activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.wangjie.wheelview.R
import java.util.*

/**
 * Created by Wonka on 14.08.2017.
 */
class ActivityTwo : AppCompatActivity() {
    private var mQuantOfTries = 0
    private var mMainArray = arrayOf(intArrayOf(R.drawable.ic_image101, R.drawable.ic_image102, R.drawable.ic_image103, R.drawable.ic_image104, R.drawable.ic_image105, R.drawable.ic_image106,
            R.drawable.ic_image201, R.drawable.ic_image202, R.drawable.ic_image203, R.drawable.ic_image204, R.drawable.ic_image205, R.drawable.ic_image206
    ), intArrayOf(R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12), intArrayOf(R.drawable.v1, R.drawable.v4, R.drawable.v7, R.drawable.v10,
            R.drawable.v2, R.drawable.v5, R.drawable.v8, R.drawable.v11,
            R.drawable.v3, R.drawable.v6, R.drawable.v9, R.drawable.v12
    ))
    private var iv_11: ImageView? = null
    private var iv_12: ImageView? = null
    private var iv_13: ImageView? = null
    private var iv_14: ImageView? = null
    private var iv_21: ImageView? = null
    private var iv_23: ImageView? = null
    private var iv_22: ImageView? = null
    private var iv_24: ImageView? = null
    private var iv_31: ImageView? = null
    private var iv_32: ImageView? = null
    private var iv_33: ImageView? = null
    private var iv_34: ImageView? = null

    private var cardArray = arrayOf(101, 102, 103, 104, 201, 202, 203, 204, 301, 302, 303, 304)
    private var mQuant: TextView? = null

    private var image101 = 0
    private var image102 = 0
    private var image103 = 0
    private var image104 = 0
    private var image105 = 0
    private var image106 = 0
    private var image201 = 0
    private var image202 = 0
    private var image203 = 0
    private var image204 = 0
    private var image205 = 0
    private var image206 = 0
    private var firstCard = 0
    private var secondCard = 0
    private var thirdCard = 0
    private var fourthCard = 0
    private var clickedFirst = 0
    private var clickedSecond = 0
    private var clickedThird = 0
    private var cardNumber = 1
    private var turn = 1
    private var playerPoints = 0
    private var cpuPoints = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity2)
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
        mutableListOf(cardArray).shuffle() // Integer [] cardArray={101,102,103,104,105,106,  201,202,203,204,205,206};
        // выставляем картины
        (iv_11!!.tag as String?)?.toInt()?.let { doStuff(iv_11, it) }
        (iv_12!!.tag as String?)?.toInt ()?.let { doStuff(iv_12, it) }
        (iv_13!!.tag as String?)?.toInt ()?.let { doStuff(iv_13, it) }
        (iv_14!!.tag as String?)?.toInt ()?.let { doStuff(iv_14, it) }
        (iv_21!!.tag as String?)?.toInt ()?.let { doStuff(iv_21, it) }
        (iv_22!!.tag as String?)?.toInt ()?.let { doStuff(iv_22, it) }
        (iv_23!!.tag as String?)?.toInt ()?.let { doStuff(iv_23, it) }
        (iv_24!!.tag as String?)?.toInt ()?.let { doStuff(iv_24, it) }
        (iv_31!!.tag as String?)?.toInt ()?.let { doStuff(iv_31, it) }
        (iv_32!!.tag as String?)?.toInt ()?.let { doStuff(iv_32, it) }
        (iv_33!!.tag as String?)?.toInt ()?.let { doStuff(iv_33, it) }
        (iv_34!!.tag as String?)?.toInt ()?.let { doStuff(iv_34, it) }
        iv_11!!.setOnClickListener { view ->
            //   iv_11.setTag("0");
            val theCard = (view.tag as String).toInt () // беру из вьюшки тэг
            clickMethod(iv_11, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_12!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt ()
            clickMethod(iv_12, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_13!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt ()
            clickMethod(iv_13, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_14!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt ()
            clickMethod(iv_14, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_21!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt ()
            clickMethod(iv_21, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_22!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt ()
            clickMethod(iv_22, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_23!!.setOnClickListener { view ->
            val theCard = (view.tag as String).toInt()
            clickMethod(iv_23, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_24!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt ()
            clickMethod(iv_24, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_31!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt()
            clickMethod(iv_31, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_32!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt()
            clickMethod(iv_32, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_33!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt()
            clickMethod(iv_33, theCard)
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
        iv_34!!.setOnClickListener { view ->
            val theCard: Int = (view.tag as String).toInt()
            clickMethod(iv_34, theCard) // тут просто ссетит картинки
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.image_click))
        }
    }

    private fun clickMethod(iv: ImageView?, card: Int) {
        if (cardNumber == 1) {
            firstCard = cardArray[card] // присваиваем первой карте номер из массива по тэгу
            if (firstCard in 201..299) {
                firstCard -= 100
            } else if (firstCard > 300) {
                firstCard -= 200
            }
            cardNumber = 2
            clickedFirst = card //тэгу присваивает
            iv!!.isEnabled = false
        } else if (cardNumber == 2) {
            secondCard = cardArray[card]
            if (secondCard in 201..299) {
                secondCard -= 100
            }
            if (secondCard > 300) {
                secondCard -= 200
            }
            // вписываем количество попыток
            cardNumber = 3
            clickedSecond = card
            iv!!.isEnabled = false
        } else if (cardNumber == 3) {
            thirdCard = cardArray[card]
            if (thirdCard in 201..299) {
                thirdCard -= 100
            }
            if (thirdCard > 300) {
                thirdCard -= 200
            }
            cardNumber = 1
            clickedThird = card
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
            }, 700)
        }
    }

    private fun doStuff(iv: ImageView?, card: Int) { //set the correct image to the imageview
        if (cardArray[card] == 101) { // 0
            iv!!.setImageResource(image101)
        } else if (cardArray[card] == 102) { // 1
            iv!!.setImageResource(image102)
        } else if (cardArray[card] == 103) {
            iv!!.setImageResource(image103)
        } else if (cardArray[card] == 104) {
            iv!!.setImageResource(image104)
        } else if (cardArray[card] == 201) {
            iv!!.setImageResource(image105)
        } else if (cardArray[card] == 202) {
            iv!!.setImageResource(image106)
        } else if (cardArray[card] == 203) {
            iv!!.setImageResource(image201)
        } else if (cardArray[card] == 204) {
            iv!!.setImageResource(image202)
        } else if (cardArray[card] == 301) {
            iv!!.setImageResource(image203)
        } else if (cardArray[card] == 302) {
            iv!!.setImageResource(image204)
        } else if (cardArray[card] == 303) {
            iv!!.setImageResource(image205)
        } else if (cardArray[card] == 304) { // на 11 вьюшку нажимаю
            iv!!.setImageResource(image206)
        }
    }

    private fun calculate() { // if images are equal remove them and add point
        mQuantOfTries++
        mQuant!!.text = mQuantOfTries.toString()
        if (firstCard == secondCard && thirdCard == firstCard) { // clickedFirst = card// tv_p2.setText("p2  "+cpuPoints);
            //  tv_p1.setText("p1  "+playerPoints);
            when (clickedFirst) {
                0 -> { iv_11!!.visibility = View.INVISIBLE }
                1 -> { iv_12!!.visibility = View.INVISIBLE }
                2 -> { iv_13!!.visibility = View.INVISIBLE }
                3 -> { iv_14!!.visibility = View.INVISIBLE }
                4 -> { iv_21!!.visibility = View.INVISIBLE }
                5 -> { iv_22!!.visibility = View.INVISIBLE }
                6 -> { iv_23!!.visibility = View.INVISIBLE }
                7 -> { iv_24!!.visibility = View.INVISIBLE }
                8 -> { iv_31!!.visibility = View.INVISIBLE }
                9 -> { iv_32!!.visibility = View.INVISIBLE }
                10 -> { iv_33!!.visibility = View.INVISIBLE }
                11 -> { iv_34!!.visibility = View.INVISIBLE }
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
            }// tv_p2.setText("p2  "+cpuPoints);
            //  tv_p1.setText("p1  "+playerPoints);
            when (clickedThird) {
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
            if (turn == 1) {
                turn = 2
                //  tv_p1.setTextColor(Color.GRAY);
            } else if (turn == 2) {
                turn = 1
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
            val alertDialog = AlertDialog.Builder(this@ActivityTwo)
            alertDialog.setMessage("Игра закончена ").setCancelable(false)
                    .setPositiveButton("Новая") { dialogInterface, i ->
                        val intent = Intent(applicationContext, ActivityTwo::class.java)
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
        //int number=random.nextInt(2- 0+ 1) + 0;
        val number = 2
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