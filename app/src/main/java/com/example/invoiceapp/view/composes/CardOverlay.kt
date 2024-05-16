package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun StyledComponentOverlayPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        StyledComponentOverlay {
            StyledAddCard(
                modifier = Modifier.padding(20.dp),
                onConfirm = {}, onDismiss = {},
            )
        }
    }
}

@Composable
fun StyledComponentOverlay(
    component: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { /* absorb clicks */ },
        contentAlignment = Alignment.Center
    ) {
        component()
    }
}