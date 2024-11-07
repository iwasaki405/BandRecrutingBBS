package band.portfolio.form;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import band.portfolio.domain.band.model.Reply;
import band.portfolio.domain.band.model.Users;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IndividualPostForm {

	private Integer recruitingId;

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

	private LocalDate createDateTime;

	private List<Reply> replyList;

	private Users users;

}