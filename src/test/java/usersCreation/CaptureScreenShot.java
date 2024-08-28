package usersCreation;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class CaptureScreenShot {
	


	public static String GetScreenShort(WebDriver driver, String ScreenShortName) throws IOException {

		

		// Give the Date & time with format
		String date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

		// Taking Screen Short
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Converts the taken screen short into file format
		File source = ts.getScreenshotAs(OutputType.FILE);
		String PathOfScreenShort="C:\\Users\\swathi.boda\\eclipse-workspace\\Test1\\Screen shorts";
		// This is the destination where we need to store the screen short
		String destination = PathOfScreenShort+ "\\" + ScreenShortName +" - "+ date + ".Png";

		// This will create new file by using the destination
		File finaleDestination = new File(destination);

		// then we will copy the screen short to the path
		FileUtils.copyFile(source, finaleDestination);

		return finaleDestination.getAbsolutePath().toString();

	}



}
