package com.example.stateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stateexample.ui.theme.StateExampleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateExampleTheme {
                Surface (color = MaterialTheme.colorScheme.background) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen () {

    var textState by rememberSaveable { mutableStateOf("") }

    val onTextChange = {
        text: String -> textState = text
    }

    MyTextField(
        text = textState,
        onTextChange = onTextChange
    )
}

@Composable
fun MyTextField(text: String, onTextChange: (String) -> Unit){

    TextField(
        value = text,
        onValueChange = onTextChange
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    StateExampleTheme {
        Column {
            //Spacer(modifier = Modifier.height(12.dp))
            SlotDemo (
                topContent = { DemoScreen() },
                middleContent = { FunctionA() },
                bottomContent = { ButtonDemo() }
            )
        }
    }
}

@Composable
fun FunctionA() {

    var switchState by remember { mutableStateOf(true) }

    val onSwitchChange = {
         value: Boolean -> switchState = value
    }

    FunctionB(
        switchState = switchState,
        onSwitchChange = onSwitchChange
    )
}

@Composable
fun FunctionB(
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {
    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange
    )
}

@Composable
fun SlotDemo(
    topContent: @Composable () -> Unit,
    middleContent: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit
) {
    Column {
        topContent()
        middleContent()
        bottomContent()
    }
}

@Composable
fun ButtonDemo() {
    Button(onClick = { }) {
        Text("Finya Hii Kitu!")
    }
}