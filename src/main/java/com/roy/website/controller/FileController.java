package com.roy.website.controller;

import com.sun.net.httpserver.Authenticator;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Author: Roy
 * @Date: 2019/5/13 16:36
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping(value = "/picture")
    public void picture(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, ServletException {

        System.out.println(request.getParts().size());

        // 判断文件是否存在
        if (!file.isEmpty()) {
            String path = "D:/File/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            path += file.getOriginalFilename();
            File localFile = new File(path);
            try {
                file.transferTo(localFile);
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * windows下的文件路径
     */
    private final String File_PATH = "F:/upload/temp";

    @PostMapping(value = "/upload")
    String uploadFileBufferToLocal(MultipartFile file,HttpServletRequest request) {
        MultipartHttpServletRequest muti = (MultipartHttpServletRequest) request;
        System.out.println(muti.getMultiFileMap().size());
        System.out.println(muti.getFile("file"));
        //将文件缓冲到本地
        boolean localFile = createLocalFile(File_PATH, file);
        if(!localFile){
//            log.error("Create local file failed!");
            return "Create local file failed!";
        }
//        log.info("Create local file successfully");

        return "Create local file successfully";
    }

    /**
     * 通过上传的文件名，缓冲到本地，后面才能解压、验证
     * @param filePath 临时缓冲到本地的目录
     * @param file
     */
    public boolean createLocalFile(String filePath,MultipartFile file) {
        File localFile = new File(filePath);
        //先创建目录
        localFile.mkdirs();

        String originalFilename = file.getOriginalFilename();
        String path = filePath+"/"+originalFilename;

//        log.info("createLocalFile path = {}", path);

        localFile = new File(path);
        FileOutputStream fos = null;
        InputStream in = null;
        try {

            if(localFile.exists()){
                //如果文件存在删除文件
                boolean delete = localFile.delete();
                if (delete == false){
//                    log.error("Delete exist file \"{}\" failed!!!",path,new Exception("Delete exist file \""+path+"\" failed!!!"));
                }
            }
            //创建文件
            if(!localFile.exists()){
                //如果文件不存在，则创建新的文件
                localFile.createNewFile();
//                log.info("Create file successfully,the file is {}",path);
            }

            //创建文件成功后，写入内容到文件里
            fos = new FileOutputStream(localFile);
            in = file.getInputStream();
            byte[] bytes = new byte[1024];

            int len = -1;

            while((len = in.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }

            fos.flush();
//            log.info("Reading uploaded file and buffering to local successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(fos != null) {
                    fos.close();
                }
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
//                log.error("InputStream or OutputStream close error : {}", e);
                return false;
            }
        }

        return true;
    }

    private String path = "F:/upload/temp/";

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file, String message) throws Exception {
        System.out.println(message);
        System.out.println(file.getName());//获取表单中文件组件的名字
        System.out.println(file.getOriginalFilename());//获取上传文件的名字
        System.out.println(file.getSize());//文件的上传大小
        //根据路径+时间戳+文件后缀名来创建文件
        File localFile = new File(path, file.getName());
        //如果是传入服务器  file.getInputStream();用输入输出流来读取
        //储存为本地文件
        file.transferTo(localFile);
//        return new FileInfo(localFile.getAbsolutePath());
        return "Success";
    }
    /**
     * 下载文件
     * @param id
     * @param request
     * @param response
     * @throws IOException
     * @throws FileNotFoundException
     */
    //@GetMapping("/{id}")
    public void downLoad(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        //JDK 1.7后 可以在try中自动关闭流文件
        //inputStream 输入 读，OutputStream输出，写
        try (InputStream inputStream = new FileInputStream(new File(path, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {
            //设置响应类型
            response.setContentType("application/x-download");
            //Content-disposition是 MIME 协议的扩展，MIME 协议指示 MIME 用户代理如何显示附加的文件。当 Internet Explorer 接收到头时，它会激活文件下载对话框，它的文件名框自动填充了头中指定的文件名。

            response.addHeader("Content-Disposition", "attachment;filename=text.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
