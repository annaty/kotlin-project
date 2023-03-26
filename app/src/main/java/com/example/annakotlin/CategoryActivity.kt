package com.example.annakotlin

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Category (
    val category_id: String,
    val title: String,
    val products_url: String,
) {
}

class CategoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val title = intent.extras?.getString("title")
        setHeaderTitle(title)
        showBack()

        val categories = arrayListOf<Category>()

        val recyclerViewCategories = findViewById<RecyclerView>(R.id.recyclerViewCategories)
        recyclerViewCategories.layoutManager = LinearLayoutManager(this)

        val categoryAdapter = CategoryAdapter(categories)
        recyclerViewCategories.adapter = categoryAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://www.ugarit.online/epsi/categories.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Categories", "############## " + e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                Log.e("Categories", "################# response.code:" + response.code)
                if (data != null && response.code == 200) {
                    Log.e("Categories", data)
                    val jsCategories = JSONObject(data)
                    val jsArrayCategories = jsCategories.getJSONArray("items")
                    for (i in 0 until jsArrayCategories.length()) {
                        val js = jsArrayCategories.getJSONObject(i)
                        val category = Category(
                            js.optString("category_id", ""),
                            js.optString("title", ""),
                            js.optString("products_url", ""),
                        )
                        categories.add(category)
                        runOnUiThread(Runnable {
                            categoryAdapter.notifyDataSetChanged()
                        })
                    }
                }
            }
        })
    }
}