// Copyright 2022 MrDev
package com.mrdev.plugin.localization.handler

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.mrdev.plugin.localization.helper.Loc

/**
 * This is a custom [TypedHandlerDelegate] that handles actions activated keystrokes in the editor.
 * The execute method inserts a fixed string at Offset 0 of the document.
 * Document changes are made in the context of a write action.
 */
internal class TypedHandler : TypedHandlerDelegate() {
    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        // Get the document and project
        //val document = editor.document
        // Construct the runnable to substitute the string at offset 0 in the document
        //val runnable = Runnable { document.insertString(0, Loc.textOf("plugin_name")) }
        // Make the document change in the context of a write action.
        //WriteCommandAction.runWriteCommandAction(project, runnable)
        return Result.STOP
    }
}