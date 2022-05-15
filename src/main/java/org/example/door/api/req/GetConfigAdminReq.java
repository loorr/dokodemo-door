package org.example.door.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class GetConfigAdminReq {

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "describe")
    private String describe;

    @ApiModelProperty(value = "data")
    private String content;

}
