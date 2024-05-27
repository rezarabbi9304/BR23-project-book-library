package com.example.booklibrarybrain

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booklibrarybrain.presentation.BookScreen
import com.example.booklibrarybrain.ui.theme.BookLibraryBrainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            BookLibraryBrainTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->

                    NavHost(modifier = Modifier.padding(innerPadding),navController = navController, startDestination = "Home") {
                        composable(route = "Home") {
                            BookScreen(navController)
                        }

                        composable("Details" +  "?url={url}",
                            arguments = listOf(
                                navArgument(name = "url"){
                                        type  = NavType.StringType
                                        defaultValue = ""
                                }
                            )
                        ){
                          val url =   it.arguments?.getString("url")
                            AndroidView(
                                factory = { context ->
                                    WebView(context).apply {
                                        settings.javaScriptEnabled = true
                                        webViewClient = WebViewClient()

                                        settings.loadWithOverviewMode = true
                                        settings.useWideViewPort = true
                                        settings.setSupportZoom(true)
                                    }
                                },
                                update = { webView ->
                                    webView.loadUrl("${url}")
                                }
                            )
                        }


                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookLibraryBrainTheme {
        Greeting("Android")
    }
}