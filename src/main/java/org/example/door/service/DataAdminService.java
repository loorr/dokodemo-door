package org.example.door.service;

import com.tove.web.infra.common.PageResult;
import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.vo.ConfigLogVO;
import org.example.door.api.vo.ConfigVO;

public interface DataAdminService {

    PageResult<ConfigVO> getConfig(GetConfigAdminReq req);

    PageResult<ConfigLogVO> getConfigLog(GetConfigAdminReq req);

    Boolean updateConfig(AddOrUpdataConfigAdminReq req);

    Boolean addConfig(AddOrUpdataConfigAdminReq req);

    Boolean deleteConfig(DeleteConfigAdminReq req);

}
