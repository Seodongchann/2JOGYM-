package trainer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import lombok.Data;
import main.Admins;
import members.Member;

@Data
public class TrainerGUIApp extends JPanel implements ActionListener {
	private EncodeDecode inde;
	private TrainerInput ti;
	private TrainerSearch ts;
	private TrainerDAO tDAO = new TrainerDAOImpl();
	private Date start_date;
	private List<Trainer> list;

	public TrainerGUIApp(Admins admin) {
		setLayout(null);

		ti = new TrainerInput(this);
		ts = new TrainerSearch(this);

		add(ti);
		add(ts);

		ti.setLocation(50, 50);
		ts.setLocation(500, 50);

		setSize(1350, 800);
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

			textClear(result);
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
			int tr_code = Integer.parseInt(ti.getTrNumtf().getText());
			inde = new EncodeDecode();

			String image;
			if (ti.getFile() == null) {
				image = "";
			} else {
				image = inde.encodeImage(ti.getFile());
			}
			Trainer trainer = new Trainer(tr_code, name, phone, gender, birth, address);
			int result = tDAO.TrainerUpdate(trainer);
			textClear(result);
		} else if (commend.equals("삭제")) {
			int trainer_id = Integer.parseInt(ti.getTrNumtf().getText());
			int result = tDAO.deleteTrainer(trainer_id);
			textClear(result);
		} else if (commend.equals("초기화")) {
			textClear(1);
			ti.getRgbtn().setEnabled(true);
			ti.getRetouchbtn().setEnabled(false);
			ti.getDeletebtn().setEnabled(false);
		} else if (commend.equals("검색")) {
			searchName();
			revalidate();
			repaint();
		} else if (commend.equals("전체")) {
			searchAll();
			revalidate();
			repaint();
		}
	}

	public void textClear(int result) {
		if (result == 1) {
			ti.getNametf().setText("");
			if (ti.getRd2().isSelected()) {
				ti.getRd2().setSelected(false);
				ti.getRd().setSelected(true);
			}
			ti.getTrNumlbl().setVisible(false);
			ti.getTrNumtf().setText("");
			ti.getTrNumtf().setVisible(false);
			ti.getBirthtf().setText("");
			ti.getPhonetf().setText("");
			ti.getAdtf().setText("");
			if (!ti.getRd().isSelected()) {
				ti.getRd().setSelected(true);
			}
		}

	}

	private void searchName() {
		String name = ts.getSerachTf().getText();
		list = tDAO.trainerSelectName(name);

		DefaultTableModel model;
		model = new DefaultTableModel(ts.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};
		for (Trainer t : list) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(t.getId()));
			v.add(t.getName());
			v.add(String.valueOf(t.getPhone()));
			v.add(t.getGender());
			v.add(t.getBirth());
			v.add(t.getAddress());
			// v.add(String.valueOf(t.getEnroll_code()));
			model.addRow(v);
		}

		ts.getTable().setModel(model);
		ts.getScroll().setViewportView(ts.getTable());

		ts.getSerachTf().setText("");
	}

	private void searchAll() {
		list = tDAO.TrainerselectAll();

		// vector.add("등록코드");

		// defaultTableModel 생성
		DefaultTableModel model;
		model = new DefaultTableModel(ts.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Trainer t : list) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(t.getId()));
			v.add(t.getName());
			v.add(String.valueOf(t.getPhone()));
			v.add(t.getGender());
			v.add(t.getBirth());
			v.add(t.getAddress());
			// v.add(String.valueOf(t.getEnroll_code()));
			model.addRow(v);
		}
		ts.getTable().setModel(model);
		ts.getScroll().setViewportView(ts.getTable());

	}

}
