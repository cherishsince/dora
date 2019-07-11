//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.scheduler;

import cn.coget.doraemon.constant.DeletedStatusEnum;
import cn.coget.doraemon.constant.GitAccountStatusEnum;
import cn.coget.doraemon.constant.ShellExecutePlanStatusEnum;
import cn.coget.doraemon.constant.ShellExecutePlanVolumeEnum;
import cn.coget.doraemon.dao.GitAccountMapper;
import cn.coget.doraemon.dao.ShellExecutePlanMapper;
import cn.coget.doraemon.dao.ShellTemplateMapper;
import cn.coget.doraemon.dataobject.GitAccountDO;
import cn.coget.doraemon.dataobject.ShellExecutePlanDO;
import cn.coget.doraemon.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ShellExecutePlanJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShellExecutePlanJob.class);
    private static final Integer MAX_COUNT = 100;
    private static final Integer DEFAULT_VOLUME = 0;
    @Autowired
    public ShellExecutePlanMapper shellExecutePlanMapper;
    @Autowired
    public ShellTemplateMapper shellTemplateMapper;
    @Autowired
    private GitAccountMapper gitAccountMapper;

    public ShellExecutePlanJob() {
    }

    @Scheduled(
            cron = "0 0/8 * * * ?"
    )
    public void run() {
        long now = System.currentTimeMillis();
        int size = MAX_COUNT;
        int current = 1;

        while(true) {
            IPage<GitAccountDO> page = this.listActiveUser(size, current);
            ++current;
            List<GitAccountDO> listActiveUser = page.getRecords();
            if (CollectionUtils.isEmpty(listActiveUser)) {
                LOGGER.debug("没有任务的时候，退出!");
                LOGGER.info("执行计划[创建] 第 {} 批, 耗时 {} 毫秒!", current, System.currentTimeMillis() - now);
                return;
            }

            Set<Integer> userIds = (Set)listActiveUser.stream().map(GitAccountDO::getId).collect(Collectors.toSet());
            Map<Integer, ShellExecutePlanDO> existingPlansMap = this.getExistingPlans(userIds);
            Iterator var9 = listActiveUser.iterator();

            while(var9.hasNext()) {
                GitAccountDO gitAccountDO = (GitAccountDO)var9.next();
                if (!existingPlansMap.containsKey(gitAccountDO.getId())) {
                    ShellExecutePlanDO shellExecutePlanDO = (ShellExecutePlanDO)(new ShellExecutePlanDO()).setGitAccountId(gitAccountDO.getId()).setShellTemplateId(1).setVolume(ShellExecutePlanVolumeEnum.VOLUME_1.getValue()).setExecuteCount(DEFAULT_VOLUME).setStatus(ShellExecutePlanStatusEnum.WAIT_EXECUTE.getValue()).setDeleted(DeletedStatusEnum.DELETED_NO.getValue()).setCreateTime(new Date());
                    this.shellExecutePlanMapper.insert(shellExecutePlanDO);
                }
            }
        }
    }

    private Map<Integer, ShellExecutePlanDO> getExistingPlans(Set<Integer> userIds) {
        Date beginTime = DateUtil.getDayBegin(new Date());
        Date endTime = DateUtil.getDayEnd(new Date());
        List<ShellExecutePlanDO> existingPlans = this.shellExecutePlanMapper.selectList((Wrapper)((QueryWrapper)((QueryWrapper)(new QueryWrapper()).gt("create_time", beginTime)).lt("create_time", endTime)).in("git_account_id", userIds));
        return (Map)existingPlans.stream().collect(Collectors.toMap(ShellExecutePlanDO::getGitAccountId, (o) -> {
            return o;
        }));
    }

    private IPage<GitAccountDO> listActiveUser(Integer size, Integer current) {
        IPage<GitAccountDO> page = this.gitAccountMapper.selectPage((new Page()).setSize((long)size).setCurrent((long)current), (Wrapper)((QueryWrapper)(new QueryWrapper()).eq("status", GitAccountStatusEnum.ACTIVE.getValue())).orderByDesc("create_time"));
        return page;
    }
}
