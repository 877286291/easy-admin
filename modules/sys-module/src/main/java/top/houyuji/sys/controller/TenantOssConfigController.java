package top.houyuji.sys.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.houyuji.common.base.R;
import top.houyuji.sys.domain.dto.OssConfigDTO;
import top.houyuji.sys.service.SysTenantOssConfigService;


@RestController
@RequestMapping("/sys/tenant/oss/config")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：商户oss配置")
public class TenantOssConfigController {
    private final SysTenantOssConfigService service;

    /**
     * 获取oss配置
     *
     * @param sysCode 系统编码
     * @return oss配置
     */
    @GetMapping
    @Operation(summary = "获取oss配置")
    public R<OssConfigDTO> getOssConfig(String sysCode) {
        OssConfigDTO res = service.findBySysCode(sysCode);
        return R.OK(res);
    }

    /**
     * 更新保存oss配置
     *
     * @param dto oss配置
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新保存oss配置")
    public R<String> save(@Validated @RequestBody OssConfigDTO dto) {
        service.save(dto);
        return R.OK();
    }
}
