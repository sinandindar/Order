package com.weborder;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {
	private final WebDriver driver;

	public Order(WebDriver driver) {
		this.driver = driver; 
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:/Users/sinan/Documents/selenium dependencies/drivers/chromedriver.exe");

		Order order = new Order(new ChromeDriver());
		order.loginAs("Tester", "test");
		order.openOrderPage();
		order.fillForm();
		order.isTextPresent("New order has been successfully added.");
	}
	
	public void loginAs(String username, String password) {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");        
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();                  
	}
	public void openOrderPage() {
		driver.findElement(By.linkText("Order")).click();
	}
	public void fillForm() {
		Random rand = new Random();
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(1+rand.nextInt(99)+"");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("John " + abc.charAt(rand.nextInt(26)) + " Smith");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("123 Any st");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Anytown");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(1+rand.nextInt(99998)+"");
		int card = 3 + rand.nextInt(2);
		String cardId = "ctl00_MainContent_fmwOrder_cardList_" + ((card == 3) ? (2) : (card == 4) ? (0): (1));
		driver.findElement(By.id(cardId)).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(card + "" + 1+rand.nextInt(999999998)+ "" + ((card < 5) ? (1+rand.nextInt(999998)): (1+rand.nextInt(99998))));
		int m = rand.nextInt(1);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(m + "" + ((m == 0) ? (1+rand.nextInt(9)): (rand.nextInt(2))) + "/" + 18+rand.nextInt(81));
		driver.findElement(By.linkText("Process")).click();    	
	}
	public boolean isTextPresent(String text){
		try{
			boolean b = driver.getPageSource().contains(text);
			if(b) System.out.println("Successful!");
			Thread.sleep(3000);
			driver.close();
			return b;
		}
		catch(Exception e){
			return false;
		}
	}
}