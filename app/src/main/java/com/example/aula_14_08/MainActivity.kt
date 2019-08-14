package com.example.aula_14_08

import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //revising the activity cycle

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Log.d("fiap", "onStart method")
        super.onStart()
    }

    override fun onResume() {
        Log.d("fiap", "onResume method")
        super.onResume()
    }

    override fun onPause() {
        Log.d("fiap", "onPause method")
        super.onPause()
    }

    override fun onStop() {
        Log.d("fiap", "onStop method")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("fiap", "onDestroy method")
        super.onDestroy()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //making a call with a button
        btn_ligar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE), 0)
            } else {
                makeCall()
            }
        }
        //moving the activity to Tela2
        btn_tela2.setOnClickListener{
            val intent = Intent(this, Tela2::class.java)
            //val is a constant in kotlin
            val nome = editText_id.text.toString()
            intent.putExtra("param", nome)
            startActivity(intent)
        }


    }

    //CALL_PHONE was a unrecognised method, so you have to import it's permission by hand

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            makeCall()
        }
    }

    @SuppressLint("MissingPermission")
    private fun makeCall() {
        val uri = "tel:" + 5556
        val call = Intent(Intent.ACTION_CALL)
        call.data = Uri.parse(uri)
        startActivity(call)
        //call was a Activity with a permission warning. The ...valeu: was added to get rid of the warning
    }
}
