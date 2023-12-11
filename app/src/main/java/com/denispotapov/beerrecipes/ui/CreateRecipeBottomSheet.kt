package com.denispotapov.beerrecipes.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.denispotapov.beerrecipes.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateRecipeBottomSheet(bottomSheetState: MutableState<Boolean>) {

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            true
        },
        skipHalfExpanded = true
    )

    LaunchedEffect(key1 = bottomSheetState.value, block = {
        if (bottomSheetState.value) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    })

    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        //    .background(colorResource(id = R.color.background_card)),
        sheetState = sheetState,
        sheetGesturesEnabled = false,
        sheetContent = {
            BackHandler(enabled = sheetState.isVisible) {
                scope.launch {
                    sheetState.hide()
                    bottomSheetState.value = false
                }
            }
            Text(
                modifier = Modifier
                    //.fillMaxWidth()
                    .fillMaxSize(),
                //  .align(alignment = Alignment.Center),
                textAlign = TextAlign.Center,
                text = "Создание рецепта"
            )

        },
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = colorResource(id = R.color.background_card)
    ) {}

}