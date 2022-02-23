// Copyright 2022 MrDev
package com.mrdev.multilanguages.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

/**
 * Menu action to replace a selection of characters with a fixed string.
 *
 * @see AnAction
 */
class GenActionGet : AnAction() {
    /**
     * Replaces the run of text selected by the primary caret with a fixed string.
     *
     * @param e Event related to this action
     */
    override fun actionPerformed(e: AnActionEvent) {
        Action.perform("get", e)
    }

    /**
     * Sets visibility and enables this action menu item if:
     *
     *  * a project is open
     *  * an editor is active
     *  * some characters are selected
     *
     *
     * @param e Event related to this action
     */
    override fun update(e: AnActionEvent) {
        // Get required data keys
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.isEnabledAndVisible = project != null && editor != null && editor.selectionModel.hasSelection()
    }
}