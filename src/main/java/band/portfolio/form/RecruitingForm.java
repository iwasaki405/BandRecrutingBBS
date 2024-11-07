package band.portfolio.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruitingForm {

	private Integer userId;

	@NotBlank(message = "タイトルは必須入力です")
	@Length(max = 100)
	private String title;

	@NotBlank(message = "募集文は必須入力です")
	@Length(max = 300)
	private String content;

	private String area;

	@Min(5)
	@Max(99)
	private Integer minAge;

	@Min(5)
	@Max(99)
	private Integer maxAge;

	private String part;

}
