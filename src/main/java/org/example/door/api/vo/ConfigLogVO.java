package org.example.door.api.vo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

@Data
public class ConfigLogVO {

    private Long id;

    private String key;

    private String secretKey;

    private String describe;

    private JSON content;

    private Date updateTime;

}
