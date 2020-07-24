package com.uppertools.workshop.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

}
