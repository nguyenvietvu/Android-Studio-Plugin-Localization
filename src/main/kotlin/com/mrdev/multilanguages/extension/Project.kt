package com.mrdev.multilanguages.extension

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

fun Project.showNtf(title: String, message: String, type: NotificationType = NotificationType.INFORMATION) {
    val notificationGroup = NotificationGroup(
        displayId = "com.mrdev.multilanguages.GenAction",
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