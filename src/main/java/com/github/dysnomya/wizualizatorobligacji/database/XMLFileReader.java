package com.github.dysnomya.wizualizatorobligacji.database;

import com.github.dysnomya.wizualizatorobligacji.model.Bond;
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
    }

    private void readTOS() {
        Sheet sheet = workbook.getSheet("TOS");

        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            String name = sheet.getRow(row).getCell(0).getStringCellValue();
            double oprocentowanie = sheet.getRow(row).getCell(9).getNumericCellValue();

            BondDAO.addBond(new TOS(name, 0, oprocentowanie));
            System.out.println(name + " " + oprocentowanie);
        }
    }
}
