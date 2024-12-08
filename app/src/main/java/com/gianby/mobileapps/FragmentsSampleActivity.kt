package com.gianby.mobileapps

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class FragmentsSampleActivity : AppCompatActivity(R.layout.activity_fragments_sample) {
    private var showingFragmentA = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*findViewById<Button>(R.id.navigateButton).setOnClickListener{
            toggleFragment()
        }*/
    }

    private fun toggleFragment() {
        val nextFragment: Fragment = if (showingFragmentA) FragmentB() else FragmentA()

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, nextFragment)
        }

        showingFragmentA = !showingFragmentA // Update the current fragment tracker
    }
}