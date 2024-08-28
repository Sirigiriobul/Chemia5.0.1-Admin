package usersCreation;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import models.Header;
// This Class is responsible for storing all the file's paths & to return
// To read Data from properties file
// To read & write on the excel files
public class Utililities {


	public FileInputStream input;
	public static XSSFWorkbook WB;
	
	// Path Of Excel file

	// Path is an interface, Paths is one of the class on interface path,
	// get("Path of the file on the eclipse") will get the path on eclipse
	// .toAbsolutePath will get the absolute path means full path of the file on the
	// system
	// .toString will convert the all the absolute to string format
	// By using the paths class from path interface we will get the path of the file
	// then we will convert the path to the absolute path & convert the path to
	// string
	// then we return the string for further use

	// PDF file Path
	
	
	public String PDF_FilePath() {

		Path path = Paths.get("Files/PDF_File.pdf");
		return path.toAbsolutePath().toString();

	}

	// Word file Path
	public String Word_FilePath() {

		Path path = Paths.get("Files/Word_File.docx");
		return path.toAbsolutePath().toString();

	}

	// Image file Path
	public String Image_FilePath() {

		Path path = Paths.get("Files/Image_File.jpg");
		return path.toAbsolutePath().toString();

	}

	// Image file Path
	public String Xl_FilePath() {

		Path path = Paths.get("Files/Excel_File.xlsx");
		return path.toAbsolutePath().toString();

	}

	// Test cases XL file Path
	public static String TestCaseXLPath() {

		Path path = Paths.get("Excel_data/TestCases.xlsx");
		return path.toAbsolutePath().toString();

	}
	// OutPut XL file Path
		public static String outPutXLPath() {

			Path path = Paths.get("outputExcel.xlsx");
			return path.toAbsolutePath().toString();

		}
	// Data XL file path
	public static String DataXLpath() {

		Path path = Paths.get("src\\test\\resources\\Add Multiple Users Template 1.xlsx");
		return path.toAbsolutePath().toString();

	}

	// Properties File path
	public static String PathOfPropartiesFile() {

		Path path = Paths.get("src/test/resources/testdata.properties");
		return path.toAbsolutePath().toString();

	}

	// Report path
	public String PathOfReport() {

		Path path = Paths.get("reports");
		return path.toAbsolutePath().toString();

	}
	
	public String CSV_FilePath() {

		Path path = Paths.get("Excel_data/user.csv");
		return path.toAbsolutePath().toString();

	}

	// Screen short path
	public static String PathOfScreenShort() {

		Path path = Paths.get("Screen shorts");
		return path.toAbsolutePath().toString();

	}

	// To get data from properties file
	public String getDataFromProparties(String Key) throws Exception {

		// FileInputStreem is responsible for get the file path
		// Properties is class , we load the file by using the properties class
		// then we get the key value by using getProparty

		FileInputStream input = new FileInputStream(PathOfPropartiesFile());
		Properties pro = new Properties();
		pro.load(input);
		return pro.getProperty(Key);

	}

	// To create the work book
	public String LoadXLfile(int SheetNumber, int RowNumber, int columnNumber) throws Exception {

		input = new FileInputStream(DataXLpath());
		WB = new XSSFWorkbook(input);
		return WB.getSheetAt(SheetNumber).getRow(RowNumber).getCell(columnNumber).getStringCellValue();

	}

	// To get String type data from the cell from Data XL
	public String getDataFromXl(int SheetNumber, int RowNumber, int columnNumber) {
		String data = null;
		Cell cell =WB.getSheetAt(SheetNumber).getRow(RowNumber).getCell(columnNumber);
		if(cell .getCellType()==CellType.STRING) 
		    data = cell.getStringCellValue(); 
		else if(cell.getCellType()==CellType.NUMERIC) 
		    data = String.valueOf(cell.getNumericCellValue());
		//return WB.getSheetAt(SheetNumber).getRow(RowNumber).getCell(columnNumber).getStringCellValue();
		return data;
	}

	// To get numeric type data from the cell from Data XL
	public int getDataFromXl2(int SheetNumber, int RowNumber, int columnNumber) {

		return (int) WB.getSheetAt(SheetNumber).getRow(RowNumber).getCell(columnNumber).getNumericCellValue();
	}

	// To write Data on XL file
	public void writeOnFTSexcelFile(int SheetNumber, int RowNumber, int ColumnNumber, String Data)
			throws Exception {

		// Will get the path of the XL file
		FileInputStream file = new FileInputStream(DataXLpath());

		// Opens the excel work book
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get the sheet form the workbook as per the sheet index
		XSSFSheet sheet = workbook.getSheetAt(SheetNumber);

		// Get the row form the sheet
		Row row = sheet.getRow(RowNumber);
		if (row == null) {
			System.out.println("Row " + RowNumber + " does not exist. Creating new row.");
			row = sheet.createRow(RowNumber);
		}

		// Get the cell from the row & set the data in the cell
		Cell cell = row.getCell(ColumnNumber);
		if (cell == null) {
			System.out.println("Cell " + ColumnNumber + " in row " + RowNumber + " does not exist. Creating new cell.");
			cell = row.createCell(ColumnNumber);
		}
		// Set the cell value
		cell.setCellValue(Data);

		// To save the excel file
		// get the path of the excel file
		FileOutputStream fileout = new FileOutputStream(DataXLpath());

		// save the work book
		workbook.write(fileout);

		// close the file
		fileout.close();

		// Close the work book
		workbook.close();

	}

