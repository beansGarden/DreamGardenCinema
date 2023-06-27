package edu.kh.dgc.movie.model.dto.crawling.format;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import edu.kh.dgc.DreamGardenCinemaBootApplication;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.Person;
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
		
		
		// 스틸컷 이미지 요소 얻어오기
        // cloned 된거 다 삭제하기
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
		List<WebElement> cloned = driver.findElements(By.className("cloned"));
		
		System.out.println(cloned);
		
		for(WebElement items : cloned) {
			  
			js.executeScript("document.querySelector('.cloned').remove()");
					  
		}
		
		
		// 클론된거 지우고 각 owl-item에 있는 src 사진 다운받기
		WebElement stage = driver.findElement(By.id("visual_top"));
		
		List<WebElement> items = stage.findElements(By.className("owl-item"));
		
		String projectDirectory = System.getProperty("user.dir");
		
		// 스틸컷 폴더 생성
		String stillCutFolderPath = projectDirectory + "\\src\\main\\resources\\static\\images\\movie\\스틸컷\\" + movieNo; 
    	File Folder = new File(stillCutFolderPath);

    	// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
    	if (!Folder.exists()) {
    		try{
    		    Folder.mkdir(); //폴더 생성합니다.
    		    System.out.println("폴더가 생성되었습니다.");
    	        } 
    	        catch(Exception e){
    		    e.getStackTrace();
    		}        
             }else {
    		System.out.println("이미 폴더가 생성되어 있습니다.");
    	}
		
		int index = 1;
		
		for(WebElement item : items) {
			
//			System.out.println(index);
			
			String imgSrc = item.findElement(By.tagName("img")).getAttribute("src");
			System.out.println(imgSrc);
			
			// 실제 저장할 경로
			String saveImgSrc = projectDirectory + "\\src\\main\\resources\\static\\images\\movie\\스틸컷\\" + movieNo + "\\" + index + ".jpg"; 
			
			BufferedImage saveImage = null;
			
			// 이미지를 다운로드 하기
			try {
				saveImage = ImageIO.read(new URL(imgSrc));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				ImageIO.write(saveImage, "jpg", new File(saveImgSrc));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// db에 저장할 경로(resolver가 된 이후의 경로)
			String imgPath = "/images/movie/스틸컷/" + movieNo + "/" + index + ".jpg";
			
			Map<String, Object> img = new HashMap<>();
			
			img.put("movieNo", movieNo);
			img.put("imgPath", imgPath);
			img.put("role", "스틸컷");
			
			service.insertMovieStillCut(img);
			
			index++;
			
			
			
		}

		

		
		
		
	}

	public void peopleInfoCrawl(ChromeDriver driver){
		
		/* Spring container에 bean 넣기 */
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DreamGardenCinemaBootApplication.class);
		context.getBean(CrawlFormat.class);
		CrawlingService service = context.getBean(CrawlingServiceImpl.class);
		
		try {
			WebElement castingMoreBtn = driver.findElement(By.id("casting_more_btn"));
			castingMoreBtn.click();
		} catch (Exception e) {
			System.out.println("더보기 버튼 없음");
		}
		
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
		
		String saveImgDirectory = null; 
		
		// 각 인물에 대한 요소 접근 -> DB에 넣기
		for (WebElement element : elements) {
			
			
			// 인물 이름
			String name = element.findElement(By.className("tit")).getText();
			System.out.println(name);
			
			// 인물역할
			String role = element.findElement(By.className("txt")).getText();
			// 인물역할이 공란이면 콘솔에서 null 출력
			if(role.equals("")) System.out.println("null");
			else System.out.println(role);
			
			// 인물 이미지 크롤링 경로
			WebElement img = element.findElement(By.tagName("img"));
			String imgSrc = img.getAttribute("src");
			System.out.println(imgSrc);
			
			if(imgSrc.contains("no")) imgSrc = "";
			
			/* 로컬로 이미지 저장하기 위한 단계 */
			// 운영체제에서 경로를 얻어와서 프로젝트 이미지 경로를 설정하기 위한 단계
			String projectDirectory = System.getProperty("user.dir");
			// C:\dreamGardenCinema\dreamGardenCinema-boot
			
			String imgInsert;
			
			// 영화인 폴더 안에 영화 넘버기준으로 영화인 프로필 이미지 경로
			if(!imgSrc.isBlank()) {
			saveImgDirectory = projectDirectory + "\\src\\main\\resources\\static\\images\\movie\\영화인\\" + movieNo + "\\" + name + ".jpg";
			
			System.out.println(saveImgDirectory);
			
			
	        // 영화고유넘버에 의거한 폴더 생성 
	    	String PeopleFolderPath = projectDirectory + "\\src\\main\\resources\\static\\images\\movie\\영화인\\" + movieNo; 
	    	File Folder = new File(PeopleFolderPath);

	    	// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
	    	if (!Folder.exists()) {
	    		try{
	    		    Folder.mkdir(); //폴더 생성합니다.
	    		    System.out.println("폴더가 생성되었습니다.");
	    	        } 
	    	        catch(Exception e){
	    		    e.getStackTrace();
	    		}        
	             }else {
	    		System.out.println("이미 폴더가 생성되어 있습니다.");
	    	}
			
			
			// 이미지를 다운로드 하기
			BufferedImage saveImage = null;

			try {
				saveImage = ImageIO.read(new URL(imgSrc));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				ImageIO.write(saveImage, "jpg", new File(saveImgDirectory));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imgInsert = "/images/movie/영화인/" + movieNo + "/" + name + ".jpg";
			
			}else { // 이미지 없으면 null.png 경로로 저장
				System.out.println("이미지 정보 없음");
				saveImgDirectory = projectDirectory + "\\src\\main\\resources\\static\\images\\movie\\영화인\\null.png";
				System.out.println(saveImgDirectory);
				imgInsert = "/images/movie/영화인/null.png";
			}
			
			System.out.println();
			
			System.out.println(imgInsert);
			
			// DB insert 과정
			// DB 들어갈 people 정보를 세팅하기
			
			Person person = new Person();
			person.setMovieNo(movieNo);
			person.setName(name);
			person.setRole(role);
			person.setImg(imgInsert);
			
			service.insertMoviePerson(person);
			
		}
		
	}

}
