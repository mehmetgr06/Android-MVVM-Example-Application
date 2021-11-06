package com.example.phonehelper.app.base

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import com.example.phonehelper.databinding.LayoutBasicDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BasicDialog(context: Context, text: String) : BaseDialog() {

    override val dialogView: LayoutBasicDialogBinding =
        LayoutBasicDialogBinding.inflate(LayoutInflater.from(context))

    override val builder: MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context)
            .setView(dialogView.root)

    override var cancelable: Boolean = false

    init {
        dialogView.apply {
            tvDialog.text = text
            btnDialog.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }
}