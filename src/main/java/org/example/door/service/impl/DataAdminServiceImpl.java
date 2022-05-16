package org.example.door.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tove.web.infra.common.BaseErrorCode;
import com.tove.web.infra.common.BaseException;
import com.tove.web.infra.common.PageResult;
import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.req.GetDataReq;
import org.example.door.api.vo.ConfigLogVO;
import org.example.door.api.vo.ConfigVO;
import org.example.door.dao.DataAdminMapper;
import org.example.door.model.Config;
import org.example.door.model.ConfigLog;
import org.example.door.model.StateEnum;
import org.example.door.service.DataAdminService;
import org.example.door.service.DataService;
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

    @Resource
    private DataService dataService;

    @Override
    public PageResult<ConfigVO> getConfig(GetConfigAdminReq req) {
        PageHelper.startPage(req.getPage(), req.getRows());
        Page<Config> list =  dataAdminMapper.getConfig(req);
        List<ConfigVO> configVOList =  list.stream().map(o->{
            ConfigVO configVO = new ConfigVO();
            BeanUtils.copyProperties(o,configVO);
            configVO.setState(StateEnum.of(o.getState()));
            configVO.setContent(JSON.parseObject(o.getContent(), JSON.class));
            return configVO;
        }).collect(Collectors.toList());
        return new PageResult<ConfigVO>(list.getTotal(), configVOList);
    }

    @Override
    public PageResult<ConfigLogVO> getConfigLog(GetConfigAdminReq req) {
        Page<ConfigLog> page = dataAdminMapper.getConfigLog(req);

        List<ConfigLogVO> list = page.stream().map(o->{
            ConfigLogVO vo = new ConfigLogVO();
            BeanUtils.copyProperties(o,vo);
            vo.setContent(JSON.parseObject(o.getContent(), JSON.class));
            return vo;
        }).collect(Collectors.toList());
        return new PageResult<ConfigLogVO>(page.getTotal(), list);
    }

    @Transactional
    @Override
    public Boolean updateConfig(AddOrUpdataConfigAdminReq req) {
        if (req.getId() == null || !checkDataFormat(req.getContent())){
            return false;
        }
        Config config = new Config();
        BeanUtils.copyProperties(req, config);
        updateConfig(config);
        refreshDataVO(req.getKey());
        return true;
    }

    @Override
    public Boolean addConfig(AddOrUpdataConfigAdminReq req) {
        checkDataFormat(req.getContent());

        Config config = new Config();
        BeanUtils.copyProperties(req, config);
        config.setState(req.getState().getCode());
        int rows = dataAdminMapper.addConfig(config);
        refreshDataVO(req.getKey());
        return rows>0;
    }

    @Transactional
    @Override
    public Boolean deleteConfig(DeleteConfigAdminReq req) {
        Long id = req.getId();
        Config config = dataAdminMapper.getConfigById(id);
        ConfigLog configLog = configToLog(config);
        deleteConfig(id, configLog);
        refreshDataVO(config.getKey());
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
    
    private boolean checkDataFormat(String data){
        try {
            JSON.parseObject(data, JSON.class);
        }catch (Exception e){
            throw new BaseException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
        return true;
    }

    private void refreshDataVO(String key){
        dataService.releaseDataVO(key);
        GetDataReq req = new GetDataReq();
        req.setKey(key);
        dataService.getData(req);
    }

}
