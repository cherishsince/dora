//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.execute.dataobject;

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
