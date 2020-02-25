package org;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.com.art.application.direction.ActivityMainDirection;
import org.wangjie.wheelview.R;
import org.com.art.application.game_activity.SixStep;
import org.com.art.application.sample.CenterZoomLayoutManager;

import java.util.ArrayList;

/**
 * Created by Wonka on 21.08.2017.
 */

public class WheelViewAlternative extends Activity {

    private static final String TAG = WheelViewAlternative.class.getSimpleName();

    private int prevCenterPos;   // Keep track the previous pos to dehighlight
    private int prevCenterPos1;

    public float firstItemWidthDate;
    public float paddingDate;
    public float itemWidthDate;
    public int allPixelsDate;
    public int finalWidthDate;
    private DateAdapter dateAdapter;
    private ArrayList<LabelerDate> labelerDates = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alternative_timeline);
        getRecyclerviewDate();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        // PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK);
        //  SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_paintings).withTextColor(Color.BLACK);
        // SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.bookshelf).withTextColor(Color.BLACK);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(2).withName("Малевич Game").withSelectable(false).withTextColor(Color.BLACK);


        //  item1.withName("A new name for this drawerItem").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.BLACK));
//notify the drawer about the updated element. it will take care about everything else


        FirebaseCrash.logcat(Log.INFO, "WheelView", "  WTF  ");

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
                        //item1,
                        //new DividerDrawerItem(),
                        //  item2,
                        // item3,
                        item4
                        // new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                ).withTranslucentStatusBar(false).withAccountHeader(headerResult).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
//                        if(position==1){
//                            Intent intent=new Intent(view.getContext(), TimelineActivity.class);
//                            startActivity(intent);
//                        }

                        if (position == 1) {
                            Intent intent = new Intent(view.getContext(), SixStep.class);
                            startActivity(intent);

                        }
                        return false;
                    }
                }).withSelectedItem(-1)//эта штука селекшн дефаултный убирает

                .build();


    }


    public void getRecyclerviewDate() {


        final RecyclerView recyclerViewDate = (RecyclerView) findViewById(R.id.rv_tasks_date);// RecyclerView installing


//        if (recyclerViewDate != null) {
//            recyclerViewDate.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    setDateValue();
//                }
//            }, 300);
//
//


        ViewTreeObserver vtoDate = recyclerViewDate.getViewTreeObserver();
        vtoDate.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {


            @Override
            public boolean onPreDraw() {
                recyclerViewDate.getViewTreeObserver().removeOnPreDrawListener(this);
                finalWidthDate = recyclerViewDate.getMeasuredHeight();
                itemWidthDate = getResources().getDimension(R.dimen.item_dob);


                paddingDate = (finalWidthDate - itemWidthDate) / 3; // Отступы просчитываем


                firstItemWidthDate = paddingDate;
                allPixelsDate = 0;

//                final LinearLayoutManager dateLayoutManager = new LinearLayoutManager(getApplicationContext());
//                dateLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


                final CenterZoomLayoutManager dateLayoutManager = new CenterZoomLayoutManager(getApplicationContext());
                dateLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                recyclerViewDate.setLayoutManager(dateLayoutManager);//  Manager adding


                recyclerViewDate.addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        int center = recyclerViewDate.getHeight() / 2;

                        View centerView = recyclerViewDate.findChildViewUnder(center, recyclerViewDate.getRight());

                        int centerPos = recyclerViewDate.getChildAdapterPosition(centerView); // номер центрального итема

                        if (prevCenterPos != centerPos) {
                            // dehighlight the previously highlighted view


                            View prevView = recyclerViewDate.getLayoutManager().findViewByPosition(prevCenterPos);// 0
                            //    View prevView = recyclerViewDate.getLayoutManager().findViewByPosition(prevCenterPos);//


                            if (prevView != null) {
                                TextView button = prevView.findViewById(R.id.txt_date);
                                button.setTextColor(Color.GRAY);

                                TextView mDateTextView = prevView.findViewById(R.id.textDate);
                                mDateTextView.setTextColor(Color.GRAY);
                            }


                            // highlight view in the middle
                            if (centerView != null) {
                                TextView button = centerView.findViewById(R.id.txt_date);
                                button.setTextColor(Color.BLACK);

                                TextView mDateTextView = centerView.findViewById(R.id.textDate);
                                mDateTextView.setTextColor(Color.BLACK);
                            }

                            prevCenterPos = centerPos;
                        }


                    }


                });

                //  recyclerViewDate.getLayoutManager().scrollToPosition(5);


//                recyclerViewDate.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerViewDate.smoothScrollToPosition(dateAdapter.getItemCount()-5);
//                        setDateValue();
//                    }
//                }, 5000);

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    recyclerViewDate.scrollToPosition(dateAdapter.getItemCount() - 4);
                                }
                            }, 1);

                } else {
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    recyclerViewDate.smoothScrollToPosition(dateAdapter.getItemCount() - 4);
                                }
                            }, 1);

                }


