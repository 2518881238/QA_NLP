package com.cnn.cnnn;

import com.cnn.entity.Cnn;

/**
 * @author 朱宏
 * @description：Cnn正则化类
 * 2019年3月28日
 */
public class CnnNomalization {
	/**
	 * @author 朱宏
	 * @description：标准差正则化
	 * 2019年3月28日
	 * @param cnn
	 */
	public static float[] standardDeviation(Cnn cnn) {
		int listSize = cnn.getCnnDataList().size();
		int listSizeGet0 = cnn.getCnnDataList().get(0).size();
		float totalScore = 0;
		float avgScore = 0;
		float standardDev = 0;
		float sqrtDev = 0;
		int cycleTime = 0;
		for (int i = 0; i < listSize; i++) {
			for (int j = 0; j < listSizeGet0; j++) {
				totalScore = totalScore + cnn.getCnnDataList().get(i).get(j);
				cycleTime  = cycleTime + 1;
			}
		}
		avgScore = totalScore / cycleTime;
		for (int i = 0; i < listSize; i++) {
			for (int j = 0; j < listSizeGet0; j++) {
				sqrtDev = (float) Math.pow(cnn.getCnnDataList().get(i).get(j) - avgScore, 2);
			}
		}
		standardDev = (float) Math.sqrt(sqrtDev);
		float[] arr = new float[2];	
		arr[0] = standardDev;
		arr[1] = avgScore; 
		return arr;
	}
}
