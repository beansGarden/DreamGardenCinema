package edu.kh.dgc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;

@PropertySource("classpath:/config.properties")
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

	// 파일을 hdd에 저장하기 전 임시로 가지고 있을 메모리 용량
	@Value("${spring.servlet.multipart.file-size-threshold}")
	private long fileSizeThreshold;

	// 파일 1개 크기 제한
	@Value("${spring.servlet.multipart.max-file-size}")
	private long maxFileSize;

	// 요청당 파일 크기 제한
	@Value("${spring.servlet.multipart.max-request-size}")
	private long maxRequestSize;

	@Bean
	public MultipartConfigElement configElement() {

		MultipartConfigFactory factory = new MultipartConfigFactory();

		factory.setFileSizeThreshold(DataSize.ofBytes(fileSizeThreshold));
		factory.setMaxFileSize(DataSize.ofBytes(fileSizeThreshold));
		factory.setMaxRequestSize(DataSize.ofBytes(fileSizeThreshold));

		return factory.createMultipartConfig();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		// MultipartResolver : 파일은 파일로, 텍스트를 텍스트로 자동 구분

		return new StandardServletMultipartResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String webPath = "/images/**";
		String resourcePath = "file:" + System.getProperty("user.dir") + "/src/main/resources/static/images/customerservice/";
		System.out.println(resourcePath);

		registry.addResourceHandler(webPath).addResourceLocations(resourcePath);
	}

}
