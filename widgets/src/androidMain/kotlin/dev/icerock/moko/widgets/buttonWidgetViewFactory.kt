/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.RippleDrawable
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import dev.icerock.moko.widgets.core.VFC
import dev.icerock.moko.widgets.core.ViewFactoryContext
import dev.icerock.moko.widgets.old.applyStyle
import dev.icerock.moko.widgets.style.background.buildBackground
import dev.icerock.moko.widgets.style.ext.applyMargin
import dev.icerock.moko.widgets.style.ext.toPlatformSize

actual var buttonWidgetViewFactory: VFC<ButtonWidget> = { context: ViewFactoryContext,
                                                          widget: ButtonWidget ->
    val ctx = context.androidContext
    val dm = ctx.resources.displayMetrics
    val style = widget.style

    val button = Button(ctx).apply {
        layoutParams = LinearLayout.LayoutParams(
            style.size.width.toPlatformSize(dm),
            style.size.height.toPlatformSize(dm)
        ).apply {
            applyMargin(ctx, style.margins)
        }

        style.background?.let {
            val rippleDrawable = RippleDrawable(
                ColorStateList.valueOf(Color.GRAY),
                it.buildBackground(ctx), null
            )

            background = rippleDrawable
        }

        applyStyle(style.textStyle)

        style.isAllCaps?.also { isAllCaps = it }
    }

    val enabledLiveData = widget.enabled
    if (enabledLiveData != null) {
        enabledLiveData.ld().observe(context.lifecycleOwner, Observer { enabled ->
            button.isEnabled = enabled == true
        })
    }

    button.setOnClickListener {
        widget.onTap()
    }

    widget.text.ld().observe(context.lifecycleOwner, Observer { text ->
        button.text = text?.toString(ctx)
    })

    button
}
