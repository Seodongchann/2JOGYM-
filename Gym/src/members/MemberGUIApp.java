package members;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class MemberGUIApp extends JFrame implements ActionListener {
	private EncodeDecode inde;
	private MemberInput mm;
	private MemberSearch ms;
	private MemberDAO mDAO = new MemberDAOImpl();

	public MemberGUIApp() {
		super("사용자");
		setLayout(null);

		mm = new MemberInput(this);
		ms = new MemberSearch(this);

		add(mm);
		add(ms);

		mm.setLocation(50, 50);
		ms.setLocation(500, 50);

		setSize(1350, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand();
		// 회원 등록 기능
		if (commend.equals("등록")) {
			String name = mm.getNametf().getText();
			int phone = Integer.parseInt(mm.getPhonetf().getText());
			String gender = null;
			if (mm.getRd().isSelected()) {
				gender = mm.getRd().getText();
			} else {
				gender = mm.getRd2().getText();
			}
			String birth = mm.getBirthtf().getText();
			String address = mm.getAdtf().getText();
			int enroll_code = Integer.parseInt(mm.getMemberNumtf().getText());
			inde = new EncodeDecode();

			String image;
			if (mm.getFile() == null) {
				image = "";
			} else {
				image = inde.encodeImage(mm.getFile());
			}
			Member member = new Member(name, phone, gender, birth, address, enroll_code, image);
			int result = mDAO.memberInsert(member);

			if (result == 1) {
				mm.getNametf().setText("");
				mm.getPhonetf().setText("");
				mm.getBirthtf().setText("");
				mm.getAdtf().setText("");
				mm.getMemberNumtf().setText("");
			}

		} else if (commend.equals("수정")) {
			String name = mm.getNametf().getText();
			int phone = Integer.parseInt(mm.getPhonetf().getText());
			String gender = null;
			if (mm.getRd().isSelected()) {
				gender = mm.getRd().getText();
			} else {
				gender = mm.getRd2().getText();
			}
			String birth = mm.getBirthtf().getText();
			String address = mm.getAdtf().getText();
			int enroll_code = Integer.parseInt(mm.getMemberNumtf().getText());
			inde = new EncodeDecode();

			String image;
			if (mm.getFile() == null) {
				image = "";
			} else {
				image = inde.encodeImage(mm.getFile());
			}
			Member member = new Member(name, phone, gender, birth, address, enroll_code, image);
			int result = mDAO.memberUpdate(member);
			if (result == 1) {
				mm.getNametf().setText("");
				mm.getPhonetf().setText("");
				mm.getBirthtf().setText("");
				mm.getAdtf().setText("");
				mm.getMemberNumtf().setText("");
			}
		}
	}

	public static void main(String[] args) {
		new MemberGUIApp().setVisible(true);
	}

}
