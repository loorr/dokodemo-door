package org.example.door.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.example.door.model.StateEnum;

@Data
@ApiModel
public class AddOrUpdataConfigAdminReq {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "secretKey")
    private String secretKey;

    @ApiModelProperty(value = "describe")
    private String describe;

    @ApiModelProperty(value = "content")
    private String content;

    @ApiModelProperty(value = "state")
    private StateEnum state;

}
