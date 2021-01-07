package utils;


import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A dirty simple program that reads an Excel file.
 * @author www.codejava.net
 *
 */
public class RedTestCasesFromExcel {




    public static String[][] read() throws IOException {
        String [][] mydata= new String[11][4];
        String excelFilePath = "data/testdata.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        int i=0;
        int j=0;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            j=0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case STRING:

                        mydata[i][j]=cell.getStringCellValue();
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case _NONE:
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                    case FORMULA:
                        break;
                    case BLANK:
                        break;
                    case ERROR:
                        break;
                }


                j=j+1;
            }

            i=i+1;

        }

        workbook.close();
        inputStream.close();
        return  mydata;
    }

}