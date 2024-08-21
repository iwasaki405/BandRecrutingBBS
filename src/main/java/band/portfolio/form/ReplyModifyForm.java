package band.portfolio.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReplyModifyForm {

	@NotBlank
	@Length(max = 300)
	private String content;
	
}
