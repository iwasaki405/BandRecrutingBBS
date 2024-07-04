package band.portfolio.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class IndividualPostForm {
	
	private Integer recrutingId;
	private Integer userId;
	private String title;
	private String content;
	private Integer isDeleted;
	private LocalDate createDateTime;
	private LocalDate updateDateTime;
	
}