package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun StyledOutlinedTextFieldPreview(){
    val textFieldValueFillMaxWidth = remember { mutableStateOf("") }
    val textFieldValueNoSupportingText = remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column {
            StyledOutlinedTextField(
                modifier = Modifier
                    .padding(20.dp),
                props = StyledOutlinedTextFieldProps(
                    textFieldValue = textFieldValueFillMaxWidth
                )
            )
            Row {
                StyledOutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                )
                StyledOutlinedTextField(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .weight(1f),
                    props = StyledOutlinedTextFieldProps(
                        textFieldValue = textFieldValueNoSupportingText,
                        supportingText = "supporting text"
                    )
                )
            }
        }
    }
}

data class StyledOutlinedTextFieldProps(
    val textFieldValue: MutableState<String> = mutableStateOf(""),
    val labelText: String = "label",
    val placeholderText: String = "",
    val supportingText: String = "",
    val readOnly: Boolean = false,
    val trailingIcon: @Composable() (() -> Unit)? = null
)

@Composable
fun StyledOutlinedTextField(
    modifier: Modifier = Modifier,
    props: StyledOutlinedTextFieldProps = StyledOutlinedTextFieldProps()
    // isError: MutableState<Boolean> = mutableStateOf(false),
    // validator: (String) -> Boolean = {string -> string.isNotEmpty()},
    // immediateValidation: Boolean = false,
    // keyboardType: KeyboardType = KeyboardType.Text
    ){
    var isTextFieldFocused by remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isTextFieldFocused = focusState.isFocused
            },
        value = props.textFieldValue.value,
        onValueChange = { props.textFieldValue.value = it },
        label = { Text(text = props.labelText) },
        placeholder = { Text(text = props.placeholderText)},
        readOnly = props.readOnly,
        singleLine = true,
        // isError = isError.value,
        // textStyle = ,
        supportingText = { Text(text = props.supportingText)},
        /*
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isTextFieldFocused = false  // Ensure validation happens on "Done"
                if(!immediateValidation)
                    isError.value = validator(textState.value)
            }
        )
        */
        trailingIcon = props.trailingIcon
    )
    /*
    if(immediateValidation){
        LaunchedEffect(textState.value) {
            isError.value = validator(textState.value)
        }
    }
    */
}

/*
    colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Secondary100,
            unfocusedBorderColor = Secondary100,
            errorBorderColor = ConfirmRed,

            focusedLabelColor = FontOnBackground,
            unfocusedLabelColor = FontOnBackground,
            errorLabelColor = FontOnBackground,

            focusedPlaceholderColor = OnSurfaceSecondary,
            unfocusedPlaceholderColor = OnSurfaceSecondary,
            errorPlaceholderColor = OnSurfaceSecondary,

            focusedSupportingTextColor = OnBackground,
            unfocusedSupportingTextColor = OnBackground,
            errorSupportingTextColor = ConfirmRed,

            focusedTextColor = OnSurfaceSecondary,
            unfocusedTextColor = OnSurfaceSecondary,
            errorTextColor = OnSurfaceSecondary,

            cursorColor = FontOnBackground,
            errorCursorColor = FontOnBackground
        ),
        shape = RoundedCornerShape(5.dp),
 */