package cn.lkq520.web.service;

import cn.lkq520.pojo.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
public interface FileService extends IService<File> {

    List<File> getListByFileTypeId(int FileTypeId);
}
