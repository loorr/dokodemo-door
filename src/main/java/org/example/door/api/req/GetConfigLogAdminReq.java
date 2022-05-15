package org.example.door.api.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GetConfigLogAdminReq {

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "describe")
    private String describe;

    @ApiModelProperty(value = "data")
    private String content;

    @ApiModelProperty(value = "start")
    private Date start;

    @ApiModelProperty(value = "end")
    private Date end;

}
