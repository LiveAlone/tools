package io.terminus.tools.codegen;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.terminus.tools.codegen.component.InspectFieldFromBeans;
import io.terminus.tools.codegen.component.InspectFieldFromDatasource;
import io.terminus.tools.codegen.model.Field;
import io.terminus.tools.codegen.model.SingleClass;
import io.terminus.tools.codegen.template.Pebbler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-18
 */
@Component
@Slf4j
public class SchemaGenerator implements CommandLineRunner {

    private final Pebbler pebbler;

    // mysql datasource get info
    private final String MYSQL_DATA_SOURCE = "datasource";

    // dir beans get bean info
    private final String BEANS_DATA_SOURCE = "beans";

    private final InspectFieldFromDatasource inspectFieldFromDatasource;

    private final InspectFieldFromBeans inspectFieldFromBeans;

    @Autowired
    public SchemaGenerator(Pebbler pebbler,
                           InspectFieldFromDatasource inspectFieldFromDatasource,
                           InspectFieldFromBeans inspectFieldFromBeans) {
        this.pebbler = pebbler;
        this.inspectFieldFromDatasource = inspectFieldFromDatasource;
        this.inspectFieldFromBeans = inspectFieldFromBeans;
    }

    public void run(String... args) throws Exception {
        if (args.length == 0){
            throw new IllegalArgumentException("no full qualified calss name specified");
        }

        List<String> tables = Lists.newArrayList();

        // 获取对应的数据表的信息
        List<SingleClass> singleClasses = inspectFieldFromDatasource.queryInspectFields(tables);

        for (SingleClass singleClass : singleClasses) {
            fromDatasource(singleClass);
        }

//        List<String> tables = Lists.newArrayList();
//        String type = args[0];
//        Map<String,List<Field>> inspectMaps = null;
//        if(Objects.equals(type, MYSQL_DATA_SOURCE)){
//            inspectMaps = inspectFieldFromDatasource.queryInspectFields(tables);
//            for (Map.Entry<String, List<Field>> stringListEntry : inspectMaps.entrySet()) {
//                fromDateSource(stringListEntry.getKey(), stringListEntry.getValue());
//            }
//            return;
//        }

//        if (Objects.equals(type, BEANS_DATA_SOURCE)){
//            inspectMaps = inspectFieldFromBeans.queryAllClassInBeans();
//            for (Map.Entry<String, List<InspectField>> stringListEntry : inspectMaps.entrySet()) {
//                fromDataBeans(stringListEntry.getKey(), stringListEntry.getValue());
//            }
//            return;
//        }

//        if(isNull(inspectMaps)){
//            log.error("input data source error");
//            return;
//        }
    }

    private void fromDatasource(SingleClass singleClass) throws Exception{
        //build context
        Map<String, Object> context = Maps.newHashMap();
        context.put("singleClass", singleClass);
        context.put("fieldNames",singleClass.queryFieldName());

//        toClass(context, singleClass);
//
//        toMapper(context, singleClass);
//
//        toDao(context, singleClass);

        toReadService(context, singleClass);

        toReadServiceImpl(context, singleClass);
    }


    private void fromDataBeans(String className, List<Field> inspectFields) throws Exception{
        // build context
//        Map<String,Object> context = Maps.newHashMap();
//        context.put("className", className);
//        context.put("mapperName", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,className));
//        context.put("tableName", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,className)+'s');
//        context.put("fields",inspectFields);
//
//        // to schema
//        toSchema(context);
//
//        // to mapper
//        toMapper(context);
    }

    private void toMapper(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("mapper2.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("sql/" + singleClass.getLowClassName() + "Mapper.xml");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toClass(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("classes.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("javaCode/" + singleClass.getClassName() + ".java");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toSchema(Map<String,Object> context) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("schema.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("schemaSql/" + context.get("tableName").toString() + "_schema.sql");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toDao(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("dao.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("dao/" + singleClass.getClassName() + "Dao.java");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toReadService(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("readService.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("service/" + singleClass.getClassName() + "ReadService.java");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toReadServiceImpl(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("readServiceImpl.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("service/" + singleClass.getClassName() + "ReadServiceImpl.java");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }
}
