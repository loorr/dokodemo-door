package org.example.door.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class GetDataReq {

    @ApiModelProperty(value = "动态id")
    @NotNull
    private String key;

}
