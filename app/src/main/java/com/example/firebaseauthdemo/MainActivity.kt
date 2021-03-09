package com.example.firebaseauthdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        val tv_user_id:TextView = findViewById(R.id.tv_userid)
        val tv_user_email:TextView = findViewById(R.id.tv_emailid)
        val logout:Button = findViewById(R.id.button_logout)

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        tv_user_id.text = "User ID :: $userId"
        tv_user_email.text="Email ID :: $emailId"

    }
}