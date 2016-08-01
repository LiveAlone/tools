package io.terminus.tools.codegen.util;

import com.google.common.base.CaseFormat;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe: 不同的命名格式转换
 */
public class CaseFormatUtil {

    /**
     * yao_test to yaoTest
     * @param source
     * @return
     */
    public static String lowSubToLowCamel(String source){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, source);
    }

    /**
     * yao_qi_jun to YaoQiJun
     * @param source
     * @return
     */
    public static String lowSubToUpCamel(String source){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,source);
    }

    /**
     * yaoQiJun to yao_qi_jun
     * @param source
     * @return
     */
    public static String lowCamelToLowSub(String source){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, source);
    }

    /**
     * YaoQiJun to yao_qi_jun
     * @param source
     * @return
     */
    public static String upCamelToLowSub(String source){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, source);
    }

    public static String upCamelToLowCamel(String source){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, source);
    }
}
