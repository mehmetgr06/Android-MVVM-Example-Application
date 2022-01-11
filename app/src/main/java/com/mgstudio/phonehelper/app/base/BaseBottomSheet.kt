package com.mgstudio.phonehelper.app.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mgstudio.phonehelper.R
import com.mgstudio.phonehelper.app.extension.heightPixels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

abstract class BaseBottomSheet<B : ViewDataBinding> : BottomSheetDialogFragment() {

    abstract fun isFullScreen(): Boolean

    abstract fun setBackground(): Int?

    override fun getTheme(): Int = R.style.RoundedBottomSheetTheme

    /**
     * @return layout-land resource id
     */
    abstract fun getLayoutId(): Int

    lateinit var binding: B

    private fun <T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        bottomSheet?.let { frame ->
            setBackground()?.let {
                frame.setBackgroundColor(ContextCompat.getColor(requireContext(), it))
            }
            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(frame)
            frame.layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }
    }

    private fun skipCollapsed(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        bottomSheet?.let {
            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(it)
            it.minimumHeight = requireActivity().heightPixels() * 30 / 100
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // follows inherited method.
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        val newMaterialShapeDrawable = createMaterialShapeDrawable(bottomSheet)
                        ViewCompat.setBackground(bottomSheet, newMaterialShapeDrawable)
                    }
                }
            })
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        if (isFullScreen()) {
            dialog.setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                setupFullHeight(bottomSheetDialog)
            }
        } else {
            dialog.setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                skipCollapsed(bottomSheetDialog)
            }
        }

        return dialog
    }

    private fun createMaterialShapeDrawable(bottomSheet: View): MaterialShapeDrawable {
        val shapeAppearanceModel =
            //Create a ShapeAppearanceModel with the same shapeAppearanceOverlay used in the style
            ShapeAppearanceModel.builder(context, 0, R.style.RoundedShape)
                .build()

        //Create a new MaterialShapeDrawable (you can't use the original MaterialShapeDrawable in the BottoSheet)
        val currentMaterialShapeDrawable = bottomSheet.background as MaterialShapeDrawable
        return MaterialShapeDrawable(shapeAppearanceModel).apply {
            //Copy the attributes in the new MaterialShapeDrawable
            initializeElevationOverlay(context)
            fillColor = currentMaterialShapeDrawable.fillColor
            tintList = currentMaterialShapeDrawable.tintList
            elevation = currentMaterialShapeDrawable.elevation
            strokeWidth = currentMaterialShapeDrawable.strokeWidth
            strokeColor = currentMaterialShapeDrawable.strokeColor
        }
    }

    override fun onStart() {
        super.onStart()
        if(isFullScreen()) {
            val sheetContainer = requireView().parent as ViewGroup
            sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(
            inflater,
            getLayoutId(),
            container
        )
        return binding.root
    }
}