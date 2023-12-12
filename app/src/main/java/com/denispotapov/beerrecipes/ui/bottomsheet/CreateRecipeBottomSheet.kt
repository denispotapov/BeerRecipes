package com.denispotapov.beerrecipes.ui.bottomsheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.denispotapov.beerrecipes.R
import com.denispotapov.beerrecipes.ui.compose_ui.styles.TextBodyMedium
import com.denispotapov.beerrecipes.ui.compose_ui.styles.TextFootnoteRegular
import com.denispotapov.beerrecipes.ui.compose_ui.textfield.CustomTextField
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

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                BottomSheetHeader(stringRes = R.string.recipe_create, model = null)

                Text(
                    text = stringResource(id = R.string.recipe_ingredients),
                    style = TextBodyMedium(R.color.black),
                )

                Divider(
                    Modifier
                        .padding(vertical = 16.dp),
                    color = colorResource(id = R.color.separator),
                    thickness = 0.5.dp
                )

                Text(
                    text = stringResource(id = R.string.recipe_ingredients_grain),
                    style = TextFootnoteRegular(color = R.color.grey)
                )

                CustomTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    // .weight(1f)
                    /* .onFocusChanged { focusState ->
                         if (focusState.isFocused) {
                             onTextFieldClicked.invoke()
                         }
                     },*/
                    value = "Солод",
                    onValueChange = { /*pollAnswer.answer.value = it*/ },
                    placeholder = {
                        Text(
                            text = "Солод",
                            style = TextBodyMedium(R.color.separator)
                        )
                    },
                    textStyle = TextBodyMedium(R.color.black),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.grey),
                        cursorColor = colorResource(id = R.color.grey),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
                )

                Divider(
                    Modifier
                        .padding(vertical = 16.dp),
                    color = colorResource(id = R.color.separator),
                    thickness = 0.5.dp
                )

                Text(
                    text = stringResource(id = R.string.recipe_ingredients_hope),
                    style = TextFootnoteRegular(color = R.color.grey)
                )

                Divider(
                    Modifier
                        .padding(vertical = 16.dp),
                    color = colorResource(id = R.color.separator),
                    thickness = 0.5.dp
                )

            }

        },
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = colorResource(id = R.color.background_card)
    ) {}

}