package org.com.art.application.game_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.wangjie.wheelview.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Wonka on 14.08.2017.
 */

public class ActivityTwo extends AppCompatActivity {

    int mQuantOfTries;

    int[][] mMainArray = {

            { R.drawable.ic_image101, R.drawable.ic_image102, R.drawable.ic_image103, R.drawable.ic_image104, R.drawable.ic_image105, R.drawable.ic_image106,
                    R.drawable.ic_image201, R.drawable.ic_image202, R.drawable.ic_image203, R.drawable.ic_image204, R.drawable.ic_image205, R.drawable.ic_image206
            },

            { R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
                    R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12,},

            { R.drawable.v1, R.drawable.v4, R.drawable.v7, R.drawable.v10,
                    R.drawable.v2, R.drawable.v5, R.drawable.v8, R.drawable.v11,
                    R.drawable.v3, R.drawable.v6, R.drawable.v9, R.drawable.v12

            }
    };


    TextView tv_p1, tv_p2;

    ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_23, iv_22, iv_24, iv_31, iv_32, iv_33, iv_34;
    //array for the images

    Integer[] cardArray = {101, 102, 103, 104, 201, 202, 203, 204, 301, 302, 303, 304};

    TextView mQuant;


    //actual images то куда дровербл пихается
    int image101, image102, image103, image104, image105, image106,
            image201, image202, image203, image204, image205, image206;


    int firstCard, secondCard, thirdCard, fourthCard;
    int clickedFirst, clickedSecond, clickedThird, clickedFourth;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0, cpuPoints = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity2);

        mQuantOfTries = 0;

//        tv_p1= (TextView) findViewById(R.id.tv_p1);
//        tv_p2= (TextView) findViewById(R.id.tv_p2);
        mQuant = (TextView) findViewById(R.id.mQuantity);
        mQuant.setText(String.valueOf(mQuantOfTries));


        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_14 = (ImageView) findViewById(R.id.iv_14);

        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_24 = (ImageView) findViewById(R.id.iv_24);

        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        iv_34 = (ImageView) findViewById(R.id.iv_34);


        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");

        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");

        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");


        //load card images

        frontOfCardsResources();

        //shuffle images
        Collections.shuffle(Arrays.asList(cardArray));  // Integer [] cardArray={101,102,103,104,105,106,  201,202,203,204,205,206};

