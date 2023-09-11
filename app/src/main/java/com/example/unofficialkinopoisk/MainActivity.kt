package com.example.unofficialkinopoisk

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val client = OkHttpClient()

		val request = Request.Builder()
			.url("https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS&page=1")
			.addHeader("accept", "application/json")
			.addHeader("X-API-KEY", "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
			.build()

		client.newCall(request).enqueue(object : Callback {
			override fun onFailure(call: Call, e: IOException) {
				e.printStackTrace()
			}

			override fun onResponse(call: Call, response: Response) {
				response.use {
					if (!response.isSuccessful) {
						throw IOException(
							"Запрос к серверу не был успешен:" +
									" ${response.code} ${response.message}"
						)
					}

					Log.d("TAG", response.body!!.string())
				}
			}
		})
	}
}
