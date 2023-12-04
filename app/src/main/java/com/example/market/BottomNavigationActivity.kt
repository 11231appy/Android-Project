package com.example.market

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

       
        auth = FirebaseAuth.getInstance()

        
        auth.addAuthStateListener { firebaseAuth ->
           
            val user = firebaseAuth.currentUser
            if (user != null) {
              
                navController?.navigate(R.id.homeFragment)
            } else {
               
                navController?.navigate(R.id.loginFragment)
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
       
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

       
        if (::navController.isInitialized) {
           
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_home -> navController?.navigate(R.id.homeFragment)
                    R.id.action_chat -> navController?.navigate(R.id.chatFragment)
                    R.id.action_profile -> navController?.navigate(R.id.profileFragment)
                }
                true
            }

          
            if (auth.currentUser != null) {
                navController?.navigate(R.id.homeFragment) 
            } else {
                navController?.navigate(R.id.loginFragment)
            }
        }
    }
}
