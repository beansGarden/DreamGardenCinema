package edu.kh.dgc.qna.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor//생성자
@Getter
@Setter
@ToString 
public class Qna {
	
	private int qnaNo;
	private int userNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaEnrollDate;
	private String qnaDeleteFl;
	private String qnaCheckFl;
	private String qnaCategory;
	private String userNickname;
	
	//JOIN QNA_COMMENT
	
	private int qnaCommentNo;
	private String qnaCommentDate;
	private String qnaCommentFl;
	private String qnaComment;
	
	//JOIN USER_INFO
	private int userRating;
	private String userId;
	
	//JOIN QNA_IMG
	private String qnaImage;
	
	//검색필터
	private  String type;
	private String keyword;
	
	//비회원 정보
	private String nonMemberName;
	private String nonMemberEmail;
	private String nonMemberTel;
	
	private String selectedValue;
	
	//IMG COLLECTION
	private List<QnaImage> imageList;
	
}



