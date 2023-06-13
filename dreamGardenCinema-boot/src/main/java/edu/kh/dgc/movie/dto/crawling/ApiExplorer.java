package edu.kh.dgc.movie.dto.crawling;
/* Java 샘플 코드 */

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
	public static void main(String[] args) throws IOException {
		
		/*
		 * https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.
		 * jsp?
		 * collection=kmdb_new2&ServiceKey=W872GJ31W6A7DJ79Y2UA&detail=Y&query=%22%22
		 */
		
		/* URL */ 
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2");
		
		/* Service Key */ 
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=W872GJ31W6A7DJ79Y2UA");
		
		/* 디테일 추가 */
		urlBuilder.append("&detail=" + "Y");
		
		/* 검색할 영화 */
		urlBuilder.append("&" + "query=" + URLEncoder.encode("해리포터와비밀의방","UTF-8"));
			
		/* 상영년도 */ 
		/*
		 * urlBuilder .append("&" + URLEncoder.encode("val001", "UTF-8") + "=" +
		 * URLEncoder.encode("2018", "UTF-8"));
		 */
		
		/* 상영 월 */ 
		/*
		 * urlBuilder .append("&" + URLEncoder.encode("val002", "UTF-8") + "=" +
		 * URLEncoder.encode("01", "UTF-8"));
		 */
		
		
		URL url = new URL(urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		
		conn.setRequestProperty("Content-type", "application/json");
		
		System.out.println("Response code: " + conn.getResponseCode());
		
		BufferedReader rd;
		
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		
		String line;
		
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		rd.close();
		
		conn.disconnect();
		
		System.out.println(sb.toString());
		
	}
}