package com.example.stepwise.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepwise.ui.theme.*

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Greeting
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Good evening, let's finish strong 🌿",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Progress Ring
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(240.dp)) {
                drawArc(
                    color = Lavender,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = PrimaryGreen,
                    startAngle = -90f,
                    sweepAngle = (6843f / 8500f) * 360f,
                    useCenter = false,
                    style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "6,843",
                    style = MaterialTheme.typography.displayLarge,
                    color = TextPrimary
                )
                Text(
                    text = "steps today",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Stats Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem("Pace", "92", "steps/min")
                StatItem("Streak", "5", "days 🔥", LemonadeYellow)
                StatItem("Label", "Push", "Day")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Log Steps Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Lavender)
        ) {
            Text("Log Steps Manually", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Motivational Line
        Text(
            text = "You're 1,657 steps away — about a 18 min walk",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun StatItem(label: String, value: String, unit: String, badgeColor: androidx.compose.ui.graphics.Color? = null) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(value, style = MaterialTheme.typography.headlineMedium, color = TextPrimary)
            if (badgeColor != null) {
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .background(badgeColor, CircleShape)
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(unit, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Spacer(modifier = Modifier.width(4.dp))
                Text(unit, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
            }
        }
    }
}
