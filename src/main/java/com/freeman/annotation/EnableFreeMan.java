package com.freeman.annotation;

import com.freeman.FreeManAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wangchao
 * @Description:
 * @date 2023/8/18 14:39
 * @vVersion 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(FreeManAutoConfiguration.class)
public @interface EnableFreeMan {
}
