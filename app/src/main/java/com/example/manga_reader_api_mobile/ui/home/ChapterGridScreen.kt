package com.example.manga_reader_api_mobile.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.manga_reader_api_mobile.data.Chapter
import com.example.manga_reader_api_mobile.data.ChapterPage
import com.example.manga_reader_api_mobile.viewmodel.MangaViewModel

@Composable
fun ChapterGridScreen(
	viewModel: MangaViewModel,
	onChapterClick: () -> Unit
) {
	val manga by viewModel.selectedManga.collectAsState() // not chapters, sc -> manga.chapters
	val chapters = manga?.chapters

	if(chapters != null) {
		LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
			items(chapters) { chapter ->
				ChapterItem(chapter = chapter, onClick = {
					viewModel.getByChapterID(chapter.id)
					onChapterClick()
				})
			}
		}
	} else {
		Text("No chapters available.")
	}
}

@Composable
fun ChapterItem(chapter: Chapter, onClick: () -> Unit) {
	Card(
		modifier = Modifier
			.padding(8.dp)
			.clickable(onClick = onClick)
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text(chapter.title)
		}
	}
}