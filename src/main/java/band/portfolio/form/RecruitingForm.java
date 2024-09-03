package band.portfolio.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruitingForm {

	private Integer userId;
	
	@Length(max = 100)
	private String title = "無題";
	
	@NotBlank(message="募集文は必須入力です")
	@Length(max = 300)
	private String content;
	
	

}
