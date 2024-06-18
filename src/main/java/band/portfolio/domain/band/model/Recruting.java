package band.portfolio.domain.band.model;

import java.util.Date;

import lombok.Data;

@Data
public class Recruting {
	
	private Integer recrutingId;
	private Integer userId;
	private String title;
	private String content;
	private Integer isDeleted;
	private Date createDateTime;
	private Date updateDateTime;

}
