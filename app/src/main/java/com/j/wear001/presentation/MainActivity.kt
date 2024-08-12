/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.j.wear001.presentation

import android.R
import android.R.attr.button
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.j.wear001.databinding.ActivityMainBinding
import com.j.wear001.presentation.theme.Wear001Theme
import com.j.wear001.presentation.user.AppDatabase
import com.j.wear001.presentation.user.UserDao


class MainActivity : ComponentActivity() {

    var TAG = "log------"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // 获取文本框的引用
        val textbox1 = binding.editText1
        val textbox2 = binding.editText2


        // Listen for changes in the textboxes
        textbox1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the model or perform other actions when the text changes
                // Error - 错误日志
                Log.e(TAG, "Error: Text box 1 value: ${textbox1.text}");
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // 打印文本框的值
        println("Text box 1 value: ${textbox1.text}")
        println("Text box 2 value: ${textbox2.text}")
        //Log.e(TAG, "Error log message");
        // Set a click listener for a button
        binding.button.setOnClickListener {
            // Perform an action when the button is clicked
            Log.e(TAG, "Text box 2 value: ${textbox2.text}")
            val intent = Intent(this, ChatActivity::class.java)
             startActivity(intent)
        }


    }

}

@Composable
fun WearApp(greetingName: String) {
    Wear001Theme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            TimeText()
            Greeting(greetingName = greetingName)
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = "xxxx"
        //text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}