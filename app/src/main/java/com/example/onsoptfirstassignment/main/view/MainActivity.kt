package com.example.onsoptfirstassignment.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onsoptfirstassignment.preference.LoginPreference
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.signup.view.SignUpActivity
import com.example.onsoptfirstassignment.detail.view.WelcomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val SIGN_UP_CODE = 1
    private var loginId : String? = null
    private var loginPassword : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_register.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivityForResult(intent,SIGN_UP_CODE)
        }

        btn_login.setOnClickListener {
            val intent = Intent(applicationContext, WelcomeActivity::class.java)
            if(LoginPreference.myIsLogin) {
                intent.putExtra("id", LoginPreference.myId)
                Toast.makeText(this, "자동 로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                if(loginId != null) {
                    if((et_mail.text.toString() == loginId) && (et_main_password.text.toString() == loginPassword)) {
                        intent.putExtra("id", loginId)
                        LoginPreference.myId = loginId!!
                        LoginPreference.myEtPassword = loginPassword!!
                        LoginPreference.myIsLogin = true
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "회원정보가 잘못되었습니다 다시 하세요", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this, "회원가입부터 하세요", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SIGN_UP_CODE) {
            loginId = data!!.getStringExtra("id")
            loginPassword = data.getStringExtra("password")
        }
    }
}