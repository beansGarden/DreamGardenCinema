package edu.project.dreamGardenCinema.user.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userNickname;
	private String userTel;
	private String userEmail;
	private String userRegiNum;
	private String userEnrollDate;
	private int userAmount;
	private String userRole;
	private int userRating;
	private String userClose;

}
