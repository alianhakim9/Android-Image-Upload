package id.alianhakim.imageupload.data.repository

import id.alianhakim.imageupload.api.FileApi
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class ImageRepositoryRepositoryImpl constructor(
    private val api: FileApi
) : ImageRepository {
    override suspend fun uploadImage(file: File): Boolean {
        return try {
            api.uploadImage(
                file = MultipartBody.Part
                    .createFormData(
                        name = file.nameWithoutExtension,
                        filename = file.name,
                        body = file.asRequestBody() 
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