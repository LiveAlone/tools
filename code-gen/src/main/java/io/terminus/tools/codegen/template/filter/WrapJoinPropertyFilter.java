package io.terminus.tools.codegen.template.filter;

import com.mitchellbosecke.pebble.extension.Filter;
import io.terminus.tools.codegen.model.Field;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-15
 */
public class WrapJoinPropertyFilter implements Filter {

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
                builder.append("#{").append(entry).append("}");
            }
            isFirst = false;
        }
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
