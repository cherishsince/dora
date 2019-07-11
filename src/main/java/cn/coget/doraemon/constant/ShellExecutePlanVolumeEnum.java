//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.constant;

public enum ShellExecutePlanVolumeEnum {
    VOLUME_1(1, "提交强度一（一点色）"),
    VOLUME_2(2, "提交强度二（绿色）"),
    VOLUME_3(3, "提交强度三（深色）"),
    VOLUME_4(4, "提交强度四（黑色）");

    private Integer value;
    private String name;

    private ShellExecutePlanVolumeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getValue() {
        return this.value;
    }
}
