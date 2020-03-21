//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.execute.generator;

import cn.coget.doraemon.execute.generator.ShellGenerator.ShellConfig;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GitHubShellConfig extends ShellConfig {
    private String mail;
    private String username;
    private String objectName;
    private String content;
    private String publicKey;
    private String privateKey;
}
