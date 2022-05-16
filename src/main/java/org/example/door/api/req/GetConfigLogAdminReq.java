package org.example.door.api.req;

import com.tove.web.infra.common.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GetConfigLogAdminReq extends Page {

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "describe")
    private String describe;

    @ApiModelProperty(value = "data")
    private String content;

    @ApiModelProperty(value = "start")
    private Date startDate;

    @ApiModelProperty(value = "end")
    private Date endDate;

}
