package com.example.ozinsenew.presentation.components.video

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay


@Composable
fun CustomVideoPlayer(
    context: Context,
    videoUrl: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUrl)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            currentPosition = exoPlayer.currentPosition
            duration = exoPlayer.duration
            delay(500)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        IconButton(
            onClick = { onBack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x80000000))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home of tulips", color = Color.White)
            Text("1 episode", color = Color.LightGray, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    formatTime(currentPosition),
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Slider(
                    value = currentPosition.coerceAtLeast(0L).toFloat(),
                    onValueChange = {
                        exoPlayer.seekTo(it.toLong())
                        currentPosition = it.toLong()
                    },
                    valueRange = 0f..(duration.takeIf { it > 0 } ?: 1).toFloat(),
                    modifier = Modifier.weight(8f),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Magenta,
                        activeTrackColor = Color.Magenta
                    )
                )
                Text(
                    formatTime(duration),
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    exoPlayer.seekBack()
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                IconButton(onClick = {
                    isPlaying = !isPlaying
                    if (isPlaying) exoPlayer.play() else exoPlayer.pause()
                }) {
                    Icon(
                        if (isPlaying) Icons.Default.Check else Icons.Default.PlayArrow,
                        contentDescription = "Play/Pause",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }

                IconButton(onClick = {
                    exoPlayer.seekForward()
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Forward",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
