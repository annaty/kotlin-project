package com.example.annakotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setHeaderTitle(getString(R.string.app_name))

        val buttonInfo = findViewById<Button>(R.id.buttonInfo)
        buttonInfo.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, InfoActivity::class.java)
            newIntent.putExtra("title", getString(R.string.info))
            startActivity(newIntent)
        })

        val buttonProducts = findViewById<Button>(R.id.buttonProducts)
        buttonProducts.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, CategoryActivity::class.java)
            newIntent.putExtra("title", getString(R.string.categories))
            startActivity(newIntent)
        })
    }
}