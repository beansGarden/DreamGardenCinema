package edu.kh.dgc.notice.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String enrollDate;
	private int noticeViewpoint;
	private String noticeFL;
}
