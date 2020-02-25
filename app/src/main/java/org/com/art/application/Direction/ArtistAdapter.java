package org.com.art.application.direction;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.com.art.application.artist_activity.ArtistActivity;
import org.wangjie.wheelview.R;

/**
 * Created by Wonka on 01.08.2017.
 */

public class ArtistAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] mDataList;
    Context mContext;
    int mNumber;
    Intent intent;


    public ArtistAdapter(String[] dataList, int number) {
        mDataList = dataList;
        mNumber=number;
    }

//    public void setData(ArrayList<Direction> data) {
//
//        mDataList = data;
//        notifyDataSetChanged();
//  }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        ImageView mPicture;

        public ItemViewHolder(View itemView) {
            super(itemView);
           // text = (TextView) itemView.findViewById(R.id.horizontal_item_text);
            mPicture= (ImageView) itemView.findViewById(R.id.artistImage);
        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.artist_recycler_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        mContext=parent.getContext();
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {

      //  final Direction direction = mDataList.get(mNumber);// getting url for artists icon

        final ItemViewHolder holder = (ItemViewHolder) rawHolder;
     //   String[] parts = direction.getUrlImage().split(",");// Здесь получаю урлы на Картины
        // holder.text.setText(direction.getName());



       // ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(parts));
        Glide.with(mContext)
                .load(mDataList[position])
                .centerCrop()
                .placeholder(R.drawable.papiros)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.mPicture.setImageDrawable(resource);
                    }
                });
                //.into(holder.mPicture);

        holder.mPicture.layout(0,0,0,0);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position){ //Номер художника и номер направления
                    case 0:
                        intent = new Intent(mContext, ArtistActivity.class);
                        intent.putExtra("Number","0"+","+mNumber);
                        break;

                    case 1:
                        intent = new Intent(mContext, ArtistActivity.class);
                        intent.putExtra("Number","1"+","+mNumber);
                        break;


                    case 2:
                        intent = new Intent(mContext, ArtistActivity.class);
                        intent.putExtra("Number","2"+","+mNumber);
                        break;

                    case 3:
                        intent = new Intent(mContext, ArtistActivity.class);
                        intent.putExtra("Number","3"+","+mNumber);
                        break;


                    default:
                        intent = new Intent(mContext, ArtistActivity.class);
                        intent.putExtra("Number","4"+","+mNumber);
                        break;

                }

                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mDataList.length;
    }

    //mDatalist.size()

}