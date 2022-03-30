package study.dao;


import org.apache.ibatis.annotations.Mapper;
import study.po.FileSystem;

import java.util.List;

@Mapper
public interface FileSystemDao {
    /**
     * 保存数据
     * @param fileSystem
     */

    void save(FileSystem fileSystem);

    /**
     * 获得元数据
     * @param id
     * @return
     */

    FileSystem getFileSystem(String id);






    /**
     * 查找全部元数据
     * @return
     */

    List<FileSystem> findAllFile();
}
