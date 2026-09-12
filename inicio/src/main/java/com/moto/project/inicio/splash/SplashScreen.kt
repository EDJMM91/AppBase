package com.moto.project.inicio.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moto.project.theme.Primary
import com.moto.project.theme.OnPrimary
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.hiltViewModel

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by hiltViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContent {
            SplashScreen(
                state = splashViewModel.splashState,
                onTitleChange = { splashViewModel.updateTitle(it) },
                onSubtitleChange = { splashViewModel.updateSubtitle(it) }
            )
        }
    }
}

@Composable
fun SplashScreen(
    state: SplashState,
    onTitleChange: (String) -> Unit = {},
    onSubtitleChange: (String) -> Unit = {}
) {
    var titulo by remember { mutableStateOf("AppBase") }
    var subtitulo by remember { mutableStateOf("Cargando...") }

    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(OnPrimary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .rotate(rotation),
                color = OnPrimary,
                strokeWidth = 6.dp
            )
            Text(
                text = "v1.0",
                style = MaterialTheme.typography.bodySmall,
                color = OnPrimary,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = titulo,
            onValueChange = {
                titulo = it
                onTitleChange(it)
            },
            modifier = Modifier.fillMaxSize(0.8f),
            textStyle = MaterialTheme.typography.headlineMedium.copy(
                color = OnPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = OnPrimary
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = subtitulo,
            onValueChange = {
                subtitulo = it
                onSubtitleChange(it)
            },
            modifier = Modifier.fillMaxSize(0.6f),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = OnPrimary.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = OnPrimary
            ),
            singleLine = true
        )
    }
}
