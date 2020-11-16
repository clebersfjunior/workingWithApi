package Configuracoes;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    private static FormulaEvaluator formulaEvaluator;

    public static void dataTableConfig (String pathXls) throws IOException{
        try {
            FileInputStream excel = new FileInputStream(new File(pathXls));
            workbook = new XSSFWorkbook(excel);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            Assert.fail();
        }
    }

    public String getNameSheets (int iSheet) {
        String sNameSheet = workbook.getSheetName(iSheet);
        return sNameSheet;
    }

    public int countSheets(){
        int iSheet = workbook.getNumberOfSheets();
        return iSheet;
    }

    public int countRows(int iSheet){
        sheet = workbook.getSheetAt(iSheet);
        int iRow = sheet.getLastRowNum();
        return iRow;
    }

    public int countColumns(int iSheet){
        int iColunas = 0;
        String sName = getNameSheets(iSheet);
        if(sName.equalsIgnoreCase("config")){
            return iColunas;
        } else {
            Iterator rowIterator = sheet.rowIterator();
            if (rowIterator.hasNext()){
                Row headerRow = (Row) rowIterator.next();
                iColunas = headerRow.getPhysicalNumberOfCells();
            }
            return iColunas;
        }
    }

    public int rowLimit (int iCol, int iSheet, String sValue){
        int iRow = countRows(iSheet);
        String sName = getNameSheets(iSheet);
        if(sName.equalsIgnoreCase("config")){
            return 0;
        } else {
            formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int col = 0; col < iCol; col++){
                if(sheet.getRow(0).getCell(col).getStringCellValue().equalsIgnoreCase(sValue)){
                    for(int r = 0;r < iRow;r++){
                        Cell cell = sheet.getRow(r).getCell(col);
                        try{
                            switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()){
                                case NUMERIC:
                                    break;
                                case STRING:
                                    break;
                                case BLANK:
                                    iRow = r;
                                    break;
                            }
                        }catch (NullPointerException e){
                            iRow = r;
                        }
                    }
                }
            }
        }
        return iRow;
    }

    public static String [] getDataTable (int iColunas, int iRow){
        HSSFDataFormatter hdf = new HSSFDataFormatter();
        formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        String dados[] = new String [iColunas];
        for (int c = 0; c < iColunas; c++){
            try{
                Cell cell = sheet.getRow(iRow).getCell(c);
                switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()){
                    case STRING:
                        dados[c] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        dados[c] = hdf.formatCellValue(cell);
                        break;
                    case BOOLEAN:
                        dados[c] = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case BLANK:
                        dados[c] = String.valueOf(cell.getStringCellValue());
                        break;
                 }
            } catch (NullPointerException e){
                c = iColunas+1;
            }
        }
        return dados;
    }
}