// выставляем картины

        doStuff(iv_11, Integer.parseInt((String) iv_11.getTag()));
        doStuff(iv_12, Integer.parseInt((String) iv_12.getTag()));
        doStuff(iv_13, Integer.parseInt((String) iv_13.getTag()));
        doStuff(iv_14, Integer.parseInt((String) iv_14.getTag()));


        doStuff(iv_21, Integer.parseInt((String) iv_21.getTag()));
        doStuff(iv_22, Integer.parseInt((String) iv_22.getTag()));
        doStuff(iv_23, Integer.parseInt((String) iv_23.getTag()));
        doStuff(iv_24, Integer.parseInt((String) iv_24.getTag()));


        doStuff(iv_31, Integer.parseInt((String) iv_31.getTag()));
        doStuff(iv_32, Integer.parseInt((String) iv_32.getTag()));
        doStuff(iv_33, Integer.parseInt((String) iv_33.getTag()));
        doStuff(iv_34, Integer.parseInt((String) iv_34.getTag()));


        iv_11.setOnClickListener(new View.OnClickListener() {  //   iv_11.setTag("0");
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());// беру из вьюшки тэг
                clickMethod(iv_11, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_12, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_13, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });


        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_14, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_21, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_22, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_23, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_24, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));


            }
        });


        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_31, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));

            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_32, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));

            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_33, theCard);
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));

            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                clickMethod(iv_34, theCard); // тут просто ссетит картинки
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.image_click));

            }
        });
    }


    private void clickMethod(ImageView iv, int card) {


        if (cardNumber == 1) {
            firstCard = cardArray[card];// присваиваем первой карте номер из массива по тэгу

            if (firstCard > 200 & firstCard < 300) {
                firstCard = firstCard - 100;
            } else if (firstCard > 300) {
                firstCard = firstCard - 200;
            }

            cardNumber = 2;

            clickedFirst = card;//тэгу присваивает

            iv.setEnabled(false);

        } else if (cardNumber == 2) {

            secondCard = cardArray[card];

            if (secondCard > 200 & secondCard < 300) {
                secondCard = secondCard - 100;
            }
            if (secondCard > 300) {
                secondCard = secondCard - 200;
            }

            // вписываем количество попыток

            cardNumber = 3;
            clickedSecond = card;

            iv.setEnabled(false);


        } else if (cardNumber == 3) {

            thirdCard = cardArray[card];

            if (thirdCard > 200 & thirdCard < 300) {
                thirdCard = thirdCard - 100;
            }
            if (thirdCard > 300) {
                thirdCard = thirdCard - 200;
            }


            cardNumber = 1;


            clickedThird = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);

            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);

            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if selected images are equal
                    calculate();
                }
            }, 700);

        }


    }


    private void doStuff(ImageView iv, int card) {


        //set the correct image to the imageview

        if (cardArray[card] == 101) {// 0
            iv.setImageResource(image101);
        } else if (cardArray[card] == 102) { // 1
            iv.setImageResource(image102);
        } else if (cardArray[card] == 103) {
            iv.setImageResource(image103);
        } else if (cardArray[card] == 104) {
            iv.setImageResource(image104);
        } else if (cardArray[card] == 201) {
            iv.setImageResource(image105);
        } else if (cardArray[card] == 202) {
            iv.setImageResource(image106);
        } else if (cardArray[card] == 203) {
            iv.setImageResource(image201);
        } else if (cardArray[card] == 204) {
            iv.setImageResource(image202);


        } else if (cardArray[card] == 301) {
            iv.setImageResource(image203);
        } else if (cardArray[card] == 302) {
            iv.setImageResource(image204);
        } else if (cardArray[card] == 303) {
            iv.setImageResource(image205);
        } else if (cardArray[card] == 304) { // на 11 вьюшку нажимаю
            iv.setImageResource(image206);
        }
    }


    private void calculate() {
        // if images are equal remove them and add point

        mQuantOfTries++;
        mQuant.setText(String.valueOf(mQuantOfTries));

        if (firstCard == secondCard & thirdCard == firstCard) {

            // clickedFirst = card
            if (clickedFirst == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_14.setVisibility(View.INVISIBLE);

            } else if (clickedFirst == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv_24.setVisibility(View.INVISIBLE);


            } else if (clickedFirst == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }


            if (clickedSecond == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 3) {
                iv_14.setVisibility(View.INVISIBLE);


            } else if (clickedSecond == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 7) {
                iv_24.setVisibility(View.INVISIBLE);


            } else if (clickedSecond == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }


            if (clickedThird == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 3) {
                iv_14.setVisibility(View.INVISIBLE);


            } else if (clickedThird == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedThird == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }

            //add   points
            if (turn == 1) {
                playerPoints++;
                //  tv_p1.setText("p1  "+playerPoints);
            } else if (turn == 2) {
                cpuPoints++;
                // tv_p2.setText("p2  "+cpuPoints);
            }
        } else {
//            iv_11.setImageResource(R.drawable.card_cover);
//            iv_12.setImageResource(R.drawable.card_cover);
//            iv_13.setImageResource(R.drawable.card_cover);
//            iv_14.setImageResource(R.drawable.card_cover);
//
//
//            iv_21.setImageResource(R.drawable.card_cover);
//            iv_22.setImageResource(R.drawable.card_cover);
//            iv_23.setImageResource(R.drawable.card_cover);
//            iv_24.setImageResource(R.drawable.card_cover);
//
//
//            iv_31.setImageResource(R.drawable.card_cover);
//            iv_32.setImageResource(R.drawable.card_cover);
//            iv_33.setImageResource(R.drawable.card_cover);
//            iv_34.setImageResource(R.drawable.card_cover);
//

            //change the player turn

            if (turn == 1) {
                turn = 2;
                //  tv_p1.setTextColor(Color.GRAY);
                //tv_p2.setTextColor(Color.BLACK);
            } else if (turn == 2) {
                turn = 1;
                // tv_p2.setTextColor(Color.GRAY);
                //tv_p1.setTextColor(Color.BLACK);
            }
        }


        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);

        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);

        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        checkEnd();


    }

    private void checkEnd() {
        if (iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&

                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE &&

                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_34.getVisibility() == View.INVISIBLE
        ) {


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityTwo.this);
            alertDialog.setMessage("Игра закончена ").setCancelable(false)
                    .setPositiveButton("Новая", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
                            startActivity(intent);
                            finish();

                        }
                    }).setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();

            Button buttonPositive = alertDialog1.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.black));
            Button buttonNegative = alertDialog1.getButton(DialogInterface.BUTTON_NEGATIVE);
            buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
    }


    //Определяются картины и художники
    private void frontOfCardsResources() {
        Random random = new Random();


        //int number=random.nextInt(2- 0+ 1) + 0;
        int number = 2;

        image101 = mMainArray[number][0];
        image102 = mMainArray[number][1];
        image103 = mMainArray[number][2];
        image104 = mMainArray[number][3];
        image105 = mMainArray[number][4];
        image106 = mMainArray[number][5];

        image201 = mMainArray[number][6];
        image202 = mMainArray[number][7];

        image203 = mMainArray[number][8];

        image204 = mMainArray[number][9];
        image205 = mMainArray[number][10];
        image206 = mMainArray[number][11];
    }
}
