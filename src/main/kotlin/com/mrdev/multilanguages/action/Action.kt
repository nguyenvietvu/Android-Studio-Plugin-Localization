// Copyright 2022 MrDev
package com.mrdev.multilanguages.action

import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.TextRange
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.mrdev.multilanguages.api.Translate
import com.mrdev.multilanguages.extension.key
import com.mrdev.multilanguages.extension.showNtf
import com.mrdev.multilanguages.helper.Loc
import java.util.*
import java.io.*

object Action {
    fun perform(type: String, e: AnActionEvent){
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        val document = editor.document
        val primaryCaret = editor.caretModel.primaryCaret
        val start = primaryCaret.selectionStart
        val end = primaryCaret.selectionEnd
        val selection = document.getText(TextRange(start, end))
        if(selection.trim().isEmpty()){
            project.showNtf("Info", Loc.textOf("existing_string"))
            return
        }
        val key = selection.key

        WriteCommandAction.runWriteCommandAction(project) {
            if("get" == type) {
                document.replaceString(
                    start,
                    end,
                    "getString(R.string.$key)"
                )
            } else {
                document.replaceString(
                    start,
                    end,
                    "R.string.$key"
                )
            }
        }

        val files = FilenameIndex.getFilesByName(project, "strings.xml", GlobalSearchScope.projectScope(project))
        WriteCommandAction.runWriteCommandAction(project) {
            for (file in files) {
                val directory = file.containingDirectory
                val dir = directory.toString()
                var str = selection
                var lang = "en"
                //get language code
                if(dir.substring(dir.length-6, dir.length) != "values") {
                    lang = dir.substring(dir.length - 2, dir.length)
                    str = Translate.make(selection, "en", lang)
                }

                if(file.textLength < 13){
                    project.showNtf(Loc.textOf("info"), Loc.textOf("invalid_strings"), NotificationType.ERROR)
                    continue
                }
                val xmlFile = file as XmlFile
                if(xmlFile.document == null){
                    project.showNtf(Loc.textOf("info"), Loc.textOf("invalid_strings"), NotificationType.ERROR)
                    continue
                }
                val tags = xmlFile.document!!.rootTag!!.subTags

                val ts = ArrayList<XmlTag>()
                for (j in tags.indices) {
                    val value = tags[j].getAttributeValue("name")
                    if (key == value) {
                        ts.add(tags[j])
                    }
                }
                val count = ts.size.toLong()
                if (count == 0L) {
                    val f = File(file.originalFile.virtualFile.path)
                    var text = file.text.substring(0, file.text.length - "</resources>".length - 1)
                    text += "\n    <string name=\"$key\">$str</string>\n"
                    text += "</resources>"
                    f.bufferedWriter().use { out ->
                        out.write(text)
                    }
                } else {
                    project.showNtf(Loc.textOf("info"), Loc.textOf("existing_string"))
                }
            }
        }
        primaryCaret.removeSelection()
    }
}