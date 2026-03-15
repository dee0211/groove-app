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

data class Song(val title: String, val artist: String, val duration: String, val color: Color)

@Composable
fun MusicScreen() {
    val songs = listOf(
        Song("Blinding Lights", "The Weeknd", "3:20", Color(0xFFFFCDD2)),
        Song("Levitating", "Dua Lipa", "3:23", Color(0xFFC8E6C9)),
        Song("As It Was", "Harry Styles", "2:37", Color(0xFFBBDEFB)),
        Song("Stay", "The Kid LAROI", "2:21", Color(0xFFFFF9C4)),
        Song("Shivers", "Ed Sheeran", "3:27", Color(0xFFE1BEE7))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp)
    ) {
        // Top Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🎵", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "1,657 steps to go · ~18 min walk",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Text("Generate Playlist", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Playlist Card
        Card(
            modifier = Modifier.fillMaxWidth().weight(1f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Lavender.copy(alpha = 0.1f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Generated Playlist", fontWeight = FontWeight.Bold, color = TextPrimary)
                    Spacer(modifier = Modifier.weight(1f))
                    // Waveform placeholder
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(4) { i ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 1.dp)
                                    .width(2.dp)
                                    .height(if (i % 2 == 0) 12.dp else 8.dp)
                                    .background(PrimaryGreen)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(songs) { song ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(song.color, RoundedCornerShape(8.dp))
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(song.title, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text(song.artist, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
                            }
                            Text(song.duration, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SpotifyGreen)
        ) {
            Text("Open in Spotify", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Based on your most played tracks this week",
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
