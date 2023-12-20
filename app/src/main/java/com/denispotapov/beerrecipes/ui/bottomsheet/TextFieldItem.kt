package com.denispotapov.beerrecipes.ui.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.denispotapov.beerrecipes.R
import com.denispotapov.beerrecipes.data.models.Malt
import com.denispotapov.beerrecipes.ui.compose_ui.styles.TextBodyMedium
import com.denispotapov.beerrecipes.ui.compose_ui.textfield.CustomTextField

@Composable
fun <T> TextFieldItem(model: T, list: SnapshotStateList<T>) {

    val malt = model as Malt
    val listMalt = list as SnapshotStateList<*>

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 120.dp)
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val isListDefault = listMalt.size == 1

        if (!isListDefault) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        listMalt.remove(malt)
                    },
                painter = painterResource(id = R.drawable.ic_minus_circle_24_fill_error),
                tint = colorResource(id = R.color.error),
                contentDescription = null
            )
        }

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                /*.onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        onTextFieldClicked.invoke()
                    }
                }*/
            value = malt.name.value,
            onValueChange = { malt.name.value = it },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.recipe_malt),
                    style = TextBodyMedium(R.color.tertiary)
                )
            },
            textStyle = TextBodyMedium(R.color.black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.quaternary),
                cursorColor = colorResource(id = R.color.quaternary),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        )

        if (!isListDefault) {
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp),
                painter = painterResource(id = R.drawable.ic_burger_fill),
                tint = colorResource(id = R.color.greys_grey),
                contentDescription = null
            )
        }
    }
}