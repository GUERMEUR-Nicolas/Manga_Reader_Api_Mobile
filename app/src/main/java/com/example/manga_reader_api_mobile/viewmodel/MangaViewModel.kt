package com.example.manga_reader_api_mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manga_reader_api_mobile.data.Chapter
import com.example.manga_reader_api_mobile.data.ChapterPage
import com.example.manga_reader_api_mobile.data.Manga
import com.example.manga_reader_api_mobile.network.Api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MangaViewModel : ViewModel() {
	private val _mangas = MutableStateFlow<List<Manga>>(emptyList())
	val mangas: StateFlow<List<Manga>> = _mangas

	private val _selectedManga = MutableStateFlow<Manga?>(null)
	val selectedManga: StateFlow<Manga?> = _selectedManga

	private val _chapters = MutableStateFlow<List<ChapterPage>>(emptyList())
	val chapters: StateFlow<List<ChapterPage>> = _chapters

	private val _currentChapterPages = MutableStateFlow<Manga?>(null)
	val currentChapterPages: StateFlow<Manga?> = _currentChapterPages

	private val _currentChapterIndex = MutableStateFlow(0)
	val currentChapterIndex: StateFlow<Int> = _currentChapterIndex

	private val _isLoading = MutableStateFlow(false)
	val isLoading: StateFlow<Boolean> = _isLoading

	private val _error = MutableStateFlow<String?>(null)
	val error: StateFlow<String?> = _error

	// --- API Interaction Functions ---
	fun getRandom() {
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.getRandom()
				if (response.isSuccessful) {
					_mangas.value = response.body()?.data ?: emptyList()
				} else {
					println("Error: ${response.code()}")
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				println("Exception: ${e.message}")
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}

	fun getTop() {
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.getTop()
				if (response.isSuccessful) {
					_mangas.value = response.body()?.data ?: emptyList()
				} else {
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}

	fun searchManga(name: String) {
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.searchManga(name)
				if (response.isSuccessful) {
					_mangas.value = response.body()?.data ?: emptyList()
				} else {
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}

	fun getBySlugChapterID(slug: String, id: Int) {
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.getBySlugChapterID(slug, id)
				if (response.isSuccessful) {
					_chapters.value = response.body()?.data ?: emptyList()
				} else {
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}

	fun getBySlugChapter(slug: String) {
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.getBySlugChapter(slug)
				if (response.isSuccessful) {
					_selectedManga.value = response.body()?.data
				} else {
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}

	fun getBySlug(slug: String) {
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.getBySlug(slug)
				if (response.isSuccessful) {
					_selectedManga.value = response.body()?.data
				} else {
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}

	fun getByChapterID(id: Int){
		viewModelScope.launch {
			_isLoading.value = true
			_error.value = null
			try {
				val response = Api.retrofitService.getByChapterID(id)
				if (response.isSuccessful) {
					_chapters.value = response.body()?.data ?: emptyList()
				} else {
					_error.value = "Error: ${response.code()}"
				}
			} catch (e: Exception) {
				_error.value = "Exception: ${e.message}"
			} finally {
				_isLoading.value = false
			}
		}
	}
}