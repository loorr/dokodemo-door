package org.example.door.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.door.api.req.GetDataReq;
import org.example.door.model.Config;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataMapper {

    @Select("select * from `config` where state = 0 and `key` = #{key}")
    Config getData(GetDataReq req);

}
