package com.book.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtils {
    // 创建一个模板引擎
    private static final TemplateEngine TEMPLATE_ENGINE;

    static {
        // 1.创建模板引擎对象
        TEMPLATE_ENGINE = new TemplateEngine();

        // 2.创建Thymeleaf解析器对象
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        // 3. 设置服务器端编码方式
        templateResolver.setCharacterEncoding("utf-8");

        // 4. 给模板引擎对象设置模板解析器
        TEMPLATE_ENGINE.setTemplateResolver(templateResolver);
    }

    public static void process(String template, IContext iContext, Writer writer){
        TEMPLATE_ENGINE.process(template, iContext, writer);
    }

}
