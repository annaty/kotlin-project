package com.example.annakotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class InfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val title = intent.extras?.getString("title")
        setHeaderTitle(title)
        showBack()

        val button = findViewById<TextView>(R.id.link)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.epsi.fr"))
            startActivity(intent)
        }
    }
}