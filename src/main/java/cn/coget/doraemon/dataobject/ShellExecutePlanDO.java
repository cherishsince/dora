//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("shell_execute_plan")
@Data
@Accessors(chain = true)
public class ShellExecutePlanDO extends DeletableDO {
    private Integer id;
    private Integer gitAccountId;
    private Integer shellTemplateId;
    private Integer volume;
    private Integer executeCount;
    private Integer status;
}
