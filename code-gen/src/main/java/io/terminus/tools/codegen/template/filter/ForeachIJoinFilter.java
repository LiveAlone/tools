package io.terminus.tools.codegen.template.filter;

import com.mitchellbosecke.pebble.extension.Filter;
import io.terminus.tools.codegen.model.Field;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yaoqijun.
 * Date:2016-04-23
 * Email:yaoqj@terminus.io
 * Descirbe: mapper foreach 循环内容包裹方式
 */
public class ForeachIJoinFilter implements Filter {
    @Override
    public Object apply(Object input, Map<String, Object> args) {
        if (input == null) {
            return null;
        }

        List<Field> fields = (List<Field>) input;
        StringBuilder builder = new StringBuilder();
        String glue = ",";
        boolean isFirst = true;

        for(int i=1; i<fields.size(); i++){
            String entry = fields.get(i).getFieldName();
            if(!isFirst){
                builder.append(glue);
            }

            if(Objects.equals("createdAt", entry)
                    || Objects.equals("updatedAt", entry)){
                builder.append("now()");
            }else {
                builder.append("#{i.").append(entry).append("}");
            }
            isFirst = false;
        }
        return builder.toString();
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
