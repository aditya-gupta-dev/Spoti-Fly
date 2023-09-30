package com.example.spotifly.components

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotifly.R
import com.example.spotifly.api.ApiService
import com.example.spotifly.data.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Song(context: Context, track: Track) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(40.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            ImageAsync(context, track.imageUrl)
            Spacer(modifier = Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = track.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = track.artist,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Composable
fun ImageAsync(context: Context, url: String) {
    var image: ImageBitmap? by remember { mutableStateOf(null) }
    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.IO) {
            image = try {
                val response = ApiService.service.get(url).execute()
                if(response.isSuccessful) {
                    val data = response.body()!!.bytes()
                    BitmapFactory.decodeByteArray(data, 0, data.size).asImageBitmap()
                } else {
                    BitmapFactory.decodeResource(context.resources, R.raw.error).asImageBitmap()
                }
            } catch (exp: Exception) {
                BitmapFactory.decodeResource(context.resources, R.raw.error).asImageBitmap()
            }
        }
    }
    Card(
        modifier = Modifier.size(56.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        if(image != null) {
            Image(bitmap = image!!, contentDescription = "")
        } else {
            CircularProgressIndicator()
        }
    }
}