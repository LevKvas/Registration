package com.example.application_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.application_1.fragment_1.FirstFragment
import com.example.application_1.fragment_2.SecondFragment
import com.example.application_1.ui.theme.Application_1Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showFirstFragment()
        }
    }

    // Control the appearance and disappearance
    private fun showFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FirstFragment())
            .commit()
    }

    fun showSecondFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SecondFragment())
            .addToBackStack("second")
            .commit()
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
    Application_1Theme {
        Greeting("Android")
    }
}