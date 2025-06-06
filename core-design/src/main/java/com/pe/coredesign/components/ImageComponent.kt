package com.pe.coredesign.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageComponent (
    modifier: Modifier,
    resourceValue: Int
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = resourceValue),
        contentDescription = "Icon"
    )
}