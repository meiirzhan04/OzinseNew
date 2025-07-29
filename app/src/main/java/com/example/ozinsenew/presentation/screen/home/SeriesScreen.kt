package com.example.ozinsenew.presentation.screen.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Preview
@Composable
fun SeriesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = "Бейне сабақ",
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )

        YouTubeVideoPlayer(
            videoId = "Wsu6YoAEExo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .aspectRatio(16f / 9f)
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YouTubeVideoPlayer(videoId: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.domStorageEnabled = true

                val html = """
                    <!DOCTYPE html>
                    <html>
                    <body style="margin:0;padding:0;">
                        <iframe width="100%" height="100%" 
                                src="https://www.youtube.com/embed/$videoId" 
                                frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                                allowfullscreen>
                        </iframe>
                    </body>
                    </html>
                """.trimIndent()

                loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
            }
        },
        modifier = modifier
    )
}
