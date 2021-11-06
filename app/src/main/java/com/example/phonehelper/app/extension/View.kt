package com.example.phonehelper.app.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.gone() {
    visibility = View.GONE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // follows parent method.
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // follows parent method.
        }

        override fun afterTextChanged(s: Editable?) {
            s?.let {
                afterTextChanged.invoke(it.toString())
            }
        }
    })
}

fun ImageView.loadImageFromUrl(url: String, placeHolder: Drawable? = null) {
    Glide.with(this).load(url).listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            if (placeHolder != null) {
                setImageDrawable(placeHolder)
            }
            return true
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

    }).into(this)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun Group.addGroupClick(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun multipleOnClick(vararg view: View, onClick: () -> Unit) {
    view.forEach {
        it.setOnClickListener {
            onClick.invoke()
        }
    }
}