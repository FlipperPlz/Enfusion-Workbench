package com.flipperplz.enfusionWorkbench.steam

import com.codedisaster.steamworks.SteamAPI
import com.flipperplz.enfusionWorkbench.EnfusionWorkbenchConstants
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class SteamworksStartupActivity : StartupActivity {

    class SteamworksInitializationTask(
        project: Project
    ) : Task.Backgroundable(
        project,
        "Initializing Steamworks"
    ) {
        override fun run(indicator: ProgressIndicator) {
            SteamAPI.loadLibraries()
            if(!SteamAPI.init()) onThrowable(Exception("Steam not open"))
        }


        override fun onSuccess() {
            Notifications.Bus.notify(Notification(
                EnfusionWorkbenchConstants.NOTIFICATION_GROUP,
                "Task completed",
                "Steamworks has successfully initialized.",
                NotificationType.INFORMATION
            ))
        }

        override fun onThrowable(error: Throwable) {
//            Notifications.Bus.notify(Notification(
//                "Enfusion-Workbench", "Task failed",
//                "An error occurred while running my task: ${error.message}",
//                NotificationType.ERROR
//            ))
        }
    }

    override fun runActivity(project: Project) {
        TODO("Not yet implemented")
    }
}