package com.cnn.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 朱宏
 * @description：Cnn实体类
 * 2019年3月25日
 */
public class Cnn {
	
	private static List<ArrayList<Float>> cnnDataList = null;//CNN数据二维矩阵
	private static List<ArrayList<Float>> filterList = null;//cnn数据卷积核矩阵
	private static int cnnCountLayer = 0;//cnn层数
	private static String poolingMethod = null;//cnn池化方法
	private static String inputString = null;//cnn输入串
	private static String textString = null;//cnn、输出串
	
	public List<ArrayList<Float>> getCnnDataList() {
		return cnnDataList;
	}
	
	public void setCnnDataList(List<ArrayList<Float>> cnnDataList) {
		Cnn.cnnDataList = cnnDataList;
	}
	
	public List<ArrayList<Float>> getFilterList() {
		return filterList;
	}
	
	public void setFilterList(List<ArrayList<Float>> filterList) {
		Cnn.filterList = filterList;
	}
	
	public int getCnnCountLayer() {
		return cnnCountLayer;
	}
	
	public void setCnnCountLayer(int cnnCountLayer) {
		Cnn.cnnCountLayer = cnnCountLayer;
	}
	
	public String getPoolingMethod() {
		return poolingMethod;
	}
	
	public  void setPoolingMethod(String poolingMethod) {
		Cnn.poolingMethod = poolingMethod;
	}
	
	public String getInputString() {
		return inputString;
	}
	
	public void setInputString(String inputString) {
		Cnn.inputString = inputString;
	}
	
	public String getTextString() {
		return textString;
	}
	
	public void setTextString(String textString) {
		Cnn.textString = textString;
	}
	
	
}
