package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Preview
@Composable
fun StyledCategoryManagerPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        StyledCategoryManager(
            modifier = Modifier
                .padding(20.dp),
            label = "Choose option",
            CategoryManagerProps(
                selectedIndex = remember { mutableIntStateOf(0) },
                options = mutableListOf("Option 1", "Option 2", "Option 3"),
                chosenCategories = remember { mutableStateListOf("Option 1", "Option 3") }
            )
        )
    }
}

data class CategoryManagerProps(
    val modifier: Modifier = Modifier,
    val selectedIndex: MutableState<Int>, // do not forget to initialize with remember
    val options: MutableList<String> = mutableListOf(),
    val chosenCategories: MutableList<String> = mutableListOf(),
    val onCreateCategory: () -> Unit = {}
)

@Composable
fun StyledCategoryManager(
    modifier: Modifier = Modifier,
    label: String = "label",
    props: CategoryManagerProps = CategoryManagerProps(
        selectedIndex = remember { mutableIntStateOf(0) }
    )
) {
    Column (
        modifier = modifier
    ){
        StyledDropDownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            labelText = label,
            StyledDropDownMenuProps(
                options = props.options,
                selectedIndex = props.selectedIndex,
                onCreateCategory = {
                    props.onCreateCategory()
                },
                onCategorySelected = { category ->
                    if (!props.chosenCategories.contains(category)) {
                        props.chosenCategories.add(category)
                    }
                }
            )
        )
        FlowRow(
            modifier = Modifier.padding(10.dp, 5.dp),
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp
        ) {
            props.chosenCategories.forEach { category ->
                Chip(
                    text = category,
                    onClick = { props.chosenCategories.remove(category) }
                )
            }
        }
    }
}