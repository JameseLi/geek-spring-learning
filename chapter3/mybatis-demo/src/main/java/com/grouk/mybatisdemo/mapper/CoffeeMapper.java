package com.grouk.mybatisdemo.mapper;

import com.grouk.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;

/**
 * @author lizhengjun
 */
@Mapper
public interface CoffeeMapper {
    @Insert("insert into dbgirl.t_coffee (name,price,create_time,update_time) values (#{name},#{price},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true)
    Long save(Coffee coffee);

    @Select("select * from dbgirl.t_coffee where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime")
    })
    Coffee findById(@Param("id") Long id);
}
