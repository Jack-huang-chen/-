package study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.po.FileSystem;
import study.service.FileService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@RequestMapping("/file")
@Controller
public class FileController {
    @Resource
    private FileService fileService;

    /**
     * 获得接口元数据
     * @param id
     * @return
     */
    @GetMapping("/getFileDetail")
    @ResponseBody
    public FileSystem getFile(String id){
        return fileService.getFileSystem(id);
    }

    /**
     * 展示所有文件数据,跳转到主页面
     * @param model
     * @return
     */
    @GetMapping("showAll")
    public String findAll(Model model){
        List<FileSystem> FileSystem = fileService.findAllFile();
        //存入作用域中
        model.addAttribute("files",FileSystem);
        return "showAll";
    }

    /**
     * 上传文件接口
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        return fileService.save(file);


    }

    /**
     * 下载文件
     * @param id
     * @param response
     * @return
     * @throws
     */
    @GetMapping("download")
    @ResponseBody
    public String download(String id,HttpServletResponse response) {
        return fileService.download(id,response);
    }
}
