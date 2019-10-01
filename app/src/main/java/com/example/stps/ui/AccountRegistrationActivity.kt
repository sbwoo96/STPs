package com.example.stps

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.stps.networking.RequestHttpURLConnection
import kotlinx.android.synthetic.main.activity_account_registration.*
import org.json.JSONObject

class AccountRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_registration)

        // 이전 버튼 클릭 시 PhoneNumberVerificationActivity 로 이동
        btn_previous.setOnClickListener {
            finish() // 이전 화면으로 돌아가기 때문에 finish 날림
        }

        // 가입하기 클릭 시 AppIntroductionActivity 로 이동
        btn_join.setOnClickListener {

            val userName = et_name.text.toString().trim()
            val userEmail = et_email.text.toString().trim()
            val userPassword = et_password.text.toString().trim()
            val userBirthday = et_birthday.text.toString().trim()

            Log.d("사용자 이름", userName)
            Log.d("사용자 이메일", userEmail)
            Log.d("사용자 비밀번호", userPassword)
            Log.d("사용자 생일", userBirthday)

            val json = JSONObject()
            json.put("userName", userName)
            json.put("userEmail", userEmail)
            json.put("userPassword", userPassword)
            json.put("userBirthday", userBirthday)

            Log.d("사용자 정보 jsonObject", json.toString())

            //httpurlconnection 통신으로 회원가입에 필요한 데이터 전송(POST방식)
            HttpTask {
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val jsonResponse = JSONObject(it)
                Log.d("jsonRes : ", jsonResponse.toString())
                if(jsonResponse.getString("status") == "true") {
                    startActivity(Intent(applicationContext, AppIntroductionActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                    })
                } else {
                    Log.d("post Data::::::::::", jsonResponse.getString("message"))
                    Toast.makeText(applicationContext, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show()

                }
            }.execute("POST", "http://172.30.1.30:8000/stps/registration.php", json.toString())

        }
    }

    //asynctask를 이용한 httpurlconnection 통신(POST)
    class HttpTask(var callback: (String?) -> Unit) : AsyncTask<String, Unit, String>() {

        override fun doInBackground(vararg params: String): String? {

            return RequestHttpURLConnection.requestHttp(*params)

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            callback(result)
        }
    }
}