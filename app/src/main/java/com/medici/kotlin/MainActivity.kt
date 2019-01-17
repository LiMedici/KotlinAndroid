package com.medici.kotlin

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE = 1000

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_content.text = "Hello World"
        tv_content.setOnClickListener {
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            this@MainActivity.startActivityForResult(intent,REQUEST_CODE)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var count = 100000
        while (count > 0){
            count--
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        newConfig?.let{
            if(it.orientation == Configuration.ORIENTATION_LANDSCAPE){
                println("ORIENTATION_LANDSCAPE")
            }else if(it.orientation == Configuration.ORIENTATION_PORTRAIT){
                println("ORIENTATION_PORTRAIT")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            /*if(requestCode == REQUEST_CODE){
                var count = 1000000
                while (count > 0){
                    count--
                }
            }*/
        }
    }
}
