package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.AppDatabase
import com.example.data.HisabRepository
import com.example.ui.HisabApp
import com.example.ui.HisabViewModel
import com.example.ui.theme.HisabCalculatorTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val repository = HisabRepository(AppDatabase.getInstance(applicationContext).hisabDao())
    setContent {
      HisabCalculatorTheme {
        val hisabViewModel: HisabViewModel =
          viewModel(factory = HisabViewModel.Factory(repository))
        HisabApp(viewModel = hisabViewModel)
      }
    }
  }
}
