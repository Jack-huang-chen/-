package study.service.imp;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.dao.FileSystemDao;
import study.po.FileSystem;
import study.service.FileService;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {
    @Resource
    private FileSystemDao fileSystemDao;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(MultipartFile file) {
        String result = "上传失败，请选择文件";
        if (!file.isEmpty()) {
            try {
                String id = UUID.randomUUID().toString();
                FileSystem fileSystem = new FileSystem();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String date = dateFormat.format(new Date());
                String uploadDir = uploadPath + date;
                String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                fileSystem.setCreatTime(dateFormat.format(new Date()));
                fileSystem.setFileContent(file.getContentType());
                fileSystem.setOldName(file.getOriginalFilename());
                fileSystem.setFileSize(file.getSize());
                fileSystem.setLocation(uploadDir);
                fileSystem.setFileName(fileName);
                fileSystem.setId(id);
                fileSystemDao.save(fileSystem);
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                executeUpload(uploadDir + "/" + fileName, file);
                result = id;
            } catch (IOException e) {
                e.printStackTrace();
                return "文件上传失败";
            }
        }
        return result;
    }

    @Override
    public FileSystem getFileSystem(String id) {
        if(id == null||"".equals(id)){
            return null;
        }
        return fileSystemDao.getFileSystem(id);
    }

    @Override
    public List<FileSystem> findAllFile() {
        return fileSystemDao.findAllFile();
    }

    @Override
    public String download(String id, HttpServletResponse response) {
        try {
            FileSystem fileSystem = fileSystemDao.getFileSystem(id);
            //读取文件，获取文件输入流
            FileInputStream is = new FileInputStream(new File(fileSystem.getLocation(), fileSystem.getFileName()));
            //附件下载
            response.setHeader("content-disposition", "attachment;filename*=utf-8''" + fileSystem.getOldName());
            //获取响应输出流
            ServletOutputStream os = response.getOutputStream();
            IOUtils.copy(is, os);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        } catch (IOException e) {
            e.printStackTrace();
            return "410";
        }
        return "";

    }

    private void executeUpload(String uploadDir, MultipartFile file) throws IOException {
        File serverFile = new File(uploadDir);
        serverFile.createNewFile();
        file.transferTo(serverFile);
    }
}
