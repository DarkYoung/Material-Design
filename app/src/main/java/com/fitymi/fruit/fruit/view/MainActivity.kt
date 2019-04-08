package com.fitymi.fruit.fruit.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fitymi.fruit.R
import com.fitymi.fruit.fruit.module.Fruit
import com.fitymi.fruit.fruit.module.FruitAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val fruits = arrayOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Grape", R.drawable.grape),
        Fruit("Lemon", R.drawable.lemon),
        Fruit("Pitaya", R.drawable.pitaya),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry Tomato", R.drawable.cherry_tomato),
        Fruit("Kiwifruit", R.drawable.kiwifruit)
    )

    private val fruitList = arrayListOf<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        initFruits()

        with(recycler_view) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = FruitAdapter(fruitList)
        }

        with(nav_view) {
            setCheckedItem(R.id.friends)
            setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                    drawer_layout.closeDrawers()
                    return true
                }
            })
        }

        fab.setOnClickListener {
            Snackbar.make(it, "Calling", Snackbar.LENGTH_SHORT)
                .setAction("Cancel") {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                }.show()
        }

        refresh_layout.setOnRefreshListener {
            it.finishRefresh(refreshFruits())
            Toast.makeText(this, "refresh successfully", Toast.LENGTH_SHORT).show()
        }

        refresh_layout.setOnLoadMoreListener {
            it.finishLoadMore(loadFruits())
            Toast.makeText(this, "load successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun refreshFruits(): Boolean {
        checkNotNull(recycler_view.adapter)
        Thread {
            runOnUiThread {
                initFruits()
                recycler_view.adapter?.notifyDataSetChanged()
            }
        }.start()
        return true
    }

    private fun initFruits(): Int {
        fruitList.clear()
        with(Random(SystemClock.currentThreadTimeMillis())) {
            for (i in 0 until 50) {
                fruitList.add(fruits[nextInt(fruits.size)])
            }
            return 50
        }
    }

    private fun loadFruits(): Boolean {
        checkNotNull(fruitList)
        Thread {
            runOnUiThread {
                with(Random(SystemClock.currentThreadTimeMillis())) {
                    for (i in 0 until 10) {
                        fruitList.add(fruits[nextInt(fruits.size)])
                    }
                }
                recycler_view.adapter?.notifyDataSetChanged()
            }
        }.start()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toobar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> drawer_layout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }


}
