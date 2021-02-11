package br.com.victorzuccolotto.jogodaforca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Palavras {
	List<String> palavras = new ArrayList<>();
	Set<Character> letrasUsadas = new LinkedHashSet<>();
	String palavraSelecionada = new String();
	StringBuilder palavraCensurada;

	public Palavras() {
		this.palavras.add("teto");
		this.palavras.add("conflito");
		this.palavras.add("carpa");
		this.palavras.add("conflito");
		this.palavras.add("sereia");
		this.palavras.add("tarde");
		this.palavras.add("acumular");
		this.palavras.add("falha");
		this.palavras.add("enfurecer");
		this.palavras.add("picante");
		this.palavras.add("irrigar");
		this.palavras.add("torradeira");
		this.palavras.add("esponja");
		this.palavras.add("trabalho");
		this.palavras.add("rosto");
		this.palavras.add("sardinha");
		this.palavras.add("retrato");
		this.palavras.add("dinheiro");
		this.palavras.add("dureza");
		this.palavras.add("forno");
		this.palavras.add("dardos");
		palavraAleatoria();
		setPalavraCensurada();
	}

	public void palavraAleatoria() {
		Collections.shuffle(palavras);
		palavraSelecionada = palavras.get(0);
	}

	public String getPalavraSelecionada() {
		return palavraSelecionada;
	}

	public boolean verificaPalavra(char c) {
		adicionaCaractere(c);
		Set<Integer> index = new HashSet<>();
		if (this.palavraSelecionada.indexOf(c) == -1)
			return false;
		for (int i = this.palavraSelecionada.indexOf(c, 0); i < this.palavraSelecionada.length(); i++) {
			i = this.palavraSelecionada.indexOf(c, i);
			if (!index.add(this.palavraSelecionada.indexOf(c, i))) {
				break;
			}
		}
		for (Integer integer : index) {
			this.palavraCensurada.setCharAt(integer, c);
		}
		return true;
	}

	public void setPalavraCensurada() {
		this.palavraCensurada = new StringBuilder(this.palavraSelecionada.replaceAll(".", "*"));
	}

	public boolean verificaVitoria() {
		if (this.palavraCensurada.toString().equals(this.palavraSelecionada)) {
			System.out.println(this.palavraCensurada);
			System.out.println("Você venceu, parabens!");
			return true;
		}
		return false;
	}

	private void adicionaCaractere(char c) {
		this.letrasUsadas.add(c);
	}

}
