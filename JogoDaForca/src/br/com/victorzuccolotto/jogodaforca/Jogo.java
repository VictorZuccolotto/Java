package br.com.victorzuccolotto.jogodaforca;

import java.util.Scanner;

public class Jogo {

	public void jogada(Palavras plvrs, Vida vida) {
		while (true) {
			if(!plvrs.verificaPalavra(lerDoTeclado()))
				vida.diminui();
			if(plvrs.verificaVitoria() || vida.verificaVida(plvrs))
				break;
			progresso(plvrs, vida);
		}

	}

	private void progresso(Palavras plvrs, Vida vida) {
		System.out.println(plvrs.palavraCensurada);
		System.out.println("Voce tem mais " + (vida.QuantidadeDeVidas) +" chances");
	}

	public char lerDoTeclado() {
		Scanner scan = new Scanner(System.in);
		String sc = scan.next();
		try {
			if (sc.length() > 1) {
				extracted();
			}
		} catch (Exception e) {
			System.out.println("Digite somente um caractere");
			this.lerDoTeclado();
		}
//		scan.close();
		return sc.charAt(0);
	}

	private void extracted() throws Exception {
		throw new Exception();
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		Palavras plvrs = new Palavras();
		Vida vida = new Vida(plvrs.palavraSelecionada.length());
		jogo.jogada(plvrs,vida);
	}

}
