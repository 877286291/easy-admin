package top.houyuji.common.mybatis.tenant.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import top.houyuji.common.base.core.TenantContext;
import top.houyuji.common.base.core.UserInfo;
import top.houyuji.common.mybatis.tenant.config.TenantProperties;

@Slf4j
@RequiredArgsConstructor
public class PlusTenantLineHandler implements TenantLineHandler {
    private final TenantProperties tenantProperties;

    @Override
    public Expression getTenantId() {
        UserInfo userInfo = TenantContext.get();
        if (null == userInfo) {
            log.warn("无法获取有效的租户id - sysCode");
            return new NullValue();
        }
        String sysCode = userInfo.getSysCode();
        return new StringValue(sysCode);
    }

    @Override
    public String getTenantIdColumn() {
        return tenantProperties.getTenantColumn();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return tenantProperties.getIgnoreTables().contains(tableName);
    }
}
