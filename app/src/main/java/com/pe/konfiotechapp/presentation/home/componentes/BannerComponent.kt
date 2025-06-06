package com.pe.konfiotechapp.presentation.home.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.coredesign.components.TextComponent

@Composable
fun BannerComponent (
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(12.dp)
            .background(Color.Green),
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ){
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    textValue = it,
                    fontSizeValue = 24.sp
                )
            }
            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    textValue = it
                )
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    apiLevel = 34
)
@Composable
fun BannerComponentPreview(){
    BannerComponent(
        title = "Dogs We Love",
        description = "Ver mas ...")
}