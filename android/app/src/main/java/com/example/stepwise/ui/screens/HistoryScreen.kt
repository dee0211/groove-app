package com.example.stepwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepwise.ui.theme.*

data class HistoryEntry(val date: String, val steps: Int, val goal: Int, val hit: Boolean)

@Composable
fun HistoryScreen() {
    val history = listOf(
        HistoryEntry("Today", 6843, 8500, false),
        HistoryEntry("Yesterday", 8800, 8500, true),
        HistoryEntry("Mar 13", 5400, 8500, false),
        HistoryEntry("Mar 12", 9100, 8500, true),
        HistoryEntry("Mar 11", 7200, 7000, true),
        HistoryEntry("Mar 10", 8200, 7000, true),
        HistoryEntry("Mar 09", 7500, 7000, true),
        HistoryEntry("Mar 08", 9000, 7000, true),
        HistoryEntry("Mar 07", 6800, 7000, false),
        HistoryEntry("Mar 06", 7100, 7000, true)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "8 out of 10 goals hit this month 💪",
            style = MaterialTheme.typography.headlineMedium,
            color = TextPrimary,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Text(
                    "This Week",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(history.take(5)) { entry ->
                HistoryCard(entry)
            }
            item {
                Text(
                    "Last Week",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }
            items(history.drop(5)) { entry ->
                HistoryCard(entry)
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun HistoryCard(entry: HistoryEntry) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(entry.date, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("Goal: ${entry.goal}", style = MaterialTheme.typography.labelMedium, color = TextSecondary)
            }
            
            Text(
                entry.steps.toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = TextPrimary,
                fontSize = 20.sp
            )

            Surface(
                color = if (entry.hit) PrimaryGreen.copy(alpha = 0.1f) else Color.LightGray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (entry.hit) "✓" else "✕",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    color = if (entry.hit) PrimaryGreen else TextSecondary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
