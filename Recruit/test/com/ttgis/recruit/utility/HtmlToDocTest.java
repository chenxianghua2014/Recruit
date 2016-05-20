/**   
* @Title: HtmlToDocTest.java 
* @Package: com.ttgis.recruit.utility 
* @Description: TODO 
* @author: hua
* @date: 2016年5月18日 上午8:40:44 
* @version V1.0   
*/
package com.ttgis.recruit.utility;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.Before;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.lowagie.text.rtf.RtfWriter2;
import com.ttgis.recruit.controller.ResumeController;
import com.ttgis.recruit.domain.Resume;
import com.ttgis.recruit.domain.Resume_ITjn;
import com.ttgis.recruit.domain.Resume_fj;
import com.ttgis.recruit.domain.Resume_gzjl;
import com.ttgis.recruit.domain.Resume_jyjl;
import com.ttgis.recruit.domain.Resume_pxjl;
import com.ttgis.recruit.domain.Resume_qtxx;
import com.ttgis.recruit.domain.Resume_sjjl;
import com.ttgis.recruit.domain.Resume_sx;
import com.ttgis.recruit.domain.Resume_xmjy;
import com.ttgis.recruit.domain.Resume_xnjl;
import com.ttgis.recruit.domain.Resume_xnzw;
import com.ttgis.recruit.domain.Resume_yynl;
import com.ttgis.recruit.domain.Resume_zs;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.ResumeService;
import com.ttgis.recruit.service.Resume_ITjnService;
import com.ttgis.recruit.service.Resume_fjService;
import com.ttgis.recruit.service.Resume_gzjlService;
import com.ttgis.recruit.service.Resume_jyjlService;
import com.ttgis.recruit.service.Resume_pxjlService;
import com.ttgis.recruit.service.Resume_qtxxService;
import com.ttgis.recruit.service.Resume_sjjlService;
import com.ttgis.recruit.service.Resume_sxService;
import com.ttgis.recruit.service.Resume_xmjyService;
import com.ttgis.recruit.service.Resume_xnjlService;
import com.ttgis.recruit.service.Resume_xnzwService;
import com.ttgis.recruit.service.Resume_yynlService;
import com.ttgis.recruit.service.Resume_zsService;
import com.ttgis.recruit.service.XxmcService;
import com.ttgis.recruit.service.ZwglService;

/** 
* @ClassName: HtmlToDocTest 
* @Description: TODO 
* @author: cxh 
* @date: 2016年5月18日 上午8:40:44 
*  
*/
public class HtmlToDocTest {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private Resume_jyjlService resume_jyjlService;
    @Autowired
    private Resume_xnzwService resume_xnzwService;
    @Autowired
    private Resume_xnjlService resume_xnjlService;
    @Autowired
    private Resume_sjjlService resume_sjjlService;
    @Autowired
    private Resume_sxService resume_sxService;
    @Autowired
    private Resume_gzjlService resume_gzjlService;
    @Autowired
    private Resume_xmjyService resume_xmjyService;
    @Autowired
    private Resume_pxjlService resume_pxjlService;
    @Autowired
    private Resume_yynlService resume_yynlService;
    @Autowired
    private Resume_qtxxService resume_qtxxService;
    @Autowired
    private Resume_fjService resume_fjService;
    @Autowired
    private Resume_ITjnService resume_ITjnService;
    @Autowired
    private Resume_zsService resume_zsService;
    @Autowired
    private ZwglService zwglServices;
    @Autowired
    private JtjlkService jtjlkService;
    @Autowired
    private XxmcService xxmcService;

