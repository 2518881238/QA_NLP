package com.cnn.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.huaban.analysis.jieba.JiebaSegmenter;

/**
 * @description cnn应用工具类
 * @author 朱宏
 */
public class CnnUtils {
	 /**
     * 标准分词：朱宏
     * @param str
     * @return
     */
    public static List<Term> CutWordsStandards(String str){
        List<Term> standardsList = HanLP.segment(str);
        return standardsList;
    }
    /**
     * nlp分词：朱宏
     * @param str
     * @return
     */
    public static List<Term> cutWordsNLP (String str) {
        List<Term> nlpList = NLPTokenizer.segment(str);
		return nlpList;
    }
    /**
     * jieba分词：朱宏
     * @param str
     * @return List<String>
     */
    public static List<String> jiebaList(String str) {
    	 JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
         List<String> strings = jiebaSegmenter.sentenceProcess(str);
         List<String> strList = new ArrayList<String>();
         String[] marks = {",","，","。",".","/","、","？","?",":","：","《","》",
        		 "<",">","[","]","【","】","{","}","\\","|","*",";","；","-"};
         //去除常用标点
         for (int i = 0; i < strings.size(); i++) {
             if (Arrays.asList(marks).contains(strings.get(i))) {
                 continue;
             }
             strList.add(strings.get(i));
         }
         return strList;
    }
    /**
     * jieba分词：朱宏
     * @param str
     * @return StringBuffer
     */
    public static String jiebaStr(String str) {
   	 	JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        List<String> strings = jiebaSegmenter.sentenceProcess(str);
        String sb = "";
        String[] marks = {",","，","。",".","/","、","？","?",":","：","《","》",
       		 "<",">","[","]","【","】","{","}","\\","|","*",";","；","-"};
        //去除常用标点
        for (int i = 0; i < strings.size(); i++) {
            if (Arrays.asList(marks).contains(strings.get(i))) {
                continue;
            }
            sb = sb+strings.get(i)+" ";
        }
        return sb;
   }
    public static void main(String[] args) {
    	String a = "计算机在现代社会各个方面中起着必不可少的作用";
    	System.out.println("标准分词List<Term> standardsList = HanLP.segment(str);"+"\n"+jiebaStr(a));
    	System.out.println("NLP分词List<Term> nlpList = NLPTokenizer.segment(str);"+"\n"+jiebaStr(a));
    	System.out.println("jieba分词List<String> strings = jiebaSegmenter.sentenceProcess(str);"+"\n"+jiebaStr(a));
	}
}
