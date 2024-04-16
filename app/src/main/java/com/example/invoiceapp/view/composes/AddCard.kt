package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun StyledAddCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        StyledAddCard(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Composable
fun StyledAddCard(
    modifier: Modifier = Modifier,
    styledOutlinedTextFieldProps: StyledOutlinedTextFieldProps = StyledOutlinedTextFieldProps(),
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val textFieldState = remember { mutableStateOf("") }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Add something"
            )
            StyledOutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp),
                props = StyledOutlinedTextFieldProps(
                    textFieldValue = textFieldState,
                    labelText = "New category"  // Example label
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                StyledButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    isOutlined = true
                ) { onDismiss() }
                StyledButton(
                    modifier = Modifier.weight(1f),
                    text = "Add",
                    isEnabled = textFieldState.value.isNotEmpty()
                ) { onConfirm(textFieldState.value) }
            }
        }
    }
}