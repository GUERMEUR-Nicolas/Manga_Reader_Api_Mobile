package com.example.manga_reader_api_mobile.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.manga_reader_api_mobile.viewmodel.MangaViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterReaderScreen(viewModel: MangaViewModel) {
	val pages by viewModel.chapters.collectAsState()

	if (pages.isNotEmpty()) {
		val pagerState = rememberPagerState(initialPage = pages.size-1, pageCount = { pages.size })
		HorizontalPager(
			state = pagerState,
			modifier = Modifier.fillMaxSize()
		) { page ->
			AsyncImage(
				model = pages.asReversed()[page].image,
				contentDescription = "Page ${page + 1}",
				modifier = Modifier.fillMaxSize()
			)
		}
	} else {
		// Handle case where pages are not loaded yet (e.g., display a loading indicator)
		Text("Loading Chapter Pages...")
	}
}