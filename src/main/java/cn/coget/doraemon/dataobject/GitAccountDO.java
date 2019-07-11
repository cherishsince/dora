//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("git_account")
@Data
@Accessors(chain = true)
public class GitAccountDO extends DeletableDO {
    private Integer id;
    private String username;
    private String mail;
    private String objectName;
    private String objectUrl;
    private String publicKey;
    private String privateKey;
    private Integer status;
}
