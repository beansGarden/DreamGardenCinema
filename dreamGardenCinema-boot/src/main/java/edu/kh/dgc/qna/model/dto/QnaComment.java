package edu.kh.dgc.qna.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor//생성자
@Getter
@Setter
@ToString 
public class QnaComment {
	
	private int qnaCommentNo;
	private int userNo;
	private int qnaNo;
	private String qnaCommentDate;
	private String qnaCommentFl;
	private String qnaComment;
	
	
}



