package com.androidpractice.budday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidpractice.budday.ui.screens.home.HomeScreen
import com.androidpractice.budday.ui.theme.BuddayTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.androidpractice.budday.ui.screens.birthday.AddEditBirthdayScreen
import com.androidpractice.budday.ui.screens.birthday.BirthdayDetailScreen
import com.androidpractice.budday.ui.screens.month.MonthScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuddayTheme {
                BuddayApp()
            }
        }
    }
}

@Composable
fun BuddayApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (navController.currentBackStackEntry?.destination?.route == "home") {
                FloatingActionButton(
                    onClick = { navController.navigate("birthday/add") }
                ) {
                    Icon(Icons.Default.Add, "Add Birthday")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    onMonthClick = { monthNumber ->
                        navController.navigate("month/$monthNumber")
                    }
                )
            }

            composable(
                "month/{monthNumber}",
                arguments = listOf(navArgument("monthNumber") { type = NavType.IntType })
            ) { backStackEntry ->
                val monthNumber = backStackEntry.arguments?.getInt("monthNumber") ?: 1
                MonthScreen(
                    monthNumber = monthNumber,
                    onBackClick = { navController.popBackStack() },
                    onBirthdayClick = { birthdayId ->
                        navController.navigate("birthday/$birthdayId")
                    }
                )
            }

            composable(
                "birthday/{birthdayId}",
                arguments = listOf(navArgument("birthdayId") { type = NavType.IntType })
            ) { backStackEntry ->
                val birthdayId = backStackEntry.arguments?.getInt("birthdayId") ?: -1
                BirthdayDetailScreen(
                    birthdayId = birthdayId,
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { id ->
                        navController.navigate("birthday/edit/$id")
                    }
                )
            }

            composable("birthday/add") {
                AddEditBirthdayScreen(
                    onBackClick = { navController.popBackStack() },
                    onSaveClick = {
                        // We'll implement this later
                        navController.popBackStack()
                    }
                )
            }

            composable(
                "birthday/edit/{birthdayId}",
                arguments = listOf(navArgument("birthdayId") { type = NavType.IntType })
            ) { backStackEntry ->
                val birthdayId = backStackEntry.arguments?.getInt("birthdayId") ?: -1
                AddEditBirthdayScreen(
                    birthdayId = birthdayId,
                    onBackClick = { navController.popBackStack() },
                    onSaveClick = {
                        // We'll implement this later
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}