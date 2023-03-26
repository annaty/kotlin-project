package com.example.annakotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(val products: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_product_cell, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = products.get(position)
        holder.textViewName.text = product.name
        holder.textViewDescription.text = product.description

        Picasso.get().load(product.picture_url).into(holder.imageViewProduct)
        holder.textViewName.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.getContext(), ProductActivity::class.java)
            intent.putExtra("name", product.name)
            intent.putExtra("description", product.description)
            intent.putExtra("picture_url", product.picture_url)

            it.getContext().startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
        val imageViewProduct = view.findViewById<ImageView>(R.id.imageViewProduct)

    }
}