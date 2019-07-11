//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.constant;

public enum GitAccountStatusEnum {
    NOT_ACTIVE(1, "未激活"),
    ACTIVE(2, "激活");

    private Integer value;
    private String name;

    private GitAccountStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
