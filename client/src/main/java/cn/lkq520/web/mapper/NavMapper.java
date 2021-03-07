package cn.lkq520.web.mapper;

import cn.lkq520.pojo.Nav;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
@Repository
public interface NavMapper extends BaseMapper<Nav> {

    List<Nav> getNavWithItem();

}
