package cn.coget.dora.actuator.c2.template.l1.l2;

import cn.coget.dora.actuator.c2.template.l1.FileTemplate;

import java.io.File;
import java.io.InputStream;

/**
 * 模板
 *
 * author: sin
 * time: 2020/5/2 11:46 上午
 */
@Deprecated
public class DefaultTemplate implements FileTemplate {

    @Override
    public TemplateDefinition reader(String file) {
        return null;
    }

    @Override
    public TemplateDefinition reader(File file) {
        return null;
    }

    @Override
    public TemplateDefinition reader(InputStream inputStream) {
        return null;
    }
}
