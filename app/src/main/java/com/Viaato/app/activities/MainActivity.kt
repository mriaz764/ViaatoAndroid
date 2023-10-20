package com.Viaato.app.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.Viaato.app.MyApplication
import com.Viaato.app.R

class MainActivity : AppCompatActivity() {
    private val RECORD_REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createImpl()
        setupPermissions()
        (applicationContext as MyApplication).registerServices()

    }
    private fun createImpl() {
        val btn = findViewById<ImageButton>(R.id.nextbtn)
        btn.setOnClickListener{
            if (checkPermission()) {
                val i = Intent(this, MapNavigationActivity::class.java)
                startActivity(i)
                finish()
            }else{
                Toast.makeText(this, "Permission to record denied", Toast.LENGTH_LONG).show()
                setupPermissions()
            }
        }
    }
    private fun setupPermissions() {
        if (!checkPermission()) {
            Log.i("APP_RI", "Permission to record denied")
            makeRequest()
        }
    }
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            RECORD_REQUEST_CODE
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() ||
                    grantResults[0] != PackageManager.PERMISSION_GRANTED
                ) {
                    Log.i("APP", "Permission has been denied by user")
                } else {
                    Log.i("APP", "Permission has been granted by user")
                }
            }
        }
    }
}