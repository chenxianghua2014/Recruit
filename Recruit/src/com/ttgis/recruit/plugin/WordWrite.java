package com.ttgis.recruit.plugin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

public class WordWrite
{
	public static void WriteWord(String filePath, String fileName, String newFileName, String context)
	{
		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("Empty", context.replace("\n", "\r"));

		// 读取word模板
		FileInputStream in = null;
		try
		{
			in = new FileInputStream(new File(filePath + fileName));
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		HWPFDocument hdt = null;
		try
		{
			hdt = new HWPFDocument(in);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		// 读取word文本内容
		Range bodyRange = hdt.getRange();
		// 替换文本内容
		for (Map.Entry<String, String> entry : bodyMap.entrySet())
		{
			bodyRange.replaceText(entry.getKey().trim(), entry.getValue());
		}
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		newFileName += ".doc";
		deleteFile(filePath + newFileName);
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(filePath + newFileName, true);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			hdt.write(ostream);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// 输出字节流
		try
		{
			out.write(ostream.toByteArray());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			ostream.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static boolean deleteFile(String sPath)
	{
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists())
		{
			file.delete();
			flag = true;
		}
		return flag;
	}
}
