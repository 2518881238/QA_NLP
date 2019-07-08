package com.cnn.utils;

import java.util.Scanner;


public class RobotTest {
	public static void main(String[] args) {
		SmartRobot SR = new SmartRobot();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			System.out.println("小朱回复：" + SR.getMessage(sc.nextLine())); 
		}
	}
}
