package com.example.invoiceapp.view.composes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UnifiedLine(
    modifier: Modifier = Modifier,
    leftCompose: @Composable (RowScope.() -> Unit),
    rightCompose: @Composable (RowScope.() -> Unit),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftCompose()
            Spacer(Modifier.weight(1f, true))
            rightCompose()
        }
    }
    Divider(
        modifier = Modifier.padding(bottom = 5.dp),
        color = Color.Gray,
        thickness = 1.dp
    )
}