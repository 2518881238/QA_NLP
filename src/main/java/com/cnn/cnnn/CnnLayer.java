package com.cnn.cnnn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.lucene.analysis.tokenattributes.FlagsAttribute;
import org.apache.lucene.search.ScoreCachingWrappingScorer;

import com.cnn.entity.Cnn;
import com.cnn.utils.CnnUtils;
import com.word2vec.Word2VEC;


public class CnnLayer {
	public static void main(String[] args) throws IOException {
//		long startTime = System.currentTimeMillis();
//		String str0 = "哪些交通违规行为可以不被处罚";
//		String str1 = "机动车驾驶人的哪些驾驶行为会被扣分";
//		
//		mainLayer(str0, str1);
//		long endTime = System.currentTimeMillis();
//		System.out.println("程序执行时间"+(long)(endTime - startTime));
		
		
		@SuppressWarnings("resource")
		//Scanner sc = new Scanner(System.in);
		//String input = sc.nextLine();
		String input = "在小区里养鸡的是什么心态跟谁投诉";
//		float a  = mainLayer(input, "在小区里养鸡的是什么心态跟谁投诉");
//		System.err.println(a);
		File file = new File("D:/NLP/法律-原始文档.txt");
		String encoding = "utf-8";
		if (file.isFile() && file.exists()) {
			//float a  = mainLayer(input, "在小区里养鸡的是什么心态跟谁投诉");
			HashMap<String , Float> score = new HashMap<String, Float>();
			
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			float sco = 0;
			int flag = 0;
			while ((lineTxt = bufferedReader.readLine()) != null) {		
				sco = mainLayer("我看电影看了八十块钱", "那场电影要看我八十块钱");
				score.put(lineTxt, sco);
				flag++;
			}
			read.close();	
			System.err.println("句子相似度为：" + score);
			float a = score.get("在小区里养鸡的是什么心态跟谁投诉");
			System.err.println(a);
		} else {
			System.out.println("执行异常");
		}
		
	}
	
	/**
	 * @author 朱宏
	 * @description：cnn数据输入层
	 * 2019年3月25日
	 * @param cnn
	 * @throws IOException
	 */
	public static void inputLayer(Cnn cnn) throws IOException {
		Word2VEC vec = new Word2VEC();
		//vec.loadJavaModel("D:/NLP/法律-分词model.txt");
		vec.loadJavaModel("D:/NLP/精准度测试集model2.txt");
		List<String> str0CW = CnnUtils.jiebaList(cnn.getInputString());
		List<String> str1CW = CnnUtils.jiebaList(cnn.getTextString());
		ArrayList<Float> scorerow = null;
		List<ArrayList<Float>> scoreCol = new ArrayList<ArrayList<Float>>();
		for (int i = 0; i < str0CW.size(); i++) {
			scorerow = new ArrayList<Float>();
			for (int j = 0; j < str1CW.size(); j++) {				
				float score = vec.wordDis(str0CW.get(i),str1CW.get(j));
				scorerow.add(score);
			}
			scoreCol.add(scorerow);
		}
		cnn.setCnnDataList(scoreCol);
	}
	
	/**
	 * @author 朱宏
	 * @description：双核卷积层
	 * 2019年3月25日
	 * @param cnn
	 */
	public static void convolutionLayer2 (Cnn cnn) {
		List<ArrayList<Float>> nextCnnList = new ArrayList<ArrayList<Float>>();
		int k = 0;
		for (int i = 0; i < cnn.getCnnDataList().size()-1; i++) {
			ArrayList<Float> nextCnnData = new ArrayList<Float>();
			for (int j = 0; j < cnn.getCnnDataList().get(0).size()-1; j++) {
				float convoNum0 = cnn.getCnnDataList().get(i).get(j) * cnn.getFilterList().get(k).get(k);
				float convoNum1 = cnn.getCnnDataList().get(i).get(j+1) * cnn.getFilterList().get(k).get(k+1);
				float convoNum2 = cnn.getCnnDataList().get(i+1).get(j) * cnn.getFilterList().get(k+1).get(k);
				float convoNum3 = cnn.getCnnDataList().get(i+1).get(j+1) * cnn.getFilterList().get(k+1).get(k+1);
				float convoNumTotal = (convoNum0 + convoNum1 + convoNum2 + convoNum3)/4;
				nextCnnData.add(convoNumTotal);
			}
			nextCnnList.add(nextCnnData);
		}
		cnn.setCnnDataList(nextCnnList);
	}
	
