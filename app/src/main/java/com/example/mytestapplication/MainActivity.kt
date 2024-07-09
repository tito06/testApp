package com.example.mytestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.mytestapplication.data.App
import com.example.mytestapplication.screen.ScreenVm
import com.example.mytestapplication.ui.theme.MyTestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val appViewModel:ScreenVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestApplicationTheme {
             /*   AppList(appViewModel.appList.collectAsState())
                appViewModel.appList(378)*/
               Appscreen()
                appViewModel.appList(378)
            }
        }
    }

    @Composable
    fun Appscreen(){
        Column {
            Header()
            TabLayout()
        }

    }


    @Composable
    fun Header(){
        Box(
            Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(colorResource(id = R.color.customColor))) {

            Row(modifier = Modifier
                .padding(4.dp, 40.dp, 0.dp, 4.dp)
                //.width(230.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {


               Icon(
                   imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                   contentDescription ="back",
                   tint = Color.White,
                   modifier = Modifier
                       .padding(8.dp)
                       .clickable {

                       }
               )

                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Example Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp),
                        alignment = Alignment.Center
                    )



                        Text(text = "NAME",
                            color = Color.White)

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(24.dp), // Adjust corner radius as needed
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .padding(16.dp)
                            .width(150.dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = "Connected",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }




                }




            }

        }
    }


    @Composable
    fun AppList(appList: State<List<App>>){
        LazyColumn {
            items(appList.value){
                App(it)
            }
        }

    }


    @Composable
    fun App(app :App){

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(8.dp, 2.dp, 4.dp, 0.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            )
        ) {

            Row(modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(data = app.app_icon,
                        builder = {
                            transformations(CircleCropTransformation())

                        }),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        // .weight(0.2f)
                        .size(40.dp))

                Box(modifier = Modifier.width(20.dp))

                Column ( verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .width(5.dp)
                        .weight(0.8f)){

                    val displaytext = app.app_name.take(15)

                    Text(
                        text = displaytext,
                        fontWeight = FontWeight.Light,
                        maxLines = 1
                    )


                }

                ToggleButton(app)

            }

        }
    }

    @Composable
    fun TabContent(tabIndex: Int) {
        if (tabIndex == 0) AppList(appList =appViewModel.appList.collectAsState() ) else Text(text = "Void")

    }

    @Composable
    fun TabLayout() {
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }

        val bgc:Color = colorResource(id = R.color.customColor)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            // TabRow with tabs
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = bgc,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.customColor))
            ) {
                repeat(2) { index ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = if (index == 0) "Applications" else "Settings",
                            )

                        },
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }

            // Display tab content based on selectedTabIndex
            TabContent(tabIndex = selectedTabIndex)
        }
    }


    @Composable
    fun ToggleButton(app:App) {
        var isToggled by remember { mutableStateOf(false) }

        IconButton(
            onClick = { isToggled = !isToggled },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (app.status == "Active") R.drawable.ic_toggled_on else R.drawable.ic_toggled_off
                ),
                contentDescription = "Toggle Button",
                tint = if (isToggled) Color.Green else Color.Gray
            )
        }
    }
}

