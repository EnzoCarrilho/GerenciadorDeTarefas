package br.dev.enzo.tarefas.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Utils {
	
	public static String gerarUUID8() {
		
		UUID uuid = UUID.randomUUID();
		String uuid8 = uuid.toString().substring(0, 8);
		return uuid8;
	}
	
}
