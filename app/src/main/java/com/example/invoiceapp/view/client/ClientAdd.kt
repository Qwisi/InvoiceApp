package com.example.invoiceapp.view.client

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.view.composes.StyledOutlinedTextField
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps

@Composable
fun ClientAdd(
    modifier: Modifier = Modifier,
    clientProps: ClientProperties
){
    LazyColumn(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        item {
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                StyledOutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    props = clientProps.clientNameProps
                )
                StyledOutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp),
                    props = clientProps.clientSurnameProps
                )
            }
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientPhoneProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientCountryProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientCityProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientStreetProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientHouseNumberProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientPostCodeProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientDiscountProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientSpecialNoteProps
            )
        }
    }
}

data class ClientProperties(
    val clientNameProps: StyledOutlinedTextFieldProps,
    val clientSurnameProps: StyledOutlinedTextFieldProps,
    val clientPhoneProps: StyledOutlinedTextFieldProps,
    val clientCountryProps: StyledOutlinedTextFieldProps,
    val clientCityProps: StyledOutlinedTextFieldProps,
    val clientStreetProps: StyledOutlinedTextFieldProps,
    val clientHouseNumberProps: StyledOutlinedTextFieldProps,
    val clientPostCodeProps: StyledOutlinedTextFieldProps,
    val clientDiscountProps: StyledOutlinedTextFieldProps,
    val clientSpecialNoteProps: StyledOutlinedTextFieldProps
)

@Composable
fun clientProps(
    client: Client? = null
): ClientProperties {
    val clientName = remember(client?.name) { mutableStateOf(client?.name ?: "") }
    val clientSurname = remember(client?.surname) { mutableStateOf(client?.surname ?: "") }
    val clientPhone = remember(client?.phoneNumber) { mutableStateOf(client?.phoneNumber ?: "") }
    val clientCountry = remember(client?.country) { mutableStateOf(client?.country ?: "") }
    val clientCity = remember(client?.city) { mutableStateOf(client?.city ?: "") }
    val clientStreet = remember(client?.street) { mutableStateOf(client?.street ?: "") }
    val clientHouseNumber = remember(client?.houseNumber) { mutableStateOf(client?.houseNumber?.toString() ?: "") }
    val clientPostCode = remember(client?.postCode) { mutableStateOf(client?.postCode ?: "") }
    val clientDiscount = remember(client?.discount) { mutableStateOf(client?.discount?.toString() ?: "") }
    val clientSpecialNote = remember(client?.specialNotes) { mutableStateOf(client?.specialNotes ?: "") }

    return ClientProperties(
        clientNameProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientName,
            labelText = "Name",
            placeholderText = "John",
            supportingText = "enter valid name (no digits)",
            validator = {string -> string.contains(Regex("\\d")) },
            immediateValidation = true
        ),
        clientSurnameProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientSurname,
            labelText = "Surname",
            placeholderText = "Smith",
            supportingText = "enter valid surname (no digits)",
            validator = {string -> string.contains(Regex("\\d")) },
            immediateValidation = true
        ),
        clientPhoneProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientPhone,
            labelText = "Phone Number",
            placeholderText = "+1 234 567 8900",
            supportingText = "enter valid phone number (see format)",
            validator = {string -> string.matches(Regex("^\\+1 \\d{3} \\d{3} \\d{4}$")) },
            immediateValidation = true
        ),
        clientCountryProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientCountry,
            labelText = "Country",
            placeholderText = "USA",
            supportingText = "enter valid country name (no digits)",
            validator = {string -> string.contains(Regex("\\d")) },
            immediateValidation = true
        ),
        clientCityProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientCity,
            labelText = "City",
            placeholderText = "New York",
            supportingText = "enter valid city name (no digits)",
            validator = {string -> string.contains(Regex("\\d")) },
            immediateValidation = true
        ),
        clientStreetProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientStreet,
            labelText = "Street",
            placeholderText = "5th Avenue",
            supportingText = "enter valid street name"
        ),
        clientHouseNumberProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientHouseNumber,
            labelText = "House Number",
            placeholderText = "101",
            supportingText = "enter valid house number (no letters)",
            validator = {string -> string.all { it.isDigit() } },
            immediateValidation = true
        ),
        clientPostCodeProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientPostCode,
            labelText = "Post Code",
            placeholderText = "10001",
            supportingText = "enter valid post code (5 digits)",
            validator = {string -> string.length == 5 && string.all { it.isDigit() } },
            immediateValidation = true
        ),
        clientDiscountProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientDiscount,
            labelText = "Discount",
            placeholderText = "10%",
            supportingText = "enter valid discount (between 0 and 100%)",
            validator = {string -> string.toInt() >= 0 && string.toDouble() <= 100 },
            immediateValidation = true
        ),
        clientSpecialNoteProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientSpecialNote,
            labelText = "Special Note",
            placeholderText = "Enter any special instructions"
        )
    )
}