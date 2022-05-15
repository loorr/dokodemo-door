package org.example.door.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.vo.ConfigLogVO;
import org.example.door.api.vo.ConfigVO;
import org.example.door.dao.DataAdminMapper;
import org.example.door.model.Config;
import org.example.door.model.ConfigLog;
import org.example.door.model.StateEnum;
import org.example.door.service.DataAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataAdminServiceImpl implements DataAdminService {

    @Resource
    private DataAdminMapper dataAdminMapper;

    @Override
    public List<ConfigVO> getConfig(GetConfigAdminReq req) {
        List<Config> list =  dataAdminMapper.getConfig(req);
        return list.stream().map(o->{
            ConfigVO configVO = new ConfigVO();
            BeanUtils.copyProperties(o,configVO);
            configVO.setState(StateEnum.of(o.getState()));
            configVO.setContent(JSON.parseObject(o.getContent(), JSON.class));
            return configVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ConfigLogVO> getConfigLog(GetConfigAdminReq req) {
        List<ConfigLog> list = dataAdminMapper.getConfigLog(req);

        return list.stream().map(o->{
            ConfigLogVO vo = new ConfigLogVO();
            BeanUtils.copyProperties(o,vo);
            vo.setContent(JSON.parseObject(o.getContent(), JSON.class));
            return vo;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Boolean updateConfig(AddOrUpdataConfigAdminReq req) {
        if (req.getId() == null){
            return false;
        }
        Config config = new Config();
        BeanUtils.copyProperties(req, config);
        updateConfig(config);
        return true;
    }

    @Override
    public Boolean addConfig(AddOrUpdataConfigAdminReq req) {
        Config config = new Config();
        BeanUtils.copyProperties(req, config);
        config.setState(req.getState().getCode());
        int rows = dataAdminMapper.addConfig(config);
        return rows>0;
    }

    @Transactional
    @Override
    public Boolean deleteConfig(DeleteConfigAdminReq req) {
        Long id = req.getId();
        Config config = dataAdminMapper.getConfigById(id);
        ConfigLog configLog = configToLog(config);
        deleteConfig(id, configLog);
        return true;
    }

    @Transactional
    public void deleteConfig(Long id, ConfigLog configLog){
        int rows1 = dataAdminMapper.deleteConfig(id);
        int rows2 = dataAdminMapper.addConfigLog(configLog);
    }

    @Transactional
    public void updateConfig(Config config){
        int rows = dataAdminMapper.updateConfig(config);
        if (rows > 0){
            ConfigLog configLog = configToLog(config);
            int rows2 = dataAdminMapper.addConfigLog(configLog);
        }
    }

    private ConfigLog configToLog(Config config){
        ConfigLog configLog = new ConfigLog();
        BeanUtils.copyProperties(config, configLog);
        configLog.setUpdateTime(new Date());
        return configLog;
    }

}
