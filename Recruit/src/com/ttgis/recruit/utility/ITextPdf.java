package com.ttgis.recruit.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.lingala.zip4j.exception.ZipException;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;

public class ITextPdf
{
	public static boolean convertHtmlToPdf(String inputFile, String outputFile, String rsPath) throws Exception
	{

		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		String url = new File(inputFile).toURI().toURL().toString();
		renderer.setDocument(url);

		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		fontResolver.addFont("C:/Windows/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		// 解决图片的相对路径问题
		renderer.getSharedContext().setBaseURL(rsPath);
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return true;
	}
	
	/** 
	* @Title: convertHtmlToWord 
	* @Description: 直接转成word，有待研究 
	* @param @param inputFile
	* @param @param outputFile
	* @param @param rsPath
	* @return boolean    返回类型 
	* @param @throws Exception  参数说明 
	*/
	public static boolean convertHtmlToWord(String inputFile, String outputFile, String rsPath) throws Exception
	{
	    
	    OutputStream os = new FileOutputStream(outputFile);
	    ITextRenderer renderer = new ITextRenderer();
	    String url = new File(inputFile).toURI().toURL().toString();
	    renderer.setDocument(url);
	    
	    // 解决中文支持问题
	    ITextFontResolver fontResolver = renderer.getFontResolver();
	    fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	    fontResolver.addFont("C:/Windows/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	    
	    // 解决图片的相对路径问题
	    renderer.getSharedContext().setBaseURL(rsPath);
	    renderer.layout();
	    renderer.createPDF(os);
	    os.flush();
	    os.close();
	    return true;
	}

	public static void main(String[] argc) throws ZipException
	{

	}
}
