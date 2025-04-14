@file:OptIn(ExperimentalFoundationApi::class)

package com.example.manga_reader_api_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.manga_reader_api_mobile.ui.home.ChapterGridScreen
import com.example.manga_reader_api_mobile.ui.home.ChapterReaderScreen
import com.example.manga_reader_api_mobile.ui.home.MangaDetailScreen
import com.example.manga_reader_api_mobile.ui.home.MangaGridScreen
import com.example.manga_reader_api_mobile.ui.theme.Manga_Reader_Api_MobileTheme
import com.example.manga_reader_api_mobile.viewmodel.MangaViewModel

class MainActivity : ComponentActivity() {
	private val mangaviewmodel: MangaViewModel = MangaViewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//enableEdgeToEdge()
		setContent {
			Manga_Reader_Api_MobileTheme {
				MangaApp()
//				val pagerState: PagerState = rememberPagerState(initialPage = 0) {1}
//				//val navController = rememberNavController()
//				VerticalPager(
//					state = pagerState,
//					key = {page -> page}
//				) { page ->
//					when(page){
//						0 -> Search(mangaviewmodel, pagerState)
//					}
//				}
			}
		}
	}
}

@Composable
fun MangaApp() {
	val navController = rememberNavController()
	val viewModel: MangaViewModel = viewModel() // Get the ViewModel

	NavHost(navController = navController, startDestination = "search") {
		composable("search") {
			SearchScreen(
				viewModel = viewModel,
				onMangasLoaded = { navController.navigate("mangaGrid") },
				onMangaLoaded = { navController.navigate("mangaDetail") },
				onChapterLoaded = {navController.navigate("chapterReader")}
			)
		}
		composable("mangaGrid") {
			MangaGridScreen(
				viewModel = viewModel,
				onMangaClick = { navController.navigate("mangaDetail") }
			)
		}
		composable("mangaDetail") {
			MangaDetailScreen(
				viewModel = viewModel,
				onChapterGridLoaded = { navController.navigate("chapterGrid") },
				onChapterRead = { navController.navigate("chapterReader") }
			)
		}
		composable("chapterGrid") {
			ChapterGridScreen(
				viewModel = viewModel,
				onChapterClick = { navController.navigate("chapterReader") }
			)
		}
		composable("chapterReader") {
			ChapterReaderScreen(viewModel = viewModel)
		}
	}
}

@Composable
fun SearchScreen(
	viewModel: MangaViewModel,
	onMangasLoaded: () -> Unit,
	onMangaLoaded: () -> Unit,
	onChapterLoaded: () -> Unit
) {
	var slug by remember { mutableStateOf("") }
	var chapterId by remember { mutableStateOf("0") }
	var name by remember { mutableStateOf("") }

	Column {
		Text("Search Manga")

		TextField(value = slug, onValueChange = { slug = it }, label = { Text("Slug") })
		TextField(
			value = chapterId,
			onValueChange = { value -> chapterId = value.filter{it.isDigit()} },
			label = { Text("Chapter ID") })
		TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })

		Button(onClick = { viewModel.getRandom(); onMangasLoaded() }) { Text("Get Random") }
		Button(onClick = { viewModel.getTop(); onMangasLoaded() }) { Text("Get Top") }
		Button(onClick = { viewModel.searchManga(name); onMangasLoaded() }) { Text("Search Manga") }
		Button(onClick = { viewModel.getBySlugChapter(slug); onMangaLoaded() }) { Text("Get chapters by slug") }
		Button(onClick = { viewModel.getBySlugChapterID(slug, chapterId.toInt()); onChapterLoaded() }) { Text("Get by slug + id") }
	}
}

//@Composable
//fun Search(mvm: MangaViewModel, pagerState: PagerState) {
//	val coroutineScope = rememberCoroutineScope()
//	var text by remember {mutableStateOf("Hello")}
//	var mangas = mvm.mangas.collectAsState()
//	var isLoading = mvm.isLoading.collectAsState()
//	TextField(
//		value = text,
//		onValueChange = {text = it},
//		label = {Text("Label")}
//	)
//
//	Button(
//		onClick = {
//			mvm.getRandom()
//			while(isLoading.value){}
//			for(m in mangas.value){
//				println("Slug: ${m.slug}; Title: ${m.title}; Image: ${m.image}")
//			}
////			coroutineScope.launch {
////				pagerState.scrollToPage(1)
////			}
//		}
//	) {
//		Text("Get Top")
//	}
//}

//@Composable
//fun Pages() {
//	val pagerState: PagerState = rememberPagerState(initialPage = 0) {1}
//	HorizontalPager(
//	){ page ->}
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//	Manga_Reader_Api_MobileTheme {
//		Greeting("Android")
//	}
//}