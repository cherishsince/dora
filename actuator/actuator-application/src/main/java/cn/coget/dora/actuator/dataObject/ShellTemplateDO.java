//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.dora.actuator.dataObject;

import cn.coget.dora.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("shell_template")
@Data
@Accessors(chain = true)
public class ShellTemplateDO extends DeletableDO {
    private Integer id;
    private String template;
}
