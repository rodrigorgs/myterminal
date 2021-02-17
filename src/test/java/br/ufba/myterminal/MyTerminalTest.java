package br.ufba.myterminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;

public class MyTerminalTest {
	DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
	Terminal lanternaTerminal;
	MyTerminal terminal;
	
	@Before
	public void setup() {
		lanternaTerminal = Mockito.spy(DefaultVirtualTerminal.class);
		terminal = new MyTerminal(lanternaTerminal);
	}
	
	@Test
	public void printWritesStringOnScreen() {
		terminal.setPosition(3, 5);
		terminal.print("X");
		terminal.draw();
		assertEquals("X", terminal.getCharacter(3, 5));
	}
	
	@Test
	public void printMovesCursor() {
		terminal.setPosition(3, 5);
		terminal.print("XYZ");
		terminal.draw();
		assertEquals(6, terminal.getX());
		assertEquals(5, terminal.getY());
	}
	
	@Test
	public void screenDoesntChangeUntilDraw() {
		terminal.setPosition(3, 5);
		terminal.print("X");
		assertNotEquals("X", terminal.getCharacter(3, 5));
	}
	
	@Test
	public void getKeyReturnLastKey() throws IOException {
		KeyStroke keyStroke = new KeyStroke('r', false, false);
		Mockito.doReturn(keyStroke).when(lanternaTerminal).readInput();
		
		terminal.readKey();
		
		assertEquals(Character.valueOf('r'), terminal.getKey().getCharacter());
	}
}
