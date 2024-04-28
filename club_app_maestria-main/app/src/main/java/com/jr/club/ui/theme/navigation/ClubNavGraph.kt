package com.juanrolando.coche.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juanrolando.coche.ui.theme.coche.ClubDetailsDestination
import com.juanrolando.coche.ui.theme.coche.ClubDetailsScreen
import com.juanrolando.coche.ui.theme.home.HomeDestination
import com.juanrolando.coche.ui.theme.home.HomeScreen

@Composable
fun ClubNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCocheDetail = {
                    navController.navigate("${ClubDetailsDestination.route}/${it}")
                }
            )
        }

        composable(
            route = ClubDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ClubDetailsDestination.clubIdArg) {
                type = NavType.IntType
            })
        ) {
            ClubDetailsScreen(
                navigateBack = { navController.navigateUp() }
            )
        }

    }
}
