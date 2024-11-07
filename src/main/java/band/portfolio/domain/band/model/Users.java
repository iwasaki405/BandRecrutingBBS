package band.portfolio.domain.band.model;

import java.util.Date;

import lombok.Data;

@Data
public class Users {

	private Integer userId;
	private String emailAddress;
	private String password;
	private String userName;
	private Integer role;
	private Integer isDeleted;
	private Date createDateTime;
	private Date updateDateTime;

}
