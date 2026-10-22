package com.example.vkeducherniakapp.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.vkeducherniakapp.data.repository.AppListMockRepository
import com.example.vkeducherniakapp.presentation.ui.appdetails.AppDetailsScreen
import com.example.vkeducherniakapp.presentation.ui.applist.AppListScreen
import androidx.navigation.compose.rememberNavController

const val CATALOG_ROUTE = "catalog"
const val DETAILS_ROUTE = "details"
const val ARG_APP_ID = "appId"

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CATALOG_ROUTE,
        modifier = modifier
    ) {
        composable(CATALOG_ROUTE) {
            AppListScreen(
                apps = AppListMockRepository().getApps(),
                onAppClick = { app ->
                    navController.navigate("$DETAILS_ROUTE/${app.id}")
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = "$DETAILS_ROUTE/{$ARG_APP_ID}",
            arguments = listOf(
                navArgument(ARG_APP_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getInt(ARG_APP_ID)

            AppDetailsScreen(
                appId = requireNotNull(appId),
                onBackClick = {
                    navController.popBackStack()
                },
                onShowcaseItemClick = { clickedApp ->
                    navController.navigate("$DETAILS_ROUTE/${clickedApp.id}")
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}