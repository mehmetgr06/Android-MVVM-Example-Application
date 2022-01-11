package com.mgstudio.phonehelper.app.base

import android.content.Context
import android.view.LayoutInflater
import com.mgstudio.phonehelper.app.extension.getColorCompat
import com.mgstudio.phonehelper.app.extension.gone
import com.mgstudio.phonehelper.databinding.LayoutDialogDoubleButtonBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DoubleButtonDialog(
    context: Context,
    title: String,
    question: String,
    firstButtonText: String,
    secondButtonText: String,
    backButtonVisibility: Boolean = true,
    topButtonBgColor: Int? = null,
    bottomButtonClick: (() -> Unit?)? = null,
    topButtonClick: () -> Unit
) : BaseDialog() {

    override val dialogView: LayoutDialogDoubleButtonBinding =
        LayoutDialogDoubleButtonBinding.inflate(LayoutInflater.from(context))

    override val builder: MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context).setView(dialogView.root)

    override var cancelable: Boolean = false

    init {
        dialogView.apply {
            tvTitle.text = title
            tvText.text = question
            btnOkay.text = firstButtonText
            btnBack.text = secondButtonText
            topButtonBgColor?.let {
                btnOkay.background.setTint(context.getColorCompat(it))
            }
            if (backButtonVisibility.not()) btnBack.gone()
            btnBack.setOnClickListener {
                bottomButtonClick?.invoke()
                dialog?.dismiss()
            }
            btnOkay.setOnClickListener {
                topButtonClick.invoke()
                dialog?.dismiss()
            }
        }
    }

}