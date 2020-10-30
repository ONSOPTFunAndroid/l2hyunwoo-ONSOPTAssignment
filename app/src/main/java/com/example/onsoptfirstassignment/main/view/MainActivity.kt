package com.example.onsoptfirstassignment.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.onsoptfirstassignment.databinding.ActivityMainBinding
import com.example.onsoptfirstassignment.detail.view.WelcomeActivity
import com.example.onsoptfirstassignment.main.viewmodel.MainViewModel
import com.example.onsoptfirstassignment.preference.LoginPreference
import com.example.onsoptfirstassignment.signup.view.SignUpActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainViewModel = activityViewModel
        binding.lifecycleOwner = this

        activityViewModel.isRegisterClicked.observe(this, {
            registerButtonClickListener(it)
        })

        activityViewModel.isSignIn.observe(this, {
            signInButtonClickListener(it)
        })
    }

    private fun signInButtonClickListener(isSignIn: Boolean) {
        if (isSignIn) {
            loginStart()
            activityViewModel.setSignInFalse()
        }
    }

    private fun registerButtonClickListener(isRegisterClicked: Boolean) {
        if (isRegisterClicked) {
            transferActivityForResult(SignUpActivity::class.java)
            activityViewModel.setRegisterClickedFalse()
        }
    }

    private fun loginProcess() {
        if (activityViewModel.isMatch()) {
            activityViewModel.setAutoLoginInfo()
            "로그인 성공".toast()
            transferActivity(WelcomeActivity::class.java)
            finish()
        } else {
            "회원정보가 잘못되었습니다 다시 하세요".toast()
        }
    }

    private fun registerCheck() {
        if (!activityViewModel.registerValidation()) {
            "화원가입부터 하세요".toast()
        } else {
            loginProcess()
        }
    }

    private fun loginStart() {
        if (!isAutoRegister()) {
            registerCheck()
        }
    }

    private fun isAutoRegister(): Boolean {
        return if (LoginPreference.myIsLogin) {
            autoRegister()
            true
        } else {
            false
        }
    }

    private fun autoRegister() {
        "자동 로그인 성공".toast()
        transferActivity(WelcomeActivity::class.java)
    }

    private fun transferActivityForResult(className: Class<*>) {
        val intent = Intent(applicationContext, className)
        startActivityForResult(intent, MainViewModel.SIGN_UP_CODE)
    }

    private fun transferActivity(className: Class<*>) {
        val intent = Intent(applicationContext, className)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainViewModel.SIGN_UP_CODE) {
            val id = data!!.getStringExtra("id") as String
            val password = data.getStringExtra("password") as String
            activityViewModel.setIdAndPassword(id, password)
        }
    }

    private fun String.toast() {
        Toast.makeText(this@MainActivity, this, Toast.LENGTH_SHORT).show()
    }
}