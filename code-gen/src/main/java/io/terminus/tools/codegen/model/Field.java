package io.terminus.tools.codegen.model;

import com.google.common.base.Strings;
import io.terminus.tools.codegen.util.FieldTypeConvert;
import io.terminus.tools.codegen.util.StringConvert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field implements Serializable{

    private static final long serialVersionUID = 3169142101958853798L;

    private String fieldName;

    private String fieldType;

    private String columnName;

    private String columnType;

    private String fieldComment;    // 对应的该列的注解信息

    public static Field fromClassType(String fieldName, Class fieldType, String fieldComment){
        return Field.builder()
                .fieldName(fieldName)
                .fieldType(fieldType.getSimpleName())
                .columnName(StringConvert.classFieldToTableColumn(fieldName))
                .columnType(FieldTypeConvert.fieldTypeToColumnType(fieldType))
                .fieldComment(commentConvert(fieldComment))
                .build();
    }

    public static Field fromColumnType(String columnName, String columnType, String fieldComment){
        return Field.builder()
                .fieldName(StringConvert.tableColumnToClassField(columnName))
                .fieldType(FieldTypeConvert.columnTypeToFieldType(columnType))
                .columnName(columnName).columnType(columnType)
                .fieldComment(commentConvert(fieldComment))
                .build();
    }

    private static String commentConvert(String comment){
        if(Strings.isNullOrEmpty(comment)){
            return null;
        }else {
            return comment + "\n";
        }
    }
}
