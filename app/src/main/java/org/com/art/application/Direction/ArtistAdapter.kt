package org.com.art.application.direction

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

import org.com.art.application.artist_activity.ArtistActivity
import org.wangjie.wheelview.R

/**
 * Created by Wonka on 01.08.2017.
 */

class ArtistAdapter(private val mDataList: Array<String>, private var mNumber: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var mContext: Context
    lateinit var intent: Intent

    private inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val text: TextView? = null
        val mPicture = itemView.findViewById<ImageView>(R.id.artistImage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.artist_recycler_item, parent, false)
        val holder = ItemViewHolder(itemView)
        mContext = parent.context
        return holder
    }


    override fun onBindViewHolder(rawHolder: RecyclerView.ViewHolder, position: Int) {

        //  final Direction direction = mDataList.get(mNumber);// getting url for artists icon

        val holder = rawHolder as ItemViewHolder
        //   String[] parts = direction.getUrlImage().split(",");// Здесь получаю урлы на Картины
        // holder.text.setText(direction.getName());


        // ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(parts));
        Glide.with(mContext)
                .load(mDataList[position])
                .centerCrop()
                .placeholder(R.drawable.papiros)
                .into(object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
                        holder.mPicture.setImageDrawable(resource)
                    }
                })

        holder.mPicture.layout(0, 0, 0, 0)

        holder.itemView.setOnClickListener {
            when (position) {
                //Номер художника и номер направления
                0 -> {
                    intent = Intent(mContext, ArtistActivity::class.java)
                    intent.putExtra("Number", "0,$mNumber")
                }

                1 -> {
                    intent = Intent(mContext, ArtistActivity::class.java)
                    intent.putExtra("Number", "1,$mNumber")
                }


                2 -> {
                    intent = Intent(mContext, ArtistActivity::class.java)
                    intent.putExtra("Number", "2,$mNumber")
                }

                3 -> {
                    intent = Intent(mContext, ArtistActivity::class.java)
                    intent.putExtra("Number", "3,$mNumber")
                }


                else -> {
                    intent = Intent(mContext, ArtistActivity::class.java)
                    intent.putExtra("Number", "4,$mNumber")
                }
            }

            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}