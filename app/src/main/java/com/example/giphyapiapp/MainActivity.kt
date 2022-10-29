package com.example.giphyapiapp

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import android.widget.Toast
import com.android.volley.Request
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var rView: RecyclerView
    var dataModelArrayList = ArrayList<DataModel>()
    var dataAdapter: DataAdapter? = null
    var newUrl = URL + API_KEY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set recyclerView
        rView = findViewById(R.id.recyclerView)
        rView.layoutManager = GridLayoutManager(this, 2)
        rView.setHasFixedSize(true)

        //get the data from url
        val objectRequest = JsonObjectRequest(Request.Method.GET, newUrl, null, { response ->
            try {
                val dataArray = response.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val elem = dataArray.getJSONObject(i)
                    val elem1 = elem.getJSONObject("images")
                    val title1 = elem.getString("title")
                    val elem2 = elem1.getJSONObject("downsized_large")
                    val sourceUrl = elem2.getString("url")
                    dataModelArrayList.add(DataModel(sourceUrl, title1))
                }
                //bing data from URL to UI
                dataAdapter = DataAdapter(this, dataModelArrayList)
                rView.setAdapter(dataAdapter)
                dataAdapter!!.notifyDataSetChanged()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }) { error ->
            Toast.makeText(
                this,
                "Error" + error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        //request queue
        Standalone.getInstance(this)?.addToRequestQueue(objectRequest)
    }

    companion object {
        const val API_KEY = "EEjeWKnay8eNwJ091mC2ffGuQe96tdBN"
        const val URL = "https://api.giphy.com/v1/gifs/trending?api_key="
    }
}