package org.example.door.api.controller;

import com.tove.web.infra.common.Response;
import org.example.door.api.DataApi;
import org.example.door.api.req.GetDataReq;
import org.example.door.api.vo.DataVO;
import org.example.door.service.DataService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author loorr
 */
@RestController
public class DataController implements DataApi {

    @Resource
    private DataService dataService;

    @Override
    public Response<DataVO> getData(GetDataReq req) {
        DataVO result = dataService.getData(req);
        return Response.getOk(result);
    }
}
