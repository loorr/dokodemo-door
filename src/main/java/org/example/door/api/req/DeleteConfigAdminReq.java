package org.example.door.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class DeleteConfigAdminReq {

    @ApiModelProperty(value = "id")
    @NotNull
    private Long id;

}
