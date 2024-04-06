package demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        // driver.close();
        // driver.quit();

    }

    public String calculate7DaysBeforeDate(){
        LocalDate currentDate = LocalDate.now();
        
        // Calculate 7 days before the current date
        LocalDate sevenDaysBefore = currentDate.minusDays(7);
        System.out.println(sevenDaysBefore.toString());
        String inputDateStr = sevenDaysBefore.toString();
        
        // Parse the input date string to LocalDate
        LocalDate date = LocalDate.parse(inputDateStr);
        
        // Define the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Format the date to the desired output format
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }
    int hour;
    int minute ;
    String ampm ="";
    public void calculateTime(){
        LocalTime currentTime = LocalTime.now();
        
        // Define the desired output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        
        // Format the current time to the desired output format
        String formattedTime = currentTime.format(formatter);
        ///ampm = (hour < 12) ? "AM" : "PM";
        if (hour < 12) {
            ampm += "AM";
            //ampm ="";
        }else{
            ampm += "PM";
         //   ampm ="";
        }
        
        // Convert the hour to 12-hour format
        hour = (hour > 12) ? hour - 12 : hour;
  
        hour = currentTime.getHour();
        minute = currentTime.getMinute();
     
    }
    public void testCase01(){
        Wrapper wp = new Wrapper();

      
        try {
        long epochSeconds = Instant.now().getEpochSecond();
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(1000);
        WebElement signin = driver.findElement(By.xpath("//a[text()='Sign in to Google']"));
        //signin.click();
        wp.click(signin);
        
        String parent=driver.getWindowHandle();
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()){
               String child_window=I1.next();
               if(!parent.equals(child_window)){
                    driver.switchTo().window(child_window);
                    System.out.println(driver.switchTo().window(child_window).getTitle());
               }
        }
        Thread.sleep(3000);
        WebElement email = driver.findElement(By.xpath("//input[@id='identifierId']"));
        
        wp.send(email, "utester515@gmail.com");
        WebElement  nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
        Thread.sleep(1000);
        wp.click(nextButton);
        
        Thread.sleep(3000);
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='Passwd']"));
        Thread.sleep(1000);
        wp.send(passwordField, "userPk2012");
        WebElement passwordNextButton = driver.findElement(By.id("passwordNext"));   
       // WebElement phoneNumber = driver.findElement(By.xpath("//input[@id='phoneNumberId']"));

        wp.click(passwordNextButton);
        Thread.sleep(10000);        
        WebElement enterName = driver.findElement(By.xpath("//input[@aria-labelledby='i1']"));
        Thread.sleep(1000);      
        enterName.clear();        
        wp.send(enterName, "POOJA SHARMA");
        Thread.sleep(1000);
        WebElement why = driver.findElement(By.xpath("//textArea[@aria-describedby='i6 i7']"));
        Thread.sleep(1000);
        why.clear();
        wp.send(why,"To become a Automation Test Engineer."+epochSeconds); 
        Thread.sleep(1000);
        WebElement yoe = driver.findElement(By.id("i13"));
        wp.click(yoe);
        Thread.sleep(1000);
        WebElement skill1 = driver.findElement(By.id("i30"));
        if (!(skill1.isSelected())) {

           wp.click(skill1);
        }
        Thread.sleep(1000);
        WebElement skill2 =  driver.findElement(By.id("i33"));
        if (!(skill2.isSelected())) {
            wp.click(skill2);
        }
      
        Thread.sleep(1000);
        WebElement skill3 = driver.findElement(By.id("i39"));
        if (!(skill3.isSelected())) {
          wp.click(skill3);
        }
        Thread.sleep(1000);
        WebElement title = driver.findElement(By.xpath("//div[@aria-labelledby='i42']"));
        
        wp.click(title);
        Thread.sleep(2000);
        Actions action = new Actions(driver);
      
        WebElement title1 = title.findElement(By.xpath("(//div[@data-value='Ms'])[1]"));
        action.moveToElement(title1).click().perform();
        Thread.sleep(3000);
        wp.click(title1);
        Thread.sleep(1000);
        WebElement date = driver.findElement(By.xpath("//input[@aria-labelledby='i50']")); 
        wp.send(date,calculate7DaysBeforeDate());
    
        Thread.sleep(1000);
        WebElement Hour = driver.findElement(By.xpath("//input[@aria-label = 'Hour']"));
        Hour.clear();

        wp.send(Hour,String.valueOf(hour));
        Thread.sleep(1000);
        WebElement minutes = driver.findElement(By.xpath("//input[@aria-label = 'Minute']"));
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", minutes);
        minutes.clear();
        minutes.sendKeys(""+minute);
        //wp.send(minutes, String.valueOf(minute));
        Thread.sleep(1000); 
        WebElement AMPM = driver.findElement(By.xpath("//div[@aria-label='AM or PM']"));
        wp.click(AMPM);
        Thread.sleep(1000);

        if (ampm.equals("AM") ) {
            Thread.sleep(1000);

            WebElement am = AMPM.findElement(By.xpath("//div[@data-value='AM']"));
            action.moveToElement(am).click().perform();
            Thread.sleep(1000);
            wp.click(am);
        }else{
            Thread.sleep(1000);
            WebElement pm = AMPM.findElement(By.xpath("//div[@data-value='PM']"));
            action.moveToElement(pm).click().perform();
            Thread.sleep(1000);
            wp.click(pm);
        }
        // driver.get("https://www.amazon.in/");
        // driver.navigate().back();
        Thread.sleep(1000);

        WebElement submit = driver.findElement(By.xpath("//div[@jsname ='M2UYVd']"));
        wp.click(submit);
        Thread.sleep(1000);      
        String response = driver.findElement(By.className("vHW8K")).getText();
        System.out.println(response);
        
    }
    catch(Exception e){
        e.printStackTrace();
    }
  }
}
