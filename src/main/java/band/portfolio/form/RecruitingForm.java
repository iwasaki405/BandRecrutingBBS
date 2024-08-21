package band.portfolio.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruitingForm {

	private Integer userId;
	
	@Length(max = 100)
	private String title = "無題";
	
	@NotBlank
	@Length(max = 300)
	private String content;
	
	

}
