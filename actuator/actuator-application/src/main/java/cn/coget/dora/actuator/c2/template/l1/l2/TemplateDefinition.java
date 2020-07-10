package cn.coget.dora.actuator.c2.template.l1.l2;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 模板定义
 *
 * author: sin
 * time: 2020/5/2 11:44 上午
 */
@Data
@Accessors(chain = true)
public class TemplateDefinition {

    private String version = "1";

    private String content;

}
