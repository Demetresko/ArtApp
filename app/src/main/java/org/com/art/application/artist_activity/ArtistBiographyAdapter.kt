package org.com.art.application.artist_activity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

import org.wangjie.wheelview.R


/**
 * Created by Wonka on 03.08.2017.
 */

class ArtistBiographyAdapter(
        private val dataList: Array<String>,
        private val number: Int = 0,
        private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_artist_picture, parent, false)
        val holder = ItemViewHolder(itemView)
        mContext = parent.context

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // Artist mArtist = mDataList.get(this.number);
        val holder1 = holder as ItemViewHolder

        // holder1.text.setText(mArtist.get);
        //
        // String[] parts = mArtist.getPictures().split(",");// Здесь получаю урлы на Картины
        // holder.text.setText(direction.getName());


        //        Glide.with(mContext)
        //                .load(mDataList[position])
        //                .placeholder(R.drawable.papiros)
        //                .into(holder1.mPicture);


        Glide.with(mContext)
                .load(dataList[position])
                .placeholder(R.drawable.papiros)
                .into(object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                        holder1.mPicture.setImageDrawable(resource)
                    }
                })

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    private inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var mPicture: ImageView = itemView.findViewById<View>(R.id.pictureID) as ImageView
    }
}
