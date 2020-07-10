package cn.coget.dora.actuator.c2.template.l1.l2;

import cn.coget.dora.actuator.c2.template.l1.DataTemplate;

/**
 * 数据库读取
 *
 * author: sin
 * time: 2020/5/2 11:54 上午
 */
public class DataBaseTemplate implements DataTemplate {

    @Override
    public String reader(Long templateId) {
        throw new RuntimeException("请配置 DataBaseTemplate!");
    }
}
