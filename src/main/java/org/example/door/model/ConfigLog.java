package org.example.door.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class ConfigLog extends BaseModel {
    /**
     * 配置key
     */
    private String key;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * secret key
     */
    private String secretKey;

    /**
     * 描述
     */
    private String describe;

    /**
     * data
     */
    private String content;

}
