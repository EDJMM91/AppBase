package com.moto.project.inicio.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.moto.project.theme.Primary
import com.moto.project.theme.OnPrimary
import dagger.hilt.android.AndroidEntryPoint
import com.moto.project.inicio.splash.SplashViewModel
import androidx.lifecycle.viewmodel.compose.hiltViewModel

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by hiltViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContent { SplashScreen(state = splashViewModel.splashState) }
    }
}

@Composable
fun SplashScreen(state: SplashState) {
    Column(modifier = Modifier.fillMaxSize().background(Primary), horizontalAlignment = Alignment.CenterHorizontally, verticalAlignment = Alignment.CenterVertically) {
        Text("AppBase", color = OnPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        CircularProgressIndicator(color = OnPrimary)
    }
}
