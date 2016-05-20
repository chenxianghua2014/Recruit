package com.ttgis.recruit.utility;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
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

public class HtmlToDoc {
    
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

	 /**
     * 读取html文件到word
     * @param filepath html文件的路径
     * @return
     * @throws Exception
     */
    public boolean writeWordFile(String htmlPath, String saveDocPath, String docName, String rsPath) throws Exception {
    	return ITextPdf.convertHtmlToPdf(htmlPath, saveDocPath+docName, rsPath);
    	
/*          boolean flag = false;
           ByteArrayInputStream bais = null;
           FileOutputStream fos = null;
           try {
                  if (!"".equals(saveDocPath)) {
                         File fileDir = new File(saveDocPath);
                         if (fileDir.exists()) {
                                String content = readFile(htmlPath);
                                byte b[] = content.getBytes();
                                bais = new ByteArrayInputStream(b);
                                POIFSFileSystem poifs = new POIFSFileSystem();
                                DirectoryEntry directory = poifs.getRoot();
                                DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                                fos = new FileOutputStream(saveDocPath + docName);
                                poifs.writeFilesystem(fos);
                                bais.close();
                                fos.close();
                         }
                  }

           } catch (IOException e) {
                  e.printStackTrace();
           } finally {
                  if(fos != null) fos.close();
                  if(bais != null) bais.close();
           }
           return flag;*/
    }

    /**
     * 读取html文件到字符串
     * @param filename
     * @return
     * @throws Exception
     */
    public String readFile(String filename) throws Exception {
           StringBuffer buffer = new StringBuffer("");
           BufferedReader br = null;
           try {
                  br = new BufferedReader(new FileReader(filename));
                  buffer = new StringBuffer();
                  while (br.ready())
                         buffer.append((char) br.read());
           } catch (Exception e) {
                  e.printStackTrace();
           } finally {
                  if(br!=null) br.close();
           }
           return buffer.toString();
    }
    
