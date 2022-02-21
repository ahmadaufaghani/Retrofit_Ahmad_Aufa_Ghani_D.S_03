package com.example.retrofit_ahmadaufaghanids_03.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_ahmadaufaghanids_03.R
import com.example.retrofit_ahmadaufaghanids_03.adapter.ProvinceAdapter
import com.example.retrofit_ahmadaufaghanids_03.api.RetrofitClient
import com.example.retrofit_ahmadaufaghanids_03.model.ProvinceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province)
        showProvince()
    }

    private fun showProvince() {
      val rvProvince = findViewById<RecyclerView>(R.id.rvProvince)
      rvProvince.setHasFixedSize(true)
      rvProvince.layoutManager = LinearLayoutManager(this)

      RetrofitClient.instance.getProvince().enqueue(object : Callback<ArrayList<ProvinceResponse>>{

          override fun onFailure(call: Call<ArrayList<ProvinceResponse>>, t: Throwable) {
              Toast.makeText(this@ProvinceActivity, "${t.message}",Toast.LENGTH_SHORT).show()
          }

          override fun onResponse(
              call: Call<ArrayList<ProvinceResponse>>,
              response: Response<ArrayList<ProvinceResponse>>
          ) {
              val list = response.body()
              val adapter = list?.let { ProvinceAdapter(it) }
              rvProvince.adapter = adapter
          }

      })
    }
}