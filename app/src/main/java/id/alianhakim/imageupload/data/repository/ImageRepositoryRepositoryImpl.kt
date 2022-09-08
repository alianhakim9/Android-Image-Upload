package id.alianhakim.imageupload.data.repository

import id.alianhakim.imageupload.api.ImageApi
import id.alianhakim.imageupload.data.ImageUploadRequest
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class ImageRepositoryRepositoryImpl constructor(
    private val api: ImageApi
) : ImageRepository {
    override suspend fun uploadImage(file: File): Boolean {
        return try {
            api.uploadImage(
                file = MultipartBody.Part
                    .createFormData(
                        name = "file",
                        filename = file.name,
                        body = ImageUploadRequest(
                            file = file,
                            contentType = "image"
                        )
                    )
            )
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e: HttpException) {
            e.printStackTrace()
            false
        }
    }
}