//
//                        addOnScrollListener(new RecyclerView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                        super.onScrollStateChanged(recyclerView, newState);
//                        synchronized (this) {
//                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                                calculatePositionAndScrollDate(recyclerView);
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                        super.onScrolled(recyclerView, dx, dy);
//                        allPixelsDate += dx;
//                    }
//                });
                if (labelerDates == null) {
                    labelerDates = new ArrayList<>();
                }
                genLabelerDate();
                dateAdapter = new DateAdapter(labelerDates, (int) firstItemWidthDate);


                recyclerViewDate.setAdapter(dateAdapter);
                dateAdapter.setSelecteditem(dateAdapter.getItemCount() - 1);


                return true;
            }
        });

    }


    private void genLabelerDate() {


        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < 13; i++) {
                LabelerDate labelerDate = new LabelerDate();

                labelerDates.add(labelerDate);

                if (i == 3 || i == 12) {
                    labelerDate.setType(DateAdapter.VIEW_TYPE_PADDING);
                } else {
                    labelerDate.setType(DateAdapter.VIEW_TYPE_ITEM);
                }
            }

        } else { //заполнение
            for (int i = 0; i < 14; i++) {
                LabelerDate labelerDate = new LabelerDate();

                labelerDates.add(labelerDate);

                if (i == 3 || i == 13) {
                    labelerDate.setType(DateAdapter.VIEW_TYPE_PADDING);
                } else {
                    labelerDate.setType(DateAdapter.VIEW_TYPE_ITEM);
                }
            }
        }


    }




    /* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest timeline_recyclerview_item*/

    private void calculatePositionAndScrollDate(RecyclerView recyclerView) {
        int expectedPositionDate = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate);

        if (expectedPositionDate == -1) {
            expectedPositionDate = 0;
        } else if (expectedPositionDate >= recyclerView.getAdapter().getItemCount() - 2) {
            expectedPositionDate--;
        }
        scrollListToPositionDate(recyclerView, expectedPositionDate);

    }


    /* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest timeline_recyclerview_item*/
    private void scrollListToPositionDate(RecyclerView recyclerView, int expectedPositionDate) {
        float targetScrollPosDate = expectedPositionDate * itemWidthDate + firstItemWidthDate - paddingDate;
        float missingPxDate = targetScrollPosDate - allPixelsDate;
        if (missingPxDate != 0) {
            recyclerView.smoothScrollBy((int) missingPxDate, 0);
        }
        setDateValue();
    }


    //
    private void setDateValue() {
        int expectedPositionDateColor = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate);
        int setColorDate = expectedPositionDateColor + 1;
//        set color here
        dateAdapter.setSelecteditem(setColorDate);
    }


    public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> implements View.OnClickListener {
        private ArrayList<LabelerDate> dateDataList;

        private Context mContext;


        private static final int VIEW_TYPE_PADDING = 1;
        private static final int VIEW_TYPE_ITEM = 2;
        private int paddingWidthDate = 0;

        private int selectedItem = -1;

        public DateAdapter(ArrayList<LabelerDate> dateData, int paddingWidthDate) {
            this.dateDataList = dateData;
            this.paddingWidthDate = paddingWidthDate;

        }


        @Override
        public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_recyclerview_item,
                        parent, false);
                return new DateViewHolder(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_recyclerview_item,
                        parent, false);

//                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
//                layoutParams.height = paddingWidthDate;
//                view.setLayoutParams(layoutParams);
                return new DateViewHolder(view);

            }

        }


        @Override
        public void onBindViewHolder(final DateViewHolder holder, int position) {
            LabelerDate labelerDate = dateDataList.get(position);


            if (getItemViewType(position) == VIEW_TYPE_ITEM) { //      Сетчу текст


                holder.textViewDate.setText(labelerDate.getString1(position));
                holder.textViewDate.setVisibility(View.VISIBLE);

                holder.tvDate.setText(labelerDate.getString(position));
                holder.tvDate.setVisibility(View.VISIBLE);


                Log.d(TAG, "default " + position + ", selected " + selectedItem);

            } else {
                holder.tvDate.setVisibility(View.INVISIBLE);
                holder.textViewDate.setVisibility(View.INVISIBLE);

            }

            if (position == 4) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "1");
                        holder.mItemLayout.getContext().startActivity(intent);
                    }
                });
            }
            if (position == 5) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "2");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 6) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "3");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 7) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "4");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 8) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "5");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 9) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "6");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 10) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "7");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 11) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "8");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }
            if (position == 12) {
                holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WheelViewAlternative.this, ActivityMainDirection.class);
                        intent.putExtra("Number_in_list", "9");
                        holder.mItemLayout.getContext().startActivity(intent);

                    }
                });
            }


        }


        public void setSelecteditem(int selecteditem) {
            this.selectedItem = selecteditem;
            notifyDataSetChanged();
        }


        @Override
        public int getItemCount() {
            return dateDataList.size();
        }


        @Override
        public int getItemViewType(int position) {
            LabelerDate labelerDate = dateDataList.get(position);
            if (labelerDate.getType() == VIEW_TYPE_PADDING) {
                return VIEW_TYPE_PADDING;
            } else {
                return VIEW_TYPE_ITEM;
            }

        }

        @Override
        public void onClick(View view) {

        }


        public class DateViewHolder extends RecyclerView.ViewHolder {
            public TextView tvDate;
            public TextView textViewDate;
            public View mItemLayout;

            public DateViewHolder(View itemView) {
                super(itemView);
                tvDate = (TextView) itemView.findViewById(R.id.txt_date);
                textViewDate = itemView.findViewById(R.id.textDate);
                mItemLayout = itemView.findViewById(R.id.item_layout);
            }
        }
    }

    private class LabelerDate {

        private final String[] PLANETS = new String[]{"", "", "", "Реализм", "Импрессионизм", "Пост" + "\n" + "-импрессионизм", "Кубизм", "Экспрессионизм", "Сюрреализм", "Абстракционизм", "Абстрактная" + "\n" + "Живопись", "Поп арт", "", "", ""};
        private final String[] PLANETS1 = new String[]{"", "", "", "1850", "1860", "1880", "1910", "1915", "1920", "1930", "1950", "1960", "", "", ""};

        private int type;
        private String number;

        public String getNumber() {
            return number;
        }

        public String getString1(int i) {
            return PLANETS1[i];

        }

        public String getString(int i) {
            return PLANETS[i];
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item1 clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}