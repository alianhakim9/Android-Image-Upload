package id.alianhakim.imageupload.api

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {

    @POST("upload")
    @Multipart
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
    ): ImageUploadResponse
}