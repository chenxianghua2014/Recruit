package com.ttgis.recruit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ttgis.recruit.domain.Xcxx_attach;
import com.ttgis.recruit.service.Xcxx_attachService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

@Controller
public class UploadController
{
	@Resource
	Xcxx_attachService attachService;

	@ResponseBody
	@RequestMapping(value = "/resources/upload/fileUpload", method = RequestMethod.POST)
	public List<Xcxx_attach> upload(MultipartHttpServletRequest request, HttpSession session) throws IOException
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("Filedata");
		InputStream stream = file.getInputStream();
		String fileName = file.getOriginalFilename();
		fileName = new String(fileName.getBytes(), "gbk");
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 防止中文乱码重新进行命名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
		String strId = "56C13140-4EDF-E13D-02DF-26670EB6F517";
		String fileSavePath = request.getSession().getServletContext().getRealPath("attach") + "\\" + strId;
		String fileNameFull = fileSavePath + "\\" + newFileName;
		File saveFiledir = new File(fileSavePath); // 在该实际路径下实例化一个文件
		if (!saveFiledir.exists())
		{
			saveFiledir.mkdirs();
		}
		OutputStream bos = new FileOutputStream(fileNameFull);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
		{
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		// 关闭流
		stream.close();
		Xcxx_attach xcxx_attach = new Xcxx_attach();
		xcxx_attach.setXcxxAttachUrl("attach/" + strId + "/" + newFileName);
		xcxx_attach.setXcxxAttachAddtime(new Date());
		xcxx_attach.setXcxxAttachId(RandomGUIDUtil.generateKey());
		xcxx_attach.setXcxxId("1");
		xcxx_attach.setXcxxAttachDelflag((long) 1);
		xcxx_attach.setXcxxAttachTitle(fileName);
		xcxx_attach.setXcxxAttachContent(fileName);
		attachService.insertSelective(xcxx_attach);
		List<Xcxx_attach> xcxx_attachs = new ArrayList<Xcxx_attach>();
		xcxx_attachs.add(xcxx_attach);
		return xcxx_attachs;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadAttach", method = RequestMethod.POST)
	public List<Xcxx_attach> LoadAttach()
	{
		return attachService.selectByWhere("56C13140-4EDF-E13D-02DF-26670EB6F517");
	}

	@ResponseBody
	@RequestMapping(value = "/DelAttach", method = RequestMethod.POST)
	public int DelAttach(String id)
	{
		attachService.deleteByPrimaryKey(id);
		return 0;
	}

	@Autowired
	CommonsMultipartResolver multipartResolver;

	@ResponseBody
	@RequestMapping(value = "/PicYlUpload", method = RequestMethod.POST)
	public String PicYlUpload(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
		InputStream stream = file.getInputStream();
		String fileName = file.getOriginalFilename();
		fileName = new String(fileName.getBytes(), "gbk");
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 防止中文乱码重新进行命名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
		String strId = "56C13140-4EDF-E13D-02DF-26670EB6F517";
		String fileSavePath = request.getSession().getServletContext().getRealPath("attach") + "\\" + strId;
		String fileNameFull = fileSavePath + "\\" + newFileName;
		File saveFiledir = new File(fileSavePath); // 在该实际路径下实例化一个文件
		if (!saveFiledir.exists())
		{
			saveFiledir.mkdirs();
		}
		OutputStream bos = new FileOutputStream(fileNameFull);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
		{
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		// 关闭流
		stream.close();
		return "attach/" + strId + "/" + newFileName;
	}

	@ResponseBody
	@RequestMapping(value = "/MovUpload", method = RequestMethod.POST)
	public String MovUpload(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
		InputStream stream = file.getInputStream();
		String fileName = file.getOriginalFilename();
		fileName = new String(fileName.getBytes(), "gbk");
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 防止中文乱码重新进行命名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
		String strId = "56C13140-4EDF-E13D-02DF-26670EB6F517";
		String fileSavePath = request.getSession().getServletContext().getRealPath("attach") + "\\" + strId;
		String fileNameFull = fileSavePath + "\\" + newFileName;
		File saveFiledir = new File(fileSavePath); // 在该实际路径下实例化一个文件
		if (!saveFiledir.exists())
		{
			saveFiledir.mkdirs();
		}
		OutputStream bos = new FileOutputStream(fileNameFull);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
		{
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		// 关闭流
		stream.close();
		return "attach/" + strId + "/" + newFileName;
	}
}
