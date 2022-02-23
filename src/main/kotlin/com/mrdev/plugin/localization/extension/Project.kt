package com.mrdev.plugin.localization.extension

import com.intellij.notification.*
import com.intellij.openapi.project.Project

fun Project.showNtf(title: String, message: String, type: NotificationType = NotificationType.INFORMATION) {
    val group = NotificationGroup.create(
        displayId = "com.mrdev.plugin.localization.GenAction",
        displayType = NotificationDisplayType.BALLOON,
        isLogByDefault = false,
        null,
        null,
        null,
        null
    )

    group.createNotification(
        title = title,
        content = message,
        type = type,
        listener = null
    ).notify(this)
}