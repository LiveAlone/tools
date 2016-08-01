package io.terminus.tools.codegen.beans;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yaoqijun.
 * Date:2016-04-22
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Data
public class PersonTest implements Serializable{

    private static final long serialVersionUID = 1403107631388588416L;

    private Long id;

    private String name;

    private Integer age;

    private Double salary;

    private Float testFloat;

    private Date dateTest;
}

