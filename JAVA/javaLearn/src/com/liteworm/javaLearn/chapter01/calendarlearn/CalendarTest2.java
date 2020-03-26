package com.liteworm.javaLearn.chapter01.calendarlearn;

import java.util.Calendar;


/**
 * ����ʹ��Calendar ��ʵ��
 * @author LiteWorm
 *
 *
 */
public class CalendarTest2 {

	public static void main(String[] args) {
		Calendar ca =  Calendar.getInstance();
		System.out.println(ca.get(Calendar.MONTH));
		System.out.println(ca.get(Calendar.DATE));
		System.out.println(ca.get(Calendar.DAY_OF_YEAR));
		System.out.println(ca.getLeastMaximum(Calendar.MONTH));

	}

}
