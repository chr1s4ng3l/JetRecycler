package com.tamayo.jetrecycler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

    val itemList = listOf<Int>(1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,12,3,4,5,6,78,6,7,8,9,1,2,3,4,5,6,7,8,9,12,3,4,5,6,78)
    LazyColumn{

        items(itemList){
            Text(text = "Your number is $it")
        }

    }

}