package br.ufba.myterminal.examples;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import br.ufba.myterminal.MyTerminal;

/**
 * Aplicação de exemplo simples usando MyTerminal.
 * @author rodrigorgs
 */
public class MyTerminalAppHello {

	public static void main(String[] args) {
		// Abre janela do terminal e configura cores
		MyTerminal terminal = new MyTerminal();
		terminal.setForegroundColor(TextColor.ANSI.YELLOW);
		terminal.setBackgroundColor(TextColor.ANSI.BLUE);
		
		// Define posição inicial do texto
		int x = 3;
		int y = 6;
		while (true) {
			// Apaga tudo
			terminal.clear();
			// Posiciona o cursor
			terminal.setPosition(x, y);
			// Escreve na tela
			terminal.println("Alô mundo!");
			terminal.println("Use as setas");
			terminal.println("para mover");
			terminal.println("este texto");
			// Mostra tudo o que foi escrito
			terminal.draw();
			
			// Pausa até usuário digitar alguma tecla
			KeyStroke key = terminal.readKey();
			// Altera posição do texto de acordo com teclas direcionais
			if (key.getKeyType() == KeyType.ArrowLeft) {
				x--;
			} else if (key.getKeyType() == KeyType.ArrowRight) {
				x++;
			} else if (key.getKeyType() == KeyType.ArrowUp) {
				y--;
			} else if (key.getKeyType() == KeyType.ArrowDown) {
				y++;
			}
		}
	}
}
