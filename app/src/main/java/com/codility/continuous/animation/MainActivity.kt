package com.codility.continuous.animation

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast

@RequiresApi(Build.VERSION_CODES.KITKAT)
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mAnimationView: FavouriteAnimationView? = null
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)

        mAnimationView = findViewById<FavouriteAnimationView>(R.id.animated_view)
        findViewById<Button>(R.id.btn_pause).setOnClickListener(this)
        findViewById<Button>(R.id.btn_resume).setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mAnimationView!!.resume()
    }

    override fun onPause() {
        super.onPause()
        mAnimationView!!.pause()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_pause -> mAnimationView!!.pause()
            R.id.btn_resume -> mAnimationView!!.resume()
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}