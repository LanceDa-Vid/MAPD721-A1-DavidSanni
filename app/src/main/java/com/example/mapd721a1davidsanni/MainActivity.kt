package com.example.mapd721a1davidsanni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapd721a1davidsanni.ui.theme.MAPD721A1DavidSanniTheme
import kotlinx.coroutines.launch


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
    val store = UserStore(context)
    val coroutineScope = rememberCoroutineScope()

    // State for text fields (input)
    val idState = remember { mutableStateOf(TextFieldValue()) }
    val usernameState = remember { mutableStateOf(TextFieldValue()) }
    val courseState = remember { mutableStateOf(TextFieldValue()) }

    // State for displayed stored values
    val storedIdState = remember { mutableStateOf("") }
    val storedUsernameState = remember { mutableStateOf("") }
    val storedCourseState = remember { mutableStateOf("") }

    // Collect stored data
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

        // ID Input Field
        TextField(
            value = idState.value,
            onValueChange = { idState.value = it },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Username Input Field
        TextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Course Name Input Field
        TextField(
            value = courseState.value,
            onValueChange = { courseState.value = it },
            label = { Text("Course Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            // Store Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        store.saveUserID(idState.value.text)
                        store.saveUsername(usernameState.value.text)
                        store.saveCourse(courseState.value.text)
                    }
                },
                modifier = Modifier.weight(1f).height(60.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Store")
            }

            // Load Button (Only Updates Displayed Text Fields)
            Button(
                onClick = {
                    storedIdState.value = storedId.value
                    storedUsernameState.value = storedUsername.value
                    storedCourseState.value = storedCourse.value
                },
                modifier = Modifier.weight(1f).height(60.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Load")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Reset Button (Clears Both Stored Data & Displayed Text)
        Button(
            onClick = {
                coroutineScope.launch { store.clearData() }
                storedIdState.value = ""
                storedUsernameState.value = ""
                storedCourseState.value = ""
                idState.value = TextFieldValue("")
                usernameState.value = TextFieldValue("")
                courseState.value = TextFieldValue("")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
        ) {
            Text("Reset")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display Stored Data (Does NOT Change TextField Inputs)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, Color.DarkGray, shape = RoundedCornerShape(12.dp))
                .background(
                    if (storedIdState.value.isNotEmpty()) Color.LightGray else Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = storedIdState.value,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
            Text(
                text = storedUsernameState.value,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
            Text(
                text = storedCourseState.value,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "David Sanni\n301359093", fontWeight = FontWeight.Bold)
    }
}
