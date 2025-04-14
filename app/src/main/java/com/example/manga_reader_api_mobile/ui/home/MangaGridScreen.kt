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
import com.example.manga_reader_api_mobile.data.Manga
import com.example.manga_reader_api_mobile.viewmodel.MangaViewModel

@Composable
fun MangaGridScreen(
	viewModel: MangaViewModel,
	onMangaClick: () -> Unit
) {
	val mangas by viewModel.mangas.collectAsState()

	LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
		items(mangas) { manga ->
			MangaItem(manga = manga, onClick = {
				viewModel.getBySlug(manga.slug) // Fetch manga details
				onMangaClick()
			})
		}
	}
}

@Composable
fun MangaItem(manga: Manga, onClick: () -> Unit) {
	Card(
		modifier = Modifier
			.padding(8.dp)
			.clickable(onClick = onClick)
	) {
		Column {
			AsyncImage(
				model = manga.image,
				contentDescription = manga.title,
				modifier = Modifier.weight(1f)
			)
			Text(text = manga.title, modifier = Modifier.padding(8.dp))
		}
	}
}