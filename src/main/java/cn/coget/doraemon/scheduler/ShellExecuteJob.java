//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.scheduler;

import cn.coget.doraemon.constant.DeletedStatusEnum;
import cn.coget.doraemon.constant.ShellExecutePlanStatusEnum;
import cn.coget.doraemon.dao.GitAccountMapper;
import cn.coget.doraemon.dao.ShellExecutePlanMapper;
import cn.coget.doraemon.dao.ShellTemplateMapper;
import cn.coget.doraemon.dataobject.GitAccountDO;
import cn.coget.doraemon.dataobject.ShellExecutePlanDO;
import cn.coget.doraemon.dataobject.ShellTemplateDO;
import cn.coget.doraemon.generator.GitHubShellConfig;
import cn.coget.doraemon.generator.GitHubShellGenerator;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class ShellExecuteJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShellExecuteJob.class);
    private static final Integer MAX_RETRY_COUNT = 3;
    @Autowired
    private ShellExecutePlanMapper shellExecutePlanMapper;
    @Autowired
    private ShellTemplateMapper shellTemplateMapper;
    @Autowired
    private GitAccountMapper gitAccountMapper;
    @Autowired
    private GitHubShellGenerator gitHubShellGenerator;
    @Autowired
    private DefaultExecuteShell defaultExecuteShell;

    public ShellExecuteJob() {
    }

    @Scheduled(
            cron = "0 0/10 * * * ?"
    )
    public void run() {
        Page<ShellExecutePlanDO> panelPage = (Page)this.shellExecutePlanMapper.selectPage((new Page()).setCurrent(1L).setSize(10L).setDesc(new String[]{"update_time"}), (Wrapper)((QueryWrapper)(new QueryWrapper()).eq("deleted", DeletedStatusEnum.DELETED_NO.getValue())).in("status", new Object[]{ShellExecutePlanStatusEnum.WAIT_EXECUTE.getValue(), ShellExecutePlanStatusEnum.EXECUTE_FAIL.getValue()}));
        List<ShellExecutePlanDO> panelPageRecords = panelPage.getRecords();
        if (CollectionUtils.isEmpty(panelPageRecords)) {
            LOGGER.debug("没有可执行的任务!");
        } else {
            Iterator var3 = panelPageRecords.iterator();

            while(var3.hasNext()) {
                ShellExecutePlanDO shellExecutePlanDO = (ShellExecutePlanDO)var3.next();
                this.executeSimple(shellExecutePlanDO);
            }

        }
    }

    private void executeSimple(ShellExecutePlanDO shellExecutePlanDO) {
        if (shellExecutePlanDO.getExecuteCount() >= MAX_RETRY_COUNT) {
            LOGGER.info("超过重试次数! {}", JSON.toJSONString(shellExecutePlanDO));
            this.shellExecutePlanMapper.update((ShellExecutePlanDO)(new ShellExecutePlanDO()).setStatus(ShellExecutePlanStatusEnum.EXECUTE_FAIL.getValue()).setUpdateTime(new Date()), (Wrapper)(new QueryWrapper()).eq("id", shellExecutePlanDO.getId()));
        } else {
            ShellTemplateDO shellTemplateDO = (ShellTemplateDO)this.shellTemplateMapper.selectOne((Wrapper)(new QueryWrapper()).eq("id", shellExecutePlanDO.getShellTemplateId()));
            if (StringUtils.isEmpty(shellTemplateDO)) {
                LOGGER.error("[执行的 shell template] 为空!");
            } else {
                GitAccountDO gitAccountDO = (GitAccountDO)this.gitAccountMapper.selectOne((Wrapper)(new QueryWrapper()).eq("id", shellExecutePlanDO.getGitAccountId()));
                String content = "\nThe InfoQ Newsletter\nA round-up of last week’s content on InfoQ sent out every Tuesday. Join a community of over 250,000 senior developers. View an example\n\nGet a quick overview of content published on a variety of innovator and early adopter technologies\nLearn what you don’t know that you don’t know\nStay up to date with the latest information from the topics you are interested in\nEnter your e-mail address\nWe protect your privacy.\n\nQCons Worldwide\nNEXT EVENT\nNew York\nSoftware Development Conference\n\nJun 24 - 26, 2019\n\nUpcoming QCons\nGuangzhou/ May 25 - 28, 2019\nShanghai / Oct 17 - 19, 2019\nSan Francisco/ Nov 11 - 15, 2019\nLondon/ Mar 2 - 6, 2020\nAI London/2020\nAI San Francisco/ Spring, 2020\n";
                String shell = this.gitHubShellGenerator.generate(shellTemplateDO.getTemplate(), (new GitHubShellConfig()).setUsername(gitAccountDO.getUsername()).setMail(gitAccountDO.getMail()).setObjectName(gitAccountDO.getObjectName()).setContent(String.format(" -> %s", content)).setPrivateKey(gitAccountDO.getPrivateKey()).setPublicKey(gitAccountDO.getPublicKey()));
                LOGGER.info("生成的 shell \r\n {}", shell);
                boolean executeSuccess = this.defaultExecuteShell.execute(shell);
                if (!executeSuccess) {
                    this.shellExecutePlanMapper.update((ShellExecutePlanDO)(new ShellExecutePlanDO()).setExecuteCount(shellExecutePlanDO.getExecuteCount() + 1).setUpdateTime(new Date()), (Wrapper)(new QueryWrapper()).eq("id", shellExecutePlanDO.getId()));
                }

                this.shellExecutePlanMapper.update((new ShellExecutePlanDO()).setStatus(ShellExecutePlanStatusEnum.EXECUTE_PROGRESS.getValue()), (Wrapper)(new QueryWrapper()).eq("id", shellExecutePlanDO.getId()));
            }
        }
    }
}
