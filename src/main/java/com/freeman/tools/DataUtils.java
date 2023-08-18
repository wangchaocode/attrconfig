package com.freeman.tools;

import com.freeman.properties.FreeDict;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchao
 * @Description: 工具类，操作数据
 * @date 2023/8/18 9:56
 * @vVersion 1.0
 */
@Slf4j
public class DataUtils {
    public static final char UNDERLINE = '_';
    private static Map<String,String> map=new HashMap<String,String>();
    static{
        map.put("dictTableName","sys_dict_data");
        map.put("dictLabel","dict_label");
        map.put("dictValue","dict_value");
        map.put("dictType","dict_type");
    }
    /**
     * 设置首字母大写
     * @param attrName
     */
    private static String setFirstInitialUpperCase(String attrName) {
        if(StringUtils.isBlank(attrName)){
            return attrName;
        }
        return attrName.substring(0,1).toLowerCase()+attrName.substring(1);
    }
    /**
     * 拷贝map中某个属性给对象，给定label和属性值相同，那么赋值给属性，空不赋值
     * @param source
     * @param map
     * @param nullNotCopy
     */
    public static boolean  copyGivenAttrOfBean(Object source, Map<String, String> map, boolean nullNotCopy) throws Exception {
        boolean putFlag=false;
// 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);

        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
        if (null == map || map.size()==0){
            throw new Exception("属性map不能为空！");
        }
        try {
            boolean jumpFlag=false;
            String[] attrArray=new  String[map.size()];
            int mapI=0;
            for (Map.Entry<String, String> map1 : map.entrySet()){
                attrArray[mapI++]=map1.getKey();
            }
            for (int i = 0; i < sourceProperty.length; i++) {
                String attrName=sourceProperty[i].getName();
                // 首字母小写
                attrName=setFirstInitialUpperCase(attrName);
                if (!useList(attrArray,attrName)){
//                        log.debug("attr不存在数组中,跳过:{}",attrName);
                    continue;
                }

                String value= map.get(attrName);
                if (StringUtils.isBlank(value) && nullNotCopy) {
//                        log.debug("空值不拷贝:{}",attrName);
                    continue;
                }
                // 调用source的getter方法和dest的setter方法
//                    log.debug("{} 赋值1：{}",attrName,value);
                Object[] oParam = new Object[]{};
                oParam = new String[]{(value)};
                sourceProperty[i].getWriteMethod().invoke(source,oParam );
                log.debug("{}赋值：{}",attrName,value);
                putFlag=true;
            }
//                }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
        return putFlag;
    }

    /**
     * 判断是null则赋值默认
     *
     */
    public static void giveDefaultValue(FreeDict freeDictj) throws IllegalAccessException {
        if (null !=freeDictj){
            Class clz=freeDictj.getClass();
            // 反射获取赋值
            Field[] fields=clz.getDeclaredFields();
            for(Field f: fields){
                    f.setAccessible(true);
                    Object value =f.get(freeDictj);
                    if (null == value){
                        //赋值
                        String name=underlineToCamel(f.getName());
                        String name2=map.get(name);
                        log.debug("fName:{},mName:{},name2:{}",
                                f.getName(),name,name2);
                       f.set(freeDictj,name2 );
                    }
            }
        }
    }
    /**
     * 判断数组是否包含
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean useList(String[] arr, String targetValue) {
        // 2023-6-13空值，false
        if (!DataUtils.isArrayNotNull(arr)){
            return false;
        }
        return Arrays.asList(arr).contains(targetValue);
    }
    /**
     * 判断list是有值的
     * @return
     */
    public static boolean isListNotNull(List lt) {
        return null != lt &&
                lt.size()>0
                ? true :false;
    }
    /**
     * 判断array是有值的
     * @return
     */
    public static boolean isArrayNotNull(String[] lt) {
        return null != lt &&
                lt.length>0
                ? true :false;
    }
    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
//    public static void main(String[] args) throws IllegalAccessException {
//        FreeDict f=new FreeDict();
//        giveDefaultValue(f);
//        log.info(f.toString());
//    }
}
