package cn.lkq520.web.mapper;

import cn.lkq520.pojo.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Repository
public interface FileMapper extends BaseMapper<File> {

    List<File> getListFilesByClubId(@Param("clubId") int clubId,@Param("limit") int limit);
}
