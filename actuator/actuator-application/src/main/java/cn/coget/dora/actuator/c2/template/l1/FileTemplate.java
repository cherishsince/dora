package cn.coget.dora.actuator.c2.template.l1;

import cn.coget.dora.actuator.c2.template.Template;
import cn.coget.dora.actuator.c2.template.l1.l2.TemplateDefinition;

import java.io.File;
import java.io.InputStream;

/**
 * 文件模板读取
 *
 * author: sin
 * time: 2020/5/2 11:50 上午
 */
public interface FileTemplate extends Template {

    /**
     * 读取 - 文件
     *
     * @param file
     * @return
     */
    TemplateDefinition reader(String file);

    /**
     * 读取 - 文件
     *
     * @param file
     * @return
     */
    TemplateDefinition reader(File file);

    /**
     * 读取 - 文件
     *
     * @param inputStream
     * @return
     */
    TemplateDefinition reader(InputStream inputStream);

}
