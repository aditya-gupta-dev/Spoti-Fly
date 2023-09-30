package com.example.spotifly.data

data class Track(
    val id: String,
    val name: String,
    val duration: Long,
    val artist: String,
    val artistId: String,
    val imageUrl: String,
)