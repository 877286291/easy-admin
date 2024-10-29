package top.houyuji.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.houyuji.basic.domain.entity.BasNoticeRecord;

import java.util.List;

@Mapper
public interface BasNoticeRecordMapper extends BaseMapper<BasNoticeRecord> {

    /**
     * 根据用户id查询公告id
     *
     * @param userId 用户id
     * @return 公告id
     */
    @Select("SELECT notice_id FROM bas_notice_record WHERE user_id = #{userId}")
    List<String> findNoticeIdByUserId(String userId);
}
