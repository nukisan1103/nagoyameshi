package com.example.nagoyamesi.form;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaidRegistForm {

	@NotBlank(message = "店舗名を入力してください。")
    private String nominee;

	@NotBlank(message = "カード種別を選択してください。")
	private String card_type;

	@NotNull(message = "カード有効年を入力してください。")
	private String period_year;
	
	@NotNull(message = "カード有効月を入力してください。")
	private String period_month;

}
