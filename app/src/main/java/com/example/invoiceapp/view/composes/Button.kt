package com.example.invoiceapp.view.composes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.invoiceapp.ui.theme.Background
import com.example.invoiceapp.ui.theme.ButtonTextStyle
import com.example.invoiceapp.ui.theme.Primary100
import com.example.invoiceapp.ui.theme.Primary20
import com.example.invoiceapp.ui.theme.Primary60

@Preview (showBackground = true)
@Composable
fun OutlinedDisableButton(){
    StyledButton(Modifier.padding(10.dp), "+ Create invoice", false, true) {}
}

@Preview (showBackground = true)
@Composable
fun OutlinedEnableButton(){
    StyledButton(Modifier.padding(10.dp), "+ Create invoice", true, true) {}
}

@Preview (showBackground = true)
@Composable
fun DisableButton(){
    StyledButton(Modifier.padding(10.dp), "+ Create invoice", false) {}
}

@Preview (showBackground = true)
@Composable
fun EnableButton(){
    StyledButton(Modifier.padding(10.dp), "+ Create invoice", true) {}
}

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    isOutlined: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = if (isOutlined) Color.Transparent else Primary100
    val textColor = if (isOutlined) Primary100 else Background
    val disabledBackgroundColor = if (isOutlined) Color.Transparent else Primary20
    val disabledTextColor = if (isOutlined) Primary60 else Background.copy(alpha = 0.7f)

    Button(
        onClick = { if (isEnabled) onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        border = if (isOutlined) BorderStroke(1.dp, if (isEnabled) textColor else disabledTextColor) else null,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = disabledBackgroundColor,
            disabledContentColor = disabledTextColor
        ),
        enabled = isEnabled,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
    ) {
        Box(modifier = Modifier.padding(vertical = 5.dp),
            contentAlignment = Alignment.Center) {
            Text(
                text = text,
                style = LocalTextStyle.current.merge(ButtonTextStyle),
            )
        }
    }
}
