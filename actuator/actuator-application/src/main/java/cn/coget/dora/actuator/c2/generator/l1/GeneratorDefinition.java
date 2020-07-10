package cn.coget.dora.actuator.c2.generator.l1;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 生成器，文件定义
 *
 * author: sin
 * time: 2020/5/2 12:03 下午
 */
@Data
@Accessors(chain = true)
public class GeneratorDefinition implements Serializable, Cloneable {

    private String version = "1.0";

    private String content;
}
