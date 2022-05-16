package org.example.door.api.req;

import com.tove.web.infra.common.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class GetConfigAdminReq extends Page {

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "describe")
    private String describe;

    @ApiModelProperty(value = "data")
    private String content;

}
