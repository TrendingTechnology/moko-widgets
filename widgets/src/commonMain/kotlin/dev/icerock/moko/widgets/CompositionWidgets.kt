/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.widgets.core.AnyWidget
import dev.icerock.moko.widgets.core.WidgetScope
import dev.icerock.moko.widgets.style.background.Orientation

fun WidgetScope.switchLabeled(
    switchId: SwitchWidget.Id,
    switchState: MutableLiveData<Boolean>,
    text: LiveData<StringDesc>,
    linearId: LinearWidget.Id? = null,
    textId: TextWidget.Id? = null
): AnyWidget {
    return linear(
        id = linearId,
        styled = {
            it.copy(orientation = Orientation.HORIZONTAL)
        },
        childs = listOf(
            switch(
                id = switchId,
                state = switchState
            ),
            text(
                id = textId,
                text = text
            )
        )
    )
}