	/**
	 * @author 朱宏
	 * @description：三核卷积层
	 * 2019年3月25日
	 * @param cnn
	 */
	public static void convolutionLayer3 (Cnn cnn) {
		List<ArrayList<Float>> nextCnnList = new ArrayList<ArrayList<Float>>();
		int k = 0;
		for (int i = 0; i < cnn.getCnnDataList().size()-1; i++) {
			ArrayList<Float> nextCnnData = new ArrayList<Float>();
			for (int j = 0; j < cnn.getCnnDataList().get(0).size()-1; j++) {
				float convoNum0 = cnn.getCnnDataList().get(i).get(j) * cnn.getFilterList().get(k).get(k);
				float convoNum1 = cnn.getCnnDataList().get(i).get(j+1) * cnn.getFilterList().get(k).get(k+1);
				float convoNum2 = cnn.getCnnDataList().get(i).get(j+2) * cnn.getFilterList().get(k).get(k+2);
				float convoNum3 = cnn.getCnnDataList().get(i+1).get(j) * cnn.getFilterList().get(k+1).get(k);
				float convoNum4 = cnn.getCnnDataList().get(i+1).get(j+1) * cnn.getFilterList().get(k+1).get(k+1);
				float convoNum5 = cnn.getCnnDataList().get(i+1).get(j+2) * cnn.getFilterList().get(k+1).get(k+2);
				float convoNum6 = cnn.getCnnDataList().get(i+2).get(j) * cnn.getFilterList().get(k+2).get(k);
				float convoNum7 = cnn.getCnnDataList().get(i+2).get(j+1) * cnn.getFilterList().get(k+2).get(k+1);
				float convoNum8 = cnn.getCnnDataList().get(i+2).get(j+2) * cnn.getFilterList().get(k+2).get(k+2);
				float convoNumTotal = (convoNum0 + convoNum1 + convoNum2 + convoNum3 + convoNum4 + convoNum5 + convoNum6 + convoNum7 + convoNum8)/9;
				nextCnnData.add(convoNumTotal);
			}
			nextCnnList.add(nextCnnData);
		}
		cnn.setCnnDataList(nextCnnList);
	}
	
	/**
	 * @author 朱宏
	 * @description：双核池化层
	 * 2019年3月25日
	 * @param cnn
	 */
	public static void poolingLayer2(Cnn cnn) {
		List<ArrayList<Float>> nextCnnList = new ArrayList<ArrayList<Float>>();
		for (int i = 0; i < cnn.getCnnDataList().size()-1; i++) {
			ArrayList<Float> nextCnnData = new ArrayList<Float>();
			for (int j = 0; j < cnn.getCnnDataList().get(0).size()-1; j++) {
				float convoNum0 = cnn.getCnnDataList().get(i).get(j);
				float convoNum1 = cnn.getCnnDataList().get(i).get(j+1);
				float convoNum2 = cnn.getCnnDataList().get(i+1).get(j);
				float convoNum3 = cnn.getCnnDataList().get(i+1).get(j+1);
				float max0 = -1;
				float max1 = -1;
				float convoNumTotal = -1;
				if (convoNum0 >= convoNum1) {
					max0 = convoNum0;
				}else {
					max0 = convoNum1;
				} 
				if(convoNum2 >= convoNum3) {
					max1 = convoNum2;
				} else {
					max1 = convoNum3;
				}
				if (max0 >= max1) {
					convoNumTotal = max0;
				} else {
					convoNumTotal = max1;
				}
				nextCnnData.add(convoNumTotal);
			}
			nextCnnList.add(nextCnnData);
		}
		cnn.setCnnDataList(nextCnnList);
	}
	
