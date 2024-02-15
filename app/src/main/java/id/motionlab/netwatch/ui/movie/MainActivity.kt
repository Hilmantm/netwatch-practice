package id.motionlab.netwatch.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.motionlab.netwatch.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = findViewById<FragmentContainerView>(R.id.main_fragment)
        val mainBnv = findViewById<BottomNavigationView>(R.id.main_bnv)

        mainBnv.setupWithNavController(mainFragment.findNavController())
    }
}