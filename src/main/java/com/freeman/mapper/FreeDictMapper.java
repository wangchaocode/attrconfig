package com.freeman.mapper;

import com.freeman.properties.FreeDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangchao
 * @Description:
 * @date 2023/8/18 10:11
 * @vVersion 1.0
 */
@Mapper
public interface FreeDictMapper {
    public List<FreeDict > getFreeDict(FreeDict freeDict);
}