	/**
	 * @author 朱宏
	 * @description：三核池化层
	 * 2019年3月25日
	 * @param cnn
	 */
	public static void poolingLayer3 (Cnn cnn) {
		List<ArrayList<Float>> nextCnnList = new ArrayList<ArrayList<Float>>();
		for (int i = 0; i < cnn.getCnnDataList().size()-1; i++) {
			ArrayList<Float> nextCnnData = new ArrayList<Float>();
			for (int j = 0; j < cnn.getCnnDataList().get(0).size()-1; j++) {
				float convoNum0 = cnn.getCnnDataList().get(i).get(j);
				float convoNum1 = cnn.getCnnDataList().get(i).get(j+1);
				float convoNum2 = cnn.getCnnDataList().get(i).get(j+2);
				float convoNum3 = cnn.getCnnDataList().get(i+1).get(j);
				float convoNum4 = cnn.getCnnDataList().get(i+1).get(j+1);
				float convoNum5 = cnn.getCnnDataList().get(i+1).get(j+2);
				float convoNum6 = cnn.getCnnDataList().get(i+2).get(j);
				float convoNum7 = cnn.getCnnDataList().get(i+2).get(j+1);
				float convoNum8 = cnn.getCnnDataList().get(i+2).get(j+2);
				float num0 = compareThreeNum(convoNum0,convoNum1,convoNum2);
				float num1 = compareThreeNum(convoNum3,convoNum4,convoNum5);
				float num2 = compareThreeNum(convoNum6,convoNum7,convoNum8);
				float convoNumTotal = compareThreeNum(num0,num1,num2);
				nextCnnData.add(convoNumTotal);
			}
			nextCnnList.add(nextCnnData);
		}
		cnn.setCnnDataList(nextCnnList);
	}
	
	/**
	 * @author 朱宏
	 * @description：类静态公用方法：取出三个数中的最大值
	 * 2019年3月25日
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static float compareThreeNum(float a, float b, float c) {
		float max = -1;
		if (a >= b) {
			max= a;
		} else {
			max = b;
		} 
		if (c >= max) {
			max = c;
		}
		return max;
	}
	
	/**
	 * @author 朱宏
	 * @description：CNN的分类连接层
	 * 2019年4月8日
	 * @throws IOException
	 */
	public static float mainLayer(String str0, String str1) throws IOException {
		float scor = 0;
		Cnn cnn = new Cnn();
		cnn.setInputString(str0);
		cnn.setTextString(str1);
		inputLayer(cnn);
		RELU.reluFunc(cnn);
		List<ArrayList<Float>> filterInit = new ArrayList<ArrayList<Float>>();
		List<Float> dataList = new ArrayList<Float>();
		dataList.add((float) 1);
		dataList.add((float) 1);
		filterInit.add((ArrayList<Float>) dataList);
		filterInit.add((ArrayList<Float>) dataList);
		cnn.setFilterList(filterInit);
		while (true) {
			float[] cnnNomalNum = CnnNomalization.standardDeviation(cnn);
			if(cnn.getCnnDataList().size() <= 2 || cnn.getCnnDataList().get(0).size() <= 2){
				break;
			}
			if (cnnNomalNum[0] <= 0.05) {
				scor = cnnNomalNum[1];
				break;
			}
			poolingLayer2(cnn);
			poolingLayer2(cnn);
			poolingLayer2(cnn);
			convolutionLayer2(cnn);
			scor = cnnNomalNum[1];
		}
		return scor;
	}
	
}

