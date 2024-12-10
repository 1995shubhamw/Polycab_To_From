package package1;

import java.io.IOException;

public class ComparingURLs 
{
    public static void main(String[] args) throws IOException 
    {
        
        String excelFilePath = "C:\\Users\\SHUBHAM\\eclipse-workspace\\PolycaB_FromTo_URLs\\301s 1 (1).xlsx";

        FileInputStream fis=new FileInputStream(excelFilePath)
      //  FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        
        WebDriverManager.chromedriver.setup();
        WebDriver driver = new ChromeDriver();

        // Iterate through the rows in the Excel sheet
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Assuming the first row contains headers
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell urlCell = row.getCell(0); // Column 1
                Cell compareUrlCell = row.getCell(1); // Column 2
                Cell resultCell = row.createCell(2); // Column 3 for results

                if (urlCell != null && compareUrlCell != null) {
                    String urlToOpen = urlCell.getStringCellValue();
                    String expectedUrl = compareUrlCell.getStringCellValue();

                    // Open the URL
                    driver.get(urlToOpen);

                    // Get the current URL after opening
                    String currentUrl = driver.getCurrentUrl();

                    // Compare and write the result in Excel
                    if (currentUrl.equals(expectedUrl)) {
                        resultCell.setCellValue("Match");
                    } else {
                        resultCell.setCellValue("Mismatch: " + currentUrl);
                    }
                }
                else
                {
                    resultCell.setCellValue("Invalid Data");
                }
            }
        }
        