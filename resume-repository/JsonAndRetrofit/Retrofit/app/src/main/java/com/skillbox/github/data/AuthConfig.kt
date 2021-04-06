package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "3dcf15e88c96cb4b8a69"
    const val CLIENT_SECRET = "88d644c27719a4e5ebe3813d38f19ac0924df288"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}