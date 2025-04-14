package com.example.manga_reader_api_mobile.data

data class Manga(
	val id: Int,
	val title: String,
	val alternative_titles: List<String>?, // Assuming this can be null or empty
	val image: String,
	val slug: String,
	val synopsis: String,
	val last_chapter: Int?, // Assuming this can be null
	val author: String?, // Assuming this can be null
	val theme: String?, // Assuming this can be null
	val year: Int?, // Assuming this can be null
	val rating: Double?, // Assuming this can be null
	val created_at: String,
	val updated_at: String,
	val chapters: List<Chapter>?
)

data class Chapter(
	val id: Int,
	val title: String,
	val sequence: Int
)

data class ChapterPage(
	val id: Int,
	val image: String,
	val sequence: Int
)

// Helper data classes to handle the "data" wrapper in responses
data class MangaListResponse(
	val data: List<Manga>
)

data class Resp<T>(
	val data: T
)

data class SingleMangaResponse(
	val data: Manga
)

data class MangaWithChaptersResponse(
	val data: List<MangaWithChapters>
)

data class MangaWithChapters(
	val id: Int,
	val title: String,
	val alternative_titles: List<String>?,
	val image: String,
	val slug: String,
	val synopsis: String,
	val last_chapter: Int?,
	val author: String?,
	val theme: String?,
	val year: Int?,
	val rating: Double?,
	val created_at: String,
	val updated_at: String,
	val chapters: List<Chapter>? // Chapters are included in this response
)

data class ChapterPagesResponse(
	val data: List<ChapterPage>
)