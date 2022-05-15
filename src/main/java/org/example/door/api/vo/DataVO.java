package org.example.door.api.vo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

@Data
public class DataVO {


    private JSON data;
    private Date lastUpdated;

}
