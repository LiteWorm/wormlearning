package com.liteworm.javaLearn.chapter01.calendarlearn;

import java.util.Scanner;

/**
 * ��������
 * �Ӽ������������գ���������һ��ĵڼ���
 * @author LiteWorm
 *
 */
public class CalendarTest {

	public static void main(String[] args) {
		//1)�Ӽ�������������
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �� �� ��");
		int year = sc.nextInt(); // ��
		int month = sc.nextInt(); //��
		int day = sc.nextInt();	 // ��
		
		// 2) �������ڶ�Ӧ��һ��ĵڼ���
		int sumDays = getSumDays(year, month, day);
		
		// 3) ��ӡ�����ڶ�Ӧ��һ��ĵڼ���
		System.out.println(year + "��" + month + "��" + day + "��,��" + year + "��" + sumDays + "��");
//		Calendar ca = Calendar.getInstance();
//		System.out.println(ca.getActualMaximum(field));

	}


	/**
	 * ���������ն�Ӧ��һ��ĵڼ���
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static int getSumDays(int year, int month, int day) {
		int days = 0; //����������ڶ�Ӧ��һ���������
		for(int i = 1 ; i < month ; i ++) {
			days += getMonthDays(year, i);
		}
		days += day;
		return days;
	}


	/**
	 * ��ȡ���µ�����
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
	 * �ж��Ƿ�Ϊ����
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
