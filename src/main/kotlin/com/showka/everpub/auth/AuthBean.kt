package com.showka.everpub.auth

import com.evernote.auth.EvernoteAuth
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.EvernoteApi
import org.scribe.model.Token
import org.scribe.oauth.OAuthService
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

// NOTE ユーザー管理するならscope=sessionとすること
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
class AuthBean {

    val service: OAuthService
    lateinit var requestToken: String
    lateinit var requestTokenSecret: String
    lateinit var accessToken: Token
    lateinit var evernoteAuth: EvernoteAuth

    private val apiKey: String = System.getenv("apiKey")
    private val apiSecret: String = System.getenv("apiSecret")

    init {
        val environment = EvernoteApi()
        val authService = ServiceBuilder()
                .provider(environment)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback("http://localhost:8080/u01/g002")
                .build()
        this.service = authService
    }
}