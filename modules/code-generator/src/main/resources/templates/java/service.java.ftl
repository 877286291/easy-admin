package ${package.Service};

import ${package.Entity}.${table.entityName};
import ${package.Mapper}.${table.mapperName};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
public class ${table.serviceName} extends ServiceImpl<${table.mapperName}, ${table.entityName}> {

}
