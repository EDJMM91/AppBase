package com.moto.project.dashboard.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(uiState: DashboardUiState, onNavigateToPerfil: () -> Unit, onNavigateToConfiguracion: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(topBar = { TopAppBar(title = { Text(uiState.titulo) }) }, bottomBar = {
        NavigationBar {
            NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Filled.Home, "Inicio") }, label = { Text("Inicio") })
            NavigationBarItem(selected = false, onClick = onNavigateToPerfil, icon = { Icon(Icons.Filled.Person, "Perfil") }, label = { Text("Perfil") })
            NavigationBarItem(selected = false, onClick = onNavigateToConfiguracion, icon = { Icon(Icons.Filled.Settings, "Configuración") }, label = { Text("Configuración") })
        }
    }) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) { items(1) { DashboardItem() } }
    }
}

@Composable
fun DashboardItem() { Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) { Text("Dashboard Item") } }
