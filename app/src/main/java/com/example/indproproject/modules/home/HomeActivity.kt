package com.example.indproproject.modules.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.indproproject.R

/**
 * home activity used to display list of facts using fragment
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, FactsFragment()).commit()
    }
}
