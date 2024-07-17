package band.portfolio.form;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReplyForm {

	private Integer replyId;
	private Integer userId;
	private Integer recruitingId;
	
	@NotBlank
	@Length(max = 300)
	private String content;
	
	private Integer isDeleted;
	private LocalDate createDateTime;
	private LocalDate updateDateTime;
	
}
