package br.ufba.myterminal.examples;

import com.googlecode.lanterna.TextColor;

import br.ufba.myterminal.Menu;
import br.ufba.myterminal.MyTerminal;

/**
 * Aplicação de exemplo simples usando MyTerminal e o componente de menu.
 * @author rodrigorgs
 */
public class MyTerminalAppMenu {

	public static void main(String[] args) {
		// Cria terminal
		MyTerminal terminal = new MyTerminal();
		// Define posição para o menu
		terminal.setPosition(10, 5);

		// Cria menu com quatro itens
		Menu menu = new Menu(terminal, new String[] {"Maçã", "Caju", "Laranja", "Goiaba"});
		// Loop do menu: lê teclas e redesenha menu repetidamente, até
		// usuário escolher uma das opções do menu
		Object selected = menu.loop();
		
		// Após usuário selecionar uma opção, exibe mensagem
		terminal.setPosition(0, 0);
		terminal.setForegroundColor(TextColor.ANSI.YELLOW);
		terminal.print("Você escolheu " + selected);
		terminal.draw();
		terminal.readKey();
		System.exit(0);
	}
}
