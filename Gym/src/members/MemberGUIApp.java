package members;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import lombok.Data;
import main.Admins;

@Data
public class MemberGUIApp extends JPanel implements ActionListener {
	private EncodeDecode inde;
	private MemberInput mm;
	private MemberSearch ms;
	private MemberDAO mDAO = new MemberDAOImpl();
	private List<Member> list;

	public MemberGUIApp(Admins ad) {
		setLayout(null);

		mm = new MemberInput(this);
		ms = new MemberSearch(this);

		add(mm);
		add(ms);

		mm.setLocation(50, 50);
		ms.setLocation(500, 50);

		setSize(1350, 800);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand();
		// 회원 등록 기능
		if (commend.equals("등록")) {
			String name = mm.getNametf().getText();
			String phone = mm.getPhonetf().getText();
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
			textClear(result);
			searchAll();
		} else if (commend.equals("수정")) {// 회원 수정 기능
			String name = mm.getNametf().getText();
			String phone = mm.getPhonetf().getText();
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
			textClear(result);
			searchAll();
		} else if (commend.equals("삭제")) { // 회원 삭제 기능
			int enroll_code = Integer.parseInt(mm.getMemberNumtf().getText());
			int result = mDAO.memberDelete(enroll_code);
			textClear(result);
			searchAll();
		} else if (commend.equals("초기화")) { // 회원 입력 정보 초기화 기능
			textClear(1);
			mm.getRgbtn().setEnabled(true);
			mm.getRetouchbtn().setEnabled(false);
			mm.getDeletebtn().setEnabled(false);
		} else if (commend.equals("검색")) { // 회원 검색
			searchName();
			revalidate();
			repaint();
		} else if (commend.equals("전체")) { // 전체 회원 검색
			searchAll();
			revalidate();
			repaint();
		}
	}

	private void searchName() {
		String name = ms.getSerachTf().getText();
		list = mDAO.memberSelectName(name);

		DefaultTableModel model;
		model = new DefaultTableModel(ms.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Member m : list) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(m.getId()));
			v.add(m.getName());
			v.add(String.valueOf(m.getPhone()));
			v.add(m.getGender());
			v.add(m.getBirth());
			v.add(m.getAddress());
			v.add(String.valueOf(m.getEnroll_code()));
			model.addRow(v);
		}

		ms.getTable().setModel(model);
		ms.getScroll().setViewportView(ms.getTable());

		ms.getSerachTf().setText("");
	}

	private void searchAll() {
		list = mDAO.memberSelectAll();

		DefaultTableModel model;
		model = new DefaultTableModel(ms.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Member m : list) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(m.getId()));
			v.add(m.getName());
			v.add(String.valueOf(m.getPhone()));
			v.add(m.getGender());
			v.add(m.getBirth());
			v.add(m.getAddress());
			v.add(String.valueOf(m.getEnroll_code()));
			model.addRow(v);
		}

		ms.getTable().setModel(model);
		ms.getScroll().setViewportView(ms.getTable());
	}

	// 텍스트필드 초기화 시켜주기
	public void textClear(int result) {
		if (result == 1) {
			mm.getNametf().setText("");
			if (mm.getRd2().isSelected()) {
				mm.getRd2().setSelected(false);
				mm.getRd().setSelected(true);
			}
			mm.getPhonetf().setText("");
			mm.getBirthtf().setText("");
			mm.getAdtf().setText("");
			mm.getMemberNumtf().setText("");
			mm.getImlbl().setIcon(new ImageIcon());
		}
	}


}
