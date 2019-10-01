package com.example.stps

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.stps.networking.RequestHttpURLConnection
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btLogin = findViewById<Button>(R.id.button3) // 로그인 버튼
        val tvFindPw = findViewById<TextView>(R.id.textView2) // 비밀번호 재설정 텍스트뷰

        val etEmail = findViewById<EditText>(R.id.editText1) // 이메일 입력칸
        val etPw = findViewById<EditText>(R.id.editText2) // 비밀번호 입력칸

        // 로그인 버튼 클릭 시 메인 화면으로 이동
        btLogin.setOnClickListener {

            val json = JSONObject()
            json.put("email", etEmail.text.toString().trim())
            json.put("pw", etPw.text.toString().trim())
            Log.d("로그인 json : ", json.toString())

            //httpurlconnection 통신으로 회원가입에 필요한 데이터 전송(POST방식)
            HttpTask {
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                } else {
                    println(it)
                    val jsonRes = JSONObject(it)
                    Log.d("jsonRes : ", jsonRes.toString())
                    if (jsonRes.getString("status") == "true") {
                        startActivity(Intent(applicationContext, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                        })
                    } else {
                        Log.d("post Data::::::::::", jsonRes.getString("message"))
                        Toast.makeText(applicationContext, jsonRes.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }
            }.execute("POST", "http://172.30.1.30:8000/stps/login.php", json.toString())

        }

        // 비밀번호 재설정 텍스트뷰 클릭시 비밀번호 재설정 화면으로 이동
        tvFindPw.setOnClickListener {
            startActivity(Intent(applicationContext, ResetPasswordActivity::class.java))
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
