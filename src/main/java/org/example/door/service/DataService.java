package org.example.door.service;

import org.example.door.api.req.GetDataReq;
import org.example.door.api.vo.DataVO;

public interface DataService {
    DataVO getData(GetDataReq req);
}
