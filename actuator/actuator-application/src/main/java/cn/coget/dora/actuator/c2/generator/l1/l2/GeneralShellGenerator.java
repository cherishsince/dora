package cn.coget.dora.actuator.c2.generator.l1.l2;

import cn.coget.dora.actuator.c2.generator.l1.GeneratorDefinition;
import cn.coget.dora.actuator.c2.generator.l1.ShellGenerator;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 通用的 shell 生成器
 * <p>
 * author: sin
 * time: 2020/5/2 12:06 下午
 */
public class GeneralShellGenerator implements ShellGenerator {

    @Override
    public GeneratorDefinition generator(String template, Map<String, String> params) {
        String formatTemplate = this.doFormat(template, params);
        return new GeneratorDefinition().setContent(formatTemplate);
    }

    public static String doFormat(String template, Map<String, String> params) {
        if (StringUtils.isEmpty(template)) {
            return template;
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            template = template.replaceAll(String.format("\\{%s\\}", key), value);
        }
        return template;
    }
}
