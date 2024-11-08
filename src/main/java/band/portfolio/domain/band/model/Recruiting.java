package band.portfolio.domain.band.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Recruiting {

	private Integer recruitingId;
	private Integer userId;
	private String title;
	private String content;
	private String area;
	private int minAge;
	private int maxAge;
	private String part;
	private Integer isDeleted;
	private LocalDate createDateTime;
	private LocalDate updateDateTime;
	private List<Reply> replyList;
	private Users users;
}