	public void UploadFile(String File_path) throws Exception {
		// This robot class is for uploading hte file
		Robot rb = new Robot();
		
		rb.delay(2000);
		// The string selection class will select the file path
		StringSelection selection = new StringSelection(File_path);
		// This tollkit is responcible for copying the path to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		
		// This key press events are responcible for presing the control & V, Then Enter
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}

	public int getSheetNumber(String sheetName) throws IOException {
		input = new FileInputStream(DataXLpath());
		WB = new XSSFWorkbook(input);
		 int numberOfSheets = WB.getNumberOfSheets();
         System.out.println("numberOfSheets"+numberOfSheets);
         // Loop through sheets to find the index of the sheet with the specified name
         int sheetIndex=0;
         for (int i = 0; i <= numberOfSheets; i++) {
             XSSFSheet sheet = WB.getSheetAt(i);
             System.out.println("Sheet Name=: "+sheet.getSheetName());
             if (sheet.getSheetName().equalsIgnoreCase(sheetName)) {
                 sheetIndex = i;
                 break;
             }
                     }
         System.out.print(sheetName+" Sheet Number is: "+sheetIndex);
   		return sheetIndex;

	}

	public int getRowCount(int sheetNumber) throws IOException {
		input = new FileInputStream(DataXLpath());
		WB = new XSSFWorkbook(input);
		
		int rowcount=WB.getSheetAt(sheetNumber).getLastRowNum();
        System.out.println("rowcount"+rowcount);

		return rowcount;
	}
	
	public ArrayList<Object> geRrowData(int rowIndex, int sheetNumber) throws IOException{
		input = new FileInputStream(DataXLpath());
		WB = new XSSFWorkbook(input);
		XSSFSheet sheet = WB.getSheetAt(sheetNumber);
		 Row row = sheet.getRow(rowIndex);

         ArrayList<Object> rowData = new ArrayList<>();
         
         for (Cell cell : row) {
//        	 
             switch (cell.getCellType()) {
                 case STRING:
                     rowData.add(cell.getStringCellValue());
                     break;
                 case NUMERIC:
                	 rowData.add(cell.getNumericCellValue());
                     break;
                 case BOOLEAN:
                	 rowData.add(cell.getBooleanCellValue());
                     break;
                 case FORMULA:
                     rowData.add(cell.getCellFormula());
                     break;
                 case BLANK:
                     rowData.add(" ");
                     break;
                 default:
                     rowData.add("");
             
         }
         }
         System.out.println("RowData of row number :"+ rowIndex+" is "+rowData);
     
		return rowData;
		
	}
	
	
	 
	
	public  Map<String, Object> prepareRowData(int rowIndex, int sheetNumber){
		 XSSFSheet sheet = WB.getSheetAt(sheetNumber); // Get the first sheet

         // Read header row
         Row headerRow = sheet.getRow(0);
         if (headerRow == null) {
             throw new IllegalStateException("Header row is missing in the sheet.");
         }

         // Create a map for headers and their respective values
         Map<String, Object> rowDataMap = new HashMap<>();

         // Extract header values
         String[] headers = new String[headerRow.getLastCellNum()];
         for (int i = 0; i < headerRow.getLastCellNum(); i++) {
             Cell headerCell = headerRow.getCell(i);
             
             headers[i] = headerCell.getStringCellValue();
         }

         // Retrieve the specified row
         Row dataRow = sheet.getRow(rowIndex);
         if (dataRow == null) {
             throw new IllegalStateException("Row at index " + rowIndex + " is missing in the sheet.");
         }

         // Map the row data to the headers
         for (int i = 0; i < headers.length; i++) {
             Cell cell = dataRow.getCell(i);
             Object value = getCellValue(cell);
             rowDataMap.put(headers[i], value);
         }

         // Print or use the map as needed
         System.out.println("Row data map: " + rowDataMap);
		return rowDataMap;
	}

	private Object getCellValue(Cell cell) {
		if (cell == null) {
            return null; // Return null if the cell itself is null
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().isEmpty() ? null : cell.getStringCellValue();
            case NUMERIC:
            	
        		String data1 = String.valueOf(cell.getNumericCellValue());
        		//return WB.getSheetAt(SheetNumber).getRow(RowNumber).getCell(columnNumber).getStringCellValue();
        		return data1.subSequence(0, data1.length()-2);
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                // Evaluate the formula if needed
                return cell.getCellFormula(); // or cell.getStringCellValue() if you want the evaluated result
            default:
                return null; // Default case for unknown cell types
        }
    }		
	 
	public Object[][] readExcelData(String sheetName) throws IOException {
		input = new FileInputStream(DataXLpath());
		WB = new XSSFWorkbook(input);
        XSSFSheet sheet = WB.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = ((XSSFSheet) sheet).getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getCellType()) {
                    case STRING:
                        data[i - 1][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                    	String data1 = String.valueOf(cell.getNumericCellValue());
                	
                        data[i - 1][j] = data1.subSequence(0, data1.length()-2);
                        break;
                    case BOOLEAN:
                        data[i - 1][j] = cell.getBooleanCellValue();
                        break;
                    default:
                        data[i - 1][j] = "";
                }
            }
        }
        WB.close();
        return data;
    }



}