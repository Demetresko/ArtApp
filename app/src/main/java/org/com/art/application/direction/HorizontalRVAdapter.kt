package org.com.art.application.direction

import android.content.Context
import android.content.Intent
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
import org.com.art.application.sample.GalleryActivity

import java.util.ArrayList


/**
 * Created by Wonka on 20.07.2017.
 */

class HorizontalRVAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var images = ArrayList<String>()


    private lateinit var mDataList: Array<String>
    lateinit var mContext: Context
    private var number: Int = 0

    constructor(dataList: Array<String>, number: Int) {
        mDataList = dataList
        this.number = number
    }


    private inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var mPicture: ImageView = itemView.findViewById<View>(R.id.pictureID) as ImageView
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.horizontal_item, parent, false)
        val holder = ItemViewHolder(itemView)
        mContext = parent.context
        return holder
    }


    override fun onBindViewHolder(rawHolder: RecyclerView.ViewHolder, position: Int) {

        // Direction direction = mDataList.get(this.number);

        val holder = rawHolder as ItemViewHolder

        //  holder.text.setText(direction.getName());

        // String[] parts = direction.getUrlImage().split(",");// Здесь получаю урлы на Картины


        // holder.text.setText(direction.getName());
        Glide.with(mContext)
                .load(mDataList[position])
                .placeholder(R.drawable.papiros)
                .into(object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                        holder.mPicture.setImageDrawable(resource)
                    }
                })

        for (i in mDataList.indices) {
            images.add(mDataList[i])  // Adding pictures to gallery class

            if (position == i) {
            }
        }


        holder.mPicture.setOnClickListener {
            // тут данные надо перепихать в галлери

            val intent = Intent(holder.mPicture.context, GalleryActivity::class.java)

            intent.putExtra(GalleryActivity.EXTRA_NAME, mDataList)
            intent.putExtra(GalleryActivity.EXTRA_NUMBER, position)
            holder.mPicture.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}
