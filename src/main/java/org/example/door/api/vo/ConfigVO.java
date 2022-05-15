package org.example.door.api.vo;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.example.door.model.StateEnum;

import java.util.Date;

@Data
public class ConfigVO {

    private Long id;

    private String key;

    private String secretKey;

    private String describe;

    private JSON content;

    private StateEnum state;

    private Date dbCreateTime;

    private Date dbModifyTime;

}
