package org.example.door.service;

import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.vo.ConfigLogVO;
import org.example.door.api.vo.ConfigVO;

import java.util.List;

public interface DataAdminService {

    List<ConfigVO> getConfig(GetConfigAdminReq req);

    List<ConfigLogVO> getConfigLog(GetConfigAdminReq req);

    Boolean updateConfig(AddOrUpdataConfigAdminReq req);

    Boolean addConfig(AddOrUpdataConfigAdminReq req);

    Boolean deleteConfig(DeleteConfigAdminReq req);

}
