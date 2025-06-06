package com.pe.konfiotechapp.presentation.home.componentes

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.pe.dogs.domain.Resource

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