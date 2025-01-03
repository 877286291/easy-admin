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
    String PACKAGE_REPOSITORY = "Repository";
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
    String REPOSITORY_TEMPLATE = "/templates/java/repository.java";
    String SERVICE_TEMPLATE = "/templates/java/service.java";
    String SERVICE_IMPL_TEMPLATE = "/templates/java/serviceImpl.java";
    String CONTROLLER_TEMPLATE = "/templates/java/controller.java";


    /*file name*/
    String Entity = "Entity";
    String DTO = "Dto";
    String VO = "Vo";
    String QUERY = "Query";
    String REPOSITORY = "Repository";
    String SERVICE = "Service";
    String SERVICE_IMPL = "ServiceImpl";
    String CONTROLLER = "Controller";
    String PARENT = "Parent";


    /*super class*/
    String SUPER_ENTITY_CLASS = "top.houyuji.domain.BaseEntity";
    String SUPER_REPOSITORY_CLASS = "top.houyuji.repository.BaseJpaRepository";
    String ID_TYPE = "top.houyuji.data.core.identifier.IdGenerator";
    String SUPER_SERVICE_IMPL_CLASS = "top.houyuji.data.core.service.BaseService";
    String SUPER_DTO_CLASS = "top.houyuji.common.api.domain.BaseTenantDto";
    String SUPER_QUERY_CLASS = "top.houyuji.common.api.BaseQuery";
}
