package org.com.art.application.game_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.wangjie.wheelview.R;
import org.com.art.application.sample.TimelineActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Wonka on 14.08.2017.
 */

public class SixStep  extends AppCompatActivity {

    int mQuantOfTries;

    int [] []  mMainArray={


            {R.drawable.ic_image101,R.drawable.ic_image102,R.drawable.ic_image103,R.drawable.ic_image104,R.drawable.ic_image105,R.drawable.ic_image106,
                    R.drawable.ic_image201,R.drawable.ic_image202,R.drawable.ic_image203,R.drawable.ic_image204,R.drawable.ic_image205,R.drawable.ic_image206
            },

            {R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,
                    R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,R.drawable.b11,R.drawable.b12,},


            {R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,
                    R.drawable.i7,R.drawable.i8,R.drawable.i9,R.drawable.i10,R.drawable.i11,R.drawable.i12}

    };

    TextView tv_p1,tv_p2;

    ImageView iv_11,iv_12,iv_13,iv_14,  iv_21,iv_23,iv_22,iv_24,   iv_31,iv_32,iv_33,iv_34;
    //array for the images

    Integer [] cardArray={101,102,103,104,105,106,  201,202,203,204,205,206};

    TextView mQuant;


    //actual images то куда дровербл пихается
    int image101,image102,image103,image104,image105,image106,
            image201 ,image202,image203,image204,image205,image206;

    int firstCard,secondCard;
    int clickedFirst,clickedSecond;
    int cardNumber=1;

    int turn=1;
    int playerPoints=0,cpuPoints=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);


        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK);
        //SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_paintings).withSelectable(false).withTextColor(Color.BLACK);
       SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.malevichGame).withSelectable(false).withTextColor(Color.BLACK);

        //  item1.withName("A new name for this drawerItem").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.BLACK));
//notify the drawer about the updated element. it will take care about everything else




//create the drawer and remember the `Drawer` result object


        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.i_007)

                .build();

//Now create your drawer and pass the AccountHeader.Result

        //additional Drawer setup as shown above

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        //new DividerDrawerItem(),
                  //      item2,
                       item3
                        // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if(position==1){
                            Intent intent=new Intent(view.getContext(), TimelineActivity.class);
                            startActivity(intent);
                        }

                        if(position==2){
                            Intent intent=new Intent(view.getContext(), SixStep.class);
                            startActivity(intent);
                        }
                        return false;
                    }
                }).withSelectedItem(-1)

                .build();









        mQuantOfTries=0;

//        tv_p1= (TextView) findViewById(R.id.tv_p1);
//        tv_p2= (TextView) findViewById(R.id.tv_p2);
        mQuant= (TextView) findViewById(R.id.mQuantity);
        mQuant.setText(String.valueOf(mQuantOfTries));




        iv_11= (ImageView) findViewById(R.id.iv_11);
        iv_12= (ImageView) findViewById(R.id.iv_12);
        iv_13= (ImageView) findViewById(R.id.iv_13);
        iv_14= (ImageView) findViewById(R.id.iv_14);

        iv_21= (ImageView) findViewById(R.id.iv_21);
        iv_22= (ImageView) findViewById(R.id.iv_22);
        iv_23= (ImageView) findViewById(R.id.iv_23);
        iv_24= (ImageView) findViewById(R.id.iv_24);

        iv_31= (ImageView) findViewById(R.id.iv_31);
        iv_32= (ImageView) findViewById(R.id.iv_32);
        iv_33= (ImageView) findViewById(R.id.iv_33);
        iv_34= (ImageView) findViewById(R.id.iv_34);



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
        Collections.shuffle(Arrays.asList(cardArray));

