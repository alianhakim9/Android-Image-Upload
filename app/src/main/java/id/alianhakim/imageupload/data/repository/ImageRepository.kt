package id.alianhakim.imageupload.data.repository

import java.io.File

interface ImageRepository {
    suspend fun uploadImage(file: File): Boolean
}