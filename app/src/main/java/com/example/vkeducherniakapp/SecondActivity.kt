package com.example.vkeducherniakapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.ui.theme.VkEduCherniakAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val passedText =
            intent.getStringExtra(EXTRA_PASSED_TEXT) ?: getString(R.string.data_not_passed)

        setContent {
            VkEduCherniakAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondActivityGreeting(
                        passedText = passedText,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    companion object {
        private const val EXTRA_PASSED_TEXT = "extra_passed_text"

        fun newIntent(context: Context, passedText: String): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(EXTRA_PASSED_TEXT, passedText)
            }
        }
    }
}

@Composable
fun SecondActivityGreeting(passedText: String, modifier: Modifier = Modifier) {
    Text(
        text = passedText,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SecondActivityGreetingPreview() {
    VkEduCherniakAppTheme {
        SecondActivityGreeting("Android")
    }
}