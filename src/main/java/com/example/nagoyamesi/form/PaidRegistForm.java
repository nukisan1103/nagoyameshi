package com.example.nagoyamesi.form;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaidRegistForm {
	
	private Integer userId;
	
    private String nominee;
		
    private String card_number;
		
    private String sec_number;
	
	private String card_type;
	
	private String period_year;
	
	private String period_month;

}
