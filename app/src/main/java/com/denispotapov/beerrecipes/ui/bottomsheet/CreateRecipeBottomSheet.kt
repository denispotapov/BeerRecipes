package com.denispotapov.beerrecipes.ui.bottomsheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.denispotapov.beerrecipes.R
import com.denispotapov.beerrecipes.data.models.Ingredient
import com.denispotapov.beerrecipes.ui.compose_ui.styles.TextBodyMedium
import com.denispotapov.beerrecipes.ui.compose_ui.styles.TextFootnoteRegular
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateRecipeBottomSheet(bottomSheetState: MutableState<Boolean>) {

    val scope = rememberCoroutineScope()
    val lazyColumnState = rememberLazyListState()

    val listMalts = remember {
        listOf(Ingredient.Malt(mutableStateOf(""))).toMutableStateList()
    }

    val listHops = remember {
        listOf(Ingredient.Hop(mutableStateOf(""))).toMutableStateList()
    }

    val listYeast = remember {
        listOf(Ingredient.Yeast(mutableStateOf(""))).toMutableStateList()
    }

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

            BottomSheetHeader(stringRes = R.string.recipe_create, model = null)

            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    // .background(colorResource(R.color.materials_navigation_bar))
                    .pointerInput(Unit) {
                        /*detectDragGestures { _, _ ->
                            onDragBottomSheetCallback.invoke()
                        }*/
                    },
                //.dragContainer(dragDropState),
                state = lazyColumnState
            ) {

                item {
                    Text(
                        text = stringResource(id = R.string.recipe_ingredients),
                        style = TextBodyMedium(R.color.black),
                    )
                }

                addDivider()

                item {
                    Text(
                        text = stringResource(id = R.string.recipe_ingredients_grain),
                        style = TextFootnoteRegular(color = R.color.grey)
                    )
                }

                items(
                    items = listMalts
                ) {
                    TextFieldItem(it, listMalts, R.string.recipe_malt)
                }

                addIngredient(R.string.recipe_add_malt, listMalts, Ingredient.Malt(mutableStateOf("")))


                addDivider()

                item {
                    Text(
                        text = stringResource(id = R.string.recipe_ingredients_hope),
                        style = TextFootnoteRegular(color = R.color.grey)
                    )
                }

                items(
                    items = listHops
                ) {
                    TextFieldItem(it, listHops, R.string.recipe_ingredients_hope)
                }

                addIngredient(R.string.recipe_add_hop, listHops, Ingredient.Hop(mutableStateOf("")))

                addDivider()

                item {
                    Text(
                        text = stringResource(id = R.string.recipe_ingredients_yeast),
                        style = TextFootnoteRegular(color = R.color.grey)
                    )
                }

                items(
                    items = listYeast
                ) {
                    TextFieldItem(it, listYeast, R.string.recipe_ingredients_yeast)
                }

            }

        },
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = colorResource(id = R.color.background_card)
    ) {}

}

fun LazyListScope.addDivider() {
    item {
        Divider(
            Modifier
                .padding(vertical = 16.dp),
            color = colorResource(id = R.color.separator),
            thickness = 0.5.dp
        )
    }
}

fun <T> LazyListScope.addIngredient(
    stringResource: Int,
    listIngredient: SnapshotStateList<T>,
    model: T
) {

    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable { listIngredient.add(model) }

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_circle_24_fill_accent),
                tint = colorResource(id = R.color.green),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(id = stringResource),
                color = colorResource(id = R.color.green)
            )
        }
    }
}

/*fun LazyListScope.addIngredient(string: Int, listIngredient: SnapshotStateList<*>) {

    // listIngredient.

    val ingredientMalt = Ingredient.Malt(mutableStateOf(""))
    val ingredient = Ingredient.Hop(mutableStateOf(""))

    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                *//*.pointerInput(Unit) {
                    detectDragGestures { _, _ ->
                        onDragBottomSheetCallback.invoke()
                    }
                }*//*

                .padding(top = 16.dp)
                .clickable {
                    listIngredient.add(ingredientMalt)
                    *//*coroutineScope.launch {
                        listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
                        //TODO исправить кейс, когда фокус стоит на предыдущих полях
                        focusManager.moveFocus(FocusDirection.Next)
                    }*//*

                }

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_circle_24_fill_accent),
                tint = colorResource(id = R.color.green),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(id = string),
                color = colorResource(id = R.color.green)
            )
        }
    }
}*/