package com.example.vkeducherniakapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.ui.theme.VkEduCherniakAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEduCherniakAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var inputText: String by rememberSaveable { mutableStateOf("") }
    var isError: Boolean by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                if (it.isNotBlank()) isError = false
            },
            label = {
                Text(stringResource(R.string.input_text))
            },
            isError = isError,
            supportingText = {
                if (isError) Text(stringResource(R.string.empty_field_error_message))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (inputText.isBlank()) isError = true
            else openSecondActivityButton(context = context, passedText = inputText)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.open_second_activity))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VkEduCherniakAppTheme {
        Greeting()
    }
}

private fun openSecondActivityButton(context: Context, passedText: String) {
    val startSecondActivityIntent = SecondActivity.newIntent(context, passedText)
    context.startActivity(startSecondActivityIntent)

}