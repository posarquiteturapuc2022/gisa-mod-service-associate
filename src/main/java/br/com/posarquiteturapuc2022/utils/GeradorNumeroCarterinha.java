package br.com.posarquiteturapuc2022.utils;

import java.util.Random;

public class GeradorNumeroCarterinha {
	public static String gerarNumeroAleatorio() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		sb.append("7000");
		sb.append("-");
		for (int i = 0; i < 12; i++) {
			sb.append(random.nextInt(10));
			if ((i + 1) % 4 == 0 && i < 11) {
				sb.append("-");
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String numeroAleatorio = gerarNumeroAleatorio();
		System.out.println(numeroAleatorio);
	}
}
