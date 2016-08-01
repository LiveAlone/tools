package io.terminus.tools.codegen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Slf4j
public class FieldTypeConvert {

    /**
     * sql 字段转换 Class 字段
     * @param fieldType
     * @return
     */
    public static String columnTypeToFieldType(String jdbcType){
        String lowJdbcType = jdbcType.toLowerCase();

        if(lowJdbcType.contains("bigint")){
            return "Long";
        }

        if (lowJdbcType.contains("int")){
            return "Integer";
        }

        if(lowJdbcType.contains("varchar") || lowJdbcType.contains("text")){
            return "String";
        }
        if(lowJdbcType.contains("date")){
            return "Date";
        }
        if(lowJdbcType.contains("double") || lowJdbcType.contains("float")){
            return "Double";
        }
        log.error("jdbc type convert error !! jdbcType:{}", jdbcType);
        return null;
    }

    /**
     * class 字段 sql 字段类型
     * @param classType
     * @return
     */
    public static String fieldTypeToColumnType(Class javaType){
        if(javaType == boolean.class || javaType == Boolean.class){
            return "TINYINT(1)";
        }

        if(javaType == String.class){
            return "varchar(512)";
        }

        if(javaType == short.class || javaType == Short.class){
            return "smallint(6)";
        }

        if(javaType == int.class || javaType == Integer.class){
            return "int";   // default 11
        }

        if(javaType == long.class || javaType == Long.class){
            return "bigint(20)";
        }

        if(javaType == float.class || javaType == Float.class){
            return "float";
        }

        if(javaType == double.class || javaType == Double.class){
            return "double";
        }

        if(javaType == Date.class){
            return "datetime";
        }
        log.info("field type convert error, field type:{}", javaType.getSimpleName());
        return null;
    }

}
