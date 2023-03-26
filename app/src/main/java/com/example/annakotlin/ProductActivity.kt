package com.example.annakotlin

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Product (
    val name: String,
    val description: String,
    val picture_url: String,
) {
}

class ProductActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val title = intent.extras?.getString("title")
        val products_url = intent.extras?.getString("products_url")
        val category_id = intent.extras?.getString("category_id")
        setHeaderTitle(title)
        showBack()

        val products = arrayListOf<Product>()

        val recyclerViewProducts = findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerViewProducts.layoutManager = LinearLayoutManager(this)

        val productAdapter = ProductAdapter(products)
        recyclerViewProducts.adapter = productAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val request = products_url?.let {
            Request.Builder()
                .url(it)
                .get()
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build()
        }

        if (request != null) {
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("Products", "############## " + e.message.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val data = response.body?.string()
                    Log.e("Products", "################# response.code:" + response.code)
                    if (data != null && response.code == 200) {
                        Log.e("Products", data)
                        val jsProducts = JSONObject(data)
                        val jsArrayProducts = jsProducts.getJSONArray("items")
                        for (i in 0 until jsArrayProducts.length()) {
                            val js = jsArrayProducts.getJSONObject(i)
                            val product = Product(
                                js.optString("name", ""),
                                js.optString("description", ""),
                                js.optString("picture_url", ""),
                            )
                            products.add(product)
                            runOnUiThread(Runnable {
                                productAdapter.notifyDataSetChanged()
                            })
                        }
                    }
                }
            })
        }
    }
}