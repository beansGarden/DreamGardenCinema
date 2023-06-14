package edu.kh.dgc.qna.model.dto;

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
	
}



