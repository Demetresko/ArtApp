//package org;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.appcompat.widget.Toolbar;
//
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewTreeObserver;
//import android.widget.TextView;
//
//import com.google.firebase.crash.FirebaseCrash;
//import com.mikepenz.materialdrawer.AccountHeader;
//import com.mikepenz.materialdrawer.AccountHeaderBuilder;
//import com.mikepenz.materialdrawer.Drawer;
//import com.mikepenz.materialdrawer.DrawerBuilder;
//import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
//import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
//
//import org.com.art.application.DateAdapter;
//import org.com.art.application.LabelerDate;
//import org.wangjie.wheelview.R;
//import org.com.art.application.game_activity.SixStep;
//import org.com.art.application.sample.CenterZoomLayoutManager;
//
//import java.util.ArrayList;
//
///**
// * Created by Wonka on 21.08.2017.
// */
//
//public class WheelViewAlternative extends AppCompatActivity {
//
//    private static final String TAG = WheelViewAlternative.class.getSimpleName();
//
//    private int prevCenterPos;   // Keep track the previous pos to dehighlight
//
//    public float firstItemWidthDate;
//    public float paddingDate;
//    public float itemWidthDate;
//    public int allPixelsDate;
//    public int finalWidthDate;
//    private DateAdapter dateAdapter;
//    private ArrayList<LabelerDate> labelerDates = new ArrayList<>();
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.alternative_timeline);
//        getRecyclerviewDate();
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(2).withName("Малевич Game").withSelectable(false).withTextColor(Color.BLACK);
//        FirebaseCrash.logcat(Log.INFO, "WheelView", "  WTF  ");
//
//        AccountHeader headerResult = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.i_007)
//                .build();
//
////Now create your drawer and pass the AccountHeader.Result
//        //additional Drawer setup as shown above
//        Drawer result = new DrawerBuilder()
//                .withActivity(this)
//                .withToolbar(toolbar)
//                .addDrawerItems(
//                        //item1,
//                        //new DividerDrawerItem(),
//                        //  item2,
//                        // item3,
//                        item4
//                        // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
//                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        if (position == 1) {
//                            Intent intent = new Intent(view.getContext(), SixStep.class);
//                            startActivity(intent);
//                        }
//                        return false;
//                    }
//                }).withSelectedItem(-1)//эта штука селекшн дефаултный убирает
//                .build();
//    }
//
//    public void getRecyclerviewDate() {
//        final RecyclerView recyclerViewDate = findViewById(R.id.rv_tasks_date);// RecyclerView installing
//
//        ViewTreeObserver vtoDate = recyclerViewDate.getViewTreeObserver();
//        vtoDate.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                recyclerViewDate.getViewTreeObserver().removeOnPreDrawListener(this);
//                finalWidthDate = recyclerViewDate.getMeasuredHeight();
//                itemWidthDate = getResources().getDimension(R.dimen.item_dob);
//                paddingDate = (finalWidthDate - itemWidthDate) / 3; // Отступы просчитываем
//                firstItemWidthDate = paddingDate;
//                allPixelsDate = 0;
//
//                final CenterZoomLayoutManager dateLayoutManager = new CenterZoomLayoutManager(getApplicationContext());
//                dateLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                recyclerViewDate.setLayoutManager(dateLayoutManager);//  Manager adding
//
//                recyclerViewDate.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                        super.onScrolled(recyclerView, dx, dy);
//
//                        int center = recyclerViewDate.getHeight() / 2;
//
//                        View centerView = recyclerViewDate.findChildViewUnder(center, recyclerViewDate.getRight());
//
//                        int centerPos = recyclerViewDate.getChildAdapterPosition(centerView); // номер центрального итема
//
//                        if (prevCenterPos != centerPos) {
//                            // dehighlight the previously highlighted view
//                            View prevView = recyclerViewDate.getLayoutManager().findViewByPosition(prevCenterPos);// 0
//                            //    View prevView = recyclerViewDate.getLayoutManager().findViewByPosition(prevCenterPos);//
//                            if (prevView != null) {
//                                TextView button = prevView.findViewById(R.id.txt_date);
//                                button.setTextColor(Color.GRAY);
//
//                                TextView mDateTextView = prevView.findViewById(R.id.textDate);
//                                mDateTextView.setTextColor(Color.GRAY);
//                            }
//                            // highlight view in the middle
//                            if (centerView != null) {
//                                TextView button = centerView.findViewById(R.id.txt_date);
//                                button.setTextColor(Color.BLACK);
//
//                                TextView mDateTextView = centerView.findViewById(R.id.textDate);
//                                mDateTextView.setTextColor(Color.BLACK);
//                            }
//                            prevCenterPos = centerPos;
//                        }
//                    }
//                });
//
//                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
//                    new Handler().postDelayed(
//                            new Runnable() {
//                                @Override
//                                public void run() {
//                                    recyclerViewDate.scrollToPosition(dateAdapter.getItemCount() - 4);
//                                }
//                            }, 1);
//
//                } else {
//                    new Handler().postDelayed(
//                            new Runnable() {
//                                @Override
//                                public void run() {
//                                    recyclerViewDate.smoothScrollToPosition(dateAdapter.getItemCount() - 4);
//                                }
//                            }, 1);
//
//                }
//                if (labelerDates == null) {
//                    labelerDates = new ArrayList<>();
//                }
//                genLabelerDate();
//                dateAdapter = new DateAdapter(labelerDates, (int) firstItemWidthDate);
//                recyclerViewDate.setAdapter(dateAdapter);
//                dateAdapter.setSelecteditem(dateAdapter.getItemCount() - 1);
//                return true;
//            }
//        });
//    }
//
//    private void genLabelerDate() {
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
//            for (int i = 0; i < 13; i++) {
//                LabelerDate labelerDate = new LabelerDate();
//
//                labelerDates.add(labelerDate);
//
//                if (i == 3 || i == 12) {
//                    labelerDate.setType(DateAdapter.VIEW_TYPE_PADDING);
//                } else {
//                    labelerDate.setType(DateAdapter.VIEW_TYPE_ITEM);
//                }
//            }
//
//        } else { //заполнение
//            for (int i = 0; i < 14; i++) {
//                LabelerDate labelerDate = new LabelerDate();
//
//                labelerDates.add(labelerDate);
//
//                if (i == 3 || i == 13) {
//                    labelerDate.setType(DateAdapter.VIEW_TYPE_PADDING);
//                } else {
//                    labelerDate.setType(DateAdapter.VIEW_TYPE_ITEM);
//                }
//            }
//        }
//    }
//
//    /* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest timeline_recyclerview_item*/
//    private void calculatePositionAndScrollDate(RecyclerView recyclerView) {
//        int expectedPositionDate = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate);
//
//        if (expectedPositionDate == -1) {
//            expectedPositionDate = 0;
//        } else if (expectedPositionDate >= recyclerView.getAdapter().getItemCount() - 2) {
//            expectedPositionDate--;
//        }
//        scrollListToPositionDate(recyclerView, expectedPositionDate);
//
//    }
//
//    /* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest timeline_recyclerview_item*/
//    private void scrollListToPositionDate(RecyclerView recyclerView, int expectedPositionDate) {
//        float targetScrollPosDate = expectedPositionDate * itemWidthDate + firstItemWidthDate - paddingDate;
//        float missingPxDate = targetScrollPosDate - allPixelsDate;
//        if (missingPxDate != 0) {
//            recyclerView.smoothScrollBy((int) missingPxDate, 0);
//        }
//        setDateValue();
//    }
//
//    //
//    private void setDateValue() {
//        int expectedPositionDateColor = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate);
//        int setColorDate = expectedPositionDateColor + 1;
////        set color here
//        dateAdapter.setSelecteditem(setColorDate);
//    }
//
//
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item1 clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
