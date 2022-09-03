package jsonproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class DDTestUsingJSON {
	
	WebDriver driver;
	
	@BeforeClass
	void setUp(){
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.MILLISECONDS);
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}
	
	@Test(dataProvider="dp")
	void login(String data) {
		
		String users[]=data.split(",");
		
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.id("Email")).sendKeys(users[0]);
		driver.findElement(By.id("Password")).sendKeys(users[1]);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		String act_title=driver.getTitle();
		String exp_title="nopCommerce demo store. Login";
		Assert.assertEquals(act_title, exp_title);
		
	}
	
	@DataProvider(name="dp")
	public String[] readJson() throws IOException, ParseException{
		
		JSONParser jsonParser=new JSONParser();
		FileReader reader=new FileReader(".\\Jsonfiles\\testdata.json");
		
		Object obj=jsonParser.parse(reader);
		
		JSONObject userloginsJsonobj=(JSONObject)obj;
		
		JSONArray userloginArrays=(JSONArray)userloginsJsonobj.get("userlogins");
		
		String arr[]=new String[userloginArrays.size()];
		
		for(int i=0;i<userloginArrays.size();i++) {
			JSONObject users=(JSONObject)userloginArrays.get(i);
			String user=(String)users.get("username");
			String pwd=(String)users.get("password");
			
			arr[i]=user+","+pwd;
		}
		
		return arr;
	}
}



































