package br.ufba.myterminal;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Classe que abstrai um terminal na biblioteca Lanterna, disponível em
 * https://github.com/mabe02/lanterna
 *  
 * @author rodrigorgs
 *
 */
public class MyTerminal {
	private Terminal terminal;
	private Screen screen;
	private TextGraphics text;
	private KeyStroke keyStroke;
	private int x = 0;
	private int y = 0;
	private boolean shouldCloseOnEscape = true;

	private void init(Terminal terminal) {
		try {
			this.terminal = terminal;
			terminal.setCursorVisible(false);
			
			screen = new TerminalScreen(terminal);
			screen.startScreen();
			screen.setCursorPosition(null);
			text = screen.newTextGraphics();
		} catch (IOException e) {
			System.err.println("Erro ao criar terminal.");
			e.printStackTrace();
			System.exit(1);
		}
	}
	public MyTerminal() {
		try {
			DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
			terminal = defaultTerminalFactory.createTerminal();
			init(terminal);
		} catch (IOException e) {
			System.err.println("Erro ao criar terminal.");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public MyTerminal(Terminal terminal) {
		init(terminal);
	}
	
	/**
	 * Apaga a tela.
	 */
	public void clear() {
		screen.clear();
		x = 0;
		y = 0;
	}
	
	/**
	 * Desenha na tela. Todas as operações de escrita no terminal não ficam
	 * visíveis até que este método seja chamado.
	 */
	public void draw() {
		try {
			screen.refresh();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Aguarda até o usuário digitar alguma tecla.
	 * @return informações sobre a tecla pressionada
	 */
	public KeyStroke readKey() {
		try {
			keyStroke = terminal.readInput();
			if (shouldCloseOnEscape && keyStroke.getKeyType() == KeyType.Escape) {
				System.exit(0);
			}
			return keyStroke;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return código da última tecla pressionada pelo
	 * usuário
	 */
	public KeyType getKeyType() {
		return keyStroke == null ? null : keyStroke.getKeyType();
	}
	/**
	 * @return informações sobre a última tecla pressionada pelo
	 * usuário
	 */
	public KeyStroke getKey() {
		return keyStroke;
	}
	/**
	 * Caso a última tecla pressionada pelo usuário tenha sido uma tecla
	 * direcional, retorna a direção da tecla (UP, DOWN, LEFT, RIGHT). 
	 * @return a direção da tecla
	 */
	public Direction getDirection() {
		if (keyStroke == null) {
			return null;
		}
		
		KeyType type = keyStroke.getKeyType();
		switch (type) {
		case ArrowLeft: return Direction.LEFT;
		case ArrowRight: return Direction.RIGHT;
		case ArrowUp: return Direction.UP;
		case ArrowDown: return Direction.DOWN;
		default: return null;
		}
	}
	
	/**
	 * Escreve uma string na tela e move cursor para a próxima posição
	 * após o fim da string.
	 * @param o objeto cuja representação em String será escrita
	 */
	public void print(Object o) {
		String s = "" + o;
		text.putString(x, y, s);
		x += s.length();
	}
	/**
	 * Escreve uma string na tela e move cursor para a linha abaixo,
	 * mantendo a coluna da primeira letra da string
	 * @param o objeto cuja representação em String será escrita
	 */
	public void println(Object o) {
		String s = "" + o;
		text.putString(x, y, s);
		y++;
	}

	/**
	 * Obtém informações sobre o caractere em determinada posição
	 * do terminal.
	 * @param x coordenada X da posição
	 * @param y coordenada Y da posição
	 * @return o caracter, representado como String
	 */
	public TextCharacter getCharacterInfo(int x, int y) {
		return screen.getFrontCharacter(x, y);
	}
	
	/**
	 * Obtém o caractere em determinada posição do terminal.
	 * @param x coordenada X da posição
	 * @param y coordenada Y da posição
	 * @return o caracter, representado como String
	 */
	public String getCharacter(int x, int y) {
		TextCharacter tc = getCharacterInfo(x, y);
		return tc == null ? "" : tc.getCharacterString();
	}
	/**
	 * Define cor do texto.
	 * @param color cor do texto
	 */
	public void setForegroundColor(TextColor color) {
		text.setForegroundColor(color);
	}
	/**
	 * Define cor do fundo do texto.
	 * @param color cor do fundo do texto
	 */
	public void setBackgroundColor(TextColor color) {
		text.setBackgroundColor(color);
	}
	/**
	 * @return cor do texto
	 */
	public TextColor getForegroundColor() {
		return text.getForegroundColor();
	}
	/**
	 * @return cor do fundo do texto
	 */
	public TextColor getBackgroundColor() {
		return text.getBackgroundColor();
	}
	
	/**
	 * @return coordenada X do cursor
	 */
	public int getX() {
		return x;
	}
	/**
	 * Define a coordenada X do cursor
	 * @param x coordenada X do cursor
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return coordenada Y do cursor
	 */
	public int getY() {
		return y;
	}
	/**
	 * Define a coordenada Y do cursor
	 * @param y coordenada Y do cursor
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Define a posição do cursor
	 * @param x coordenada X do cursor
	 * @param y coordenada Y do cursor
	 */
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setPosition(TerminalPosition position) {
		setX(position.getColumn());
		setY(position.getRow());
	}

	public TerminalPosition getPosition() {
		return new TerminalPosition(x, y);
	}

	
	/**
	 * Indica se o programa será fechado quando o usuário pressionar
	 * a tecla Esc.
	 * @return true se o programa será fechado quando o usuário pressionar a
	 * tecla Esc; false caso contrário
	 */
	public boolean isShouldCloseOnEscape() {
		return shouldCloseOnEscape;
	}
	/**
	 * Define se o programa será fechado quando o usuário pressionar
	 * a tecla Esc.
	 * @param shouldCloseOnEscape true se o programa deverá ser fechado quando
	 * o usuário pressionar a tecla Esc; false caso contrário
	 */
	public void setShouldCloseOnEscape(boolean shouldCloseOnEscape) {
		this.shouldCloseOnEscape = shouldCloseOnEscape;
	}
	
	/**
	 * Emite um sinal sonoro (bipe). 
	 */
	public void beep() {
		try {
			terminal.bell();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
