package org.example.door.dao;

import org.apache.ibatis.annotations.*;
import org.example.door.api.req.AddOrUpdataConfigAdminReq;
import org.example.door.api.req.DeleteConfigAdminReq;
import org.example.door.api.req.GetConfigAdminReq;
import org.example.door.api.req.GetDataReq;
import org.example.door.model.Config;
import org.example.door.model.ConfigLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataAdminMapper {

    @Select("<script>" +
            "select * from `config` " +
            "<where> " +
            "<if test='key != null and key !=\"\"'>and `key` like CONCAT('%', #{key}, '%') </if>" +
            "<if test='describe != null and describe !=\"\"'>and `describe` like CONCAT('%', #{describe}, '%') </if>" +
            "<if test='content != null and content !=\"\"'>and `content`  like CONCAT('%', #{content}, '%') </if>" +
            "</where>" +
            "</script>")
    List<Config> getConfig(GetConfigAdminReq req);


    @Select("<script>" +
            "select * from `config_log` " +
            "<where> " +
            "<if test='key != null and key !=\"\"'>and `key` like CONCAT('%', #{key}, '%') </if>" +
            "<if test='describe != null and describe !=\"\"'>and `describe` like CONCAT('%', #{describe}, '%'), </if>" +
            "<if test='content != null and content !=\"\"'>and `content` like CONCAT('%', #{content}, '%') </if>" +
            "</where>" +
            "order by update_time desc" +
            "</script>")
    List<ConfigLog> getConfigLog(GetConfigAdminReq req);

    @Options(useGeneratedKeys=true,keyProperty="id")
    @Insert("insert into `config`(`key`,`secret_key`,`describe`,`content`,`state`)" +
            " values(#{key},#{secretKey}, #{describe}, #{content}, #{state})")
    int addConfig(Config config);

    @Options(useGeneratedKeys=true,keyProperty="id")
    @Insert("insert into `config_log` " +
            " (`key`,`update_time`,`secret_key`,`describe`,`content`)" +
            " values(#{key}, #{updateTime},#{secretKey},#{describe}, #{content})")
    int addConfigLog(ConfigLog configLog);

    @Delete("delete from `config` where `id` = #{id}")
    int deleteConfig(Long id);

    @Update("<script>" +
            "update `config` " +
            "<set> " +
            "<if test='key != null and key !=\"\"'>`key` = #{key}, </if>" +
            "<if test='secretKey != null and secretKey !=\"\"'>`secret_key` = #{secretKey}, </if>" +
            "<if test='describe != null and describe !=\"\"'> `describe` = #{describe}, </if>" +
            "<if test='content != null and content !=\"\"'>`content` =  #{content}, </if>" +
            "<if test='state != null'>`state` =  #{state} </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    int updateConfig(Config config);

    @Select("select * from `config` where id = #{id}")
    Config getConfigById(Long id);
}
