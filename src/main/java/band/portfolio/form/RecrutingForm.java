package band.portfolio.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecrutingForm {
	
	@NotNull
	private Integer userId = 1;
	
	@Length(max = 100)
	private String title = "無題";
	
	@NotBlank
	@Length(max = 300)
	private String content;

}
