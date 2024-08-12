/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.j.wear001.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.j.wear001.presentation.theme.Wear001Theme
import com.j.wear001.presentation.user.AppDatabase
import com.j.wear001.presentation.user.User
import com.j.wear001.presentation.user.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log;
import androidx.core.app.ActivityCompat
import androidx.room.Room

class MainActivity : ComponentActivity(), LocationListener {


    private var TAG = "MyActivityTag";

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    private lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp("Android")
        }
        
        // gps相关
        // 检查是否有权限获取位置信息
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            // 如果没有权限，请求权限
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
            return
        }

        // 获取 LocationManager 实例
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // 请求位置更新
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        
        //

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        userDao = db.userDao()



        // 使用协程来执行数据库操作
        CoroutineScope(Dispatchers.IO).launch {
            val users1 = userDao.getAll()
            Log.e(TAG, "Verbose xxxx =====gogo...." + users1);

            val user = User(1, "Alice", 25)
            //userDao.insert(user)

            var u = users1.get(0);
            u.name = "22";
            userDao.update(u);

            // 查询数据
            val users2 = userDao.getAll()
            Log.e(TAG, "Verbose xxxx =====gogo...." + users2);
        }


        // Verbose - 详细日志
        Log.v(TAG, "Verbose log message");

        // Debug - 调试日志
        Log.d(TAG, "Debug log message");

        // Info - 信息日志
        Log.i(TAG, "Info log message");

        // Warn - 警告日志
        Log.w(TAG, "Warn log message");

        // Error - 错误日志
        Log.e(TAG, "Error log message");



    }

    override fun onLocationChanged(location: Location) {
        TODO("Not yet implemented")

        // 当位置信息更新时调用
        val latitude = location.latitude
        val longitude = location.longitude
        val currentLocation = "当前位置：$latitude, $longitude"
        // 在这里可以将获取到的位置信息存储到数据库或其他地方
        Log.e(TAG, "Error log message" + currentLocation);
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