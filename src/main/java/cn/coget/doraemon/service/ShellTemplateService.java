//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.service;

import cn.coget.doraemon.dataobject.ShellTemplateDO;
import cn.coget.doraemon.po.ShellTemplateCreatePO;
import cn.coget.doraemon.po.ShellTemplatePageQueryPO;
import cn.coget.doraemon.po.ShellTemplateUpdatePO;
import java.util.List;

public interface ShellTemplateService {
    List<ShellTemplateDO> page(ShellTemplatePageQueryPO queryPO);

    void createTemplate(ShellTemplateCreatePO createPO);

    void updateTemplate(ShellTemplateUpdatePO updatePO);

    void deletedTemplate(String id);
}
