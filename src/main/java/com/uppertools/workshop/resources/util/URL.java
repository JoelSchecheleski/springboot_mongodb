package com.uppertools.workshop.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	/**
	 * Decodifica o texto passado como parâmetro
	 *
	 * @param text Texto a ser decodificado
	 * @return Retorna texto
	 */
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * Converte uma texto de data para uma data
	 *
	 * @param textDate     Data em formato de texto
	 * @param defaultValue Valor default caso algum problema ocorra
	 * @return Retorna date com base no texto parametrizado
	 */
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}

	}

}
