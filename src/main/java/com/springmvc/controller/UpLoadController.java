package com.springmvc.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * @author xujin
 * @package-name com.springmvc.controller
 * @createtime 2019-09-16 16:39
 */
@Controller
@RequestMapping("/file")
public class UpLoadController {
    /**
     * 传统方式
     * @return
     */
    @RequestMapping("/upload1")
    public String upload1(HttpServletRequest request) throws Exception {
        //使用fileupload组件上传
        //上传位置
        String path=request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
        ServletFileUpload  servletFileUpload=new ServletFileUpload(diskFileItemFactory);
        //解析request
        List<FileItem> fileItemList=servletFileUpload.parseRequest(request);
        for (FileItem fileItem : fileItemList) {
            //进行判断，当前fileItem对象是否为上传文件项
            if(fileItem.isFormField()){
                System.out.println(fileItem.getFieldName());
                System.out.println(fileItem.getString("utf-8"));
            }else{
                String fileName=fileItem.getName().substring(fileItem.getName().lastIndexOf("\\")+1);
                //把文件的名称设置成唯一值,uuid
                String uuid=UUID.randomUUID().toString().replace("-","");
                //完成文件上传
                fileName=uuid+"_"+fileName;
                fileItem.write(new File(path,fileName));
                //删除临时文件，当上传大于10KB的文件的时候就会产生临时文件
                fileItem.delete();
            }
        }
        return "success";
    }

    /**
     * springmvc方式上传
     * @param request
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload2")
    public String upload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        String path=request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName=upload.getOriginalFilename();
        //把文件的名称设置成唯一值,uuid
        String uuid=UUID.randomUUID().toString().replace("-","");
        //完成文件上传
        fileName=uuid+"_"+fileName;
        upload.transferTo(new File(path,fileName));
        return "success";
    }

    /**
     * 文件远程传输
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload3")
    public String upload3( MultipartFile upload) throws Exception {

        //定义上传世文件服务器路径
        String path="http://49.234.117.202:8080/fileuploadserver/upload/";
        String fileName=upload.getOriginalFilename();
        //把文件的名称设置成唯一值,uuid
        String uuid=UUID.randomUUID().toString().replace("-","");
        //完成文件上传
        fileName=uuid+"_"+fileName;

        //创建客户端对象
        Client client=Client.create();
        //和图片服务器进行连接
        WebResource webResource=client.resource(path+fileName);
        //上传文件
        webResource.put(upload.getBytes());
        return "success";
    }
}
