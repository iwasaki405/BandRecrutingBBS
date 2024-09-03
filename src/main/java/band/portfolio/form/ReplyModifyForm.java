package band.portfolio.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReplyModifyForm {

	@NotBlank(message="返信文は必須入力です")
	@Length(max = 300)
	private String content;
	
}
