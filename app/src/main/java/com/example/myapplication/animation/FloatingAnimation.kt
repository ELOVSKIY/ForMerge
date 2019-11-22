package com.example.myapplication.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View


fun rotateFab(v: View, rotate: Boolean): Boolean {
    v.animate().setDuration(200)
            .rotation(if (rotate) 135f else 0f)
    return rotate
}

fun showIn(v: View) {

    v.visibility = View.VISIBLE
    v.alpha = 0f
    v.translationY = v.height.toFloat()
    v.animate()
            .setDuration(200)
            .translationY(0f)
            .alpha(1f)
            .start()
}

fun showOut(v: View) {
    v.visibility = View.GONE
    v.alpha = 1f
    v.translationY = 0f
    v.animate()
            .setDuration(200)
            .translationY(v.height.toFloat())
            .alpha(0f)
            .start()
}

fun initView(v: View){
    v.visibility = View.GONE
    v.translationY = v.height.toFloat()
    v.alpha = 0f
}
