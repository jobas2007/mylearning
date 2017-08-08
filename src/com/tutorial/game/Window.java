package com.tutorial.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Creates Window.
 * 
 * @author Arpit Singhai.
 *
 */
public class Window extends Canvas{

	private static final long serialVersionUID = -1413099016987259526L;
	
	
	public Window(final int width, final int height, final String title, final Game game) {
		JFrame frame=new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}

}
