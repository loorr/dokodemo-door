package org.example.door.api;

import com.tove.web.infra.common.PageResult;
import com.tove.web.infra.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.vo.ConfigLogVO;
import org.example.door.api.vo.ConfigVO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(value = "获取配置信息")
public interface DataAdminApi {

    @ApiOperation("获取数据")
    @PostMapping(value = "/admin/get-config-page", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<PageResult<ConfigVO>> getConfigPage(@RequestBody @Validated GetConfigAdminReq req);

    @ApiOperation("获取数据")
    @PostMapping(value = "/admin/get-config-log-page", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<PageResult<ConfigLogVO>> getConfigLog(@RequestBody @Validated GetConfigAdminReq req);

    @ApiOperation("更新数据")
    @PostMapping(value = "/admin/update-config", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> updateConfig(@RequestBody @Validated AddOrUpdataConfigAdminReq req);

    @ApiOperation("添加数据")
    @PostMapping(value = "/admin/add-config", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> addConfig(@RequestBody @Validated AddOrUpdataConfigAdminReq req);

    @ApiOperation("删除数据")
    @PostMapping(value = "/admin/delete-config", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> deleteConfig(@RequestBody @Validated DeleteConfigAdminReq req);

}
