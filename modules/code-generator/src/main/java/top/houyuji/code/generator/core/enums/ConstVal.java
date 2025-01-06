package top.houyuji.code.generator.core.enums;

/**
 * 常量
 */
public interface ConstVal {
    String JAVA_TMPDIR = "java.io.tmpdir";
    /*package KEY*/
    String PACKAGE_ENTITY = "Entity";
    String PACKAGE_DTO = "Dto";
    String PACKAGE_VO = "Vo";
    String PACKAGE_QUERY = "Query";
    String PACKAGE_MAPPER = "Mapper";
    String PACKAGE_SERVICE = "Service";
    String PACKAGE_SERVICE_IMPL = "ServiceImpl";
    String PACKAGE_CONTROLLER = "Controller";
    String PACKAGE_PARENT = "Parent";
    String PACKAGE_MODULE_NAME = "ModuleName";


    /*template*/
    String ENTITY_TEMPLATE = "/templates/java/entity.java";
    String DTO_TEMPLATE = "/templates/java/dto.java";
    String VO_TEMPLATE = "/templates/java/vo.java";
    String QUERY_TEMPLATE = "/templates/java/query.java";
    String MAPPER_TEMPLATE = "/templates/java/mapper.java";
    String MAPPER_XML_TEMPLATE = "/templates/java/mapper.xml";
    String SERVICE_TEMPLATE = "/templates/java/service.java";
    String CONTROLLER_TEMPLATE = "/templates/java/controller.java";


    /*file name*/
    String Entity = "Entity";
    String DTO = "DTO";
    String VO = "VO";
    String QUERY = "Query";
    String MAPPER = "Mapper";
    String SERVICE = "Service";
    String CONTROLLER = "Controller";
    String PARENT = "Parent";


    /*super class*/
    String SUPER_ENTITY_CLASS = "top.houyuji.common.mybatis.tenant.core.BaseTenantEntity";
    String SUPER_MAPPER_CLASS = "top.houyuji.common.mybatis.core.mapper.BaseMapper";
    String ID_TYPE = "top.houyuji.data.core.identifier.IdGenerator";
    String SUPER_DTO_CLASS = "top.houyuji.common.mybatis.tenant.core.BaseTenantDTO";
    String SUPER_QUERY_CLASS = "top.houyuji.common.api.BaseQuery";
}
