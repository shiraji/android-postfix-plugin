/*
 * Copyright (C) 2014 Bob Browning
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kogitune.intellij.codeinsight.postfix;

import com.intellij.codeInsight.template.postfix.templates.JavaPostfixTemplateProvider;
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplate;
import com.intellij.util.containers.ContainerUtil;
import com.kogitune.intellij.codeinsight.postfix.templates.surround.DynamicTemplate;
import com.kogitune.intellij.codeinsight.postfix.templates.surround.LogDTemplate;
import com.kogitune.intellij.codeinsight.postfix.templates.surround.LogTemplate;
import com.kogitune.intellij.codeinsight.postfix.templates.surround.ToastTemplate;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Postfix template provider for extension point.
 *
 * @author takahirom
 */
public class AndroidPostfixTemplateProvider extends JavaPostfixTemplateProvider {

    private final HashSet<PostfixTemplate> templates;

    public AndroidPostfixTemplateProvider() {
        templates = ContainerUtil.<PostfixTemplate>newHashSet(
                new ToastTemplate(),
                new LogTemplate(),
                new LogDTemplate()
        );

        templates.add(new DynamicTemplate("ll", "Log.d($TAG$, $expr$)$END$ " +
                "// ll"));
        templates.add(new DynamicTemplate("l", "Log.d($TAG$, $expr$)$END$ // " +
                "1"));
        templates.add(new DynamicTemplate("l2", "Log.d(TAG, expr); // l2"));
        templates.add(new DynamicTemplate("l3", "Log.d(TAG, expr); // l3"));
        templates.add(new DynamicTemplate("l4", "Log.d(TAG, expr); // l4"));
        templates.add(new DynamicTemplate("l5", "Log.d(TAG, expr); // l5"));
    }

    @NotNull
    @Override
    public Set<PostfixTemplate> getTemplates() {
        return templates;
    }
}
