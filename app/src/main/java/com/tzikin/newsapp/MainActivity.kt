package com.tzikin.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.firebase.FirebaseApp
import com.tzikin.core.common.ProgressBar
import com.tzikin.core.ui.AppFunctions
import com.tzikin.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AppFunctions {

    lateinit var binding: ActivityMainBinding

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)
        binding.also { it.lifecycleOwner = this }
        setContentView(binding.root)
    }

    override fun showProgressBar() {

         binding.animatedIcon.visibility = View.VISIBLE

        /*
        progressBar = ProgressBar()
        progressBar.show(supportFragmentManager, "MyProgressBar")

         */
    }

    override fun hideProgressBar() {
        binding.animatedIcon.visibility = View.GONE
        //progressBar.dismiss()
    }
}