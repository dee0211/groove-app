package com.example.stepwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stepwise.ui.screens.*
import com.example.stepwise.ui.theme.StepWiseTheme

sealed class Screen(val route: String, val label: String, val iconFilled: ImageVector, val iconOutlined: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Week : Screen("week", "Week", Icons.Filled.DateRange, Icons.Outlined.DateRange)
    object History : Screen("history", "History", Icons.Filled.List, Icons.Outlined.List)
    object Music : Screen("music", "Music", Icons.Filled.PlayArrow, Icons.Outlined.PlayArrow)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepWiseTheme {
                val navController = rememberNavController()
                val items = listOf(
                    Screen.Home,
                    Screen.Week,
                    Screen.History,
                    Screen.Music,
                    Screen.Settings
                )

                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                                NavigationBarItem(
                                    icon = { 
                                        Icon(
                                            if (selected) screen.iconFilled else screen.iconOutlined,
                                            contentDescription = null
                                        ) 
                                    },
                                    label = { Text(screen.label) },
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        indicatorColor = MaterialTheme.colorScheme.surface
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
                        composable(Screen.Home.route) { HomeScreen() }
                        composable(Screen.Week.route) { WeekScreen() }
                        composable(Screen.History.route) { HistoryScreen() }
                        composable(Screen.Music.route) { MusicScreen() }
                        composable(Screen.Settings.route) { SettingsScreen() }
                    }
                }
            }
        }
    }
}
