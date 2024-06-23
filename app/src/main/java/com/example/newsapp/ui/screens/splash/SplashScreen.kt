package com.example.newsapp.ui.screens.splash

import android.view.animation.AccelerateDecelerateInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.screens.navigation.NavigationScreens
import com.example.newsapp.ui.theme.SplashIconSize
import com.example.newsapp.ui.theme.background
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = {
                    AccelerateDecelerateInterpolator().getInterpolation(it)
                }
            )
        )
        delay(1000)
        navController.navigate(NavigationScreens.NewsScreen.name) {
            launchSingleTop = true
            popUpTo(navController.graph.id) { inclusive = true }
        }
        
    }
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Logo",
            modifier = Modifier
                .size(SplashIconSize)
        )
    }
}