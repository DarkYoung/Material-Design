package com.fitymi.fruit.fruit.module

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fitymi.fruit.R
import com.fitymi.fruit.fruitdetail.view.FruitDetailActivity
import kotlinx.android.synthetic.main.item_fruit.view.*

class FruitAdapter(private var mFruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        if (mContext == null)
            mContext = parent.context

        return ViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_fruit, parent, false)
        ).apply {
            cardView.setOnClickListener {
                val fruit = mFruitList[adapterPosition]
                FruitDetailActivity.actionStart(mContext!!, fruit.name, fruit.imageId)
            }
        }
    }

    override fun getItemCount(): Int {
        return mFruitList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        checkNotNull(mContext)
        with(mFruitList[p1]) {
            p0.fruitName.text = this.name
            Glide.with(mContext!!).load(this.imageId).into(p0.fruitImage)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView as CardView
        var fruitImage: ImageView = itemView.fruit_image
        var fruitName: TextView = itemView.fruit_name
    }
}