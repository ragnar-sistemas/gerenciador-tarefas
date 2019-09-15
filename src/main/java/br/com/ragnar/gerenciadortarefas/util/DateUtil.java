package br.com.ragnar.gerenciadortarefas.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	private static final String OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO = "Ops! Alguma coisa não saiu como esperado.";
	public static final String FORMATO_DATA_BRASILEIRO = "dd/MM/yyyy";

	private DateUtil() {
	}

	public static Date toDate(String data) {
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException("Ops! A data não pode ser nula ou vazia.");
		}
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return (Date) formatter.parse(data);
		} catch (ParseException e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO);
		}
	}

	public static Date convertBr(String date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA_BRASILEIRO, new Locale("pt", "BR"));
		format.setLenient(false);
		try {
			return new java.util.Date(format.parse(date).getTime());
		} catch (Exception e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO);
		}
	}

	public static String convertBr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA_BRASILEIRO, new Locale("pt", "BR"));
		try {
			return format.format(date);
		} catch (Exception e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO);
		}
	}

}
