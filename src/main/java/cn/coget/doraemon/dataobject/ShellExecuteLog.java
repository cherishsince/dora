//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("shell_execute_log")
@Data
@Accessors(chain = true)
public class ShellExecuteLog extends BaseDO {
    private Integer id;
    private Integer shellTemplateId;
    private Integer shellExecutePlanId;
    private String template;
    private String computerName;
    private String ip;
    private String message;
}
