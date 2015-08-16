package com.kogitune.intellij.codeinsight.postfix.templates.surround;

import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.impl.ConstantNode;
import com.intellij.codeInsight.template.impl.MacroCallNode;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.kogitune.intellij.codeinsight.postfix.internal.RichChooserStringBasedPostfixTemplate;
import com.kogitune.intellij.codeinsight.postfix.macro.ToStringIfNeedMacro;
import com.kogitune.intellij.codeinsight.postfix.utils.AndroidPostfixTemplatesUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.codeInsight.template.postfix.util.JavaPostfixTemplatesUtils.IS_NON_VOID;

/**
 * Created by Shiraji on 15/08/15.
 */
public class DynamicTemplate extends RichChooserStringBasedPostfixTemplate {
    public static final Condition<PsiElement> IS_NON_NULL = new Condition<PsiElement>() {
        @Override
        public boolean value(PsiElement element) {
            return IS_NON_VOID.value(element) && !AndroidPostfixTemplatesUtils.isAnnotatedNullable(element);
        }
    };
    private String mTemplateString;

    public DynamicTemplate(@NotNull String name, @NotNull String example) {
        super(name, example, IS_NON_NULL);
        mTemplateString = example;
    }

    @Nullable
    @Override
    public String getTemplateString(@NotNull PsiElement element) {
        return mTemplateString;
    }

    @Override
    protected void addExprVariable(@NotNull PsiElement expr, Template template) {
        final ToStringIfNeedMacro toStringIfNeedMacro = new ToStringIfNeedMacro();
        MacroCallNode macroCallNode = new MacroCallNode(toStringIfNeedMacro);
        macroCallNode.addParameter(new ConstantNode(expr.getText()));
        template.addVariable("expr", macroCallNode, false);
    }
}
