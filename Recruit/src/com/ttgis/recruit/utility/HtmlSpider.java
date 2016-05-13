package com.ttgis.recruit.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttgis.recruit.utility.random.RandomGUIDUtil;

public class HtmlSpider
{
	// 获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
	// 编码
	private static final String ECODING = "UTF-8";

	public static void main(String[] args) throws IOException
	{
		// String filepath = "d:/11/";
		// String url_str =
		// "http://localhost:8080/Recruit/expjl?resumeId=9E09F1BA-7636-B314-7C5D-8687A4E15365&userid=9E09F1BA-7636-B314-7C5D-8687A4E15365";
		// // 获得html文本内容
		// String HTML = getHtml(url_str);
		//
		// // 获取图片标签
		// List<String> imgUrl = getImageUrl(HTML);
		// 获取图片src地址
		// List<String> imgSrc = getImageSrc(imgUrl);

		// saveHtmlTo(url_str, filepath);
	    String urlUri = "http://localhost:8080/Recruit/expjl?resumeId=60030F27-A73C-5E2D-4DE5-A5BBF6537D7E&userid=60030F27-A73C-5E2D-4DE5-A5BBF6537D7E";
	    String path = "D:\\Tomcat 7.0\\webapps\\Recruit\\uppics/resumes/60030F27-A73C-5E2D-4DE5-A5BBF6537D7E/";
	    String htmlPath = HtmlSpider.saveHtmlTo(urlUri, path);
	    System.out.println(htmlPath);
	}

	/***
	 * 获取HTML内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getHTML(String url) throws Exception
	{
		URL uri = new URL(url);
		URLConnection connection = uri.openConnection();
		connection.setDoOutput(true);
		connection.setReadTimeout(10 * 1000);
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

		InputStream in = connection.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		StringBuffer sb = new StringBuffer();
		while ((length = in.read(buf, 0, buf.length)) > 0)
		{
			sb.append(new String(buf, ECODING));
		}
		in.close();
		return sb.toString();
	}

	/***
	 * 获取ImageUrl地址
	 * 
	 * @param HTML
	 * @return
	 */
	public static List<String> getImageUrl(String HTML)
	{
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
		List<String> listImgUrl = new ArrayList<String>();
		while (matcher.find())
		{
			listImgUrl.add(matcher.group());
		}
		return listImgUrl;
	}

	/***
	 * 获取ImageSrc地址
	 * 
	 * @param listImageUrl
	 * @return
	 * @throws IOException
	 */
	public static List<String> getImageSrc(List<String> listImageUrl) throws IOException
	{
		List<String> listImgSrc = new ArrayList<String>();
		for (String image : listImageUrl)
		{
			// image = image.replace("src=\"", "src=\"" +
			// PropertiesUtils.getValue("server") + "/Recruit/");
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find())
			{
				listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
			}
		}
		return listImgSrc;
	}

	/***
	 * 下载图片
	 * 
	 * @param listImgSrc
	 */
	public static void Download(List<String> listImgSrc, String savePath)
	{
		try
		{
			for (String url : listImgSrc)
			{
				String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
				URL uri = new URL(url);
				InputStream in = uri.openStream();
				FileOutputStream fo = new FileOutputStream(new File(savePath + imageName));
				byte[] buf = new byte[1024];
				int length = 0;
				while ((length = in.read(buf, 0, buf.length)) != -1)
				{
					fo.write(buf, 0, length);
				}
				in.close();
				fo.close();
			}
		} catch (Exception e)
		{
			System.out.println("下载失败");
		}
	}

	/**
	 * URL数据抓取
	 * 
	 * @param urlStr
	 *            URL地址
	 * @return
	 */
	public static String getHtml(String urlStr)
	{
		URL url = null;
		String htm_str = "";
		try
		{
			url = new URL(urlStr);
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		int sec_cont = 10000;
		try
		{
			URLConnection url_con = url.openConnection();
			url_con.setDoOutput(true);
			url_con.setReadTimeout(10 * sec_cont);
			url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			InputStream htm_in = url_con.getInputStream();
			htm_str = InputStream2String(htm_in, ECODING);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return htm_str;
	}

	/**
	 * 抓取URL内容并保存到置顶目录
	 * 
	 * @param url_str
	 *            URL地址
	 * @param filepath
	 *            保存本地目录
	 * @return
	 * @throws IOException
	 */
	public static String saveHtmlTo(String url_str, String filepath) throws IOException
	{
		String savePath = "";
		String htm_str = getHtml(url_str).replaceAll("&", "＆");
//		if (htm_str.length() > 7054)
//			System.out.println(htm_str.substring(7050, 7060));
		File f = new File(filepath);
		if (!f.exists() || f.isDirectory())
			f.mkdirs();
		savePath = filepath + RandomGUIDUtil.generateKey() + ".html";
		boolean b = saveHtml(savePath, htm_str);
		if (b == false)
			return "";
		return savePath;
	}

	/**
	 * 保存网页到置顶目录
	 * 
	 * @param filepath
	 *            保存路径
	 * @param str
	 *            网页内容
	 * @return
	 */
	public static boolean saveHtml(String filepath, String str)
	{
		try
		{
			OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "utf-8");
			outs.write(str);
			System.out.print(str);
			outs.close();
		} catch (IOException e)
		{
			System.out.println("保存异常！");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String InputStream2String(InputStream in_st, String charset) throws IOException
	{
		BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
		StringBuffer res = new StringBuffer();
		String line = "";
		while ((line = buff.readLine()) != null)
		{
			res.append(line);
		}
		return res.toString();
	}

}
