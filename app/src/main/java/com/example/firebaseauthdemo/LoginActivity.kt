package com.example.firebaseauthdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var tv_Register:TextView = findViewById(R.id.tv_login)
        var button_login:Button = findViewById(R.id.button_login)
        var et_register: EditText = findViewById(R.id.et_login_email)
        var et_password: EditText = findViewById(R.id.et_login_password)
        tv_Register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        button_login.setOnClickListener {
            if(et_register.text.toString().equals(""))
            {
                Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show()
            }
            else if (et_password.text.toString().equals(""))
            {
                Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show()
            }
            else {
                val email:String = et_register.text.toString()
                val password:String = et_password.text.toString()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(
                                {task->
                                    if (task.isSuccessful)
                                    {
                                        val firebaseUser:FirebaseUser = task.result!!.user!!
                                        Toast.makeText(this,"You are Login Successfully",Toast.LENGTH_SHORT).show()

                                        val intent = Intent(this,MainActivity::class.java)
                                        intent.putExtra("user_id",firebaseUser.uid)
                                        intent.putExtra("email_id",firebaseUser.email)
                                        startActivity(intent)
                                        finish()


                                    }
                                    else
                                    {
                                        Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                                    }
                                }
                        )
            }


        }
    }
}