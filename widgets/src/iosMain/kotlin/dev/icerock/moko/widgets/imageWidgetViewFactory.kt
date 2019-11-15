/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets

import dev.icerock.moko.widgets.core.VFC
import platform.UIKit.UIColor
import platform.UIKit.UIView
import platform.UIKit.backgroundColor
import platform.UIKit.translatesAutoresizingMaskIntoConstraints

actual var imageWidgetViewFactory: VFC<ImageWidget> = { _, _ ->
    // TODO add factory implementation
    UIView().apply {
        translatesAutoresizingMaskIntoConstraints = false
        backgroundColor = UIColor.redColor
    }
}