    static Logger log = Logger.getLogger(ResumeController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    HttpSession session;
    String resumeId = "60030F27-A73C-5E2D-4DE5-A5BBF6537D7E";

    /**
     * Test method for {@link com.ttgis.recruit.utility.HtmlToDoc#writeWordFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     * @throws IOException 
     * @throws DocumentException 
     */
    @Before
    public void setUp() {
	// resumeId = "60030F27-A73C-5E2D-4DE5-A5BBF6537D7E"; //我的id
    }

    @Test
    public void testWordStyle() throws DocumentException, IOException {
	Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
	List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(resumeId);
	Resume_jyjl latestTimeJyjl = Jyjl.get(Jyjl.size() - 1);// 查询结果按教育时间升序排列，取最新时间
	List<Resume_xnjl> Xnjl = resume_xnjlService.getlistResumeXnjlByResumeId(resumeId);
	List<Resume_xnzw> Xnzw = resume_xnzwService.getlistResumeXnzwByResumeId(resumeId);
	List<Resume_sjjl> Sjjl = resume_sjjlService.getlistResumeSjjlByResumeId(resumeId);
	List<Resume_sx> Sxjl = resume_sxService.getlistResumeSxByResumeId(resumeId);
	List<Resume_gzjl> Gzjl = resume_gzjlService.getlistResumeGzjlByResumeId(resumeId);
	Resume_gzjl latestTimeGzjl = Gzjl.get(0);// 查询结果按工作时间降序排列，取最新时间
	List<Resume_xmjy> Xmjy = resume_xmjyService.getlistResumeXmjyByResumeId(resumeId);
	List<Resume_pxjl> Pxjl = resume_pxjlService.getlistResumePxjlByResumeId(resumeId);
	List<Resume_yynl> Yynl = resume_yynlService.getlistResumeYynlByResumeId(resumeId);
	Resume_yynl firstYynl = Yynl.get(0);// 查询结果按添加时间升序排列，取第一个即为取第一个添加的外语种类
	List<Resume_qtxx> Qtxx = resume_qtxxService.getlistResumeQtxxByResumeId(resumeId);
	List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(resumeId);
	List<Resume_ITjn> ITjn = resume_ITjnService.getlistResumeITjnByResumeId(resumeId);
	List<Resume_zs> Zs = resume_zsService.getlistResumeZsByResumeId(resumeId);
	String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
	File file1 = new File(path + resume.getResumeId() + ".doc");
	try {

	    // 设置纸张大小
	    Document document = new Document(PageSize.A4);
	    // 建立一个书写器.，与document对象关联
	    RtfWriter2.getInstance(document, new FileOutputStream((file1)));
	    document.open();
	    // 设置中文字体
	    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	    // 标题字体风格
	    Font titleFont = new Font(bfChinese, 14, Font.BOLD);
	    // 正文字体风格
	    Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
	    // 设置标题
	    Paragraph title = new Paragraph("应聘人员登记表");
	    // 设置标题格式对齐方式
	    title.setAlignment(Element.ALIGN_CENTER);
	    title.setFont(titleFont);
	    document.add(title);

	    /*
	     * Paragraph context = new Paragraph(contextString);
	     * context.setAlignment(Element.ALIGN_LEFT);
	     * context.setFont(contextFont);
	     * // 段间距
	     * context.setSpacingBefore(3);
	     * // 设置第一行空的列数
	     * context.setFirstLineIndent(20);
	     * document.add(context);
	     */

	    // 设置Table表格,创建一个8列的表格(table前半部分，简历之前，图片占最后两列，四行宽度共10%)
	    Table table = new Table(8);
	    int width[] = { 10, 20, 10, 20, 10, 20, 5, 5 };// 设置每列宽度比例
	    table.setWidths(width);
	    table.setWidth(100);// 占页面宽度比例
	    table.setAlignment(Element.ALIGN_CENTER);// 居中
	    // table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
	    table.setAutoFillEmptyCells(true);// 自动填满
	    table.setBorderWidth(1);// 边框宽度
	    // 设置表头 空
	    /*
	     * Cell haderCell = new Cell("");
	     * haderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	     * haderCell.setHeader(true);
	     * haderCell.setColspan(8);
	     * table.addCell(haderCell);
	     * table.endHeaders();
	     */

	    /**
	     * 表格第一行设置
	     * */
	    Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.BLACK);

	    Cell cell1_1 = new Cell(new Paragraph("姓名", fontChinese));
	    table.addCell(cell1_1);
	    table.addCell(new Cell(resume.getResumeName()));

	    Cell cell1_3 = new Cell(new Paragraph("性别", fontChinese));
	    table.addCell(cell1_3);
	    table.addCell(new Cell(resume.getResumeSex()));

	    Cell cell1_5 = new Cell(new Paragraph("出生年月", fontChinese));
	    table.addCell(cell1_5);
	    table.addCell(new Cell(resume.getResumeBirthday().replaceAll("-", ".").substring(0, 7)));

	    // 设置图片
	    if (resume.getResumePhotos() != null && resume.getResumePhotos().contains("")) {
		String PhotosString = resume.getResumePhotos();
		String Photospath = request.getSession().getServletContext().getRealPath("/");
		String final1Path = Photospath + PhotosString;
		Image img = Image.getInstance(final1Path);
		if (img != null && img.getWidth() >= 0 && img.getHeight() >= 0) {
		    img.scaleAbsolute(70, 80);
		    Cell photoCell1 = new Cell();
		    photoCell1.add(img);
		    table.addCell(photoCell1);
		    photoCell1.setRowspan(4);
		    photoCell1.setColspan(2);
		}

	    } else {
		Cell photoCell2 = new Cell("Photos");
		table.addCell(photoCell2);
		photoCell2.setRowspan(4);
		photoCell2.setColspan(2);

	    }

	    /**
	     * 表格第二行设置
	     * */
	    Cell cell2_1 = new Cell(new Paragraph("民族", fontChinese));
	    table.addCell(cell2_1);
	    table.addCell(new Cell(resume.getResumeItjtjn()));

	    Cell cell2_3 = new Cell(new Paragraph("出生地", fontChinese));
	    table.addCell(cell2_3);
	    table.addCell(new Cell(resume.getResumeRxqhkszcsProvince() + resume.getResumeRxqhkszcsCity()));

	    Cell cell2_5 = new Cell(new Paragraph("籍贯", fontChinese));
	    table.addCell(cell2_5);
	    table.addCell(new Cell(resume.getResumeHdzs() + resume.getResumeQtzs()));
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    /**
	     * 表格第三行设置，学历学位跨两行
	     * */
	    Cell cell3And4_1 = new Cell(new Paragraph("入党时间", fontChinese));
	    table.addCell(cell3And4_1);
	    cell3And4_1.setRowspan(2);
	    // 入党时间设置，如果不是党员，3And4_2单元格的值设为无
	    Cell cell3And4_2 = null;
	    if (resume.getResumeZzmm().equals("党员")) {
		cell3And4_2 = new Cell(resume.getResumeQtjn());

	    } else {
		cell3And4_2 = new Cell("无");
	    }
	    table.addCell(cell3And4_2);
	    cell3And4_2.setRowspan(2);

	    Cell cell3_3 = new Cell(new Paragraph("学历", fontChinese));
	    table.addCell(cell3_3);
	    table.addCell(new Cell(latestTimeJyjl.getResumeXl()));

	    Cell cell3And4_5 = new Cell(new Paragraph("职称/资格", fontChinese));
	    table.addCell(cell3And4_5);
	    cell3And4_5.setRowspan(2);
	    Cell cell3And4_6 = new Cell(latestTimeGzjl.getResumeGzbm());
	    table.addCell(cell3And4_6);
	    cell3And4_6.setRowspan(2);
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    /**
	     * 表格第四行设置，学历学位跨两行
	     * */
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));
	    Cell cell4_3 = new Cell(new Paragraph("学位", fontChinese));
	    table.addCell(cell4_3);
	    table.addCell(new Cell(latestTimeJyjl.getResumeZyms()));
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    /**
	     * 表格第五行设置，参加工作时间跨两行
	     * */
	    Cell cell5And6_1 = new Cell(new Paragraph("参加工作时间", fontChinese));
	    table.addCell(cell5And6_1);
	    cell5And6_1.setRowspan(2);
	    Cell cell5And6_2 = new Cell(resume.getResumeCjgzsj());
	    table.addCell(cell5And6_2);
	    cell5And6_2.setRowspan(2);

