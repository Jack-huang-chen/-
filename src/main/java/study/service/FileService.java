package study.service;

import org.springframework.web.multipart.MultipartFile;
import study.po.FileSystem;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface FileService {

    /**
     * 保存元数据到数据库
     * @param file
     * @return
     */
    String save(MultipartFile file);

    /**
     * 通过id获得元数据
     * @param id
     * @return
     */
    FileSystem getFileSystem(String id);

    /**
     * 查找所有数据文件
     * @return
     */
    List<FileSystem> findAllFile();

    /**
     * 下载文件
     * @param id
     * @param response
     * @return
     */
    String download(String id, HttpServletResponse response);
}
