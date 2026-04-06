package com.example.ui.theme.design

import com.example.ui.theme.dimension.DefaultIcon
import com.example.ui.theme.dimension.DefaultSpacing
import com.example.ui.theme.dimension.DefaultStroke
import com.example.ui.theme.dimension.Icon
import com.example.ui.theme.dimension.Spacing
import com.example.ui.theme.dimension.Stroke

/**
 * Provides access to the default dimensions used in the application.
 */
object Pds {
    val spacing: Spacing
        get() = DefaultSpacing()

    val icon: Icon
        get() = DefaultIcon()

    val stroke: Stroke
        get() = DefaultStroke()
}