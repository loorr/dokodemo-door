package org.example.door.api.controller;

import com.tove.web.infra.common.PageResult;
import com.tove.web.infra.common.Response;
import org.example.door.api.DataAdminApi;
import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.vo.ConfigLogVO;
import org.example.door.api.vo.ConfigVO;
import org.example.door.service.DataAdminService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DataAdminController implements DataAdminApi {

    @Resource
    private DataAdminService dataAdminService;

    @Override
    public Response<PageResult<ConfigVO>> getConfigPage(GetConfigAdminReq req) {
        PageResult<ConfigVO> configVO = dataAdminService.getConfig(req);
        return Response.getOk(configVO);
    }

    @Override
    public Response<PageResult<ConfigLogVO>> getConfigLog(GetConfigAdminReq req) {
        PageResult<ConfigLogVO> result = dataAdminService.getConfigLog(req);
        return Response.getOk(result);
    }

    @Override
    public Response<Boolean> updateConfig(AddOrUpdataConfigAdminReq req) {
        Boolean result = dataAdminService.updateConfig(req);
        return Response.getOk(result);
    }

    @Override
    public Response<Boolean> addConfig(AddOrUpdataConfigAdminReq req) {
        Boolean result = dataAdminService.addConfig(req);
        return Response.getOk(result);
    }

    @Override
    public Response<Boolean> deleteConfig(DeleteConfigAdminReq req) {
        Boolean result = dataAdminService.deleteConfig(req);
        return Response.getOk(result);
    }
}
