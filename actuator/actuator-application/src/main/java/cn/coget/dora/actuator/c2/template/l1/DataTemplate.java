package cn.coget.dora.actuator.c2.template.l1;

import cn.coget.dora.actuator.c2.template.Template;

/**
 * 文件模板读取
 * <p>
 * author: sin
 * time: 2020/5/2 11:50 上午
 */
public interface DataTemplate extends Template {

    String reader(Long templateId);

}
