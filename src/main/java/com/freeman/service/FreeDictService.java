package com.freeman.service;

import com.freeman.properties.FreeDict;
import com.freeman.mapper.FreeDictMapper;
import com.freeman.tools.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchao
 * @Description:
 * @date 2023/8/18 9:52
 * @vVersion 1.0
 */
@Slf4j
@Service
public class FreeDictService {
    @Autowired
    FreeDictMapper freeDictMapper;
    @Autowired
    FreeDict freeDict;

    /**
     * 放入需要转换类,返回结果
     * @return
     */
    public boolean queryOneDict(Object obj) throws Exception {
        if (null == freeDict.getTypeValue()){
            log.error("请配置Dict信息!");
            return false;
        }
        DataUtils.giveDefaultValue(freeDict);

        List<FreeDict> freeDicts= freeDictMapper.getFreeDict(freeDict);
        if (!DataUtils.isListNotNull(freeDicts)){
            log.info("dict字典未查询到相关信息！");
            return false;
        }
        Map<String, String> kpxx = new HashMap<>(16);
        for(FreeDict sd: freeDicts){
            kpxx.put(sd.getDictLabel(), sd.getDictValue());
        }
       return  DataUtils.copyGivenAttrOfBean(obj,kpxx,freeDict.isCopyNull());
    }
}
