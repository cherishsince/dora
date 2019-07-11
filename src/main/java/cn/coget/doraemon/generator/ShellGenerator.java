//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.generator;

import java.io.Serializable;

public interface ShellGenerator {

    String templatePath();

    String generate(String template, ShellGenerator.ShellConfig shellConfig);

    class ShellConfig implements Serializable {
        public ShellConfig() {
        }
    }
}
