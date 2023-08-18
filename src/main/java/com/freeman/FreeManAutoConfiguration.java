package com.freeman;

import com.freeman.mapper.FreeDictMapper;
import com.freeman.properties.FreeDict;
import com.freeman.service.FreeDictService;
import com.freeman.tools.DataUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.xml.crypto.Data;

/**
 * @author wangchao
 * @Description:
 * @date 2023/8/18 14:37
 * @vVersion 1.0
 */
@Import({FreeDict.class,FreeDictService.class, DataUtils.class})
@MapperScan("com.freeman.mapper")
@Configuration
public class FreeManAutoConfiguration {
}
