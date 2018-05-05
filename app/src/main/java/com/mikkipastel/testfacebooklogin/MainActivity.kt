package com.mikkipastel.testfacebooklogin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.facebook.FacebookException
import com.facebook.login.LoginManager

class MainActivity : AppCompatActivity(), FacebookCallback<LoginResult> {

    private val EMAIL = "email"
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setReadPermissions(Arrays.asList(EMAIL))

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, this)
    }

    override fun onSuccess(result: LoginResult?) {
        val token = result?.accessToken?.token
        Log.d("LoginManager", token)
    }

    override fun onCancel() {
        Log.d("LoginManager", "onCancel")
    }

    override fun onError(error: FacebookException?) {
        Log.d("LoginManager", "onError")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
