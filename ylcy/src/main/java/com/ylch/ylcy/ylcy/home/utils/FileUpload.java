package com.ylch.ylcy.ylcy.home.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**  
* <p>Title: FileUpload</p>  
* <p>Description: 文件保存类</p>  
* @author AntChenxi  
* @date 2018年6月1日  
*/
public class FileUpload {
	
	/**
	 * 保存单个文件
	 * @param path 保存路径
	 * @param input 输入流
	 * @throws IOException
	 */
	public static void writeToLocal(String path, InputStream input) throws IOException {
		int index;
		byte[] bytes = new byte[1024];
		File f = new File(path);
		if(!f.exists()) {
			f.createNewFile();
		}
		FileOutputStream downloadFile = new FileOutputStream(path);
		while ((index = input.read(bytes)) != -1) {
			downloadFile.write(bytes, 0, index);
			downloadFile.flush();
		}
		downloadFile.close();
		input.close();
	}
}
