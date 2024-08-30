package main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import memberShip.MemberShipGUIApp;
import members.MemberGUIApp;

public class Admins extends JFrame {
	private MemberGUIApp guiApp;
	private MemberShipGUIApp shipguiApp;

	public Admins() {
		guiApp = new MemberGUIApp(this);
		shipguiApp = new MemberShipGUIApp(this);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add(guiApp, "회원관리");
		tabbedPane.add(shipguiApp, "회원권 구매");

		add(tabbedPane);
		setSize(1350,850);
	}

	public static void main(String[] args) {
		new Admins().setVisible(true);
	}
}
