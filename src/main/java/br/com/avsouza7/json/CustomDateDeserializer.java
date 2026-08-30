package br.com.avsouza7.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.avsouza7.exceptions.CustomException;
import br.com.avsouza7.util.DateUtil;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	private static final String YYYY_MM_DD = "dd/MM/yyyy";
	private static final String YYYY_MM_DD_T_HH_MM_SS_SSSXXX = "dd/MM/yyyy'T'HH:mm:ss.SSSXXX";

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
		String date = jsonparser.getText();
		if (date != null && date.length() == YYYY_MM_DD.length()) {
			return format(date, YYYY_MM_DD);
		}
		return DateUtil.zeraHorasData(format(date, YYYY_MM_DD_T_HH_MM_SS_SSSXXX));
	}

	private Date format(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			format.setLenient(false);
			return format.parse(date);
		} catch (ParseException ex) {
			throw new CustomException("Formato da data informada é inválida.",
					"Erro ao converter a data informada. Deve estar no formato ISO 8601: yyyy-MM-dd.");
		}
	}

}
