package com.example.stepwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Note: In a real app, you'd add the font files to res/font
// val PlusJakartaSans = FontFamily(
//     Font(R.font.plus_jakarta_sans_regular, FontWeight.Normal),
//     Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold),
//     Font(R.font.plus_jakarta_sans_extrabold, FontWeight.ExtraBold)
// )

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default, // PlusJakartaSans
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default, // PlusJakartaSans
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // PlusJakartaSans
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default, // PlusJakartaSans
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
