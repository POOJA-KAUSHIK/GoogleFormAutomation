package demo;

import org.openqa.selenium.WebElement;

public class Wrapper {
    public void click(WebElement ele){
        try {
            Thread.sleep(2000);
            ele.click();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public void send(WebElement ele,String text){
        try {
            Thread.sleep(2000);
            ele.sendKeys(text);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
      
    }

}
