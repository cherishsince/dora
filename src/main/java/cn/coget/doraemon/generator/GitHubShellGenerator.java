//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.generator;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

public class GitHubShellGenerator extends BaseShellGeneratorSupport implements ShellGenerator {
    private static final String TEMPLATE_PATH = "shell-template/github-template.sh";

    public GitHubShellGenerator() {
    }

    public String templatePath() {
        return "shell-template/github-template.sh";
    }

    public String generate(String template, ShellConfig shellConfig) {
        GitHubShellConfig config = (GitHubShellConfig)shellConfig;
        Field[] fields = config.getClass().getDeclaredFields();
        Map<String, Object> templateParams = new LinkedHashMap();
        Field[] var6 = fields;
        int var7 = fields.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Field field = var6[var8];
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)) {
                Object fieldValue = ReflectionUtils.getField(field, config);
                if (fieldValue == null) {
                    fieldValue = "";
                }

                templateParams.put(field.getName(), fieldValue);
            }
        }

        return doFormat(template, templateParams);
    }
}
