package com.example.onsoptfirstassignment.signup.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.onsoptfirstassignment.data.RetrofitServiceImpl
import com.example.onsoptfirstassignment.databinding.ActivitySignUpBinding
import com.example.onsoptfirstassignment.signup.repository.SignUpRepository
import com.example.onsoptfirstassignment.signup.viewmodel.SignUpViewModel
import com.example.onsoptfirstassignment.signup.viewmodel.SignUpViewModelFactory
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this

        signUpViewModel.isRegisterButtonClicked.observe(this, {
            if (it) {
                registerProcess()
                signUpViewModel.isButtonClickedSetFalse()
            }
        })
    }

    private fun initViewModel() {
        signUpViewModel = ViewModelProvider(
            this, SignUpViewModelFactory(
                SignUpRepository(RetrofitServiceImpl.getSignUpInstance())
            )
        ).get(SignUpViewModel::class.java)
    }

    private fun sendDataToLoginActivity() {
        val intent = Intent()
        intent.putExtra("id", et_id.text.toString())
        intent.putExtra("password", et_password.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun registerProcess() {
        if (signUpViewModel.editTextBlankCheck()) {
            signUpViewModel.setUserInfoInSharedPreference()
            signUpViewModel.sendDataToServer()
            "회원가입 성공".toast()
            sendDataToLoginActivity()
        } else {
            "빈 곳이 있는 지 다시 한 번 확인해보세요!".toast()
        }
    }

    private fun String.toast() {
        Toast.makeText(this@SignUpActivity, this, Toast.LENGTH_SHORT).show()
    }
}