package br.ufba.myterminal;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;

public class Menu {
	private MyTerminal terminal;
	private Object[] items;
	private int index = 0;
	private TextColor fgColor;
	private TextColor bgColor;
	private TextColor selectionFgColor;
	private TextColor selectionBgColor;
	
	
	public Menu(MyTerminal terminal, Object[] items, TextColor fgColor, TextColor bgColor, TextColor selectionFgColor, TextColor selectionBgColor) {
		this.items = items.clone();
		this.terminal = terminal;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
		this.selectionBgColor = selectionBgColor;
		this.selectionFgColor = selectionFgColor;
	}

	public Menu(MyTerminal terminal, Object[] items) {
		this(terminal, items, TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, TextColor.ANSI.BLACK, TextColor.ANSI.CYAN);
	}
	
	public void changeSelection(Direction direction) {
		if (direction == Direction.DOWN) {
			index = (index + 1) % items.length;
		} else if (direction == Direction.UP) {
			index = (index + items.length - 1) % items.length;
		}
	}
	
	public void draw() {
		TextColor oldFgColor = terminal.getForegroundColor();
		TextColor oldBgColor = terminal.getBackgroundColor();
		int startX = terminal.getX();
		int startY = terminal.getY();
		
		for (int i = 0; i < items.length; i++) {
			if (i == index) {
				terminal.setForegroundColor(selectionFgColor);
				terminal.setBackgroundColor(selectionBgColor);
			} else {
				terminal.setForegroundColor(fgColor);
				terminal.setBackgroundColor(bgColor);
			}
			terminal.println("" + items[i]);
		}
		
		terminal.setX(startX);
		terminal.setY(startY);
		terminal.setForegroundColor(oldFgColor);
		terminal.setBackgroundColor(oldBgColor);
	}
	
	public Object loop() {
		do {
			draw();
			terminal.draw();
			
			terminal.readKey();
			changeSelection(terminal.getDirection());
		} while (terminal.getKeyType() != KeyType.Enter);

		return getSelectedItem();
	}
	
	public Object getSelectedItem() {
		return items[index];
	}
	
	public void setSelectedIndex(int i) {
		if (i >= 0 && i < items.length && i != index) {
			index = i;
			draw();
		}
	}
}
