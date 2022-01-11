package com.mgstudio.phonehelper.app.ui.profile

import android.content.Context
import android.view.LayoutInflater
import com.mgstudio.phonehelper.app.base.BaseDialog
import com.mgstudio.phonehelper.app.extension.click
import com.mgstudio.phonehelper.databinding.LayoutEditDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UsernameEditDialog(context: Context, clickListener: (String) -> Unit) : BaseDialog() {

    override val dialogView: LayoutEditDialogBinding =
        LayoutEditDialogBinding.inflate(LayoutInflater.from(context))

    override val builder: MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context).setView(dialogView.root)

    override var cancelable: Boolean = false

    init {
        dialogView.apply {
            imgClose.click {
                dialog?.dismiss()
            }
            btnDone.click {
                clickListener.invoke(edtUsername.text.toString())
            }
        }
    }
}