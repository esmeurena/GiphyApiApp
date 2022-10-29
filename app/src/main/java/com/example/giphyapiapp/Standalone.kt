package com.example.giphyapiapp

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.example.giphyapiapp.Standalone
import com.android.volley.toolbox.Volley

class Standalone private constructor(private var ctx: Context) {
    private var requestQueue: RequestQueue?

    init {
        requestQueue = getRequestQueue()
    }

    private fun getRequestQueue(): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.applicationContext)
        }
        return requestQueue as RequestQueue
    }

    //add fetched objects to request queue
    fun <T> addToRequestQueue(req: Request<T>?) {
        getRequestQueue().add(req)
    }

    companion object {
        private var instance: Standalone? = null

        //creating a standalone instance
        fun getInstance(context: Context): Standalone? {
            if (instance == null) {
                instance = Standalone(context)
            }
            return instance
        }
    }
}