package com.example.demo.mapper;

import com.example.demo.dto.IdName;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author dustforest
 */
public interface UserMapper {
    @Select("select * from person")
    List<Map<String, Object>> queryUserListMap();

    @Select("select * from person")
    List<IdName> queryUserListDto();
}
