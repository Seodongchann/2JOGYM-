package main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import memberShip.MemberShipGUIApp;
import members.MemberGUIApp;
import pt.PTGUIApp;
import trainer.TrainerGUIApp;

public class Admins extends JFrame {
	private MemberGUIApp guiApp;
	private MemberShipGUIApp shipguiApp;
	private TrainerGUIApp trainerguiApp;
	private PTGUIApp ptguiApp;
	public Admins() {
		guiApp = new MemberGUIApp(this);
		shipguiApp = new MemberShipGUIApp(this);
		trainerguiApp = new TrainerGUIApp(this);
		ptguiApp= new PTGUIApp(this);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add(guiApp, "회원관리");
		tabbedPane.add(shipguiApp, "회원권 구매");
		tabbedPane.add(trainerguiApp, "트레이너 관리");
		tabbedPane.add(ptguiApp,"pt권 구매");

		add(tabbedPane);
		setSize(1350, 850);
	}

	public static void main(String[] args) {
		new Admins().setVisible(true);
	}
}
