package org.com.art.application.direction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.WheelViewAlternative;
import org.com.art.application.game_activity.SixStep;
import org.wangjie.wheelview.R;

import java.util.ArrayList;

public class ActivityMainDirection extends AppCompatActivity {
    MaterialDialog dialog;
    ArrayList<Direction> gArray;
    TextView mConception,mDescription,mTools,mDirectionTitle,mTitleDirection,mTitleConception,mTitleMethods;
    ProgressBar progressBar;

    ImageView mFirstImage;
    LinearLayoutManager layoutManager,layoutManager2 ;

    private NavigationView mNavigationView;

    DatabaseReference databaseReference;

    int number,i;

    RecyclerView recycler_artist;
    RecyclerView recycler_pictures;

    TextView mTextView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.scroll_main_direction);

        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);

         progressBar= (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);







        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK);
      //  SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_paintings).withSelectable(false).withTextColor(Color.BLACK);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.malevichGame).withSelectable(false).withTextColor(Color.BLACK);

        //  item1.withName("A new name for this drawerItem").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.BLACK));
//notify the drawer about the updated element. it will take care about everything else1




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
                .withToolbar(mToolbar)
                .addDrawerItems(
                        item1,
                        //new DividerDrawerItem(),
                       // item2,
                        item3
                        // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withActionBarDrawerToggle(true).withActionBarDrawerToggleAnimated(true).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if(position==1){
                            Intent intent=new Intent(view.getContext(), WheelViewAlternative.class);
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

//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);






        FirebaseDatabase database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();


        gArray=new ArrayList<>();


        recycler_artist= (RecyclerView) findViewById(R.id.horizontal_artist_list);
        recycler_pictures=(RecyclerView) findViewById(R.id.horizontal_list);

        mDescription= (TextView) findViewById(R.id.mDescription);
        mConception= (TextView) findViewById(R.id.mConception);
        mTools= (TextView) findViewById(R.id.mToolsText);

        mTitleConception= (TextView) findViewById(R.id.titleConception);
        mTitleDirection= (TextView) findViewById(R.id.titleDescription);
        mTitleMethods= (TextView) findViewById(R.id.titleMethods);

        mFirstImage=(ImageView) findViewById(R.id.first_image);
        mDirectionTitle=(TextView) findViewById(R.id.main_title);

        View targetView = findViewById(R.id.mConceptionLayout);
        targetView.getParent().requestChildFocus(targetView,targetView);

     mTitleConception.setVisibility(View.INVISIBLE);
        mTitleDirection.setVisibility(View.INVISIBLE);
        mTitleMethods.setVisibility(View.INVISIBLE);




        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recycler_artist.setHasFixedSize(false);
        recycler_artist.setLayoutManager(layoutManager);

        layoutManager2=new LinearLayoutManager(this);
        layoutManager2.scrollToPositionWithOffset(2,20);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recycler_pictures.setHasFixedSize(true);

        layoutManager2.scrollToPositionWithOffset(2,20);

        recycler_pictures.setLayoutManager(layoutManager2);

        FirebaseCrash.logcat(Log.INFO,"Activity Direction","  WTF  ");



        databaseReference.addChildEventListener(new com.google.firebase.database.ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {




int count=0;


                Log.e("children tag",(dataSnapshot).toString());

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {

count++;
                    if(count >= dataSnapshot.getChildrenCount()){
                        //stop progress bar here

                        progressBar.setVisibility(View.INVISIBLE);

                        mTitleConception.setVisibility(View.VISIBLE);
                        mTitleDirection.setVisibility(View.VISIBLE);
                        mTitleMethods.setVisibility(View.VISIBLE);

                    }



                    //Log.e("child tag",child.toString());

                        Direction direction = child.getValue(Direction.class);
                    gArray.add(direction);
                    Log.e("new_dir_added", direction.toString());

                }
                Intent intent=getIntent();
                String srt=intent.getStringExtra("Number_in_list");

                number=Integer.parseInt(srt);

                Log.e("arrSize", gArray.size() +"   " + number)
                ;

                Log.e("url image erre",(gArray.get(number).toString()));
                Log.e("image_url", gArray.get(number).getMUrlImage());

                String [] mPictureList=gArray.get(number).getMUrlImage().split(",");

                String [] mArtistListImage=gArray.get(number).getMArtistListImage().split(",");



               // recycler_artist.setAdapter(new ArtistAdapter(gArray,number));// список художников в Направлении
              //  recycler_pictures.setAdapter(new HorizontalRVAdapter(gArray,number)); // список картин в направлении

                recycler_artist.setAdapter(new ArtistAdapter(mArtistListImage,number));
                recycler_pictures.setAdapter(new HorizontalRVAdapter(mPictureList,number));



                mConception.setText(gArray.get(number).getConcept());
                mDescription.setText(gArray.get(number).getDesc());
                mTools.setText(gArray.get(number).getTechnique());
                mDirectionTitle.setText(gArray.get(number).getName());

                Glide.with(getApplicationContext())
                        .load(gArray.get(number).getImage())
                        .placeholder(R.drawable.rama)
                        .into(mFirstImage);





            }




            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}



