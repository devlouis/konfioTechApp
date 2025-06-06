package com.pe.coredesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.coredesign.R
import com.pe.coredesign.theme.AppColors
import com.pe.coredesign.theme.AppColors.Background
import com.pe.coredesign.theme.AppColors.Primary
import com.pe.coredesign.theme.AppColors.WhiteColor

@Composable
fun AppToolbar(
    title: String? = null,
    isBackButtonVisible: Boolean = false,
    primaryButtonClicked: () -> Unit = {}
) {
    Row(modifier = Modifier
        .background(Primary)
        .systemBarsPadding()
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 0.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Icon(
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    primaryButtonClicked()
                },
            painter = painterResource(id = if (isBackButtonVisible) R.drawable.ic_arrow_back else R.drawable.ic_pets),
            contentDescription = if (isBackButtonVisible) "back button" else "User image",
            tint = WhiteColor

        )
        Spacer(modifier = Modifier.width(16.dp))
        title?.let {
            TextComponent(
                modifier = Modifier.wrapContentSize(),
                textValue = it,
                fontSizeValue = 20.sp,
                textColorValue = WhiteColor
            )
        }

    }
}

@Preview(
    showBackground = true,
    apiLevel = 34
)
@Composable
fun AppToolBarPreview(){
    AppToolbar()
}