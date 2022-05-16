package org.example.door.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.example.door.api.req.GetDataReq;
import org.example.door.api.vo.DataVO;
import org.example.door.dao.DataMapper;
import org.example.door.model.Config;
import org.example.door.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    private static final Cache<String, DataVO> dataVOCache = Caffeine.newBuilder()
            .expireAfterWrite(30L, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();


    @Override
    public DataVO getData(GetDataReq req) {
        DataVO result = dataVOCache.getIfPresent(req.getKey());
        if (result == null){
            Config data =  dataMapper.getData(req);
            if (data != null){
                result = new DataVO();
                result.setData(JSON.parseObject(data.getContent(), JSON.class));
                result.setLastUpdated(data.getDbModifyTime());
            }
            dataVOCache.put(req.getKey(), result);
        }
        return result;
    }

    @Override
    public void releaseDataVO(String key){
        dataVOCache.invalidate(key);
    }
}
