package edu.kh.dgc.movie.model.dto.crawling;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import edu.kh.dgc.DreamGardenCinemaBootApplication;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.CrawlingService;
import edu.kh.dgc.movie.model.service.CrawlingServiceImpl;

@Component
public class SeleniumnForCrawlLSFinal {
	
	
    public static void main(String[] args) {
        
        // 현재 package의 workspace 경로, Windows는 [ chromedriver.exe ]
        String path = "C:\\driver\\chromedriver.exe";  // 현재 package의
        
        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", path.toString());
        
        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // 페이지 풀로드
        
        // WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver( options );
        
        // 웹페이지 요청
        driver.get("https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=19586");
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        
        
//    	private int movieNo;
        
//    	private String movieTitle;
        String movieTitle = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[2]/strong")).getText();
        System.out.println( movieTitle );            
        
//    	private String poster;
//      /images/common/main/포스터/범죄도시.jpg
        
        // 운영체제에서 경로를 얻어와서 프로젝트 이미지 경로를 설정하기 위한 단계
        String projectDirectory = System.getProperty("user.dir");
        // C:\dreamGardenCinema\dreamGardenCinema-boot
        
        String saveImgDirectory = projectDirectory + "\\src\\main\\resources\\static\\images\\common\\main\\포스터\\";
        
        System.out.println( saveImgDirectory );
        
        
        // 브라우저에서 src 속성 경로를 얻어오기 위한 단계
        String posterSrc = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[1]/img")).getAttribute("src");
        System.out.println( posterSrc );      
        
        // 이미지를 다운로드 하기
        BufferedImage saveImage = null;
        
        try {
            saveImage = ImageIO.read(new URL(posterSrc));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 윈도우는 이름에 : 가 들어가면 안되기 때문에 movieTitle을 가공해야한다. ( : 문자 빼기)
        String movieTitleReplace = movieTitle.replaceAll("[:]", "");
        
        try {
			ImageIO.write(saveImage, "jpg", new File(saveImgDirectory + movieTitleReplace + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//      /images/common/main/포스터/범죄도시.jpg
        // boot 기준으로 이미지 경로를 지정하기 위한 단계
        String poster = "/images/common/main/포스터/" + movieTitleReplace + ".jpg";
        System.out.println( poster );        
        
        
//    	private String synopsis;
        String syhopsis = driver.findElement(By.xpath("//*[@id=\"mCSB_1_container\"]/p/span")).getText();
        System.out.println( syhopsis );            
        
//    	private String runningTime;
        String runningTime = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/ul[2]/li[1]/strong/em[3]")).getText();
        
        runningTime = runningTime.replaceAll("분", "");
        
        System.out.println( runningTime );            
        
//    	private String rating;
//        /images/common/main/12세.png
        
        String rating = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[2]/span")).getText();
        // 전체관람가, 만12세이상관람가, 만15세이상관람가, 청소년관람불가
        if(rating.equals("전체관람가")) rating = "/images/common/main/ALL.png";
        else if(rating.equals("만12세이상관람가")) rating = "/images/common/main/12세.png";
        else if(rating.equals("만15세이상관람가")) rating = "/images/common/main/15세.png";
    	else if(rating.equals("청소년관람불가")) rating = "/images/common/main/18세.png";
        
        
        System.out.println( rating );            
        
        
        
//    	private String releaseDate;
        String releaseDate = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/ul[2]/li[1]/strong/em[2]")).getText();
        
        releaseDate = releaseDate.replaceAll("[.]", "-");
        releaseDate = releaseDate.replaceAll(" 개봉", "");
        
        
        System.out.println( releaseDate );            
        
//    	private String producer;
//		" "
        
//    	private int moviePrice;
//		12000
        
//    	private String ratio; -> 생략

//    	private String mainPoster;
//      null
        
//    	private String genre;
        String genre = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/ul[2]/li[1]/strong/em[1]")).getText();
        System.out.println( genre );            
        
        
        // 탭 종료
        driver.close();
        
        // 5초 후에 WebDriver 종료
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
            driver.quit();
        }
        
        
        // DB insert 과정
        // DB 들어갈 movie 정보를 세팅하기
        Movie movie = new Movie();
        
        movie.setMovieTitle(movieTitle);
        movie.setPoster(poster);
        movie.setSynopsis(syhopsis);
        movie.setRunningTime(runningTime);
        movie.setRating(rating);
        movie.setReleaseDate(releaseDate);
        movie.setGenre(genre);
        movie.setMoviePrice(12000);
        movie.setScreening("C");
        
        System.out.println(movie);
        
        @SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DreamGardenCinemaBootApplication.class);
        context.getBean(SeleniumnForCrawlLS.class);
        CrawlingService service = context.getBean(CrawlingServiceImpl.class);
        
        service.insertMovieInfo(movie);
        
        
        
    }
    
}
