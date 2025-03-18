/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process_Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Lê H
 */
public class FileDAO {
    public static FileDAO instance;
    
    public static FileDAO instance(){
        if(instance == null)
            instance = new FileDAO();
        return instance;
    }
    public FileDAO(){
    }
    
    public boolean file_Write(JTable table, String path, String title){
        FileOutputStream fw = null;
        
        try {
            fw = new FileOutputStream(path);
            
            XSSFWorkbook wb = new XSSFWorkbook();
            
            XSSFSheet sheet = wb.createSheet(title);
            
            XSSFRow row = null;
            Cell cell = null;
            
            row = sheet.createRow(0);
            for(int i = 0; i< table.getColumnCount() -1; i++){
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(table.getColumnName(i));
            }
            
            for(int i = 0; i< table.getRowCount(); i++){
                row = sheet.createRow(i + 1);
                for(int j= 0; j< table.getColumnCount() -1; j++){
                    
                    
                    if(table.getValueAt(i, j).getClass().getName().equals(String.class.getName())){
                        cell = row.createCell(j, CellType.STRING);
                        cell.setCellValue((String)table.getValueAt(i, j));
                        continue;
                    }
                    
                    if(table.getValueAt(i, j).getClass().getName().equals(Date.class.getName())){
                        cell = row.createCell(j);
                        cell.setCellValue(((Date) table.getValueAt(i, j)).toLocalDate());
                        
                        CellStyle cellType = wb.createCellStyle();
                        DataFormat dtfm = wb.createDataFormat();
                        cellType.setDataFormat(dtfm.getFormat("d/m/yy"));
                        cell.setCellStyle(cellType);
                        continue;
                    }
                    
                    if(table.getValueAt(i, j).getClass().getName().equals(Integer.class.getName())){
                        cell = row.createCell(j, CellType.NUMERIC);
                        cell.setCellValue((double)((Integer)table.getValueAt(i, j)) );
                        continue;
                    }
                    cell = row.createCell(j, CellType.NUMERIC);
                    cell.setCellValue((double) table.getValueAt(i, j));
                }
            }
            
            wb.write(fw);
            fw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Không thể mở file: \"" + path + "\"");
            ex.printStackTrace();
            return false;
        }
        return true;
        
    }
    
}
