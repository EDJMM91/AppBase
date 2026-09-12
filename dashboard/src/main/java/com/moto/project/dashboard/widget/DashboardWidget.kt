package com.moto.project.dashboard.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DashboardHeaderWidget(titulo: String, subtitulo: String = "") {
    Column(modifier = Modifier.padding(16.dp)) { Text(titulo, style = MaterialTheme.typography.headlineSmall) }
}
