package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GameOverWindow extends JFrame{
	
	private JButton btnRestart;
	private JButton btnExit;
	private JLabel lblNewLabel;
	private JFrame prevGUI;
	
	/**
	 * Crea una nueva ventana de fin de juego.
	 * @param prevGUI La ventana del juego que terminó.
	 */
	public GameOverWindow(JFrame prevGUI) {
		this.prevGUI = prevGUI;
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new java.awt.Color(0,0,41));
		setBounds(100, 100, 525, 450);
		this.setUndecorated(true);
		
		this.setLocationRelativeTo(prevGUI);
		
		initialize();
	}
	
	/**
	 * Crea todos los componentes de la GUI.
	 */
	private void initialize() {
		btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				restartGame();
			}
			
		});
		btnRestart.setForeground(new java.awt.Color(200, 200, 200));
		btnRestart.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnRestart.setBackground(new java.awt.Color(0, 0, 100));
		btnRestart.setBounds(25, 350, 225, 75);
		getContentPane().add(btnRestart);
		
		btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
			
		});
		btnExit.setForeground(new java.awt.Color(200, 200, 200));
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnExit.setBounds(275, 350, 225, 75);
		btnExit.setBackground(new java.awt.Color(0, 0, 100));
		getContentPane().add(btnExit);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GameOverWindow.class.getResource("/gui/img/backgrounds/bgGameOver.png")));
		lblNewLabel.setBounds(0, 0, 525, 450);
		getContentPane().add(lblNewLabel);
	}
	
	/**
	 * Termina la ejecucion del juego.
	 */
	protected void exitGame() {
		System.exit(0);
	}
	
	/**
	 * Reinicia el juego, cierra la ventana del juego anterior y luego la del game over.
	 */
	protected void restartGame(){
		prevGUI.dispose();
		Game game = new Game();
		GUI ventana = game.getGUI();
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		game.start();
		
		this.dispose();
	}
}
