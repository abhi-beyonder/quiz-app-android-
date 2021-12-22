package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener {
            if(et_name.text.toString().isEmpty()){

//                If the string is empty then make this toast

                Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show()
            }
            else{
//               ----------------- sending intent when the start is clicked-----------------
                val intent = Intent(this,QuizQustionActivity::class.java)

                intent.putExtra(Consatnts.USER_NAME,et_name.text.toString())
                startActivity(intent)
//                finish will close the current activity
                finish()
            }
        }
    }
}