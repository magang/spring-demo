package com.example.demo.mapper.ext;

import com.example.demo.entity.Person;
import org.apache.ibatis.annotations.Select;

public interface PersonExtMapper {
    @Select("select * from person where id=#{id}")
    Person get(int id);
}