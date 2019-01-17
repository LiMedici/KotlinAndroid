package com.medici.kotlin

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.medici.kotlin.common.no
import com.medici.kotlin.common.otherwise
import com.medici.kotlin.common.yes
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

        val str1 = false.yes {
            "YES"
        }.otherwise {
            "NO"
        }

        println(str1)

        val str2 = false.no {
            "NO"
        }.otherwise {
            "YES"
        }

        println(str2)

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
            if(requestCode == REQUEST_CODE){
                var count = 1000000
                while (count > 0){
                    count--
                }
            }
        }
    }
}
