package com.ttgis.recruit.utility;

import java.io.BufferedReader;
import java.io.FileReader;

public class HtmlToDoc {

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
	
	public static void main(String[] args) throws Exception {
		new HtmlToDoc().writeWordFile("D:\\workspace\\在线招聘社区\\开发库\\2-程序\\源程序\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Recruit\\uppics\\resumes\\1111其他web开发董再兴北京市其他男\\E6043045-0B22-6D22-4A5B-8502B47F71C3 - 副本.html", "d:/", "aaa.doc", "");
	}

}
