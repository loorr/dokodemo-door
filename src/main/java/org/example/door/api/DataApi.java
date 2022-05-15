package org.example.door.api;

import com.tove.web.infra.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.door.api.req.GetDataReq;
import org.example.door.api.vo.DataVO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "获取配置信息")
public interface DataApi {

    @ApiOperation("获取数据")
    @PostMapping(value = "/admin/get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<DataVO> getData(@RequestBody @Validated GetDataReq req);

}
