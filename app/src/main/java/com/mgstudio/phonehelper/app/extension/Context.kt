package com.mgstudio.phonehelper.app.extension

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mgstudio.phonehelper.app.base.BasicDialog
import com.mgstudio.phonehelper.app.base.DoubleButtonDialog

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Activity.heightPixels(): Int {
    val outMetrics = DisplayMetrics()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display?.getRealMetrics(outMetrics)
    } else {
        @Suppress("DEPRECATION")
        val display = windowManager.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(outMetrics)
    }

    return outMetrics.heightPixels
}

fun Activity.widthPixels(): Int {
    val outMetrics = DisplayMetrics()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display?.getRealMetrics(outMetrics)
    } else {
        @Suppress("DEPRECATION")
        val display = windowManager.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(outMetrics)
    }

    return outMetrics.widthPixels
}

fun Context.drawable(res: Int): Drawable? = ContextCompat.getDrawable(this, res)

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
            .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Context.showDialog(
    title: String,
    question: String,
    firstButtonText: String,
    secondButtonText: String,
    backButtonVisibility: Boolean = true,
    topButtonBgColor: Int? = null,
    bottomButtonClick: (() -> Unit?)? = null,
    topButtonClick: () -> Unit,
) {
    DoubleButtonDialog(
        this,
        title,
        question,
        firstButtonText,
        secondButtonText, backButtonVisibility, topButtonBgColor, bottomButtonClick, topButtonClick
    ).create().show()
}

fun Context.showBasicDialog(
    errorMessage: String
) {
    BasicDialog(
        this,
        errorMessage
    ).create().show()
}