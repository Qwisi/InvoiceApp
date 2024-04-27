package com.example.invoiceapp.view.composes

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.invoiceapp.ui.theme.Background
import com.example.invoiceapp.ui.theme.Secondary100
import com.example.invoiceapp.ui.theme.SubText100

@Composable
fun LogoGrid(
    modifier: Modifier,
    imageUris: MutableList<Uri>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(imageUris) { uri ->
            LogoGridItem(
                imageUri = uri,
                onRemove = {
                    imageUris.remove(uri)
                }
            )
        }
    }
}

@Composable
fun LogoGridItem(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    onRemove: () -> Unit
) {
    Surface(
        modifier = modifier.size(95.dp).padding(end = 5.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Secondary100)
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.padding(8.dp)
        ) {
            imageUri?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = "Icon Image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
            }
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove Icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRemove() }
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Background, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp),
                tint = SubText100
            )
        }
    }
}
