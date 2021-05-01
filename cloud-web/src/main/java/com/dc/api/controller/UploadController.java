package com.dc.api.controller;


import com.dc.api.entity.MultipartFileParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @PACKAGE_NAME: com.dc.api.controller
 * @NAME: UploadController
 * @USER: 25738
 * @DATE: 2021/5/1
 **/
@Slf4j
@Controller
public class UploadController {

    @RequestMapping("/uploadFile")
    public String upload() {

        return "uploadFile";
    }

    /**
     * 文件上传实现分片上传
     *
     * @param multipartFileParam
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFileParam multipartFileParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        Map<String, Object> result = new HashMap<>();
        log.info(multipartFileParam.toString());
        String dir = new StringJoiner(File.separator, File.separator, File.separator)
                .add("upload")
                .toString();
        File baseDir = mkdir(dir + multipartFileParam.getName());
        if (baseDir.exists()){
            result.put("a","success");
            return result;
        }
        File tempDir = mkdir(dir + multipartFileParam.getChunk() + "_" + multipartFileParam.getName());
        if (!tempDir.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(tempDir);
            BufferedOutputStream os = new BufferedOutputStream(fileOutputStream);
            byte[] fileBytes = multipartFileParam.getFile().getBytes();
            os.write(fileBytes);
            fileOutputStream.close();
            os.flush();
        }
        if (multipartFileParam.getChunks() == (multipartFileParam.getChunk() + 1)) {
            merge(multipartFileParam);
        }
        result.put("a","success");
        return result;
    }

    private static void merge(MultipartFileParam multipartFileParam) throws IOException {
        String bind = new StringJoiner(File.separator, File.separator, File.separator)
                .add("upload")
                .toString();
        File bindFile = mkdir(bind + multipartFileParam.getName());

        FileOutputStream fileOutputStream = new FileOutputStream(bindFile);
        BufferedOutputStream os = new BufferedOutputStream(fileOutputStream);
        byte[] bytes;
        for (int i = 0; i < multipartFileParam.getChunks(); i++) {
            File file = mkdir(bind + i + "_" + multipartFileParam.getName());
            while (!file.exists()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            bytes = FileUtils.readFileToByteArray(file);
            os.write(bytes);
            os.flush();
            file.delete();
        }
        fileOutputStream.close();
    }

    public static File touchFile(String path) throws IOException {
        File file = mkdir(path);
        if (!file.exists()) file.createNewFile();
        return file;
    }

    public static File mkdir(String path) throws IOException {
        File file = new File(path);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) parentFile.mkdirs();
        return file;
    }
}
