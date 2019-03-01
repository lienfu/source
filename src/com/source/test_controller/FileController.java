package com.source.test_controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.source.test_model.Files;
import com.source.test_services.FileService;
import com.source.tools.JacksonJsonUtil;


@Controller()
@RequestMapping("file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	//后台接收ultipartFile[] file为input中的name ajaxfileupload为ID
	@RequestMapping(value="import", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String file_import(@RequestParam MultipartFile[] file_name) throws Exception  {
		//转成文件上传请求
		MultipartFile files =file_name[0];
		String result =fileService.file_import(files);
		return JacksonJsonUtil.beanToJson(result);
		
	}
	
	
	@RequestMapping(value="upload", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String file_upload(@RequestParam MultipartFile[] filenames,HttpServletRequest request) throws Exception  {
		//转成文件上传请求
		MultipartFile file =filenames[0];
		//输出前台得到的文件名称，含后缀名
		//System.out.println(file.getOriginalFilename());
		//截取前台传来文件的后缀名
		String filetype= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
		
		if(filetype.equalsIgnoreCase("docx")||filetype.equalsIgnoreCase("doc") ||filetype.equalsIgnoreCase("xlsx")||filetype.equalsIgnoreCase("xls")||filetype.equalsIgnoreCase("jpg")) {
			
			String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase()+"_";
			String filename = uuid+file.getOriginalFilename();
			int hCode =filename.hashCode();
			String hex = Integer.toHexString(hCode);
			String mkdir = hex.charAt(0)+"\\"+hex.charAt(1)+"\\";
			
			//物理存储地址
			//String path = request.getServletContext().getRealPath("/files/")+mkdir; 
			String path =  "/file/"+mkdir;
			File filepath = new File(path,filename);
			if(!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
		
			//将上传文件保存到目标文件中
			file.transferTo(new File(path+File.separator+filename));
			//获取数据库图片存储路径
			String root = request.getContextPath();
			String mkdirsql = hex.charAt(0)+"/"+hex.charAt(1)+"/";
			//String sqlpath = root+"/files/"+mkdirsql+filename;
			String sqlpath = "/file/"+mkdirsql +filename;
			Files f = new Files();
			f.setName(filename);
			f.setPath(sqlpath);
			fileService.file_upload(f);
		}
		return JacksonJsonUtil.beanToJson("存入数据库");
	}
	
}
