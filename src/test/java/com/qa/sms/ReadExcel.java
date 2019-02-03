package com.qa.sms;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcel {

	
	private static XSSFWorkbook book;
	private static List<String[]> listdata;
	private static LocalDateTime now;

	@Test
	public static void send() {

		listdata = new ArrayList<String[]>();
		now = LocalDateTime.now();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		try {
			FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "\\data\\data.xlsx");
			book = new XSSFWorkbook(fin);
		}

		catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}
		XSSFSheet sheet = book.getSheetAt(0);
		int rows = sheet.getLastRowNum();

		for (int i = 1; i <= rows; i++) {
			XSSFRow row1 = sheet.getRow(i);
			DataFormatter dataFormatter = new DataFormatter();
			String cellStringValue = dataFormatter.formatCellValue(row1.getCell(0));

			String[] s1 = cellStringValue.split("/");

			int day1 = Integer.parseInt(s1[0]);
			int month1 = Integer.parseInt(s1[1]);

			if ((day == day1) && (month == month1)) {
				Cell cell1 = row1.getCell(3);
				DataFormatter formatter = new DataFormatter();
				String phonenumber = formatter.formatCellValue(cell1);
				String[] birthdayDetails = new String[2];
				birthdayDetails[0] = row1.getCell(1).getStringCellValue();
				birthdayDetails[1] = phonenumber;
				listdata.add(birthdayDetails);
			} else {
				System.out.println("no birthday today for " + row1.getCell(1).getStringCellValue());
			}

		}

		for (int i = 0; i < listdata.size(); i++) {

			String[] str = listdata.get(i);
			System.out.println("-------------------------------------------");
			System.out.println(str[0] + " " + str[1] + "...Happy birthday");
			SmsUtil test = new SmsUtil();
			test.sendMessage(str[0], str[1]);

		}

	}

}
