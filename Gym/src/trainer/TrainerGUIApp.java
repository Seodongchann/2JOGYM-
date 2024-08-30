package trainer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import files.EncodeDecode;

public class TrainerGUIApp extends JFrame implements ActionListener {
	private EncodeDecode inde;
	private TrainerInput ti;
	private TrainerSearch ts;
	private TrainerDAO tDAO = new TrainerDAOImpl();
	private Date start_date;

	public TrainerGUIApp() {
		super("사용자");
		setLayout(null);

		ti = new TrainerInput(this);
		ts = new TrainerSearch(this);

		add(ti);
		add(ts);

		ti.setLocation(50, 50);
		ts.setLocation(500, 50);

		setSize(1350, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand();
		// 트레이너 등록 
		if (commend.equals("등록")) {
			String name = ti.getNametf().getText();
			String phone = ti.getPhonetf().getText();
			String gender = null;
			if (ti.getRd().isSelected()) {
				gender = ti.getRd().getText();
			} else {
				gender = ti.getRd2().getText();
			}
			String birth = ti.getBirthtf().getText();
			String address = ti.getAdtf().getText();


			inde = new EncodeDecode();

			String image;
			if (ti.getFile() == null) {
				image = "";
			} else {
				image = inde.encodeImage(ti.getFile());
			}
			Trainer trainer = new Trainer(name, phone, gender, birth, address);
			int result = tDAO.TrainerInsert(trainer);

			if (result == 1) {
				ti.getNametf().setText("");
				ti.getPhonetf().setText("");
				ti.getBirthtf().setText("");
				ti.getAdtf().setText("");
//				ti.getMemberNumtf().setText("");
			}

		} else if (commend.equals("수정")) {
			String name = ti.getNametf().getText();
			String phone = ti.getPhonetf().getText();
			String gender = null;
			if (ti.getRd().isSelected()) {
				gender = ti.getRd().getText();
			} else {
				gender = ti.getRd2().getText();
			}
			String birth = ti.getBirthtf().getText();
			String address = ti.getAdtf().getText();
//			int enroll_code = Integer.parseInt(ti.getMemberNumtf().getText());
			inde = new EncodeDecode();

			String image;
			if (ti.getFile() == null) {
				image = "";
			} else {
				image = inde.encodeImage(ti.getFile());
			}
			Trainer trainer = new Trainer(name, phone, gender, birth, address);
			int result = tDAO.TrainerUpdate(trainer);
			if (result == 1) {
				ti.getNametf().setText("");
				ti.getPhonetf().setText("");
				ti.getBirthtf().setText("");
				ti.getAdtf().setText("");
//				ti.getMemberNumtf().setText("");
			}
		}
	}

	public static void main(String[] args) {
		new TrainerGUIApp().setVisible(true);
	}

}
