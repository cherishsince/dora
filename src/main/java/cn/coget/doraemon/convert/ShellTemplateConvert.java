//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.convert;

import cn.coget.doraemon.dataobject.ShellTemplateDO;
import cn.coget.doraemon.po.ShellTemplateCreatePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShellTemplateConvert {
    ShellTemplateConvert INSTANCE = Mappers.getMapper(ShellTemplateConvert.class);

    @Mappings({})
    ShellTemplateDO convert(ShellTemplateCreatePO createPO);
}
