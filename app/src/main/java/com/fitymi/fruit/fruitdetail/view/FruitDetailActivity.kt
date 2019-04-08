package com.fitymi.fruit.fruitdetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fitymi.fruit.R
import kotlinx.android.synthetic.main.activity_fruit_detail.*

class FruitDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)
        val fruitName = intent.getStringExtra(FRUIT_NAME)
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 1)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(fruit_image)
        fruit_content_text.text = generateFruitContent(fruitName)
    }

    private fun generateFruitContent(fruitName: String?): String {
        val builder = StringBuilder()
        for (i in 0..500) {
            builder.append(fruitName)
        }
        return builder.toString()
    }

    companion object {
        private const val FRUIT_NAME = "fruit_name"
        private const val FRUIT_IMAGE_ID = "fruit_image_id"
        fun actionStart(context: Context, fruitName: String?, fruitImageId: Int) {
            with(Intent(context, FruitDetailActivity::class.java)) {
                putExtra(FRUIT_NAME, fruitName)
                putExtra(FRUIT_IMAGE_ID, fruitImageId)
                context.startActivity(this)
            }
        }
    }
}