package com.tamayo.jetrecycler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tamayo.jetrecycler.ui.theme.JetRecyclerTheme
import com.tamayo.jetrecycler.ui.theme.Luis
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetRecyclerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    SimpleRecyclerView()


                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleRecyclerView() {

    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val superHero: Map<String, List<SuperHero>> = getSuperHere().groupBy {
        it.publisher
    }


    Column {
        LazyColumn(
            state = rvState,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {

                superHero.forEach { (publisher, mySuper) ->

                    stickyHeader {
                        Text(text = publisher, textAlign = TextAlign.Center, color = Color.Red, modifier = Modifier.fillMaxWidth().background(Color.Gray))
                    }
                    items(mySuper) {
                        ItemHero(superHero = it) { superHero ->
                            println(superHero.superHeroName)
                        }

                    }
                }

            }, modifier = Modifier.weight(1f)
        )


        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if (showButton) {
            OutlinedButton(onClick = {
                coroutineScope.launch {
                    rvState.animateScrollToItem(0)

                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Clicking me")

            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Color.Green),
        onClick = { onItemSelected(superHero) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = superHero.superHeroName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Text(text = superHero.realName, color = Luis)
            Text(text = superHero.publisher)

        }
    }

}


fun getSuperHere(): List<SuperHero> {
    return listOf(
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman),
        SuperHero("Spider Man", "Peter Parker", "MARVEL", R.drawable.spiderman),
        SuperHero("Thor", "Thor Odinson", "MARVEL", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern)
    )

}