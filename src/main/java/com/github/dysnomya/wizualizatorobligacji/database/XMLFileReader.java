package com.github.dysnomya.wizualizatorobligacji.database;

import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import com.github.dysnomya.wizualizatorobligacji.model.COI;
import com.github.dysnomya.wizualizatorobligacji.model.TOS;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class XMLFileReader {
    private Workbook workbook;

    public XMLFileReader(Workbook workbook) {
        this.workbook = workbook;
    }

    public void readSheet() {
        readTOS();
        readCOI();
    }

    private void readTOS() {
        Sheet sheet = workbook.getSheet("TOS");

        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            String name = sheet.getRow(row).getCell(0).getStringCellValue();
            double oprocentowanie = sheet.getRow(row).getCell(9).getNumericCellValue();

            BondDAO.addBond(new TOS(name, 0, oprocentowanie));
        }
    }

    private void readCOI() {
        Sheet sheet = workbook.getSheet("COI");

        for (int row = 2; row <= sheet.getLastRowNum(); row++) {
            String name = sheet.getRow(row).getCell(0).getStringCellValue();
            double[] oprocentowanie = new double[4];
            for (int i = 0; i < 4; i++) {
                oprocentowanie[i] = sheet.getRow(row).getCell(i + 9).getNumericCellValue();
            }

            BondDAO.addBond(new COI(name, 0, oprocentowanie));
        }
    }
}
