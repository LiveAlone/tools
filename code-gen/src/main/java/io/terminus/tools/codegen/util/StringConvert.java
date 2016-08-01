package io.terminus.tools.codegen.util;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe: 定义字符串转换方式
 */
public class StringConvert {

    public static String beanClassNameToTableName(String className){
        return CaseFormatUtil.upCamelToLowSub(className) + "s";
    }

    public static String beanClassNametoLowClassName(String className){
        return CaseFormatUtil.upCamelToLowCamel(className);
    }

    public static String tableNameToClassName(String tableName){
        return CaseFormatUtil.lowSubToUpCamel(tableName.substring(0, tableName.length() - 1));
    }

    public static String tableNameToMapperName(String tableName){
        return CaseFormatUtil.lowSubToLowCamel(tableName.substring(0, tableName.length() - 1));
    }

    public static String tableColumnToClassField(String column){
        return CaseFormatUtil.lowSubToLowCamel(column);
    }

    public static String classFieldToTableColumn(String field){
        return CaseFormatUtil.lowCamelToLowSub(field);
    }
}
