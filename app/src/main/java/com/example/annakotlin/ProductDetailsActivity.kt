package com.example.annakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val title = intent.extras?.getString("name")
        val description = intent.extras?.getString("description")
        val picture_url = intent.extras?.getString("picture_url")

        val image = findViewById<ImageView>(R.id.imageViewProduct)
        Picasso.get().load(picture_url).into(image)

        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        textViewDescription.text = description

        setHeaderTitle(title)
        showBack()
    }
}