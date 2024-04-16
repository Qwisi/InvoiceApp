package com.example.invoiceapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.invoiceapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
/* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val InterMediumFontFamily = FontFamily(Font(R.font.inter_medium))

val ButtonTextStyle = TextStyle(
    fontFamily = InterMediumFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    letterSpacing = 0.005.em,
    lineHeight = 20.sp
)

val HeadLineRobotoMedium = TextStyle(
    fontFamily = InterMediumFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
    lineHeight = 28.sp
)

val RobotoRegularFontFamily = FontFamily(Font(R.font.roboto_regular))

val HeadLineRobotoRegular = TextStyle(
    fontFamily = RobotoRegularFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    letterSpacing = 0.em,
    lineHeight = 28.sp
)

val TextFieldLabel = TextStyle(
    fontFamily = RobotoRegularFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp
)

val TextFieldValue = TextStyle(
    fontFamily = RobotoRegularFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.005.em,
    lineHeight = 24.sp
)
