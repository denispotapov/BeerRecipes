package com.denispotapov.beerrecipes.ui.bottomsheet

import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.denispotapov.beerrecipes.R
import com.denispotapov.beerrecipes.ui.compose_ui.styles.TextHeadline

@Composable
fun <T> BottomSheetHeader(
    stringRes: Int,
    alpha: Float = 1f,
    isEnabled: Boolean = false,
    isVisibleIconAccept: Boolean = false,
    model: T?,
    onAcceptIconClicked: ((T) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
            .wrapContentHeight()
            .fillMaxWidth(),
       // verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.clickable {
               // closeBottomSheetHelper.closeBottomSheet()
            },
            painter = painterResource(id = R.drawable.ic_close_24),
            tint = Color.Black,
            contentDescription = null // decorative element
        )
        Text(
            text = if (stringRes != View.NO_ID) stringResource(id = stringRes) else "",
            style = TextHeadline(color = R.color.black),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
      //  if (isVisibleIconAccept) {
            Icon(
                modifier = Modifier
                    .alpha(alpha)
                    .clickable(enabled = isEnabled) {
                        model?.let { onAcceptIconClicked?.invoke(it) }
                        //closeBottomSheetHelper.closeBottomSheet()
                    },
                painter = painterResource(id = R.drawable.ic_checkmark_24),
                tint = colorResource(id = R.color.green),
                contentDescription = null
            )
        //}
    }
}