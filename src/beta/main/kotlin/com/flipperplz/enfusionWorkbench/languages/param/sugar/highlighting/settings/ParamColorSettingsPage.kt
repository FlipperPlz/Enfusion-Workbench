package com.flipperplz.enfusionWorkbench.languages.param.sugar.highlighting.settings

import com.flipperplz.enfusionWorkbench.languages.param.sugar.highlighting.ParamSyntaxHighlighter
import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class ParamColorSettingsPage : ColorSettingsPage {
    private val descriptors = arrayOf(
        AttributesDescriptor("Keywords", ParamSyntaxHighlighter.KEYWORD),
        AttributesDescriptor("Operators", ParamSyntaxHighlighter.OPERATOR),
        AttributesDescriptor("Comments//Delimited", ParamSyntaxHighlighter.DELIMITED_COMMENT),
        AttributesDescriptor("Comments//Line", ParamSyntaxHighlighter.LINE_COMMENT),
        AttributesDescriptor("Literals//Strings", ParamSyntaxHighlighter.STRING),
        AttributesDescriptor("Literals//Numbers", ParamSyntaxHighlighter.NUMBER),
        AttributesDescriptor("Literals//Identifiers", ParamSyntaxHighlighter.IDENTIFIER),
        AttributesDescriptor("Separators//Braces", ParamSyntaxHighlighter.BRACES),
        AttributesDescriptor("Separators//Brackets", ParamSyntaxHighlighter.BRACKETS),
        AttributesDescriptor("Separators//Commas", ParamSyntaxHighlighter.COMMA),
        AttributesDescriptor("Separators//Semicolons", ParamSyntaxHighlighter.SEMICOLON),
        AttributesDescriptor("BadValue", ParamSyntaxHighlighter.BAD_CHARACTER)
    )


    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = descriptors

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "RV: Config File (rvmat | cpp)"

    override fun getIcon(): Icon = AllIcons.FileTypes.Config

    override fun getHighlighter(): SyntaxHighlighter = ParamSyntaxHighlighter()

     override fun getDemoText(): String = """
         /*/

         /**/
         /*
          * According to all known laws of aviation, there is no way a bee
          * should be able to fly. Its wings are too small to get its fat
          * little body off the ground. The bee, of course, flies anyway
          * because bees don't care what humans think is impossible...
         */
         class CfgPatches {
         	class ExternalClass;
         	class TestMod : ExternalClass {
         		names[] = {
         			{"Ryann", 1},
         			{"Elijah", 2},
         			{"Flipper", 3}
         		};
         		pi = 3.14159265359;
         		delete ExternalClass;
         		magicNumber = 1.267e8;
                hexNumber = 0x00001;
                shitty error :(
         		quotes = "Woahh "":O"" Crazy"; //As if anyone uses string escapes in DayZ.
         		crazyArray[] = {1, 1.2, "three", {"four", 5, 6.78e9}, -10, "over"};
         	};
         };
         enum {
         	who=1,
         	uses=2,
         	this,
         	shit=4
         };
     """.trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null
}