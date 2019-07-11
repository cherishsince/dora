//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.constant;

public enum ShellExecutePlanStatusEnum {
    WAIT_EXECUTE(1, "等待执行"),
    EXECUTE_PROGRESS(2, "执行中"),
    EXECUTE_SUCCESS(3, "执行成功"),
    EXECUTE_FAIL(100, "执行失败");

    private Integer value;
    private String name;

    private ShellExecutePlanStatusEnum(Integer value, String name) {
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
