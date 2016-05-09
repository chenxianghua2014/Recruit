/**
 * 董再兴 FileUtils.java 2013年7月3日
 */
package com.ttgis.recruit.utility.io;

/**
 * @author 董再兴
 * 自定义文件存储对象
 */
public class FileStoreObject {
	
	private String physicalPath;
	private String relativePath;
	private String ext;
	private String dateFormatPath;
	private String filename;
	
	public FileStoreObject(String physicalPath,String relativePath,String ext,String dateFormatPath,String filename) {
		this.physicalPath=physicalPath;
		this.relativePath=relativePath;
		this.ext=ext;
		this.dateFormatPath=dateFormatPath;
		this.filename=filename;
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public String getExt() {
		return ext;
	}

	public String getDateFormatPath() {
		return dateFormatPath;
	}

	public String getFilename() {
		return filename;
	}
	
	public String getFullPath() {
		return this.physicalPath + this.relativePath + this.dateFormatPath + "/" + this.filename + "." + this.ext;
	}
	
	public String getRelativeFullPath() {
		return this.relativePath + this.dateFormatPath + "/" + this.filename + "." + this.ext;
	}
	
	public String getDateFullPath() {
		return this.dateFormatPath + "/" + this.filename + "." + this.ext;
	}
}
