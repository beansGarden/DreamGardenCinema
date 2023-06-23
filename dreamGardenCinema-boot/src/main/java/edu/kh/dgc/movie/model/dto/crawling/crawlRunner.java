package edu.kh.dgc.movie.model.dto.crawling;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class crawlRunner {
	
    public static void main(String[] args) {
    	
    	
    	
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromeDriver\\chromedriver.exe"; 
        // System.getProperty("user.dir") == C:\dreamGardenCinema\dreamGardenCinema-boot
        
        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", path.toString());
        
        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); 				//창 열지 않고 작업
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // 페이지 풀로드
        
        // WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver( options );
        
        
        driver.get("https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=19890");
        
        // 5초 후에 WebDriver 종료
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
            driver.quit();
        }
        
    }
    
}
