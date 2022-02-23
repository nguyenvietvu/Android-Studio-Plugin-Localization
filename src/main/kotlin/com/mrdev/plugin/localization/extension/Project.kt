package com.mrdev.plugin.localization.extension

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

fun Project.showNtf(title: String, message: String, type: NotificationType = NotificationType.INFORMATION) {
    val notificationGroup = NotificationGroup(
        displayId = "com.mrdev.plugin.localization.GenAction",
        displayType = NotificationDisplayType.BALLOON,
        isLogByDefault = false
    )

    notificationGroup.createNotification(
        title = title,
        content = message,
        type = type,
        listener = null
    ).notify(this)
}