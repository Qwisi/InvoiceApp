package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import com.example.invoiceapp.R
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun StyledDropDownMenuPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        StyledDropDownMenu(
            modifier = Modifier.padding(20.dp),
            labelText = "Choose option",
            StyledDropDownMenuProps(
                selectedIndex = remember {mutableIntStateOf(0)}
            )
        )
    }
}

data class StyledDropDownMenuProps(
    val options: MutableList<String> = mutableListOf("Option 1", "Option 2", "Option 3"),
    val selectedIndex: MutableState<Int>, // do not forget to initialize with remember
    val onCreateCategory: () -> Unit = {},
    val onCategorySelected: (String) -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledDropDownMenu(
    modifier: Modifier = Modifier,
    labelText: String = "label",
    props: StyledDropDownMenuProps = StyledDropDownMenuProps(
        selectedIndex = remember {mutableIntStateOf(0)
    })
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }) {

        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = props.options.getOrElse(props.selectedIndex.value) { "" },
            onValueChange = { },
            label = { Text(text = labelText) },
            readOnly = true,
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) ImageVector.vectorResource(id = R.drawable.icon_expand_less)
                    else ImageVector.vectorResource(id = R.drawable.icon_expand_more),
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = Color.Unspecified
                )
            },
            /*colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Secondary100,
                unfocusedBorderColor = Secondary100,

                focusedLabelColor = FontOnBackground,
                unfocusedLabelColor = FontOnBackground,

                focusedTextColor = FontOnBackground,
                unfocusedTextColor = FontOnBackground
            )*/
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    //showAddCart = true
                    props.onCreateCategory()
                },
                text = { Text("+ Create category") }
            )
            props.options   //.filter { it != options[selectedIndex.value] }
                .forEachIndexed { index, option ->
                    DropdownMenuItem(
                        onClick = {
                            props.selectedIndex.value = index
                            props.onCategorySelected(option)
                            expanded = false
                        },
                        text = { Text(option) })
                }
        }
    }
        /*
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            SimpleAddCard(
                modifier = modifier,
                onConfirm = { newCategory ->
                    props.selectedIndex.value = props.options.size
                    props.options.add(newCategory)
                    //onCategorySelected(newCategory)
                    showAddCart = false
                },
                onDismiss = { showAddCart = false }
            )
        }
        */
}

/*
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Secondary100,
                unfocusedBorderColor = Secondary100,

                focusedLabelColor = FontOnBackground,
                unfocusedLabelColor = FontOnBackground,

                focusedTextColor = FontOnBackground,
                unfocusedTextColor = FontOnBackground
            )
 */
