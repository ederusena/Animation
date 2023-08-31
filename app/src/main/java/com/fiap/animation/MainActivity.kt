package com.fiap.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fiap.animation.ui.theme.AnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimacaoScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimacaoScreen() {
    var visible = remember {
        mutableStateOf(false)
    }
    var enter = remember {
        mutableStateOf(fadeIn())
    }
    var exit = remember {
        mutableStateOf(fadeOut())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                visible.value = !visible.value
                enter.value = fadeIn(animationSpec = tween(3000))
                exit.value = fadeOut(animationSpec = tween(3000))
            }) {
                Text(text = "Fade")
            }
            Button(onClick = {
                visible.value = !visible.value
                enter.value =
                    slideInHorizontally(
                        animationSpec = tween(300)
                    ) + fadeIn()
                exit.value =
                    slideOutHorizontally(
                        animationSpec = tween(300)
                    ) + fadeOut()
            }) {
                Text(text = "Slide")
            }
            Button(onClick = {
                visible.value = !visible.value
                enter.value = scaleIn()
                exit.value = scaleOut()
            }) {
                Text(text = "Scale")
            }
            Button(onClick = {
                visible.value = !visible.value
                enter.value = expandHorizontally()
                exit.value = shrinkHorizontally()
            }) {
                Text(text = "Expand")
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
        BoxComponent(
            visible = visible.value,
            enter = enter.value,
            exit = exit.value
        )
    }
}

@Composable
fun BoxComponent(
    visible: Boolean,
    enter: EnterTransition,
    exit: ExitTransition
) {
    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit
    ) {
        Box(modifier = Modifier
            .size(200.dp)
            .background(color = Color.Red))
    }
}