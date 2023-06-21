package mephi.java_exam;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWork {
    
    XSSFWorkbook workbook1 = new XSSFWorkbook();
    XSSFSheet sheet1 = workbook1.createSheet("Data");
        
    XSSFCellStyle ErrStyle = workbook1.createCellStyle();
    XSSFCellStyle DateStyle = workbook1.createCellStyle();
    //XSSFColor xc = new (XSSFColor) color;
    //XSSFColor backgroundColour = new XSSFColor((IndexedColorMap) RED);
        
    XSSFFont font = workbook1.createFont();
        
    int k=2;
        
    public void saveData(ArrayList<Sensor> sen_list, ArrayList<Date> DT) throws FileNotFoundException, IOException{

        Row headerRow1 = sheet1.createRow(0);
        Row headerRow2 = sheet1.createRow(1);
        
        headerRow1.createCell(0).setCellValue("Дата/Время");
        DateStyle.setDataFormat(22);//fmt = 21 for hh:mm:ss
        
        font.setBold(true);
        ErrStyle.setFont(font);
        ErrStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        ErrStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //headerRow2.getCell(2).setCellStyle(cellStyle);

        //cellStyle.setFillBackgroundColor(backgroundColour);
        
        Row varRow = sheet1.createRow(k);
        varRow.createCell(0).setCellValue(DT.get(k-2));
        varRow.getCell(0).setCellStyle(DateStyle);
        k++;
        
        
        for(int i=0; i<sen_list.size(); i++){
            
            headerRow1.createCell(i+1).setCellValue(sen_list.get(i).get_Sys_name());
            headerRow2.createCell(i+1).setCellValue(sen_list.get(i).get_Sens_name()); 
            sheet1.autoSizeColumn(i);
            
            varRow.createCell(i+1).setCellValue(sen_list.get(i).get_value());
            
            if (sen_list.get(i).get_value() > sen_list.get(i).get_lim2() 
                            || sen_list.get(i).get_value() < sen_list.get(i).get_lim1()){
                varRow.getCell(i+1).setCellStyle(ErrStyle);
            }
            //headerRow2.setHeightInPoints(50);
        }

        workbook1.write(new FileOutputStream("Data.xlsx"));
    }
}
