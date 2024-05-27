package com.example.booklibrarybrain.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BookScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()

) {
    val data = viewModel.state.value.bookItem.data



        Spacer(modifier = Modifier.height(100.dp))
        LazyColumn(modifier = Modifier
            .fillMaxSize(),
        ) {

            stickyHeader{
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onPrimary),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    viewModel.state.value.message?.let { Text(text = it, color = Color.Black ,fontWeight = FontWeight.Bold) }
                }
            }
            items(data.size){

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp).clickable {
                        navController.navigate("Details"+"?url=${data[it].url}")
                    }){

                    AsyncImage(model =data[it].image , contentDescription = "", modifier = Modifier
                        .clip(
                            RoundedCornerShape(15.dp)
                        ))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text =data[it].name )
                }

            }

        }





}