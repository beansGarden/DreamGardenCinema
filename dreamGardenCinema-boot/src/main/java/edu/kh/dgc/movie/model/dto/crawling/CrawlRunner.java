package edu.kh.dgc.movie.model.dto.crawling;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import edu.kh.dgc.movie.model.dto.crawling.format.CrawlFormat;

public class CrawlRunner {
	
    public static void main(String[] args) {
    	
    	CrawlFormat info = new CrawlFormat();
    	String crawlType;
    	
		/* 전제사항 
		 * 
		 * 롯데 시네마의 HTML DOCUMENT OBJECT MODEL을 기반으로 하기 때문에 
		 * 롯데 시네마 영화 상세 페이지 외의 크롤링은 해당되지 않는다.
		 * 
		 * */
    	
    	// ----- 입력할 거 -----
    	
    	// webPage 주소
    	String Goto = "https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=19481";
    	
    	// movie Screen status -> Current, Promise
    	String movieScreen = "C";
    	
    	// crwalType in (info, stillcut, people) -> 기본전제는 영화 정보(info)가 있어야 하며 있는 경우에만 나머지 두가지를 할 수 있다.
//    	crawlType = "info";
		crawlType = "stillcut";
//		crawlType = "people";
    	
		
    	// ----------------------
    	
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
        
		/*
		 * // webPage 주소 String Goto =
		 * "https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=19890";
		 */
        
        // webPage 요청
        driver.get(Goto);
        
        // 요청 후 기다리기 -> 비동기 요청 다 끝내기
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        // crawling 요소 호출
        
        if(crawlType.equals("crawl"))	info.movieInfoCrawl(driver);
        if(crawlType.equals("stillcut"))	info.stillCutInfoCrawl(driver);
        if(crawlType.equals("people"))	info.peopleInfoCrawl(driver);
        
        
        // 1초 후에 WebDriver 종료
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	System.out.println("예외호출");
        } finally {
            // WebDriver 종료
            driver.quit();
            System.out.println("프로그램 종료");
        }
        
    }
    
}
