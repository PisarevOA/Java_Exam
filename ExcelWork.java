package mephi.java_exam;

import static java.awt.Color.RED;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWork {
    
        XSSFWorkbook workbook1 = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook1.createSheet("Data");
        
        //XSSFColor cellStyle = workbook1.createCellStyle().setFillBackgroundColor(IndexedColors.LIGHT_BLUE));
        int k=2;
        
        public void saveData(ArrayList<Sensor> sen_list) throws FileNotFoundException, IOException{

        Row headerRow1 = sheet1.createRow(0);
        Row headerRow2 = sheet1.createRow(1);
        //Row headerRow3 = sheet1.createRow(2);
        
        
        Row varRow = sheet1.createRow(k);
        k++;
        
        for(int i=0; i<sen_list.size(); i++)
        {
            headerRow1.createCell(i+1).setCellValue(sen_list.get(i).get_Sys_name());
            headerRow2.createCell(i+1).setCellValue(sen_list.get(i).get_Sens_name()); 
            
            varRow.createCell(i+1).setCellValue(sen_list.get(i).get_value());

            //headerRow2.setHeightInPoints(50);
        }

        workbook1.write(new FileOutputStream("Data.xlsx"));

    }
}
