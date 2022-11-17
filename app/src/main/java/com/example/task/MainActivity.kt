package com.example.task

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init Text views
        val tx_fact = findViewById<TextView>(R.id.f_text)
        val tx_length = findViewById<TextView>(R.id.f_length)
        val bt_rf = findViewById<Button>(R.id.bt_rf)


        // Creating Alert Dialog to show when fetching data.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Fact Task")
        builder.setMessage("Please wait while fetching data.")


        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)


        // On button refresh Click to fetch more data.
        bt_rf.setOnClickListener{

            //Showing alert Dialog when click on button
            alertDialog.show()
            val api_ = ServiceGenerator.getInstance()
            val service = api_.create(ApiService::class.java)
            val call = service.getPosts();
            call.enqueue(object : Callback<PostModel> {
                override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                    if (response.code() == 200) {
                        alertDialog.hide()
                        tx_fact.setText(response.body()!!.fact)
                        tx_length.setText("(" +  response.body()!!.length.toString() + ")")
                    }
                }
                override fun onFailure(call: Call<PostModel>, t: Throwable) {
                    alertDialog.hide()
                    Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
                }
            })
        }
        alertDialog.show()
        val api_ = ServiceGenerator.getInstance()
        val service = api_.create(ApiService::class.java)
        val call = service.getPosts();

        call. enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                if (response.code() == 200) {
                    alertDialog.hide()
                    tx_fact.setText(response.body()!!.fact)
                    tx_length.setText("(" +  response.body()!!.length.toString() + ")")
                }
            }
            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                alertDialog.hide()
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
            }
        })




    }





    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { dialog, id -> super@MainActivity.onBackPressed() }
            .setNegativeButton("No", null)
            .show()
    }
}
