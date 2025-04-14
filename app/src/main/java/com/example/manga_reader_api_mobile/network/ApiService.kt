package com.example.manga_reader_api_mobile.network

import com.example.manga_reader_api_mobile.data.Chapter
import com.example.manga_reader_api_mobile.data.ChapterPage
import com.example.manga_reader_api_mobile.data.ChapterPagesResponse
import com.example.manga_reader_api_mobile.data.Manga
import com.example.manga_reader_api_mobile.data.MangaListResponse
import com.example.manga_reader_api_mobile.data.MangaWithChapters
import com.example.manga_reader_api_mobile.data.MangaWithChaptersResponse
import com.example.manga_reader_api_mobile.data.Resp
import com.example.manga_reader_api_mobile.data.SingleMangaResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
	@GET("manga/random")
	suspend fun getRandom(): Response<Resp<List<Manga>>>
	//suspend fun getRandom(): Response<MangaListResponse>

	@GET("manga/top")
	suspend fun getTop(): Response<Resp<List<Manga>>>
	//suspend fun getTop(): Response<MangaListResponse>

	@GET("manga/search/{name}")
	suspend fun searchManga(@Path("name") name: String): Response<Resp<List<Manga>>>
	//suspend fun searchManga(@Path("name") name: String): Response<MangaListResponse>

	@GET("manga/{slug}")
	suspend fun getBySlug(@Path("slug") slug: String): Response<Resp<Manga>>
	//suspend fun getBySlug(@Path("slug") slug: String): Response<SingleMangaResponse>

	@GET("manga/{slug}/chapter")
	suspend fun getBySlugChapter(@Path("slug") slug: String): Response<Resp<Manga>> // Assuming this returns a list of chapters
	//suspend fun getBySlugChapter(@Path("slug") slug: String): Response<MangaWithChaptersResponse>

	@GET("manga/{slug}/chapter/{id}")
	suspend fun getBySlugChapterID(@Path("slug") slug: String, @Path("id") id: Int): Response<Resp<List<ChapterPage>>>
	//suspend fun getBySlugChapterID(@Path("slug") slug: String, @Path("id") id: Int): Response<ChapterPagesResponse>

	@GET("chapter/{id}")
	suspend fun getByChapterID(@Path("id") id: Int): Response<Resp<List<ChapterPage>>>
	//suspend fun getByChapterID(@Path("id") id: Int): Response<ChapterPagesResponse>

	@GET("health")
	suspend fun healthCheck(): Response<Unit> // Assuming health check returns nothing
}

object Api {
	private const val BASE_URL = "http://192.168.2.18:1234/request.php/" // Replace with your base URL

	val retrofitService: ApiService by lazy {
		val gson = GsonBuilder().setLenient().create()
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
			.create(ApiService::class.java)
	}
}