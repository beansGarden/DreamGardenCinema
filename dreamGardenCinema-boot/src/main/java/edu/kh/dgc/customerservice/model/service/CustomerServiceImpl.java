package edu.kh.dgc.customerservice.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.dgc.common.utility.Util;
import edu.kh.dgc.customerservice.model.dao.CustomerServiceMapper;
import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaImage;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerServiceMapper mapper;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/resources/static/images/customerservice/";
	
	private String webPath = "/images/customerservice/";

	// FAQ 게시글 전체목록 조회 서비스
	@Override
	public List<FAQ> main(FAQ faq) {
		return mapper.main(faq);
	}

	// 검색어와 일치하는 FAQ 게시글 목록 조회
	@Override
	public List<FAQ> searchFAQList(String searchQuery) {
		return mapper.searchFAQList(searchQuery);
	}

	// 공지사항 게시글 조회한 경우 해당 게시글 번호와 일치하는 내용 페이지로 이동
	@Override
	public List<Notice> noticeSelect(String noticeNo) {
		return mapper.noticeSelect(noticeNo);
	}

	// 공지사항 전체 목록 조회
	@Override
	public List<Notice> noticeList(Notice notice) {
		return mapper.noticeList(notice);
	}

	// 공지사항 검색어 조회
	@Override
	public List<Notice> noticeSearchList(Map<String, Object> param) {
		return mapper.noticeSearchList(param);
	}

	// 1:1문의글 등록(회원)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int cusQAInsert(Qna qna, List<MultipartFile> images, String selectedValue)
			throws IllegalStateException, IOException {

		qna.setSelectedValue(selectedValue);

		int result = mapper.cusQAInsert(qna);

//		System.out.println("1:1문의삽입글: " + result);

		// 실패시 서비스 종료
		if (result == 0)
			return 0;

		int qnaNo = qna.getQnaNo();

		System.out.println(images.size());

		String nameFile = "";
		
		if (qnaNo > 0 ) { // 1:1문의글 삽입 성공시

			List<QnaImage> uploadList = new ArrayList<QnaImage>();

			for (int i = 0; i < images.size(); i++) {

				if (images.get(i).getSize() > 0) {

					QnaImage img = new QnaImage();
							/* "/images/customerservice/" */
					img.setQnaImgPath(webPath); // 웹 접근 경로
					img.setQnaNo(qnaNo); // 1:1 문의글 번호
					img.setQnaImgOrder(i);// 이미지 순서

					// 파일 원본명
					nameFile = images.get(i).getOriginalFilename();
					
					img.setQnaImgOriginal(nameFile);
					img.setQnaImgRename(Util.fileRename(nameFile));
					
					uploadList.add(img);
				}
			}

			if (!uploadList.isEmpty()) {
				result = mapper.insertQAImageList(uploadList);

				if (result == uploadList.size()) {
					for (int i = 0; i < uploadList.size(); i++) {

						int index = uploadList.get(i).getQnaImgOrder();

						System.out.println(nameFile);
						
						String rename = uploadList.get(i).getQnaImgRename();
						
						images.get(index).transferTo(new File(filePath+rename));
						uploadList.get(i).setQnaImgOrder(result);
					}
				} else {
					throw new FileUploadException(); // 예외 강제 발생
				}
			}
		}
		return qnaNo;
	}

}
