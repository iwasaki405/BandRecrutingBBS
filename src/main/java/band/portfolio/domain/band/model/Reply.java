package band.portfolio.domain.band.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Reply {

	private Integer replyId;
	private Integer userId;
	private Integer recruitingId;
	private String content;
	private Integer isDeleted;
	private LocalDate createDateTime;
	private LocalDate updateDateTime;
	
}
