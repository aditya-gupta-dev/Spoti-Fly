package com.example.spotifly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.spotifly.components.Song
import com.example.spotifly.data.Track

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val track = Track(
            name = "Song Name",
            artistId = "something",
            artist = "Some Artist",
            duration = 1000L,
            id = "something",
            imageUrl = "https://i.scdn.co/image/ab67616d00001e0214cb8532d5e5999b8aa5ebd6"
        )

        setContent {
            var text = remember { mutableStateOf("") }

            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    }
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
//                    OutlinedTextField(value = text.value, onValueChange = { text.value = it })
                    Song(context = this@MainActivity, track = track)
                }
            }
        }
    }
}
