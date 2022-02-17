package study.po;



import lombok.Data;

@Data
public class FileSystem {
    private String id;
    private String fileName;
    private String location;
    private String creatTime;
    private String fileContent;
    private String oldName;
    private long fileSize;
}
