package edu.kh.dgc.user.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
	
	@Pattern(regexp = "^[A-Za-z]+[A-Za-z0-9]{4,19}$")
	private String userId;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-z\\d$@$!%*#?&]{8,}$")
	private String userPw;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-z\\d$@$!%*#?&]{8,}$")
	private String userRePw;
	
	@Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$")
	private String userNickname;
	
	@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
	private String userTel;
	
	@Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")
	private String userEmail;
	
	@Pattern(regexp = "^(19\\d{2}|20\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")
	private String userBirth1;
	
	@Past
	private LocalDate userBirth;
	
	@NotBlank
	private String userGender;
	
	
	@NotBlank
	private String authKey;
	
	private String smsConfirmNum;
	
	private String userEnrollDate;
	
	private int userAmount;
	
	private String userRole;
	
	private int userRating;
	
	private String userClose;
	
	private String userAddress;
	
}
