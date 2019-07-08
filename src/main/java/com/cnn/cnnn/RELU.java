package com.cnn.cnnn;

import com.cnn.entity.Cnn;

/**
 * @author 朱宏
 * @description：RELU激活函数，去除不必要的特征
 * 2019年4月3日
 */
public class RELU {
	public static void  reluFunc(Cnn cnn) {
		int cols = cnn.getCnnDataList().size();
		int rows = cnn.getCnnDataList().get(0).size();
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (cnn.getCnnDataList().get(i).get(j) < 0) {
					cnn.getCnnDataList().get(i).set(j, (float) 0);
				}
			}
		}
	}
}
