package com.freeman.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangchao
 * @Description:
 * @date 2023/8/18 9:47
 * @vVersion 1.0
 */
@ConfigurationProperties(prefix = "dict")
@Component
@Data
public class FreeDict {
    /**
     * 表名 标签、值、类型
     */
    private String dictTableName;
    private String dictLabel;
    private String dictValue;
    private String dictType;

    private boolean copyNull;
    private String typeValue;
}
