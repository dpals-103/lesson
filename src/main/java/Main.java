import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resource/chromedriver.exe");

		// WebDriver 경로 설정
		System.setProperty("webdriver.chrome.driver", path.toString());

		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		// options.setHeadless(true);

		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);

		// 빈 탭 생성
		// driver.executeScript("window.open('about:blank','_blank');");

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// 첫번째 탭으로 전환
		driver.switchTo().window(tabs.get(0));
		driver.get("https://gall.dcinside.com/board/lists/?id=tree");
		
		File downloadsFolder = new File("downloads"); 
		
		if( downloadsFolder.exists()==false) {
			downloadsFolder.mkdir();
		}
		
		
		//쉬는 구간
		Util.sleep(1000); 
		
		System.out.println("번호/제목/작성자/작성일/조회수/추천수");
		// css셀렉터로 이미지 찾아내기 
		
		List<WebElement> listElements = driver
				.findElements(By.cssSelector(".gall_list .us-post"));
	
		
		for(WebElement listElement : listElements) {
			
			String src = listElement.getAttribute("src"); 
			
			int id = Integer.parseInt(listElement.findElements(By.cssSelector(".gall_num")).get(0).getText().trim());
			String title = listElement.findElements(By.cssSelector(".gall_tit > a ")).get(0).getText().trim();
			String name = listElement.findElements(By.cssSelector(".gall_writer > .nickname")).get(0).getText().trim();
			
			String ipStart = "";
			
			try {
				WebElement ipElement = listElement.findElement(By.cssSelector(".gall_writer > .ip"));
				ipStart = ipElement.getText().trim();
			} catch (NoSuchElementException e) {
				
			}
			
			
			String date = listElement.findElements(By.cssSelector(".gall_date")).get(0).getAttribute("title").trim();
			int count = Util.getAsInt(listElement.findElements(By.cssSelector(".gall_count")).get(0).getText().trim());
			int recommend = Util.getAsInt(listElement.findElements(By.cssSelector(".gall_recommend")).get(0).getText().trim());
			
			/*
			if(src.contains("tr.us-post") == false ) {
				continue;
			}*/		
		
			DCInsideArticle article = new DCInsideArticle(id, title, name, ipStart, date, count, recommend);
			System.out.println(article);
			
			/*
			BufferedImage saveImage = null;
			
			try {
				saveImage = ImageIO.read(new URL(src));
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			if (saveImage != null) {
				try {
					
					String fileName = src.split("/")[3]; 
					fileName = fileName.split("\\?")[0];
					ImageIO.write(saveImage, "jpg", new File("downloads/" + fileName + ".jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			*/
			
		
		}
		
		
	}

	

}

	
