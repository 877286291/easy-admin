package top.houyuji.common.base.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class UserInfo implements Serializable {
    private String id;
    private String username;
    private String sysCode;

    public UserInfo(String id, String username, String sysCode) {
        this.id = id;
        this.username = username;
        this.sysCode = sysCode;
    }

    public UserInfo() {
    }
}
