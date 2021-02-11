package br.com.victorzuccolotto.jogodaforca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Teste {
	
	public char funcao() {
		Scanner scan = new Scanner(System.in);
		String affs = scan.next();
		try {
			if (affs.length() > 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Digite somente um caractere");
			this.funcao();
		}
		scan.close();
		return affs.charAt(0);
	}

	
	public static void main(String[] args) {
		Teste tst = new Teste();
		List<String> palavras = new ArrayList<>();
		palavras.add("Teto");
		palavras.add("Conflito");
		palavras.add("Carpa");
		palavras.add("Conflito");
		palavras.add("Sereia");
		palavras.add("Tarde");

		System.out.println(palavras);
//		System.out.println(palavras.indexOf(1));
//		InputStreamReader asda = new InputStreamReader(System.in);
		char a = tst.funcao();
		System.out.println(palavras.get(1).indexOf(a,3));
		for (int i = 0; i < args.length; i++) {
			
		}
		System.out.println("ixi");
		Set<Character> letrasUsadas = new LinkedHashSet<>();
		letrasUsadas.add('a');
		System.out.println(letrasUsadas);
		System.out.println(letrasUsadas.add('b'));
		StringBuilder cens = new StringBuilder(palavras.get(0).replaceAll(".", "*"));
		String vapos = new String("***a*");
		System.out.println(cens.toString().equals(vapos));
	}
}
