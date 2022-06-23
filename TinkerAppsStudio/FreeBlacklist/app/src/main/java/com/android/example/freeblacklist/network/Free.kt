package com.android.example.freeblacklist.network

import com.squareup.moshi.Json


data class FreeStatus ( @Json(name = "success") val success :Boolean,
@Json(name = "result") val result : FreeResult )

data class FreeChallenge (
    @Json(name = "success") val success :Boolean,
    @Json(name = "result") val result : FreeChallengePassword
        )

data class FreeChallengePassword (
    @Json(name = "challenge") val challenge: String
)
data class FreeResult (
    @Json(name = "status") val status :String,
    @Json(name = "challenge") val challenge : String
        )
data class FreePostBlackListResponse (
    @Json(name = "success") val success: Boolean
        )

data class FreeRemoveBlackListResponse (
    @Json(name = "success") val success: Boolean
)

data class FreePostBlackList (
    @Json(name = "mac") val mac :String,
    @Json(name = "type") val type : String
)



data class FreeSession(
    @Json(name = "app_id") val app_id :String,
    @Json(name = "app_version") val app_version : String,
    @Json(name = "password") val password : String
)

data class FreeSessionToken(
    @Json(name = "result") val result : Value
)

data class Value(
    @Json(name = "session_token") val session_token : String
)

data class FreeResponse (
    @Json(name = "success") val success :Boolean,
    @Json(name = "result") val result : FreeToken
)

data class FreePost(
    @Json(name = "app_id") val app_id :String,
    @Json(name = "app_name") val app_name : String,
    @Json(name = "app_version") val app_version : String,
    @Json(name = "device_name") val device_name : String
)

data class FreeToken (
    @Json(name = "app_token") val app_token :String,
    @Json(name = "track_id") val track_id : String
)