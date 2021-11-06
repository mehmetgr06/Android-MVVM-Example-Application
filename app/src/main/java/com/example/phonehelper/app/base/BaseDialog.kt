package com.example.phonehelper.app.base

import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.example.phonehelper.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseDialog {

    abstract val dialogView: ViewDataBinding
    abstract val builder: MaterialAlertDialogBuilder

    open var cancelable: Boolean = true
    open var isBackGroundTransparent: Boolean = true

    open var dialog: AlertDialog? = null

    open fun create(): AlertDialog {
        dialog = builder.setCancelable(cancelable).create()

        if (isBackGroundTransparent) {
            dialog?.context?.let {
                dialog?.window?.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.bg_alert
                    )
                )
                dialog?.window?.setLayout(
                    it.resources.getDimensionPixelSize(R.dimen._280sdp),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        return dialog!!
    }

    open fun onCancelListener(func: () -> Unit): MaterialAlertDialogBuilder =
        builder.setOnCancelListener { func.invoke() }

}