//        tv_p2.setTextColor(Color.GRAY);



        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_11,theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_12,theCard);

            }
        });
        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_13,theCard);

            }
        });
        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_14,theCard);

            }
        });




        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_21,theCard);

            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_22,theCard);

            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_23,theCard);

            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_24,theCard);

            }
        });




        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_31,theCard);

            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_32,theCard);

            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_33,theCard);

            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_34,theCard);

            }
        });

    }




    private void doStuff(ImageView iv,int card){
        //set the correct image to the imageview

        if(cardArray[card]==101){
            iv.setImageResource(image101);
        }else if(cardArray[card]==102){
            iv.setImageResource(image102);
        } else if(cardArray[card]==103){
            iv.setImageResource(image103);
        }else if(cardArray[card]==104){
            iv.setImageResource(image104);
        }else if(cardArray[card]==105){
            iv.setImageResource(image105);
        }else if(cardArray[card]==106){
            iv.setImageResource(image106);
        }

        else if(cardArray[card]==201){
            iv.setImageResource(image201);
        }else if(cardArray[card]==202){
            iv.setImageResource(image202);
        }else if(cardArray[card]==203){
            iv.setImageResource(image203);
        }else if(cardArray[card]==204){
            iv.setImageResource(image204);
        }else if(cardArray[card]==205){
            iv.setImageResource(image205);
        }else if(cardArray[card]==206){
            iv.setImageResource(image206);
        }


        //check which image is selected and save it to temporarily variable

        if(cardNumber==1){
            firstCard=cardArray[card];
            if(firstCard>200){
                firstCard=firstCard-100;
            }
            cardNumber=2;
            clickedFirst=card;

            iv.setEnabled(false);






        }

        else if(cardNumber==2){
            secondCard=cardArray[card];
            if(secondCard>200){
                secondCard=secondCard-100;
            }





            // вписываем количество попыток

            cardNumber=1;
            clickedSecond=card;

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

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if selected images are equal
                    calculate();
                }
            },1100);
        }



    }






    private void calculate() {
        // if images are equal remove them and add point

        mQuantOfTries++;
        mQuant.setText(String.valueOf(mQuantOfTries));

        if (firstCard == secondCard) {


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




            //add   points
            if(turn==1){
                playerPoints++;
                //  tv_p1.setText("p1  "+playerPoints);
            } else if(turn==2){
                cpuPoints++;
                // tv_p2.setText("p2  "+cpuPoints);
            }
        }

        else {
            iv_11.setImageResource(R.drawable.card_cover);
            iv_12.setImageResource(R.drawable.card_cover);
            iv_13.setImageResource(R.drawable.card_cover);
            iv_14.setImageResource(R.drawable.card_cover);


            iv_21.setImageResource(R.drawable.card_cover);
            iv_22.setImageResource(R.drawable.card_cover);
            iv_23.setImageResource(R.drawable.card_cover);
            iv_24.setImageResource(R.drawable.card_cover);


            iv_31.setImageResource(R.drawable.card_cover);
            iv_32.setImageResource(R.drawable.card_cover);
            iv_33.setImageResource(R.drawable.card_cover);
            iv_34.setImageResource(R.drawable.card_cover);


            //change the player turn

            if(turn==1){
                turn=2;
                //  tv_p1.setTextColor(Color.GRAY);
                //tv_p2.setTextColor(Color.BLACK);
            } else if(turn==2) {
                turn=1;
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


    private void checkEnd(){
        if(iv_11.getVisibility()==View.INVISIBLE &&
                iv_12.getVisibility()==View.INVISIBLE &&
                iv_13.getVisibility()==View.INVISIBLE &&
                iv_14.getVisibility()==View.INVISIBLE &&

                iv_21.getVisibility()==View.INVISIBLE &&
                iv_22.getVisibility()==View.INVISIBLE &&
                iv_23.getVisibility()==View.INVISIBLE &&
                iv_24.getVisibility()==View.INVISIBLE &&

                iv_31.getVisibility()==View.INVISIBLE &&
                iv_32.getVisibility()==View.INVISIBLE &&
                iv_33.getVisibility()==View.INVISIBLE &&
                iv_34.getVisibility()==View.INVISIBLE
                ){
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(SixStep.this);
            alertDialog.setMessage("Игра закончена ").setCancelable(false)
                    .setPositiveButton("Новая", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(getApplicationContext(),SixStep.class);
                            startActivity(intent);
                            finish();

                        }
                    }).setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });





            AlertDialog alertDialog1=alertDialog.create();
            alertDialog1.show();

            Button buttonPositive = alertDialog1.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonPositive.setTextColor(ContextCompat.getColor(this,R.color.black));
            Button buttonNegative = alertDialog1.getButton(DialogInterface.BUTTON_NEGATIVE);
            buttonNegative.setTextColor(ContextCompat.getColor(this,R.color.black));

        }

    }



//Определяются картины и художники

    private void frontOfCardsResources() {
        Random random = new Random();


        int number=random.nextInt(2- 0+ 1) + 0;

        image101=mMainArray[number][0];
        image102=mMainArray[number][1];
        image103=mMainArray[number][2];
        image104=mMainArray[number][3];
        image105=mMainArray[number][4];
        image106=mMainArray[number][5];

        image201=mMainArray[number][6];
        image202=mMainArray[number][7];

        image203=mMainArray[number][8];

        image204=mMainArray[number][9];
        image205=mMainArray[number][10];
        image206=mMainArray[number][11];
    }
}
