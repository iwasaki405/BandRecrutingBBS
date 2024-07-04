package band.portfolio.domain.band.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Recruting {
	
	private Integer recrutingId;
	private Integer userId;
	private String title;
	private String content;
	private Integer isDeleted;
	private LocalDate createDateTime;
	private LocalDate updateDateTime;

}
