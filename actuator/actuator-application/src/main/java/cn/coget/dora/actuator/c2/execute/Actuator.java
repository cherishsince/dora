package cn.coget.dora.actuator.c2.execute;

/**
 * 执行单元
 *
 * author: sin
 * time: 2020/5/2 11:34 上午
 */
public interface Actuator {

    /**
     * 执行器
     *
     * @param shell
     * @return
     */
    String exec(String shell);
}
