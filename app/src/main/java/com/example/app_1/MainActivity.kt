package com.example.app_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_1.ui.theme.App1Theme

class MainActivity : ComponentActivity() {

//    Number puzzle game
    var number = mutableStateListOf("1", "2", "4", "8", "3", "6", "5", "", "7")
    var click = mutableIntStateOf(0)
    var display = mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App1Theme {

                Scaffold { paddingValues: PaddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .background(color = Color.LightGray)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if(!display.value) {
                                Text(
                                    "Clicks:",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 6.sp,
                                    color = Color.Black
                                )

                                Text(
                                    click.value.toString(),
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 6.sp,
                                    color = Color.Black
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(3f)
                        ) {
                            Column {
                                var index = 0
                                for (i in 0 until 3) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                    ) {
                                        for(j in 0 until 3) {
                                            buttonClick(this, index++)
                                        }
                                    }
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    number.shuffle()
                                    click.value = 0
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(10.dp),
                                enabled = if (display.value) {
                                    false
                                } else {
                                    true
                                }
                            ) {
                                if(!display.value) {
                                    Text(
                                        "Shuffle!",
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        letterSpacing = 6.sp,
                                        color = Color.Blue
                                    )
                                }
                            }
                        }
                    }
                }
                win()
            }
        }
    }

    fun swap(i: Int, i1: Int) {
        if (number[i1].isEmpty()) {
            number[i1] = number[i]
            number[i] = ""
            click.intValue++
        }
    }

    @Composable
    fun buttonClick(rowScope: RowScope, index: Int) {
        rowScope.apply {
            myButtron(this, index) {
                when (index) {
                    0 -> {
                        swap(0, 1)
                        swap(0, 3)
                    }

                    1 -> {
                        swap(1, 0)
                        swap(1, 2)
                        swap(1, 4)
                    }

                    2 -> {
                        swap(2, 1)
                        swap(2, 5)
                    }

                    3 -> {
                        swap(3, 0)
                        swap(3, 4)
                        swap(3, 6)
                    }

                    4 -> {
                        swap(4, 1)
                        swap(4, 3)
                        swap(4, 5)
                        swap(4, 7)
                    }

                    5 -> {
                        swap(5, 2)
                        swap(5, 4)
                        swap(5, 8)
                    }

                    6 -> {
                        swap(6, 3)
                        swap(6, 7)
                    }

                    7 -> {
                        swap(7, 6)
                        swap(7, 4)
                        swap(7, 8)
                    }

                    8 -> {
                        swap(8, 5)
                        swap(8, 7)
                    }
                }
            }
        }
    }

    @Composable
    fun win() {
        if ((number[0] == "1") && (number[1] == "2") && (number[2] == "3")) {
            if ((number[3] == "4") && (number[4] == "5") && (number[5] == "6")) {
                if ((number[6] == "7") && (number[7] == "8") && (number[8] == "")) {
                    display.value = true
                }
            }
        }
        if (display.value) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(color = Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "YOU WIN",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        letterSpacing = 5.sp
                    )
                    Text(
                        "You win in " + click.value.toString() + " Click(s)",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        letterSpacing = 5.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Button(
                        onClick = {
                            number.shuffle()
                            display.value = false
                            click.value = 0
                        },
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.White),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray, contentColor = Color.DarkGray
                        )
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = " ")
                    }
                }
            }
        }
    }

    @Composable
    fun myButtron(rowScope: RowScope, index: Int, onClick: () -> Unit) {
        rowScope.apply {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(10.dp, 20.dp),
                enabled = if (display.value) {
                    false
                } else {
                    true
                },
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                border = BorderStroke(2.dp, color = Color.White)
            ) {
                Text(text = number[index], fontSize = 100.sp, color = Color.Yellow)
            }
        }
    }

}