package br.com.victorzuccolotto.jogodaforca;

public class Vida{
	int QuantidadeDeVidas;

	public Vida(int tam) {
		setQuantidadeDeVidas(tam);
	}
	
	public void setQuantidadeDeVidas(int tam) {
		QuantidadeDeVidas = (tam/2) + 1;
	}

	public void diminui() {
		this.QuantidadeDeVidas--;
	}

	public boolean verificaVida(Palavras plvrs) {
		if (this.QuantidadeDeVidas <= 0) {
			System.out.println("Você perdeu :(");
			System.out.println("A palavra era: " + plvrs.palavraSelecionada);
			return true;
		}
		return false;
	}
}
