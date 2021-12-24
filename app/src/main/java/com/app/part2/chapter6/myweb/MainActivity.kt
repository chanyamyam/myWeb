package com.app.part2.chapter6.myweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }
    private val forwardButton: ImageButton by lazy {
        findViewById(R.id.goForwardButton)
    }
    private val backButton: ImageButton by lazy {
        findViewById(R.id.backButton)
    }
    private val homeButton: ImageButton by lazy {
        findViewById(R.id.homeButton)
    }
    private val addressBar: EditText by lazy {
        findViewById(R.id.addressBar)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        if(webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()

    }
    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(HOME_URL)
        }
    }

    private fun bindViews() {
        addressBar.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE) {
                webView.loadUrl(textView.text.toString())
            }
            return@setOnEditorActionListener false
        }

        homeButton.setOnClickListener {
            webView.loadUrl(HOME_URL)
        }

        backButton.setOnClickListener {
            webView.goBack()
        }
        forwardButton.setOnClickListener {
            webView.goForward()
        }
    }

    companion object {
        private const val HOME_URL = "https://www.google.com"
    }
}