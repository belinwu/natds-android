package com.natura.android.shortcut

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getIntOrThrow
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import com.natura.android.R

class ShortCut @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var labelAttribute: String?
    private var typeAttribute = 0
    private var backgroundColorResourceAttribute = 0
    private var iconColorResourceAttribute = 0
    private var labelColorResourceAttribute = 0
    private var iconResourceAttribute = 0

    private val labelContainer by lazy { findViewById<TextView>(R.id.shortCutLabel) }
    private val backgroundContainer by lazy { findViewById<LinearLayout>(R.id.shortcutBackground) }
    private val iconContainer by lazy { findViewById<ImageView>(R.id.shortCutIcon) }


    init {
        View.inflate(context, R.layout.short_cut, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.shortCut)
        labelAttribute = typedArray.getStringOrThrow(R.styleable.shortCut_shortCutLabel)
        backgroundColorResourceAttribute = typedArray.getResourceIdOrThrow(R.styleable.shortCut_shortCutBackgroundColor)
        iconColorResourceAttribute = typedArray.getResourceIdOrThrow(R.styleable.shortCut_shortCutIconColor)
        labelColorResourceAttribute = typedArray.getResourceIdOrThrow(R.styleable.shortCut_shortCutLabelColor)
        iconResourceAttribute = typedArray.getResourceIdOrThrow(R.styleable.shortCut_shortCutIcon)
        typeAttribute = typedArray.getIntOrThrow(R.styleable.shortCut_shortCutType)

        configureShortCutByType(typeAttribute)
    }

    fun setLabel(text: String?) {
        labelContainer.text = text
        labelContainer.setTextColor(labelColorResourceAttribute)
    }

    fun setIcon(icon: Int) {
        val iconDrawable = context.getDrawable(icon)
        iconDrawable?.setTint(iconColorResourceAttribute)
        iconContainer.setImageResource(icon)
    }

    private fun configureShortCutByType(type: Int) {
        when (typeAttribute) {
            CONTAINED -> configureContainedShortCut()
            OUTLINED -> configureOutlinedShortCut()
        }
    }

    private fun configureOutlinedShortCut() {
        setLabel(labelAttribute)
        setIcon(iconResourceAttribute)
        setBackgroundOutlined()
    }

    private fun configureContainedShortCut() {
        setLabel(labelAttribute)
        setIcon(iconResourceAttribute)
        setBackgroundContained()
    }

    private fun setBackgroundContained() {
        val drawableBackground = context.getDrawable(R.drawable.shortcut_background)
        drawableBackground?.setTint(backgroundColorResourceAttribute)
        backgroundContainer.background = drawableBackground
    }

    private fun setBackgroundOutlined() {
        val drawableBackground = context.getDrawable(R.drawable.shortcut_background) as GradientDrawable
        drawableBackground.setStroke(1, backgroundColorResourceAttribute)
        backgroundContainer.background = drawableBackground
    }

    companion object {
        const val CONTAINED = 0
        const val OUTLINED = 1
    }
}


