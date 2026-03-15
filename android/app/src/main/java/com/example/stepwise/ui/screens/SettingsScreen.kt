package com.example.stepwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepwise.ui.theme.*

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text("Settings", style = MaterialTheme.typography.headlineMedium, color = TextPrimary)
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            item {
                SettingsSection("Goal Settings") {
                    SettingsRow("Base Step Goal", "8,000") {
                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(20.dp), tint = TextSecondary)
                    }
                    SettingsToggleRow(
                        "Goal Adjustment", 
                        "Auto-adjusts based on your weekly average",
                        true
                    )
                }
            }

            item {
                SettingsSection("Spotify") {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(40.dp).background(SpotifyGreen, CircleShape), contentAlignment = Alignment.Center) {
                            Text("S", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Connected as deekshitha", fontWeight = FontWeight.Bold, color = TextPrimary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(8.dp).background(PrimaryGreen, CircleShape))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Connected", fontSize = 12.sp, color = TextSecondary)
                            }
                        }
                        Text("Disconnect", color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            item {
                SettingsSection("Data") {
                    SettingsActionRow("Reset All Data", Color.Red)
                    SettingsActionRow("Export History", TextPrimary)
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Grove v1.0", style = MaterialTheme.typography.labelMedium, color = TextSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🌿", fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(title, style = MaterialTheme.typography.labelMedium, color = TextSecondary, modifier = Modifier.padding(bottom = 12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun SettingsRow(label: String, value: String, trailing: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Medium, color = TextPrimary)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(value, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(end = 8.dp))
            trailing()
        }
    }
}

@Composable
fun SettingsToggleRow(label: String, description: String, checked: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(label, fontWeight = FontWeight.Medium, color = TextPrimary)
            Text(description, fontSize = 12.sp, color = TextSecondary)
        }
        Switch(
            checked = checked,
            onCheckedChange = {},
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = PrimaryGreen)
        )
    }
}

@Composable
fun SettingsActionRow(label: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontWeight = FontWeight.Medium, color = color)
    }
}
