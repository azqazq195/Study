package com.project.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private lateinit var recycler_view: RecyclerView
    private lateinit var etIndex: EditText

    private val exampleList = generateExampleList(500)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFindViewById()
        setRecyclerView()
    }

    fun insertItem(view: View) {
        if (etIndex.text.toString() == "") return

        val index = etIndex.text.toString().toInt()

        val newItem = ExampleItem(
            R.drawable.ic_item1,
            "New item at position $index",
            "Line2"
        )

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)

        etIndex.text = null
    }

    fun removeItem(view: View) {
        if (etIndex.text.toString() == "") return

        val index = etIndex.text.toString().toInt()

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)

        etIndex.text = null
    }

    override fun onItemClick(position: Int) {
        Snackbar.make(recycler_view, "Item $position clicked", Snackbar.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun setFindViewById() {
        recycler_view = findViewById(R.id.recycler_view)
        etIndex = findViewById(R.id.etIndex)
    }

    private fun setRecyclerView() {
        recycler_view.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun generateExampleList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_item1
                1 -> R.drawable.ic_item2
                else -> R.drawable.ic_item3
            }

            val item = ExampleItem(drawable, "Item $i", "Line2")
            list += item
        }

        return list
    }
}