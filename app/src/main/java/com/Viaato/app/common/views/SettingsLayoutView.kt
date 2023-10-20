package com.Viaato.app.common.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.Viaato.app.R

class SettingsLayoutView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CoordinatorLayout(context, attrs, defStyle) {

    private val settingsDrawerInnerLayout: LinearLayout
    init {
        inflate(context, R.layout.bottom_settings_layout, this)
        settingsDrawerInnerLayout = findViewById(R.id.settingsDrawerInnerLayout)
    }
}
