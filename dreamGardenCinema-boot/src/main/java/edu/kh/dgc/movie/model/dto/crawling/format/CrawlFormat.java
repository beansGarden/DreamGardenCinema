package edu.kh.dgc.movie.model.dto.crawling.format;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import edu.kh.dgc.DreamGardenCinemaBootApplication;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.CrawlingService;
import edu.kh.dgc.movie.model.service.CrawlingServiceImpl;

@Component
public class CrawlFormat {


	
	public void movieInfoCrawl(ChromeDriver driver) {
		
		
		/* Spring container에 bean 넣기 */
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DreamGardenCinemaBootApplication.class);
		context.getBean(CrawlFormat.class);
		CrawlingService service = context.getBean(CrawlingServiceImpl.class);
		
		
		
//    	private int movieNo;

//    	private String movieTitle;
		String movieTitle = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[2]/strong")).getText();
		System.out.println(movieTitle);

//    	private String poster;
//      /images/common/main/포스터/범죄도시.jpg

		// 운영체제에서 경로를 얻어와서 프로젝트 이미지 경로를 설정하기 위한 단계
		String projectDirectory = System.getProperty("user.dir");
		// C:\dreamGardenCinema\dreamGardenCinema-boot

		String saveImgDirectory = projectDirectory + "\\src\\main\\resources\\static\\images\\common\\main\\포스터\\";

		System.out.println(saveImgDirectory);

		// 브라우저에서 src 속성 경로를 얻어오기 위한 단계
		String posterSrc = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[1]/img")).getAttribute("src");
		System.out.println(posterSrc);

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
		System.out.println(poster);

//    	private String synopsis;
		String syhopsis = driver.findElement(By.xpath("//*[@id=\"mCSB_1_container\"]/p/span")).getText();
		System.out.println(syhopsis);

//    	private String runningTime;
		String runningTime = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/ul[2]/li[1]/strong/em[3]"))
				.getText();

		runningTime = runningTime.replaceAll("분", "");

		System.out.println(runningTime);

//    	private String rating;
//        /images/common/main/12세.png

		String rating = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[2]/span")).getText();
		// 전체관람가, 만12세이상관람가, 만15세이상관람가, 청소년관람불가
		if (rating.equals("전체관람가"))
			rating = "/images/common/main/ALL.png";
		else if (rating.equals("만12세이상관람가"))
			rating = "/images/common/main/12세.png";
		else if (rating.equals("만15세이상관람가"))
			rating = "/images/common/main/15세.png";
		else if (rating.equals("청소년관람불가"))
			rating = "/images/common/main/18세.png";
		else if (rating.equals("관람등급미정"))
			rating = "/images/common/main/미정.png";

		System.out.println(rating);

//    	private String releaseDate;
		String releaseDate = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/ul[2]/li[1]/strong/em[2]"))
				.getText();

		releaseDate = releaseDate.replaceAll("[.]", "-");
		releaseDate = releaseDate.replaceAll(" 개봉", "");

		System.out.println(releaseDate);

//    	private String producer;
//		" "

//    	private int moviePrice;
//		12000

//    	private String ratio; -> 생략

//    	private String mainPoster;
//      null

//    	private String genre;
		String genre = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/ul[2]/li[1]/strong/em[1]")).getText();
		System.out.println(genre);

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

		
		 

		service.insertMovieInfo(movie);
	}

	public void stillCutInfoCrawl(ChromeDriver driver) {
		
		/* Spring container에 bean 넣기 */
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DreamGardenCinemaBootApplication.class);
		context.getBean(CrawlFormat.class);
		CrawlingService service = context.getBean(CrawlingServiceImpl.class);
		
		
		
		
	}

	public void peopleInfoCrawl(ChromeDriver driver){
		
		/* Spring container에 bean 넣기 */
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DreamGardenCinemaBootApplication.class);
		context.getBean(CrawlFormat.class);
		CrawlingService service = context.getBean(CrawlingServiceImpl.class);
		
		
		// 영화 타이틀 얻어오기
        String movieTitle = driver.findElement(By.xpath("//*[@id=\"contents\"]/div/div[2]/strong")).getText();
        System.out.println( movieTitle );
        
        System.out.println();
        
        // 영화 타이틀에 맞는 영화고유넘버(DB) 찾기
        
        // 고유 넘버 찾기 이전에 그 영화가 있는지 찾아보기
        if(service.selectHavingMovieNoByTitle(movieTitle) == 0){
        	System.out.println("영화가 DB에 없습니다. 영화 정보부터 넣어주세요");
        	return;
        }
        
        
        int movieNo = service.selectMovieNoByTitle(movieTitle);
        System.out.println(movieNo);
        
        
        System.out.println();
        
        // 인물란 wrapper html 요소 찾고 각 요소(인물 한명 한명)에 대한 리스트 변수 지정
		List<WebElement> elements = driver.findElement(By.className("bx_list_people")).findElements(By.tagName("li"));
		
		// 각 인물에 대한 요소 접근 -> DB에 넣기
		for (WebElement element : elements) {
			WebElement name = element.findElement(By.className("tit"));
			System.out.println(name.getText());
			
			WebElement role = element.findElement(By.className("txt"));
			
			if(role.getText() == "") System.out.println("null");
			else System.out.println(role.getText());
			
			WebElement img = element.findElement(By.tagName("img"));
			String imgSrc = img.getAttribute("src");
			System.out.println(imgSrc);
			
			System.out.println();
			
		}
		
	}

}
