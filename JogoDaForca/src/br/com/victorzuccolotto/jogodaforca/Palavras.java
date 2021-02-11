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
		this.palavras.add("Teto");
		this.palavras.add("Conflito");
		this.palavras.add("Carpa");
		this.palavras.add("Conflito");
		this.palavras.add("Sereia");
		this.palavras.add("Tarde");
		this.palavras.add("Acumular");
		this.palavras.add("Falha");
		this.palavras.add("Enfurecer");
		this.palavras.add("Picante");
		this.palavras.add("Irrigar");
		this.palavras.add("Torradeira");
		this.palavras.add("Esponja");
		this.palavras.add("Trabalho");
		this.palavras.add("Rosto");
		this.palavras.add("Sardinha");
		this.palavras.add("Retrato");
		this.palavras.add("Dinheiro");
		this.palavras.add("Dureza");
		this.palavras.add("Forno");
		this.palavras.add("Dardos");
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
		Set<Integer> index = new HashSet<>();
		if (this.palavraSelecionada.indexOf(c) == -1)
			return false;
		for (int i = 0; i < this.palavraSelecionada.length(); i++) {
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
			System.out.println("Você venceu, parabens!");
			return true;
		}
		return false;
	}

}
