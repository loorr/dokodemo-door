package org.example.door.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

@Data
public class Config extends BaseModel {

    private String key;
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


    private Integer state;

}
