package com.example.stepwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stepwise.ui.theme.*

@Composable
fun WeekScreen() {
    val weekData = listOf(
        "Mon" to 7200,
        "Tue" to 9100,
        "Wed" to 5400,
        "Thu" to 8800,
        "Fri" to 6843,
        "Sat" to 0,
        "Sun" to 0
    )
    val goal = 8500

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp)
    ) {
        Text(
            text = "Weekly View",
            style = MaterialTheme.typography.headlineMedium,
            color = TextPrimary
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        // Chart Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    weekData.forEach { (day, steps) ->
                        val barColor = when {
                            day == "Fri" -> LemonadeYellow
                            steps >= goal -> PrimaryGreen
                            else -> Lavender
                        }
                        val heightFactor = if (steps > 0) (steps.toFloat() / 10000f).coerceIn(0.1f, 1f) else 0.05f
                        
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(32.dp)
                                    .fillMaxHeight(heightFactor)
                                    .background(barColor, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(day, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your average this week is 7,469 steps",
            style = MaterialTheme.typography.bodyLarge,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Personal Best Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🏅", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Personal Best · Tuesday · 9,100 steps",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "↑ Trending up this week",
            style = MaterialTheme.typography.bodyLarge,
            color = PrimaryGreen,
            fontWeight = FontWeight.Bold
        )
    }
}
