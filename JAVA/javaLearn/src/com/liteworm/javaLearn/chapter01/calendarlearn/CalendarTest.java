package com.liteworm.javaLearn.chapter01.calendarlearn;

import java.util.Scanner;


/**
 * 日历测试
 * 从键盘输入年月日，计算是这一年的第几天
 * @author LiteWorm
 *
 */
public class CalendarTest {

	public static void main(String[] args) {
		//1)从键盘输入年月日
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入 年 月 日");
		int year = sc.nextInt(); // 年
		int month = sc.nextInt(); //月
		int day = sc.nextInt();	 // 日
		
		// 2) 计算日期对应这一年的第几天
		int sumDays = getSumDays(year, month, day);
		
		// 3) 打印该日期对应这一年的第几天
		System.out.println(year + "年" + month + "月" + day + "日,是" + year + "第" + sumDays + "天");
		sc.getClass();
//		Calendar ca = Calendar.getInstance();
//		System.out.println(ca.getActualMaximum(field));

	}


	/**
	 * 计算年月日对应这一年的第几天
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static int getSumDays(int year, int month, int day) {
		int days = 0; //保存这个日期对应这一年的总天数
		for(int i = 1 ; i < month ; i ++) {
			days += getMonthDays(year, i);
		}
		days += day;
		return days;
	}


	/**
	 * 获取当月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	private static int getMonthDays(int year, int month) {
		int day = -1;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;			
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;			
			break;
		case 2:
			if(leapYear(year)) {
				day = 29;
			}else {
				day = 28;
			}	
			break;
		default:
			break;
		}
		return day; 
		}


	/**
	 * 判断是否为闰年
	 * @param year
	 * @return
	 */
	private static boolean leapYear(int year) {
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return true;
		}

		return false;
	}

}
