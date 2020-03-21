//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.execute.controller;

import cn.coget.doraemon.execute.scheduler.ShellExecuteJob;
import cn.coget.doraemon.execute.scheduler.ShellExecutePlanJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务 api
 */
@RestController
public class JobController {
    @Autowired
    private ShellExecuteJob shellExecuteJob;
    @Autowired
    private ShellExecutePlanJob shellExecutePlanJob;

    public JobController() {
    }

    @GetMapping({"job/execute"})
    public void execute() {
        this.shellExecuteJob.run();
    }

    @GetMapping({"job/execute_plan"})
    public void executePlan() {
        this.shellExecutePlanJob.run();
    }
}
