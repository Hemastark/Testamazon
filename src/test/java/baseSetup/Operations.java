package baseSetup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Operations {

	public static String filePath;
	public static WebDriver driver;
	int count;

	public String getFilePath() throws IOException {
		try {
			String filePathTemp = new java.io.File("").getAbsolutePath();
			filePath = filePathTemp.replace("\\", "/");
		} catch (Exception e) {
			System.out.println("Unable to find the File path -> " + e);
		}
		return filePath;
	}

	public boolean initialize_browser() throws IOException {
		try {
			getFilePath();
			System.setProperty("webdriver.chrome.driver", filePath + "//ChromeDriver//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} catch (Exception e) {
			System.out.println("Unable to initialize the Browser -> " + e);
			return false;
		}
		return true;
	}

	public boolean getURL(String URL) {
		try {
			driver.get(URL);
		} catch (Exception e) {
			System.out.println("Unable to navigate to URL -> " + URL + " -> " + e);
			return false;
		}
		return true;
	}

	public boolean clickElementByXpath(String xpath) throws Exception {
		try {
			internalWait(xpath);
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			System.out.println("Unable to Click the Element " + xpath + " -> " + e);
			return false;
		}
		return true;
	}

	public boolean sendElementByXpath(String xpath, String value) throws Exception {
		try {
			internalWait(xpath);
			driver.findElement(By.xpath(xpath)).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Unable to enter the value in given field " + xpath + " -> " + e);
			return false;
		}
		return true;
	}

	public void internalWait(String xpath) throws Exception {
		try {
			for (int i = 0; i <= 20; i++) {
				int PathValue = getCount(xpath);
				if (PathValue == 0) {
					Thread.sleep(1000);
					if (i == 20) {
						throw new Exception("Element is not visible");
					}
				}
				if (PathValue == 1) {
					i = 20;
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to identify the " + xpath + " value -> " + e);
		}
	}

	public int getCount(String xpath) throws Exception {
		try {
			count = driver.findElements(By.xpath(xpath)).size();
		} catch (Exception e) {
			System.out.println("Unable to get the Element Count -> " + e);
			throw e;
		}
		return count;
	}
	
	public void switchTabs(int tabIndex) {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println(tabs.get(tabIndex));
			driver.switchTo().window(tabs.get(tabIndex));
		}catch (Exception e) {
			System.out.println("Unable to Switch between tabs -> " + e);
			throw e;
		}
	}
	
	public void screenshot(String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File image = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(image, new File("./Screenshots/"+screenshotName+".png"));
		}catch (Exception e){
			System.out.println("Uable to capture the Screenshot for " + screenshotName + "->" + e.getMessage());
		}
	}
	
	public byte[] screenshotArray() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public CloseableHttpResponse get(String Url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getMethod = new HttpGet(Url);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			getMethod.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
		return httpResponse;
	}

	public CloseableHttpResponse post(String Url, String bodyContent, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost postObj = new HttpPost(Url);
		postObj.setEntity(new StringEntity(bodyContent));
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			postObj.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse httpResponse = httpClient.execute(postObj);
		return httpResponse;
	}
}
