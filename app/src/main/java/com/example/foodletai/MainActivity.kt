package com.example.foodletai // Your package name will be here

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.foodletai.View.HomePage
import com.example.foodletai.ui.theme.FOODLETAITheme
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import com.example.foodletai.ViewModel.MainViewModel

class MainActivity : ComponentActivity() {
    private var readPermission:Boolean = false;
    private var writePermission:Boolean = false;

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    private val viewModel: MainViewModel = MainViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        enableEdgeToEdge();
        // 1. Initialize the permission launcher
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            readPermission = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false
            writePermission = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: false
        }

        // 2. Request permissions
        requestPermissions()

        setContent {
            FOODLETAITheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF191919)
                ) {
                    HomePage(modifier = Modifier.fillMaxSize(),viewModel = viewModel )
                }
            }
        }
    }

    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()

        val hasReadPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val hasWritePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        writePermission = hasWritePermission
        readPermission = hasReadPermission

        if (!hasWritePermission) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasReadPermission) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionsToRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FOODLETAITheme {
        Greeting("Android")
    }
}