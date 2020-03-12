package org.com.art.application

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.com.art.application.DateAdapter.DateViewHolder
import org.wangjie.wheelview.R
import java.util.*

class DateAdapter(
        private val dateDataList: ArrayList<LabelerDate>,
        paddingWidthDate: Int,
        private val action: (List<String>) -> Unit
): RecyclerView.Adapter<DateViewHolder>(), View.OnClickListener {
    private var paddingWidthDate = 0
    private var selectedItem = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_recyclerview_item,
                    parent, false)
            DateViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_recyclerview_item,
                    parent, false)
            DateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val labelerDate = dateDataList[position]
        if (getItemViewType(position) == VIEW_TYPE_ITEM) { //      Сетчу текст
            holder.textViewDate.text = labelerDate.getString1(position)
            holder.textViewDate.visibility = View.VISIBLE
            holder.tvDate.text = labelerDate.getString(position)
            holder.tvDate.visibility = View.VISIBLE
            Log.d("lll", "default $position, selected $selectedItem")
        } else {
            holder.tvDate.visibility = View.INVISIBLE
            holder.textViewDate.visibility = View.INVISIBLE
        }
        when (position) {
            4 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "1")) } }
            5 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "2")) } }
            6 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "3")) } }
            7 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "4")) } }
            8 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "5")) } }
            9 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "6")) } }
            10 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "7")) } }
            11 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "8")) } }
            12 -> { holder.mItemLayout.setOnClickListener { action.invoke(listOf(NUMBER_IN_LIST, "9")) } }
        }
    }

    fun setSelecteditem(selecteditem: Int) {
        selectedItem = selecteditem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dateDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        val labelerDate = dateDataList[position]
        return if (labelerDate.type == VIEW_TYPE_PADDING) {
            VIEW_TYPE_PADDING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onClick(view: View) {}
    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate: TextView
        var textViewDate: TextView
        var mItemLayout: View

        init {
            tvDate = itemView.findViewById(R.id.txt_date)
            textViewDate = itemView.findViewById(R.id.textDate)
            mItemLayout = itemView.findViewById(R.id.item_layout)
        }
    }

    companion object {
        const val VIEW_TYPE_PADDING = 1
        const val VIEW_TYPE_ITEM = 2
        private const val NUMBER_IN_LIST = "NUMBER_IN_LIST"
    }

    init {
        this.paddingWidthDate = paddingWidthDate
    }
}