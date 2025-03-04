package top.houyuji.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.houyuji.basic.domain.dto.BasNoticeDTO;
import top.houyuji.basic.domain.entity.BasNoticeRecord;
import top.houyuji.basic.domain.query.BasNoticeQuery;
import top.houyuji.basic.mapper.BasNoticeRecordMapper;
import top.houyuji.basic.service.mapstruct.BasNoticeMapstruct;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.utils.CollectionUtil;
import top.houyuji.common.query.mybatis.plus.QueryHelper;
import top.houyuji.sys.domain.entity.SysNotice;
import top.houyuji.sys.service.SysNoticeService;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasNoticeService extends ServiceImpl<BasNoticeRecordMapper, BasNoticeRecord> {
    private final SysNoticeService sysNoticeService;
    private final BasNoticeMapstruct basNoticeMapstruct;

    /**
     * 查询未读消息数量
     *
     * @param userId 用户id
     * @return 未读消息数量
     */
    public Integer countUnread(String userId) {
        List<String> noticeIds = baseMapper.findNoticeIdByUserId(userId);
        if (CollectionUtil.isEmpty(noticeIds)) {
            return sysNoticeService.countNotices();
        }
        return sysNoticeService.countNotInIds(noticeIds);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<BasNoticeDTO> page(BasNoticeQuery query) {
        List<String> noticeIds = baseMapper.findNoticeIdByUserId(query.getUserId());
        query.setSorts("created desc");
        query.setEnabled(true);
        QueryWrapper<SysNotice> queryWrapper = QueryHelper.ofBean(query);
        IPage<SysNotice> page = QueryHelper.toPage(query);
        page = sysNoticeService.page(page, queryWrapper);
        List<BasNoticeDTO> res = basNoticeMapstruct.toDTOList(page.getRecords());
        res.forEach(
                notice -> isRead(notice, noticeIds)
        );
        return QueryHelper.toJsfPage(page, res);
    }


    /**
     * 标记已读
     *
     * @param userId    用户ID
     * @param noticeIds 公告ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void reads(String userId, List<String> noticeIds) {
        if (CollectionUtil.isEmpty(noticeIds)) {
            List<String> readNoticeIds = baseMapper.findNoticeIdByUserId(userId);
            if (CollectionUtil.isEmpty(readNoticeIds)) {
                noticeIds = sysNoticeService.getIds();
            } else {
                noticeIds = sysNoticeService.getIdsNotInIds(readNoticeIds);
            }
        }
        List<BasNoticeRecord> notices = noticeIds.stream().map(e -> {
            BasNoticeRecord noticeRecord = new BasNoticeRecord();
            noticeRecord.setUserId(userId);
            noticeRecord.setNoticeId(e);
            noticeRecord.setReadTime(new Date());
            noticeRecord.setRead(true);
            return noticeRecord;
        }).toList();

        saveBatch(notices);
    }

    private void isRead(BasNoticeDTO notice, List<String> noticeIds) {
        if (noticeIds.contains(notice.getId())) {
            notice.setRead(true);
            return;
        }
        notice.setRead(false);
    }

}
