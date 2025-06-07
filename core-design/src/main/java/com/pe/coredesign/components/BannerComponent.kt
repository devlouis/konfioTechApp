package com.pe.coredesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.pe.coredesign.theme.AppColors

@Composable
fun BannerComponent (
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    bannerOnClick: () -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                bannerOnClick()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(AppColors.Primary, AppColors.Secondary)
                )
            )

    ) {
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop
            )
        }

        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ){
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = it,
                    fontSizeValue = 24.sp,
                    textColorValue = AppColors.WhiteColor
                )
            }
            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = it,
                    fontSizeValue = 14.sp,
                    textColorValue = AppColors.WhiteColor
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
        description = "Ver mas ...",
        imageUrl = null,
        bannerOnClick = {

        }
    )
}