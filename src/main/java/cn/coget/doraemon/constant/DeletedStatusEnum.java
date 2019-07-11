//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.constant;

public enum DeletedStatusEnum {
    DELETED_NO(0, "正常(未删除)"),
    DELETED_YES(1, "删除");

    private Integer value;
    private String name;

    private DeletedStatusEnum(Integer value, String name) {
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
