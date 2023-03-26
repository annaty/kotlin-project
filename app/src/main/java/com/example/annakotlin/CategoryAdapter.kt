package com.example.annakotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(val categories: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_category_cell, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val category = categories.get(position)
        holder.textViewTitle.text = category.title
        holder.textViewTitle.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.getContext(), ProductActivity::class.java)
            intent.putExtra("title", category.title)
            intent.putExtra("products_url", category.products_url)
            intent.putExtra("category_id", category.category_id)

            it.getContext().startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
    }
}