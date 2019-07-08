package com.cnn.cnnn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.cnn.utils.CnnUtils;


public class PreprocessText {
	public static void main(String[] args) throws IOException {
		preProcessText("D:/NLP/精准度测试集1.txt", "D:/NLP/精准度测试集1-分词.txt", "UTF-8");
	}
	/**
	 * @description 文本预处理：朱宏
	 * @throws IOException
	 */
	public static void preProcessText(String fileRead, String fileWrite, String encoding) throws IOException {
		File file = new File(fileRead);
		File file1 = new File(fileWrite);
		if (file.isFile() && file.exists()) {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file1),encoding);
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				bufferedWriter.write(CnnUtils.jiebaStr(lineTxt));
			}
			writer.flush();
			writer.close();
			read.close();
			System.out.println("文件分词成功");
		} else {
			System.out.println("找不到指定的文件");
			return;
		}
	}
}
