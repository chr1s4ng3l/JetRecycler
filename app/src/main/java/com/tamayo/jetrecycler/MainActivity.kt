package com.tamayo.jetrecycler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tamayo.jetrecycler.ui.theme.JetRecyclerTheme

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


@Composable
fun SimpleRecyclerView() {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
        items(getSuperHere()) {

            ItemHero(superHero = it){ superHero ->
                println(superHero.superHeroName)
            }

        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemHero(superHero: SuperHero, onItemSelected:(SuperHero) -> Unit) {

    Card(
        modifier = Modifier
            .width(300.dp), border = BorderStroke(2.dp, Color.Red), onClick = {onItemSelected(superHero)}
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(text = superHero.superHeroName)
            Text(text = superHero.realName)
            Text(text = superHero.publisher)

        }
    }

}


fun getSuperHere(): List<SuperHero> {
    return listOf(
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman),
        SuperHero("Spider Man", "Peter Parker", "DC", R.drawable.spiderman),
        SuperHero("Thor", "Thor Odinson", "DC", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern)
    )

}