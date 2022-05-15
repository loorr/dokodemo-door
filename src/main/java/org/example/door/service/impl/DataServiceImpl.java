package org.example.door.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.door.api.req.GetDataReq;
import org.example.door.api.vo.DataVO;
import org.example.door.dao.DataMapper;
import org.example.door.model.Config;
import org.example.door.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataServiceImpl implements DataService {

    @Resource
    protected DataMapper dataMapper;

    @Override
    public DataVO getData(GetDataReq req) {
        Config data =  dataMapper.getData(req);
        DataVO result = new DataVO();
        result.setData(JSON.parseObject(data.getContent(), JSON.class));
        result.setLastUpdated(data.getDbModifyTime());
        return result;
    }
}
