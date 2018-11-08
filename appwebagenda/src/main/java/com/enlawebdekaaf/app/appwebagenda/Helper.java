package com.enlawebdekaaf.app.appwebagenda;

import org.apache.commons.codec.digest.DigestUtils;

public class Helper {
	public String encrypt(String texto) {
		
		String key="msdfhNsdhyfmHYSdg";
		
		String textoEncriptado= DigestUtils.sha512Hex(texto+key);
		return textoEncriptado;
	}
	

}
