package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.Club;
import cn.lkq520.pojo.File;
import cn.lkq520.web.mapper.FileMapper;
import cn.lkq520.web.service.FileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired private FileMapper fileMapper;

    @Override
    public List<File> getListByFileTypeId(int FileTypeId) {
        QueryWrapper ew = new QueryWrapper();
        ew.eq("file_type_id",FileTypeId);
        ew.orderByDesc("create_time");
        ew.last("limit 6");
        return fileMapper.selectList(ew);
    }

    @Override
    public List<File> getListFilesByClubId(int clubId, int limit) {
        return fileMapper.getListFilesByClubId(clubId,limit);
    }
}
