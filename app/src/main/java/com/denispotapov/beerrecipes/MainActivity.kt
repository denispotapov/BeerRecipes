package com.denispotapov.beerrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denispotapov.beerrecipes.data.models.Recipe
import com.denispotapov.beerrecipes.ui.theme.BeerRecipesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomSheetExpandedState: MutableState<Boolean> = mutableStateOf(false)

        setContent {
            BeerRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.background)
                ) {
                    TestView(bottomSheetExpandedState)
                    CreateRecipeBottomSheet(bottomSheetExpandedState)
                }
            }
        }
    }

}

@Composable
fun TestView(bottomSheetExpandedState: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
        // .background(Color.Yellow)
    ) {

        val listRecipe = emptyList<Recipe>()

        if (listRecipe.isNotEmpty()) {
            val listState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {

            }
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    //.background(Color.Black)
                    .align(alignment = Alignment.Center),
                textAlign = TextAlign.Center,
                text = "Вы еще не создали ни одного рецепта"
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            onClick = { bottomSheetExpandedState.value = true },
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Добавить")
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateRecipeBottomSheet(bottomSheetState: State<Boolean>) {

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            false
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeerRecipesTheme {
        TestView(mutableStateOf(false))
    }
}