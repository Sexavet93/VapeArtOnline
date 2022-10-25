package com.vapeart.presentation.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

abstract class GlideCustomTarget<T>: CustomTarget<T>() {

    override fun onResourceReady(resource: T, transition: Transition<in T>?) {
    }

    override fun onLoadCleared(placeholder: Drawable?) {
    }
}