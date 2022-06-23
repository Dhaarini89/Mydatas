package com.android.example.freeblacklist


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.cutyoutubefree.network.FreeApi
import com.android.example.freeblacklist.network.*
import com.android.example.freeblacklist.storage.FreeDBHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

class OverViewModel : ViewModel() {

    lateinit var sessiontokenvalue: String
    lateinit var track_id: String
    lateinit var text: String
    lateinit var app_token: String
    lateinit var statusvalue: String
    private val _status = MutableLiveData<FreeStatus>()
    val status: LiveData<FreeStatus> = _status
    private val _progress = MutableLiveData<String>()
    val progress: LiveData<String> = _progress
    private val _sign = MutableLiveData<String>()
    val sign: LiveData<String> = _sign
    lateinit var freedb : FreeDBHelper
     fun postFreeDetails()
    {
          val freepost = FreePost(app_id = "check",app_name = "mobile",app_version = "0.0.1",device_name = "dhaarini mobile")
          viewModelScope.launch {
                 _progress.value="Authroizing"
                 try {
                val result = FreeApi.retrofitService.postFreeInfo(freepost)
                app_token = result.result.app_token
                track_id = result.result.track_id
                     _progress.value="Authroized"
            }   catch (e : Exception)
            {
                _progress.value="Didnt not Authorize"
            }
                 Log.d("App_Token", app_token)
                 Log.d("Track_ID",track_id)
                  }


    }

    fun removeBlackListFilter()
    {
         viewModelScope.launch {
            val mac_addr = "48:88:CA:42:77:93"
            val headers = HashMap<String, String>()
            headers["X-Fbx-App-Auth"] = sessiontokenvalue
            try {
                _progress.value = "removing"
                val response = FreeApi.retrofitService.removeBlackListFilter(headers, mac_addr)
                Log.d("removeblacklistfilter",response.toString())
                _progress.value="removed blacklist"
            } catch (e : Exception)
            {
                _progress.value="unable to remove blacklist"
                Log.e("blacklisterror","Unable to remove the BlackList")
            }
             }

    }

     fun getSessionTokenDetails() {
         var job = viewModelScope.launch {
             var counter = 0
             app_token = "EgFE2v3LHWWnqCP6kE43F4oLrJRIYCskyAIKGtGrsbM3dzPWdxH3ZOmzHyUNFC00"
             track_id = "149"
             while (_status.value?.result?.status != "granted" && _status.value?.result?.status != "denied" && counter != 5) {
                 _status.value = FreeApi.retrofitService.getFreeStatus(track_id)
                 sleep(10000)
                 counter = counter + 1
                 statusvalue = _status.value?.result?.status.toString()
             }
             if (statusvalue == "granted") {
                 val data = FreeApi.retrofitService.getFreeChallenge()
                 text = data.result.challenge
                 Log.d("Challenge", text)
                 val algorithm = "HmacSHA1"
                 val keySpec = SecretKeySpec(app_token.toByteArray(), algorithm)
                 val mac = Mac.getInstance(algorithm)
                 mac.init(keySpec)
                 _sign.value = mac.doFinal(text.toByteArray())
                     .joinToString("") { String.format("%02x", it and 255.toByte()) }
                 Log.d("HmacSHA1", _sign.value.toString())
             }
             var blacklistjob = viewModelScope.launch {
             val freeSession = FreeSession("check", "0.0.1", _sign.value.toString())
             //Obtain Session token Value
             val stoken = FreeApi.retrofitService.postFreeSession(freeSession)
             sessiontokenvalue = stoken.result.session_token
             Log.d("sessionToken", sessiontokenvalue)
         }
         _progress.value = "blacklisting"
         //BlackList the Mac

             try {
                 val freePostBlackList = FreePostBlackList("48:88:CA:42:77:93", "blacklist")
                 val headers = HashMap<String, String>()
                 headers["Content-Type"] = "application/json"
                 headers["X-Fbx-App-Auth"] = sessiontokenvalue
                 val freePostBlackListResponse =
                     FreeApi.retrofitService.postFreeBlackList(headers, freePostBlackList)

                 Log.d("BlackListResponse", freePostBlackListResponse.toString())
                 _progress.value = "blacklisted"

             } catch (e: Exception) {
                 Log.e("unabletorespond", e.toString())
                 _progress.value = "cant blacklist"
             }

         }
         //  sleep(1500)
         //    blackListLaunch.cancel()

     }

}