package pojos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class etcy {

    public static void main(String[] args) {


//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.etsy.com/");

//        driver.findElement(By.xpath("//*[@id=\"nav-promo\"]/a/span[2]")).click();
       // driver.findElement(By.partialLinkText("Gifts for Mom")).click();


        List<List<Object>> rowList = new ArrayList<>();
        List<Object> kk1 = new ArrayList<>();
        kk1.add("alim"); kk1.add("adil"); kk1.add("mamat");

        List<Object> kk = new ArrayList<>();
        kk.add("alim2"); kk.add("adil2"); kk.add("mamat2");



        rowList.add(kk1); rowList.add(kk);

        System.out.println(rowList);

    }
}
