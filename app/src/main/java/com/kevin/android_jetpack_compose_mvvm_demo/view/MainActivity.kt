package com.kevin.android_jetpack_compose_mvvm_demo.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kevin.android_jetpack_compose_mvvm_demo.ui.theme.AndroidJetpackComposeMVVMDemoTheme
import com.kevin.android_jetpack_compose_mvvm_demo.viewmodel.RandomQuoteViewModel
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import com.kevin.androidmvvmdemo.util.network.APIResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewmodel: RandomQuoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidJetpackComposeMVVMDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UI(viewmodel)
                }
            }
        }
    }
}


@Composable
fun UI(viewModel: RandomQuoteViewModel) {

//    val data: APIResponse<AnimeQuotation?> by viewModel.quotationLiveData.observeAsState(initial = "Click refresh to fetch")
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        val (r1, r2) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(r1) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }) {
            Text(
                text = viewModel.quote,
                Modifier
                    .padding(all = 10.dp)
                    .align(Alignment.Center),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }
        FloatingActionButton(onClick = {
            viewModel.getQuotation()
        }, Modifier
            .constrainAs(r2) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }) {
            Icon(Icons.Filled.Refresh, "Refresh")
        }

    }
}

