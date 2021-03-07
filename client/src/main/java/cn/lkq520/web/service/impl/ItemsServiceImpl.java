package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.Items;
import cn.lkq520.web.mapper.ItemsMapper;
import cn.lkq520.web.service.ItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements ItemsService {

}
