package pt;

import javax.swing.JFrame;

public class PTGUIApp extends JFrame {
	public PTGUIApp() {

		setSize(1350, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new PTGUIApp().setVisible(true);
	}
}
