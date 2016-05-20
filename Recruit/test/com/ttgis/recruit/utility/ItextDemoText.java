/**   
* @Title: ItextDemoText.java 
* @Package: com.ttgis.recruit.utility 
* @Description: TODO 
* @author: hua
* @date: 2016年5月18日 下午1:39:45 
* @version V1.0   
*/
package com.ttgis.recruit.utility;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import com.ttgis.recruit.domain.Resume;

/** 
* @ClassName: ItextDemoText 
* @Description: TODO 
* @author: cxh 
* @date: 2016年5月18日 下午1:39:45 
*  
*/
public class ItextDemoText {

    @Test
    public void filedNullCheckTest(){
	Resume resume = new Resume();
	resume.setResumeName("chen");
	Class<?> cls = resume.getClass();
	Method[] methods = cls.getDeclaredMethods();
	Field[] fields = cls.getDeclaredFields();
	for (Field field : fields) {
	    
	}	
    }
    @SuppressWarnings("deprecation")
    @Test
    public void test() throws FileNotFoundException, DocumentException {
	/*对齐方式
	在示例代码0506中，我们也改变了单元格“big cell”的对齐方式：
	cell.HorizontalAlignment = Element.ALIGN_CENTER;
	cell.VerticalAlignment = Element.ALIGN_MIDDLE;
	注：不能总是相信垂直对齐方式。*/

	// 设置段落居中，其中1为居中对齐，2为右对齐，3为左对齐
        //p.setAlignment(1);
	
	// 设置字体，字号，加粗，颜色
        //Font font = new Font(Font.NORMAL, 20, Font.BOLD, new Color(255, 0, 0));
	
	
	// 设置段落缩进
        //p.setIndentationLeft(10);
        // 设置首行缩进
        //p.setFirstLineIndent(20f);
        // 设置段后距和段前距
        //p.setSpacingAfter(10f);
        //p.setSpacingBefore(100f);
	  // cell.setWidth("10px");
	
	
	
	
	Document document = new Document(PageSize.A4);
	RtfWriter2.getInstance(document, new FileOutputStream("E:\\" + "cxh\\" + Math.random() + "cxh.doc"));
	document.open();
	Table table = new Table(3);
	int width[] = {10, 20, 70 };// 设置每列宽度比例
	table.setWidths(width);
	table.setWidth(100);// 占页面宽度比例
	table.setAlignment(Element.ALIGN_LEFT);// 居中
	// table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
	table.setAutoFillEmptyCells(true);// 自动填满
	//table.setBorderWidth(1);// 边框宽度
	table.setPadding(20);
	//table.setSpacing(5);
	Paragraph content = new Paragraph("简历");
	Cell cell_1 = new Cell(content);
	//cell_1.setBorderWidthRight(0);
	//cell_1.setBorderWidthRight(0);;
	cell_1.setRowspan(2);
	table.addCell(cell_1);
	Cell cell_2and3 = new Cell("1.2,1.3");
	cell_2and3.setColspan(2);
	table.addCell(cell_2and3);
	//cell_2and3.setBorderWidthLeft(0);
	//cell_2and3.setBorderWidthRight(0);

	//table.addCell("");
	table.addCell("2.2");
	table.addCell("2.3");

	table.addCell("3.1");
	table.addCell("3.1");
	table.addCell("3.1");

	
	document.add(table);
	document.close();
    }

}
