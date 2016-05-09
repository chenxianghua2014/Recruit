package com.ttgis.recruit.utility;
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
  

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  

import org.w3c.dom.Document;  
import org.xhtmlrenderer.pdf.ITextFontResolver;  
import org.xhtmlrenderer.pdf.ITextRenderer;  

import com.itextpdf.text.pdf.BaseFont;
  
public class HTML2PDF {  
  
/** 
  * 调用方式 htmlToPDF(response.getOutputStream(), htmlstr, "C:/WINDOWS/Fonts/simfang.ttf", "FangSong_GB2312", "GB2312", "http://xxxx/dtd/xhtml1-transitional.dtd"); 
  *  
  * 相关图片必须使用绝对路径才能显示 如果要下载生成的文件，在此方法上方加上： 
  * response.setHeader("Content-disposition",  "attachment;filename=英文文件名.pdf"); 
  *  
  * @param 参数 
  * @param OutputStream out 输出流 
  * @param String htmlcode html代码。 
  *  
  * @param String fontName 字体名称 如：FangSong_GB2312 
  * @param String fontpath windows 字体路径 如:C:/WINDOWS/Fonts/simfang.ttf 
  * @param String encoding 编码 如:GB2312。 
  * @param String htmlDTDURL  如：http://xxxx/xhtml1-transitional.dtd 
  * version 2009.03.05 
  */  
public void htmlToPDF(OutputStream out, String htmlcode, String fontpath,  
  String  
  fontName, String encoding, String htmlDTDURL) throws Exception {  
  DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();  
  ByteArrayOutputStream bos = new ByteArrayOutputStream();  
  Transformer transformer = TransformerFactory.newInstance()  
    .newTransformer();  
  ITextRenderer renderer = new ITextRenderer();  
  ITextFontResolver resolver = renderer.getFontResolver();  
  InputStream intream = null;  
  Document doc = null;  
  Document doc2 = null;  
  try {  
  htmlcode = "";	//this.filterHeader(htmlcode, encoding, fontName, htmlDTDURL);  
    
  intream = new ByteArrayInputStream(htmlcode.getBytes(encoding));  
  doc = (Document) builder.parse(intream);  
  transformer.setOutputProperty("encoding", encoding);  
  new DOMSource(doc);  
  transformer.transform(new DOMSource(doc), new StreamResult(bos));  
  intream = new ByteArrayInputStream(bos.toString().getBytes());  
  doc2 = (Document) builder.parse(intream);  
    
  resolver.addFont(fontpath, BaseFont.IDENTITY_H,  BaseFont.NOT_EMBEDDED);  
    
  renderer.setDocument(doc2, null);  
  renderer.layout();  
    
  renderer.createPDF(out, true);  
    
  out.flush();  
  out.close();  
  } catch (Exception ex) {  
    
  throw new Exception(ex.getMessage());  
  }  
}  
}  