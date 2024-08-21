package band.portfolio.form;

import java.time.LocalDate;
import java.util.List;

import band.portfolio.domain.band.model.Reply;
import lombok.Data;

@Data
public class IndividualPostForm {
	
	private Integer recruitingId;
	private Integer userId;
	private String title;
	private String content;
	private LocalDate createDateTime;
	private List<Reply> replyList;
	
}