	    Cell cell5_3 = new Cell(new Paragraph("健康状况", fontChinese));
	    table.addCell(cell5_3);
	    Cell cell5_4And5 = new Cell(resume.getResumeItsxjn());
	    table.addCell(cell5_4And5);
	    cell5_4And5.setColspan(2);
	    table.addCell(new Cell(""));

	    Cell cell5_6 = new Cell(new Paragraph("外语种类", fontChinese));
	    table.addCell(cell5_6);
	    Cell cell5_7And8 = new Cell(firstYynl.getResumeYylb());
	    table.addCell(cell5_7And8);
	    cell5_7And8.setColspan(2);
	    table.addCell(new Cell(""));

	    /**
	     * 表格第六行设置，参加工作时间跨两行
	     * */
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));
	    Cell cell6_3 = new Cell(new Paragraph("户口地", fontChinese));
	    table.addCell(cell6_3);
	    Cell cell6_4And5 = new Cell(resume.getResumeMqszcsProvince() + resume.getResumeMqszcsCity());
	    table.addCell(cell6_4And5);
	    cell6_4And5.setColspan(2);
	    table.addCell(new Cell(""));

	    Cell cell6_6 = new Cell(new Paragraph("外语水平", fontChinese));
	    table.addCell(cell6_6);
	    Cell cell6_7And8 = new Cell(firstYynl.getResumeYylb());
	    table.addCell(cell6_7And8);
	    cell6_7And8.setColspan(2);
	    table.addCell(new Cell(""));

	    /**
	     * 表格第七行设置，
	     * */
	    Cell cell7_1 = new Cell(new Paragraph("毕业院校", fontChinese));
	    table.addCell(cell7_1);
	    Cell cell7_2To4 = new Cell(latestTimeJyjl.getResumeXxmc());
	    table.addCell(cell7_2To4);
	    cell7_2To4.setColspan(3);
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    Cell cell7_5 = new Cell(new Paragraph("专业", fontChinese));
	    table.addCell(cell7_5);
	    Cell cell7_6To8 = new Cell(latestTimeJyjl.getResumeZyl() + latestTimeJyjl.getResumeZy());
	    table.addCell(cell7_6To8);
	    cell7_6To8.setColspan(3);
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    /**
	     * 表格第八行设置，参加工作时间跨两行
	     * */
	    Cell cell8_1 = new Cell(new Paragraph("工作单位", fontChinese));
	    table.addCell(cell8_1);
	    Cell cell8_2To4 = new Cell(latestTimeGzjl.getResumeGzgs());
	    table.addCell(cell8_2To4);
	    cell8_2To4.setColspan(3);
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    Cell cell8_5 = new Cell(new Paragraph("职务", fontChinese));
	    table.addCell(cell8_5);
	    Cell cell8_6To8 = new Cell(latestTimeGzjl.getResumeGzcs());
	    table.addCell(cell8_6To8);
	    cell8_6To8.setColspan(3);
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    /**
	     * 表格第九行设置
	     * */
	    Cell cell9_1 = new Cell(new Paragraph("拟应聘岗位1", fontChinese));
	    table.addCell(cell9_1);
	    Cell cell9_2To8 = new Cell(resume.getResumeNypgw1());
	    table.addCell(cell9_2To8);
	    cell9_2To8.setColspan(7);
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));
	    table.addCell(new Cell(""));

	    /**
	     * 表格第十行行设置，如果拟应聘岗位2存在的话
	     * */
	    if (resume.getResumeNypgw1() != null && resume.getResumeNypgw1().equals("")) {
		Cell cell10_1 = new Cell(new Paragraph("拟应聘岗位2", fontChinese));
		table.addCell(cell10_1);
		Cell cell10_2To8 = new Cell(resume.getResumeNypgw2());
		table.addCell(cell10_2To8);
		cell10_2To8.setColspan(7);
		table.addCell(new Cell(""));
		table.addCell(new Cell(""));
		table.addCell(new Cell(""));
		table.addCell(new Cell(""));
		table.addCell(new Cell(""));
		table.addCell(new Cell(""));
	    }
	    Cell partTwoCell = new Cell();

	    Table partTwo = new Table(2);
	    int partTwoWidth[] = { 10, 90 };// 设置每列宽度比例
	    partTwo.setWidths(partTwoWidth);
	    partTwo.setWidth(100);// 占页面宽度比例
	    partTwo.setAlignment(Element.ALIGN_CENTER);// 居中
	    // table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
	    partTwo.setAutoFillEmptyCells(true);// 自动填满
	    partTwo.setBorderWidth(1);// 边框宽度
	    partTwo.addCell("ceshi");
	    partTwo.addCell("ceshi");
	    partTwo.addCell("ceshi");
	    partTwo.addCell("ceshi");
	    partTwoCell.add(partTwo);

	    table.addCell(partTwoCell);
	    partTwoCell.setColspan(8);
	    ;
	    document.add(table);
	    document.close();
	} catch (Exception e) {
	    // logInfo("/SavePdf", resumeId + "," + contextString);
	    e.printStackTrace();
	}

    }

    @Test
    public void testWriteWordFile() throws DocumentException, IOException {
	Document document = new Document(PageSize.A4);
	// 建立一个书写器.，与document对象关联
	RtfWriter2.getInstance(document, new FileOutputStream(("E:\\" + Math.random() + "aaa.doc")));
	document.open();
	// 设置中文字体
	BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	// 标题字体风格
	Font titleFont = new Font(bfChinese, 14, Font.BOLD);
	// 正文字体风格
	Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
	// 设置标题
	Paragraph title = new Paragraph("应聘人员登记表");
	// 设置标题格式对齐方式
	title.setAlignment(Element.ALIGN_CENTER);
	title.setFont(titleFont);
	document.add(title);
	Table table = new Table(8);
	int width[] = { 10, 20, 10, 20, 10, 20, 5, 5 };// 设置每列宽度比例
	table.setWidths(width);
	table.setWidth(100);// 占页面宽度比例
	table.setAlignment(Element.ALIGN_CENTER);// 居中
	// table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
	table.setAutoFillEmptyCells(true);// 自动填满
	table.setBorderWidth(1);// 边框宽度
	// 设置表头 空
	/*
	 * Cell haderCell = new Cell("");
	 * haderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * haderCell.setHeader(true);
	 * haderCell.setColspan(8);
	 * table.addCell(haderCell);
	 * table.endHeaders();
	 */

	/**
	 * 表格第一行设置
	 * */
	Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.BLACK);

	Cell cell1_1 = new Cell(new Paragraph("姓名", fontChinese));
	table.addCell(cell1_1);
	table.addCell(new Cell("ceshi"));

	Cell cell1_3 = new Cell(new Paragraph("性别", fontChinese));
	table.addCell(cell1_3);
	table.addCell(new Cell("ceshi"));

	Cell cell1_5 = new Cell(new Paragraph("出生年月", fontChinese));
	table.addCell(cell1_5);
	table.addCell(new Cell("ceshi"));
	Cell photoCell = new Cell(new Paragraph("ceshiceshiceshiceshiceshi"));
	photoCell.setColspan(2);
	photoCell.setRowspan(4);
	table.addCell(photoCell);
	// 设置图片
	/*
	 * if (resume.getResumePhotos() != null && resume.getResumePhotos().contains("")) {
	 * String PhotosString = resume.getResumePhotos();
	 * String Photospath = request.getSession().getServletContext().getRealPath("/");
	 * String final1Path = Photospath + PhotosString;
	 * Image img = Image.getInstance(final1Path);
	 * if (img != null && img.getWidth() >= 0 && img.getHeight() >= 0) {
	 * img.scaleAbsolute(70, 80);
	 * Cell photoCell = new Cell();
	 * photoCell.add(img);
	 * table.addCell(photoCell);
	 * photoCell.setRowspan(4);
	 * photoCell.setColspan(2);
	 * }
	 * 
	 * } else {
	 * Cell photoCell = new Cell("Photos");
	 * table.addCell(photoCell);
	 * photoCell.setRowspan(4);
	 * photoCell.setColspan(2);
	 * 
	 * }
	 */
	/**
	 * 表格第二行设置
	 * */
	Cell cell2_1 = new Cell(new Paragraph("民族", fontChinese));
	table.addCell(cell2_1);
	table.addCell(new Cell("ceshi"));

	Cell cell2_3 = new Cell(new Paragraph("出生地", fontChinese));
	table.addCell(cell2_3);
	table.addCell(new Cell("ceshi"));

	Cell cell2_5 = new Cell(new Paragraph("籍贯", fontChinese));
	table.addCell(cell2_5);
	table.addCell(new Cell("ceshi"));

	/**
	 * 表格第三行设置，学历学位跨两行
	 * */
	Cell cell3And4_1 = new Cell(new Paragraph("入党时间", fontChinese));
	table.addCell(cell3And4_1);
	cell3And4_1.setRowspan(2);
	// 入党时间设置，如果不是党员，3And4_2单元格的值设为无
	Cell cell3And4_2 = new Cell("ceshi");
	table.addCell(cell3And4_2);
	cell3And4_2.setRowspan(2);

	Cell cell3_3 = new Cell(new Paragraph("学历", fontChinese));
	table.addCell(cell3_3);
	table.addCell(new Cell("ceshi"));

	Cell cell3And4_5 = new Cell(new Paragraph("职称/资格", fontChinese));
	table.addCell(cell3And4_5);
	cell3And4_5.setRowspan(2);
	Cell cell3And4_6 = new Cell("ceshi");
	table.addCell(cell3And4_6);
	cell3And4_6.setRowspan(2);

	/**
	 * 表格第四行设置，学历学位跨两行
	 * */
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	Cell cell4_3 = new Cell(new Paragraph("学位", fontChinese));
	table.addCell(cell4_3);
	table.addCell(new Cell("ceshi"));
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));

	/**
	* 表格第五行设置，参加工作时间跨两行
	* */
	Cell cell5And6_1 = new Cell(new Paragraph("参加工作时间", fontChinese));
	table.addCell(cell5And6_1);
	cell5And6_1.setRowspan(2);
	Cell cell5And6_2 = new Cell("ceshi");
	table.addCell(cell5And6_2);
	cell5And6_2.setRowspan(2);

	Cell cell5_3 = new Cell(new Paragraph("健康状况", fontChinese));
	table.addCell(cell5_3);
	Cell cell5_4And5 = new Cell("ceshi");
	table.addCell(cell5_4And5);
	cell5_4And5.setColspan(2);
	table.addCell(new Cell(""));

	Cell cell5_6 = new Cell(new Paragraph("外语种类", fontChinese));
	table.addCell(cell5_6);
	Cell cell5_7And8 = new Cell("ceshi");
	table.addCell(cell5_7And8);
	cell5_7And8.setColspan(2);
	table.addCell(new Cell(""));

	/**
	* 表格第六行设置，参加工作时间跨两行
	* */
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	Cell cell6_3 = new Cell(new Paragraph("户口地", fontChinese));
	table.addCell(cell6_3);
	Cell cell6_4And5 = new Cell("ceshi");
	table.addCell(cell6_4And5);
	cell6_4And5.setColspan(2);
	table.addCell(new Cell(""));

	Cell cell6_6 = new Cell(new Paragraph("外语水平", fontChinese));
	table.addCell(cell6_6);
	Cell cell6_7And8 = new Cell("ceshi");
	table.addCell(cell6_7And8);
	cell6_7And8.setColspan(2);
	table.addCell(new Cell(""));

	/**
	 * 表格第七行设置，参加工作时间跨两行
	 * */
	Cell cell7_1 = new Cell(new Paragraph("毕业院校", fontChinese));
	table.addCell(cell7_1);
	Cell cell7_2To4 = new Cell("ceshi");
	table.addCell(cell7_2To4);
	cell7_2To4.setColspan(3);
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));

	Cell cell7_5 = new Cell(new Paragraph("专业", fontChinese));
	table.addCell(cell7_5);
	Cell cell7_6To8 = new Cell("ceshi");
	table.addCell(cell7_6To8);
	cell7_6To8.setColspan(3);
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	/**
	 * 表格第八行设置，参加工作时间跨两行
	 * */
	Cell cell8_1 = new Cell(new Paragraph("工作单位", fontChinese));
	table.addCell(cell8_1);
	Cell cell8_2To4 = new Cell("ceshi");
	table.addCell(cell8_2To4);
	cell8_2To4.setColspan(3);
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	Cell cell8_5 = new Cell(new Paragraph("职务", fontChinese));
	table.addCell(cell8_5);
	Cell cell8_6To8 = new Cell("ceshi");
	table.addCell(cell8_6To8);
	cell8_6To8.setColspan(3);
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	/**
	 * 表格第九行设置
	 * */
	Cell cell9_1 = new Cell(new Paragraph("拟应聘岗位1", fontChinese));
	table.addCell(cell9_1);
	Cell cell9_2To8 = new Cell("ceshi");
	table.addCell(cell9_2To8);
	cell9_2To8.setColspan(7);
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));
	table.addCell(new Cell(""));

	Cell partTwoCell = new Cell();

	Table partTwo = new Table(2);
	int partTwoWidth[] = { 10, 90 };// 设置每列宽度比例
	partTwo.setWidths(partTwoWidth);
	partTwo.setWidth(100);// 占页面宽度比例
	partTwo.setAlignment(Element.ALIGN_CENTER);// 居中
	// table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
	partTwo.setAutoFillEmptyCells(true);// 自动填满
	partTwo.setBorderWidth(1);// 边框宽度
	partTwo.addCell("ceshi");
	partTwo.addCell("ceshi");
	partTwo.addCell("ceshi");
	partTwo.addCell("ceshi");
	//partTwoCell.add(partTwo);

	table.addCell(partTwoCell);
	partTwoCell.setColspan(8);
	table.addCell("");
	table.addCell("");
	table.addCell("");
	table.addCell("");
	table.addCell("");
	table.addCell("");
	table.addCell("");
	document.add(table);
	document.close();
    }

}
