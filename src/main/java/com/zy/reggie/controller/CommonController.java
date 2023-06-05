package com.zy.reggie.controller;

import com.zy.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName CommonController
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/4 0:08
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.basePath}")
    private String basePath;

    /**
     * @description: 文件上传
     * @author zhangyu
     * @param: file
     * @return R<String>
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());
        String originalName = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+originalName.substring(originalName.lastIndexOf('.'));

        File dir=new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath+"\\"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(filename);
    }

    /**
     * @description: 文件上传
     * @author zhangyu
     * @param: file
     * @return R<String>
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
//            response.addHeader("Access-Control-Allow-Origin","*");

            FileInputStream fileInputStream=new FileInputStream(new File(basePath+"\\"+name));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes=new byte[1024];

            if (!response.isCommitted()) {
                while ((len=fileInputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,len);
                    outputStream.flush();
                }
            }

            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
