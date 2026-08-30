package br.com.avsouza7.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class FormataMonetario {

	private static final Locale PT_BR = new Locale("pt", "BR");

	private FormataMonetario() {
	}

	public static String brasileiro(Number valor) {
		return NumberFormat.getCurrencyInstance(PT_BR).format(valor);
	}

}
