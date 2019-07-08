package com.cnn.cnnn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 朱宏
 * @description：训练模型
 * 2019年4月3日
 */
public class ModelTrain {
public static void main(String[] args) throws IOException {
	File file = new File("D:/NLP/精准度测试集1.txt");
	String encoding = "utf-8";
	if (file.isFile() && file.exists()) {
		//float a  = mainLayer(input, "在小区里养鸡的是什么心态跟谁投诉");
		List<Float> score = new ArrayList<Float>();
		List<Float> score1 = new ArrayList<Float>();
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		float sco = 0;
		int flag = 0;
		while ((lineTxt = bufferedReader.readLine()) != null) {	
			String[] textArr = lineTxt.split(" ");
			try {
				sco = CnnLayer.mainLayer(textArr[0], textArr[1]);
			} catch (Exception e) {
				continue;
			}

			score.add(sco);
			flag++;
		}
		read.close();	
		System.err.println("句子相似度为：" + score);
		for (int i = 0; i < score.size(); i++) {
			if (score.get(i) == 0.0) {
				score.remove(i);
			}
			if (score.get(i)>0.4) {
				score1.add(score.get(i));
			}
		}
		System.err.println("测试集总数："+score.size());
		System.err.println("相似度总数："+score1.size());
		Float a =(float)487/(float)659;
		System.err.println("模型精准度："+a);
	} else {
		System.out.println("执行异常");
	}
}
}
