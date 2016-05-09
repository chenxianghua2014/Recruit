package com.ttgis.recruit.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordReader
{
	private FileInputStream input;
	private WordExtractor extractor;
	private String wordText;// word 内容

	public String readWord(String wordDocPath)
	{// wordDocPath,word文档的绝对路径
		try
		{
			input = new FileInputStream(new File(wordDocPath)); // 创建输入流读取Word文件
			extractor = new WordExtractor(input);// 创建WordExtractor
			wordText = extractor.getText();// 对Word文件进行提取
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (input != null)
				{
					input.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return wordText;
	}
}
