package org.com.art.application.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.wangjie.wheelview.R;
import org.com.art.application.WheelView;
import org.com.art.application.game_activity.SixStep;

import java.util.Arrays;

public class TimelineActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = TimelineActivity.class.getSimpleName();
    private static final String[] PLANETS = new String[]{"", "1850   Реализм", "1860   Импрессионизм","1880   Пост" +
            "\n"+"        -импрессионизм",
            "1910   Кубизм","1915   Экспрессионизм","1920   Сюрреализм",
            "1930   Абстракционизм", "1950   Абстрактная" +
            "\n" + "            Живопись", "1960   Поп арт"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_line_activity);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withSelectable(false).withTextColor(Color.BLACK);
      //  SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_paintings).withTextColor(Color.BLACK);
       // SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.bookshelf).withTextColor(Color.BLACK);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(2).withName("Малевич Game").withSelectable(false).withTextColor(Color.BLACK);


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

                        if(position==1){Intent intent=new Intent(view.getContext(), SixStep.class);
                        startActivity(intent);

                        }
                        return false;
                    }
                }).withSelectedItem(-1)//эта штука селекшн дефаултный убирает

                .build();

      //  result.updateItem(item2);


        WheelView wva = (WheelView) findViewById(R.id.main_wv);
        wva.setSeletion(4);
        wva.setOffset(3);//3
       // wva.getSeletedItem().
        wva.setItems(Arrays.asList(PLANETS));


        wva.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item1: " + item);
            }


        });
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

    @Override
    public void onClick(View view) {

    }
}





