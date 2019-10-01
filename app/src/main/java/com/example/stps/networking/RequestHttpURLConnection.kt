package com.example.stps.networking

import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object RequestHttpURLConnection {

    private const val TIMEOUT = 30*1000

    fun requestHttp(vararg params: String): String? {

        val url = URL(params[1])
        val httpClient = url.openConnection() as HttpURLConnection
        httpClient.readTimeout = TIMEOUT
        httpClient.connectTimeout = TIMEOUT
        httpClient.requestMethod = params[0]

        if (params[0] == "POST") {
            httpClient.instanceFollowRedirects = false
            httpClient.doOutput = true
            httpClient.doInput = true
            httpClient.useCaches = false
            httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8")
        }
        try {
            if (params[0] == "POST") {
                httpClient.connect()
                val os = httpClient.outputStream
                val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                writer.write(params[2])
                writer.flush()
                writer.close()
                os.close()
            }
            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                val stream = BufferedInputStream(httpClient.inputStream)
                return readStream(inputStream = stream)
            } else {
                println("ERROR ${httpClient.responseCode}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            httpClient.disconnect()
        }

        return null
    }

    private fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }
}