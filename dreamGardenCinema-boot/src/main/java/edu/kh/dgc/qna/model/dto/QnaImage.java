package edu.kh.dgc.qna.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class QnaImage {

	private int qnaImgNo;
	private int qnaImgOrder;
	private String qnaImgPath;
	
	private int qnaNo;
	
}
