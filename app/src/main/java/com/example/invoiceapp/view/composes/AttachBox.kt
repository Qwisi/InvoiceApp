package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.invoiceapp.R
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.ui.theme.Background
import com.example.invoiceapp.ui.theme.FontOnBackground
import com.example.invoiceapp.ui.theme.HeadLineRobotoRegular
import com.example.invoiceapp.ui.theme.SubText100
import com.example.invoiceapp.ui.theme.SubText50
import com.example.invoiceapp.ui.theme.TextFieldLabel

@Preview
@Composable
fun StyledAttachBoxPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ){
        StyledAttachBox(
            modifier = Modifier
                .padding(20.dp)
        ) { }
    }
}

@Composable
fun StyledAttachBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .padding(top = 7.dp)
            .dashedBorder(1.dp, SubText50, 4.dp)
            .clickable { onClick() }
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 40.dp)
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_download_file),
                contentDescription = "download icon",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Upload a logo", style = HeadLineRobotoRegular, color = FontOnBackground)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Support png, jpg, jpeg", style = TextFieldLabel, color = SubText100)
        }

        // Overlay Text (seen just on greeting screen)
        Surface(
            color = Background,
            modifier = Modifier
                .offset(x = (15).dp,y = (-7).dp) // Adjust this as needed to move your label into the desired position on the y-axis.
        ) {
            Text(
                text = "Attach image (optional)",
                style = TextFieldLabel,
                color = FontOnBackground
            )
        }
    }
}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)