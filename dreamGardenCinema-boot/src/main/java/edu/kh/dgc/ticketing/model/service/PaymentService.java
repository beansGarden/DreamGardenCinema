package edu.kh.dgc.ticketing.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@PropertySource("classpath:/config2.properties")
@Service
public class PaymentService {
	// ---------------------환불, 결제 토큰생성
	@Value("${port-one.RESTAPIKey}")
	private String RESTAPIKey;

	@Value("${port-one.RESTAPISecret}")
	private String RESTAPISecret;

	public String getToken() throws Exception {

		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/users/getToken");

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		JsonObject json = new JsonObject();

		json.addProperty("RESTAPIKey", RESTAPIKey);
		json.addProperty("RESTAPISecret", RESTAPISecret);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		Gson gson = new Gson();

		String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

		String token = gson.fromJson(response, Map.class).get("access_token").toString();

		br.close();
		conn.disconnect();

		return token;
	}

	public String paymentInfo(String imp_uid, String access_token) throws IOException, ParseException {
		HttpsURLConnection conn = null;

		URL url = new URL("https://api.iamport.kr/payments/" + imp_uid);

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", access_token);
		conn.setDoOutput(true);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		JSONParser parser = new JSONParser();

		JSONObject p = (JSONObject) parser.parse(br.readLine());

		String response = p.get("response").toString();

		p = (JSONObject) parser.parse(response);

		String amount = p.get("amount").toString();

		System.out.println("check : " + amount);
		return amount;
	}

	public void payMentCancle(String access_token, String imp_uid, String amount, String reason)
			throws IOException, ParseException {
		System.out.println("imp_uid = " + imp_uid);
		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel");

		conn = (HttpsURLConnection) url.openConnection();

		conn.setRequestMethod("POST");

		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", access_token);

		conn.setDoOutput(true);

		JsonObject json = new JsonObject();

		json.addProperty("reason", reason);
		json.addProperty("imp_uid", imp_uid);
		json.addProperty("amount", amount);
		json.addProperty("checksum", amount);

		System.out.println("check 1 : " + imp_uid);
		System.out.println("check 2 : " + amount);
		System.out.println(reason);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		bw.write(json.toString());
		bw.flush();
		bw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

	}
}