    /** 
     * @Title: SavePdf 
     * @Description: word导出格式的设置itext
     * @param @param resumeId
     * @param @param contextString
     * @param @param request  参数说明 
     * @return void    返回类型 
     * @throws 
     */
     public String SavePdf(String resumeId) {
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
 	    int width[] = { 10, 15, 13, 15, 13, 18, 9, 7 };// 设置每列宽度比例
 	    table.setWidths(width);
 	    table.setWidth(90);// 占页面宽度比例
 	    table.setAlignment(Element.ALIGN_CENTER);// 居中
 	    // table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
 	    table.setAutoFillEmptyCells(true);// 自动填满
 	    table.setPadding(5);
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
 	    /*
 	     * Cell photoCell = new Cell(new Paragraph("ceshiceshiceshiceshiceshi"));
 	     * photoCell.setColspan(2);
 	     * photoCell.setRowspan(4);
 	     * table.addCell(photoCell);
 	     */
 	    // 设置图片

 	   String PhotosString = resume.getResumePhotos();
	    String Photospath = request.getSession().getServletContext().getRealPath("/");
	    String final1Path = Photospath + PhotosString;
	    Image img = null;
	    try {
		img = Image.getInstance(final1Path);
		if (!(img != null && img.getWidth() >= 0 && img.getHeight() >= 0)) {
		    throw new Exception();
		}
	    } catch (Exception e) {
		System.out.println("图片不存在！");
		img = Image.getInstance(request.getSession().getServletContext().getRealPath("/") + "/images/backupPic.jpg");
	    }

	    img.scaleAbsolute(70, 80);
	    Cell photoCell = new Cell();
	    photoCell.add(img);
	    table.addCell(photoCell);
	    photoCell.setRowspan(4);
	    photoCell.setColspan(2);
	    table.addCell(new Cell(""));

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
 	    Cell cell3And4_6 = new Cell(resume.getResumeZczg());
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
 	    Cell cell5_7And8 = new Cell(firstYynl.getResumeYyfs());
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
 	    //Cell cell7_6To8 = new Cell(latestTimeJyjl.getResumeZyl() + latestTimeJyjl.getResumeZy());
 	    Cell cell7_6To8 = new Cell(latestTimeJyjl.getResumeZyl());
 	    table.addCell(cell7_6To8);
 	    cell7_6To8.setColspan(3);
 	    table.addCell(new Cell(""));
 	    table.addCell(new Cell(""));

 	    /**
 	     * 表格第八行设置，参加工作时间跨两行
 	     * */
 	    Cell cell8_1 = new Cell(new Paragraph("工作单位及部门", fontChinese));
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

 	    /**
 	     * 简历第二部分 教育经历、工作经历
 	     * */
 	    int resumerowSpan = Jyjl.size() + Gzjl.size(); // 简历第二部分教育经历和学习经历所占的行数
 	    Paragraph rowHeaderContent = new Paragraph("简历", fontChinese);
 	    Cell rowHeader = new Cell(rowHeaderContent);
 	    rowHeader.setVerticalAlignment(Element.ALIGN_CENTER);
 	    table.addCell(rowHeader);
 	    rowHeader.setRowspan(resumerowSpan);
 	    /**
 	     * 教育经历 按时间先后排列
 	     */
 	    for (int i = 0; i < Jyjl.size(); i++) {
 		Paragraph timeDetail = new Paragraph();
 		Paragraph detail = new Paragraph();
 		if (i != 0) {
 		    table.addCell("");
 		} 
 		timeDetail.add(Jyjl.get(i).getResumeJdsj().substring(0, 7).replaceAll("-", ".") + "-" + Jyjl.get(i).getResumeJdsj1().substring(0, 7).replaceAll("-", "."));
 		detail.add(" ");
 		detail.add(Jyjl.get(i).getResumeXxmc());
 		//detail.add(Jyjl.get(i).getResumeZyl() + "-" + Jyjl.get(i).getResumeZy());
 		detail.add(Jyjl.get(i).getResumeZyl());
 		detail.add("  ");
 		detail.add(Jyjl.get(i).getResumeXl());
 		Cell timeContent = new Cell(timeDetail);
 		//timeContent.setWidth(arg0);;
 		timeContent.setBorderWidthRight(0); //设置时间行右、下边框不显示
 		timeContent.setBorderWidthBottom(0);
 		
 		table.addCell(timeContent);
 		Cell jyjlContent = new Cell(detail);
 		jyjlContent.setBorderWidthBottom(0); //是指内容行下不显示
 		jyjlContent.setBorderWidthRight(1);  //bug，内容行显示右边框
 		table.addCell(jyjlContent);
 		jyjlContent.setColspan(6);
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 	    }
 	    /**
 	     * 工作经历 按时间先后排列 ，由于查询是按时间降序排列，所以需要将list序列颠倒过来
 	     */
 	    for (int j = Jyjl.size() - 1; j >= 0; j--) {
 		table.addCell("");
 		Paragraph timeDetail = new Paragraph();
 		Paragraph detail = new Paragraph();
 		timeDetail.add(Gzjl.get(j).getResumeGzsj().substring(0, 7).replaceAll("-", ".") + "-" + Gzjl.get(j).getResumeGzsj1().substring(0, 7).replaceAll("-", "."));
 		detail.add(" ");
 		detail.add(Gzjl.get(j).getResumeGzgs());
 		detail.add(Gzjl.get(j).getResumeGzcs());
 		detail.add("  ");
 		detail.add(Gzjl.get(j).getResumeGzms());
 		Cell timeContent = new Cell(timeDetail);
 		timeContent.setBorderWidthRight(0); //设置时间行右、下边框不显示
 		timeContent.setBorderWidthBottom(0);
 		table.addCell(timeContent);
 		
 		Cell GZjlContent = new Cell(detail);
 		GZjlContent.setBorderWidthBottom(0); //设置内容行下边框不显示
 		GZjlContent.setBorderWidthRight(1);  //bug，内容行显示右边框
 		table.addCell(GZjlContent);
 		GZjlContent.setColspan(6);
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 		table.addCell(new Cell(""));
 	    }

 	    /**
 	     * 奖惩情况
 	     */
 	    // 表头
 	    Paragraph jcqkHeaderContent = new Paragraph("奖惩  情况", fontChinese);
 	    Cell jcqkHeader = new Cell(jcqkHeaderContent);
 	    jcqkHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	    //jcqkHeader.setHorizontalAlignment(Element.ALIGN_MIDDLE);
 	    table.addCell(jcqkHeader);
 	    jcqkHeader.setRowspan(4);
 	    // 内容，默认跨四行
 	    Paragraph jcqkContentPar = new Paragraph(resume.getResumeJcqk());
 	    jcqkContentPar.setFirstLineIndent(10);   //首行缩进
 	    Cell jcqkContentCell = new Cell(jcqkContentPar);
 	    table.addCell(jcqkContentCell);
 	    jcqkContentCell.setRowspan(4);
 	    jcqkContentCell.setColspan(7);
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    // 默认空白三行，控制单元格的高度
 	    int blackRowNum = 3;
 	    int blackCell = blackRowNum * 8;
 	    while (blackCell > 0) {
 		table.addCell("");
 		blackCell--;
 	    }
 	    /**
 	     * 培训情况
 	     */
 	    // 表头
 	    Paragraph pxqkHeaderContent = new Paragraph("培训  情况", fontChinese);
 	    Cell pxqkHeader = new Cell(pxqkHeaderContent);
 	    pxqkHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	    table.addCell(pxqkHeader);
 	    pxqkHeader.setRowspan(4);
 	    // 内容，默认跨四行
 	    Paragraph pxqkContentPar = new Paragraph(resume.getResumePxqk());
 	    pxqkContentPar.setFirstLineIndent(10); 
 	    Cell pxqkContentCell = new Cell(pxqkContentPar);
 	    table.addCell(pxqkContentCell);
 	    pxqkContentCell.setRowspan(4);
 	    pxqkContentCell.setColspan(7);
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    // 默认空白三行，控制单元格的高度
 	    blackCell = blackRowNum * 8;
 	    while (blackCell > 0) {
 		table.addCell("");
 		blackCell--;
 	    }
 	    /**
 	     * 熟悉何种专业技能有何种特长
 	     */
 	    // 表头
 	    Paragraph sxjnqkHeaderContent = new Paragraph("熟悉何种专业技能有何种特长", fontChinese);
 	    Cell sxjnqkHeader = new Cell(sxjnqkHeaderContent);
 	    sxjnqkHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	    table.addCell(sxjnqkHeader);
 	    sxjnqkHeader.setRowspan(4);
 	    // 内容，默认跨四行
 	    Paragraph sxjnqkContentPar = new Paragraph(resume.getResumeSxhzjn());
 	    sxjnqkContentPar.setFirstLineIndent(10); 
 	    Cell sxjnqkContentCell = new Cell(sxjnqkContentPar);
 	    table.addCell(sxjnqkContentCell);
 	    sxjnqkContentCell.setRowspan(4);
 	    sxjnqkContentCell.setColspan(7);
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    // 默认空白三行，控制单元格的高度
 	    blackCell = blackRowNum * 8;
 	    while (blackCell > 0) {
 		table.addCell("");
 		blackCell--;
 	    }
 	    /**
 	     * 兴趣爱好
 	     */
 	    // 表头
 	    Paragraph xqahHeaderContent = new Paragraph("兴趣  爱好", fontChinese);
 	    Cell xqahHeader = new Cell(xqahHeaderContent);
 	    xqahHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	    table.addCell(xqahHeader);
 	    xqahHeader.setRowspan(4);
 	    // 内容，默认跨四行
 	    Paragraph xqahContentPar = new Paragraph(resume.getResumeXqah());
 	    xqahContentPar.setFirstLineIndent(10); 
 	    Cell xqahContentCell = new Cell(xqahContentPar);
 	    table.addCell(xqahContentCell);
 	    xqahContentCell.setRowspan(4);
 	    xqahContentCell.setColspan(7);
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    table.addCell("");
 	    // 默认空白三行，控制单元格的高度
 	    blackCell = blackRowNum * 8;
 	    while (blackCell > 0) {
 		table.addCell("");
 		blackCell--;
 	    }

 	    /**
 	     * 主要家庭成员及社会关系
 	     * 
 	     */
 	    // 表头
 	    Paragraph jtcyHeaderContent = new Paragraph("主要家庭成员及社会关系", fontChinese);
 	    Cell jtcyhHeader = new Cell(jtcyHeaderContent);
 	    jtcyhHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	    table.addCell(jtcyhHeader);
 	    jtcyhHeader.setRowspan(Pxjl.size() + 1);

 	    table.addCell(new Cell(new Paragraph("关系", fontChinese)));
 	    table.addCell(new Cell(new Paragraph("姓名", fontChinese)));
 	    table.addCell(new Cell(new Paragraph("出生年月", fontChinese)));
 	    table.addCell(new Cell(new Paragraph("政治面貌", fontChinese)));
 	    Cell jobAnd_ = new Cell(new Paragraph("工作单位及职务", fontChinese));
 	    table.addCell(jobAnd_);
 	    jobAnd_.setColspan(3);
 	    table.addCell("");
 	    table.addCell("");
 	    for (Resume_pxjl pxjlPer : Pxjl) {
 		table.addCell("");
 		table.addCell(new Cell(pxjlPer.getResumePxmc()));
 		table.addCell(new Cell(pxjlPer.getResumePxjg()));
 		table.addCell(new Cell(pxjlPer.getResumePxsj().substring(0, 7).replaceAll("-", ".")));
 		table.addCell(new Cell(pxjlPer.getResumePxsj1()));
 		Cell jobAndContent = new Cell(pxjlPer.getResumePxnr());
 		table.addCell(jobAndContent);
 		jobAndContent.setColspan(3);
 		table.addCell("");
 		table.addCell("");
 	    }

 	    /**
 	     * 最后一行
 	     */
 	    Cell telphoneCell = new Cell(new Paragraph("联系电话(手机)", fontChinese));
 	    telphoneCell.setColspan(2);
 	    table.addCell(telphoneCell);

 	    Cell telphoneCellContent = new Cell(resume.getResumeTelphone());
 	    telphoneCellContent.setColspan(2);
 	    table.addCell(telphoneCellContent);

 	    Cell emailCell = new Cell(new Paragraph("电子邮箱", fontChinese));
 	    table.addCell(emailCell);

 	    Cell emailCellContent = new Cell(resume.getResumeEmail());
 	    emailCellContent.setColspan(3);
 	    table.addCell(emailCellContent);

 	    document.add(table);
 	    document.close();
 	} catch (Exception e) {
 	    //logInfo("/SavePdf", resumeId + "," + contextString);
 	    e.printStackTrace();
 	}
 	return path + file1;
     }

	
	public static void main(String[] args) throws Exception {
		new HtmlToDoc().SavePdf("000CEA22-812D-E83D-BA09-8408E11F9E72"); //("D:\\workspace\\在线招聘社区\\开发库\\2-程序\\源程序\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Recruit\\uppics\\resumes\\1111其他web开发董再兴北京市其他男\\E6043045-0B22-6D22-4A5B-8502B47F71C3 - 副本.html", "d:/", "aaa.doc", "");
	}

}
