package com.example.manga_reader_api_mobile.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.manga_reader_api_mobile.viewmodel.MangaViewModel

@Composable
fun MangaDetailScreen(
	viewModel: MangaViewModel,
	onChapterGridLoaded: () -> Unit,
	onChapterRead: () -> Unit
) {
	val manga by viewModel.selectedManga.collectAsState()
	val chapters by viewModel.chapters.collectAsState()
	val scrollstate = ScrollState(0)

	manga?.let {
		Column(modifier = Modifier.padding(16.dp).verticalScroll(scrollstate, true)) {
			AsyncImage(model = it.image, contentDescription = it.title)
			Text(text = it.title, modifier = Modifier.padding(top = 8.dp))
			Text(text = it.synopsis, modifier = Modifier.padding(top = 8.dp))

			if (!manga!!.chapters.isNullOrEmpty()) {
				Button(
					onClick = {
						// Assuming you want to read the first chapter
						viewModel.getBySlugChapterID(it.slug, chapters.first().id)
						onChapterRead()
					},
					modifier = Modifier.padding(top = 16.dp)
				) {
					Text("Read First Chapter")
				}
			} else {
				Button(
					onClick = {
						viewModel.getBySlugChapter(it.slug)
						onChapterGridLoaded()
					},
					modifier = Modifier.padding(top = 16.dp)
				) {
					Text("View Chapters")
				}
			}
		}
	}
}