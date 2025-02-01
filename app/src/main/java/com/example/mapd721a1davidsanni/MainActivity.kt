package com.example.mapd721a1davidsanni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mapd721a1davidsanni.ui.theme.MAPD721A1DavidSanniTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAPD721A1DavidSanniTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Preview
@Composable
private fun Main() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val store = UserStore(context)
    val coroutineScope = rememberCoroutineScope()

    // State for text fields
    val idState = remember { mutableStateOf(TextFieldValue()) }
    val usernameState = remember { mutableStateOf(TextFieldValue()) }
    val courseState = remember { mutableStateOf(TextFieldValue()) }

    // Collect stored data from UserStore
    val storedId = store.getAccessID.collectAsState(initial = "")
    val storedUsername = store.getAccessName.collectAsState(initial = "")
    val storedCourse = store.getAccessCourse.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
//
//        // ID TextField
        TextField(
            value = idState.value,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { idState.value = it },
            label = { Text("ID") }
        )

        Spacer(modifier = Modifier.height(10.dp))

//        // Username TextField
        TextField(
            value = usernameState.value,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { usernameState.value = it },
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Course TextField
        TextField(
            value = courseState.value,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { courseState.value = it },
            label = { Text("Course Name") }
        )

        Spacer(modifier = Modifier.height(30.dp))