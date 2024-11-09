package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String readDataFromXl(String filePath,String sheetname,int rowIndex,int cellIndex) throws IOException{
		System.out.println("started to read single data from"+filePath);
		FileInputStream file = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(file);//Assuming data is in the first sheet
		Sheet sheet = workbook.getSheet(sheetname);
		return sheet.getRow(rowIndex).getCell(cellIndex).toString();
	}
	
	public static List<List<String>> readAllDataFromXl(String filePath,String sheetName){
		List<List<String>> allData = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowit=sheet.rowIterator();
			
			while(rowit.hasNext()) {
				List<String> rowDataList= new ArrayList<>();
				Row row = rowit.next();
				Iterator<Cell> cellit= row.cellIterator();
				while(cellit.hasNext()) {
					Cell cell = cellit.next();
					rowDataList.add(cell.toString());
				}
				allData.add(rowDataList);
				
			}
			   
			workbook.close();
			file.close();
		} catch(IOException e ) {
			e.printStackTrace();
			
		}
		return allData;
	}
	
	public static Object[][] readAllDataFromXlToArray(String filePath, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;
        FileInputStream file = null;

        try {
            file = new FileInputStream(new File(filePath));
            workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();
            data = new Object[rows][cols];

            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    data[i][j] = (cell != null) ? cell.toString() : "";
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught while reading data from xlsx sheet");
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (file != null) file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
	
	public static void writeExcelFile(String filePath,Object[][] data) {
		try {
			Workbook workbook = new  XSSFWorkbook(); 
			Sheet sheet = workbook.createSheet("Sheet1"); //create a sheet
			int rowCount = 0;
			for(Object[] rowData : data) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				for (Object field : rowData) {
					Cell cell = row.createCell(columnCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					} else if (field instanceof Double)	{
						cell.setCellValue((Double) field);
						
					} // Add more conditions as needed for other data types
				}
				
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			workbook.write(fileOutputStream );
			
			workbook.close();
			fileOutputStream.close();
			
			System.out.println("Excel file has been written successfully.");
		} catch (IOException e)	 {
			e.printStackTrace();
		}
	}
}
	
	
	
	 
			
					
		
		
	
	



