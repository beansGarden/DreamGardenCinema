package edu.kh.dgc.movie.model.dto.crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumnForCrawl {
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
	public static final String WEB_DRIVER_PATH = "C:\\driver\\chromedriver.exe"; //드라이버 경로
	
	public static void main(String[] args) {
		//드라이버 설정
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//크롬 설정을 담은 객체 생성
		ChromeOptions options = new ChromeOptions();
		
		//브라우저가 눈에 보이지 않고 내부적으로 돈다.
		//설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//		options.addArguments("headless");
		
		//위에서 설정한 옵션은 담은 드라이버 객체 생성
		//옵션을 설정하지 않았을 때에는 생략 가능하다.
		//WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
		WebDriver driver = new ChromeDriver(options);
		
		//이동을 원하는 url
		String url = "https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=19850";
		
		//WebDriver을 해당 url로 이동한다.
		driver.get(url);
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {}
		
		
		
		try {
			//드라이버가 null이 아니라면
			if(driver != null) {
				//드라이버 연결 종료
				driver.close(); //드라이버 연결 해제
				
				//프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
