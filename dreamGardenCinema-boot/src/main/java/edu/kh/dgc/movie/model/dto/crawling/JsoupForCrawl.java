package edu.kh.dgc.movie.model.dto.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupForCrawl {
    public static void main(String[] args) throws Exception {
        // 웹 페이지를 가져옵니다.
        Document doc = Jsoup.connect("https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=19850")
        		.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .get();
        
        
        // 결과를 출력합니다.
        System.out.println(doc.html());

    }
}
