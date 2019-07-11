//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.service.impl;

import cn.coget.doraemon.constant.DeletedStatusEnum;
import cn.coget.doraemon.convert.ShellTemplateConvert;
import cn.coget.doraemon.dao.ShellTemplateMapper;
import cn.coget.doraemon.dataobject.ShellTemplateDO;
import cn.coget.doraemon.po.ShellTemplateCreatePO;
import cn.coget.doraemon.po.ShellTemplatePageQueryPO;
import cn.coget.doraemon.po.ShellTemplateUpdatePO;
import cn.coget.doraemon.service.ShellTemplateService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShellTemplateServiceImpl implements ShellTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShellTemplateServiceImpl.class);
    @Autowired
    private ShellTemplateMapper shellTemplateMapper;

    public ShellTemplateServiceImpl() {
    }

    public List<ShellTemplateDO> page(ShellTemplatePageQueryPO queryPO) {
        return this.shellTemplateMapper.selectList(new QueryWrapper());
    }

    public void createTemplate(ShellTemplateCreatePO createPO) {
        ShellTemplateDO shellTemplateDO = (ShellTemplateDO)ShellTemplateConvert.INSTANCE.convert(createPO).setDeleted(DeletedStatusEnum.DELETED_NO.getValue()).setCreateTime(new Date());
        this.shellTemplateMapper.insert(shellTemplateDO);
    }

    public void updateTemplate(ShellTemplateUpdatePO updatePO) {
        ShellTemplateDO templateDO = (ShellTemplateDO)this.shellTemplateMapper.selectOne((Wrapper)(new QueryWrapper()).eq("id", updatePO.getId()));
        if (templateDO == null) {
            LOGGER.error("[模板不存在] {}", updatePO.toString());
        } else {
            this.shellTemplateMapper.update((ShellTemplateDO)(new ShellTemplateDO()).setTemplate(updatePO.getTemplate()).setUpdateTime(new Date()), (Wrapper)(new QueryWrapper()).eq("id", updatePO.getId()));
        }
    }

    public void deletedTemplate(String id) {
        ShellTemplateDO templateDO = (ShellTemplateDO)this.shellTemplateMapper.selectOne((Wrapper)(new QueryWrapper()).eq("id", id));
        if (templateDO == null) {
            LOGGER.error("[模板不存在] {}", id);
        } else {
            this.shellTemplateMapper.update((ShellTemplateDO)(new ShellTemplateDO()).setDeleted(DeletedStatusEnum.DELETED_YES.getValue()), (Wrapper)(new QueryWrapper()).eq("id", id));
        }
    }
}
