package band.portfolio.form;

import lombok.Data;

@Data
public class RecrutingForm {
	
	private Integer userId;
	private String title = "無題";
	private String content;

}
