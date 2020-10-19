package com.example.onsoptfirstassignment.signup.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.preference.LoginPreference
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_register.setOnClickListener {
            val intent = Intent()
            if(checkNullOrEmpty()) {
                intent.putExtra("id", et_id.text.toString())
                intent.putExtra("password", et_password.text.toString())
                LoginPreference.myIsLogin = false
                LoginPreference.myId = et_id.text.toString()
                LoginPreference.myEtPassword = et_password.text.toString()
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "빈 곳이 있는 지 다시 한 번 확인해보세요!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkNullOrEmpty() : Boolean {
        return !et_name.text.isNullOrBlank() &&
                !et_id.text.isNullOrBlank() && !et_password.text.isNullOrBlank()
    }
}