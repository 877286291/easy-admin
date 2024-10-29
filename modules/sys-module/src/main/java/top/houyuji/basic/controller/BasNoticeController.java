package top.houyuji.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.houyuji.basic.domain.dto.BasNoticeDTO;
import top.houyuji.basic.domain.query.BasNoticeQuery;
import top.houyuji.basic.service.BasNoticeService;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.R;
import top.houyuji.satoken.domain.dto.UserInfoDTO;
import top.houyuji.satoken.domain.dto.UserOrgInfoDTO;
import top.houyuji.satoken.utils.SaTokenUtil;

import java.util.List;

@RestController
@RequestMapping("/bas/notice")
@Tag(name = "履约端：公告管理")
@RequiredArgsConstructor
public class BasNoticeController {
    private final BasNoticeService basNoticeService;

    /**
     * 查询未读消息数量
     *
     * @return 未读消息数量
     */
    @GetMapping("/unread/count")
    @Operation(summary = "未读统计")
    public R<Integer> countUnread() {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        if (currentUser == null) {
            return R.NG("用户未登录");
        }
        // 排除管理端
        UserOrgInfoDTO orgInfo = currentUser.getOrgInfo();
        if (null == orgInfo) {
            return R.OK();
        }
        return R.OK(basNoticeService.countUnread(currentUser.getId()));
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询公告")
    public R<JsfPage<BasNoticeDTO>> page(BasNoticeQuery query) {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        if (currentUser == null) {
            return R.NG("用户未登录");
        }
        // 排除管理端
        UserOrgInfoDTO orgInfo = currentUser.getOrgInfo();
        if (null == orgInfo) {
            return R.OK();
        }
        String tenantId = orgInfo.getPath().split(",")[0];
        query.setOrgId(tenantId);
        query.setUserId(currentUser.getId());
        JsfPage<BasNoticeDTO> res = basNoticeService.page(query);
        return R.OK(res);
    }

    /**
     * 读公告
     *
     * @param noticeIds .
     * @return .
     */
    @PutMapping("/read")
    @Operation(summary = "读公告")
    public R<String> reads(@RequestBody List<String> noticeIds) {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        if (currentUser == null) {
            return R.OK();
        }
        basNoticeService.reads(currentUser.getId(), noticeIds);
        return R.OK();
    }

}
