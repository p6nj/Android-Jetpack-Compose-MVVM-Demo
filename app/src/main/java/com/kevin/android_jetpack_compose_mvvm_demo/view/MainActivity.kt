package com.kevin.android_jetpack_compose_mvvm_demo.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kevin.android_jetpack_compose_mvvm_demo.ui.theme.AndroidJetpackComposeMVVMDemoTheme
import com.kevin.android_jetpack_compose_mvvm_demo.viewmodel.RandomQuoteViewModel
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RandomQuoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidJetpackComposeMVVMDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UI(viewModel = viewModel)
                }
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }
}


@Composable
fun UI(viewModel: RandomQuoteViewModel) {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (r1, r2) = createRefs()

        CardList(viewModel = viewModel, modifier = Modifier
            .fillMaxWidth()
            .constrainAs(r1) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            })

        FloatingActionButton(onClick = {
            viewModel.getMultipleQuotations()
        }, Modifier
            .constrainAs(r2) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }) {
            Icon(Icons.Filled.Refresh, "Refresh")
        }

    }
}


@Composable
fun CardList(viewModel: RandomQuoteViewModel, modifier: Modifier) {

    val list = viewModel.quotes.observeAsState(initial = listOf())

    LazyColumn(modifier) {
        items(list.value) {
            Card(quote = it)
        }
    }

}


@Composable
fun Card(quote: AnimeQuotation) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Cyan, RoundedCornerShape(12.dp))
            .padding(15.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = quote.anime, style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = quote.character
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = quote.quote, style = TextStyle(
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
    Spacer(modifier = Modifier.size(10.dp))
}
