package br.com.ragnar.gerenciadortarefas.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateUtil {

	private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");
	private static final String OPS_A_DATA_NAO_PODE_SER_NULA_OU_VAZIA = "Ops! A data não pode ser nula ou vazia.";
	private static final String OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO = "Ops! Alguma coisa não saiu como esperado.";
	public static final String FORMATO_DATA_BRASILEIRO = "dd/MM/yyyy";

	private DateUtil() {
	}

	public static Date toDate(String data) {
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException(OPS_A_DATA_NAO_PODE_SER_NULA_OU_VAZIA);
		}
		try {
			DateFormat formatter = new SimpleDateFormat(FORMATO_DATA_BRASILEIRO);
			return (Date) formatter.parse(data);
		} catch (ParseException e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO);
		}
	}

	public static Date convertBr(String date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA_BRASILEIRO, LOCALE_PT_BR);
		format.setLenient(false);
		try {
			return new java.util.Date(format.parse(date).getTime());
		} catch (Exception e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO);
		}
	}

	public static String convertBr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA_BRASILEIRO, LOCALE_PT_BR);
		try {
			return format.format(date);
		} catch (Exception e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO);
		}
	}

	public static boolean isMenorOuIgual(Date dt1, Date dt2) {
		dt1 = DateUtil.zeraHorasData(dt1);
		dt2 = DateUtil.zeraHorasData(dt2);
		return dt1.before(dt2) || dt2.equals(dt1);
	}
	
	public static boolean isMenor(Date dt1, Date dt2) {
		dt1 = DateUtil.zeraHorasData(dt1);
		dt2 = DateUtil.zeraHorasData(dt2);
		return dt1.before(dt2);
	}
	
	public static boolean isMenor(Date dt1) {
		dt1 = DateUtil.zeraHorasData(dt1);
		return dt1.before(DateUtil.zeraHorasData(new Date()));
	}
	
	public static Date zeraHorasData(Date dt) {
		Calendar c = new GregorianCalendar();
		c.setTime(dt);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		return c.getTime();
	}
	
}
