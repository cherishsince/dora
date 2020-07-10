package cn.coget.dora.actuator.c2.generator.l1;

import java.util.Map;

/**
 * shell 文件生成器
 *
 * author: sin
 * time: 2020/5/2 11:37 上午
 */
public interface ShellGenerator extends DefaultGenerator {

    GeneratorDefinition generator(String template, Map<String, String> params);

}
