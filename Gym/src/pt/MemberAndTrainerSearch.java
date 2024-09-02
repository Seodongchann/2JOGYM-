package pt;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import files.EncodeDecode;
import lombok.Data;
import members.Member;
import members.MemberDAOImpl;
import trainer.Trainer;
import trainer.TrainerDAOImpl;

@Data
public class MemberAndTrainerSearch extends JPanel {
	private MemberDAOImpl mdao = new MemberDAOImpl();
	private TrainerDAOImpl tdao = new TrainerDAOImpl();
	private List<Member> member;
	private List<Trainer> trainer;
	private Vector<String> vector;
	private Vector<String> vector2;
	private JTable table;
	private JScrollPane scroll;
	private JPanel allPnl;
	private JPanel mPnl;
	private JPanel tPnl;
	private JLabel nameLbl;
	private JTextField nameTf;
	private JButton searchNameBtn;
	private JLabel tnameLbl;
	private JTextField tnameTf;
	private JButton tsearchNameBtn;
	private JTable table2;
	private JScrollPane scroll2;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JButton searchNameAllbtn;
	private JButton tsearchNameAllBtn;

	public MemberAndTrainerSearch(PTGUIApp guiApp) {
		allPnl = new JPanel();
		allPnl.setLayout(null);
		allPnl.setBounds(10, 10, 850, 450);
		mPnl = new JPanel();
		mPnl.setLayout(null);
		mPnl.setBounds(10, 0, 800, 180);
		tPnl = new JPanel();
		tPnl.setLayout(null);
		tPnl.setBounds(10, 200, 800, 180);

		nameLbl = new JLabel("이름");
		nameTf = new JTextField();
		searchNameBtn = new JButton("회원검색");
		searchNameAllbtn = new JButton("회원전체");
		nameLbl.setBounds(10, 30, 60, 20);
		nameTf.setBounds(80, 30, 100, 20);
		searchNameBtn.setBounds(200, 30, 100, 20);
		searchNameAllbtn.setBounds(320, 30, 150, 20);
		mPnl.add(nameLbl);
		mPnl.add(nameTf);
		mPnl.add(searchNameBtn);
		mPnl.add(searchNameAllbtn);

		member = mdao.memberSelectAll();

		vector = new Vector<String>();
		vector.add("번호");
		vector.addElement("이름");
		vector.add("휴대전화");
		vector.addElement("성별");
		vector.addElement("생년월일");
		vector.addElement("주소");
		vector.add("등록코드");

		model = new DefaultTableModel(vector, 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Member m : member) {
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

		table = new JTable(model);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub=
				int row = table.getSelectedRow();
				TableModel data = table.getModel();
				String enroll_code = (String) data.getValueAt(row, 6);

				int enroll = Integer.parseInt(enroll_code);

				Member member = mdao.memberSelectCode(enroll);
				guiApp.getPi().getNameTf().setText(member.getName());
				guiApp.getPi().getBirthTf().setText(member.getBirth());
				guiApp.getPi().getEnrollTf().setText(String.valueOf(member.getEnroll_code()));

				revalidate();
				repaint();
			}
		});
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 80, 650, 90);
		mPnl.add(scroll);

		tnameLbl = new JLabel("이름");
		tnameTf = new JTextField();
		tsearchNameBtn = new JButton("트레이너검색");
		tsearchNameAllBtn = new JButton("트레이너전체");
		tnameLbl.setBounds(10, 30, 60, 20);
		tnameTf.setBounds(80, 30, 100, 20);
		tsearchNameBtn.setBounds(200, 30, 100, 20);
		tsearchNameAllBtn.setBounds(320, 30, 150, 20);
		trainer = tdao.TrainerselectAll();
		vector2 = new Vector<String>();
		vector2.add("번호");
		vector2.addElement("이름");
		vector2.add("전화번호");
		vector2.addElement("성별");
		vector2.addElement("생년월일");
		vector2.addElement("주소");
		vector2.add("시작일");

		model2 = new DefaultTableModel(vector2, 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Trainer t : trainer) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(t.getId()));
			v.add(t.getName());
			v.add(String.valueOf(t.getPhone()));
			v.add(t.getGender());
			v.add(t.getBirth());
			v.add(t.getAddress());
			v.add(String.valueOf(t.getStart_date()));
			model2.addRow(v);
		}

		table2 = new JTable(model2);
		table2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub=
				int row = table2.getSelectedRow();
				TableModel data = table2.getModel();
				String trainer_code = (String) data.getValueAt(row, 0);

				int trainer_id = Integer.parseInt(trainer_code);
				Trainer tr = tdao.trainerSelectId(trainer_id);

				// System.out.println(tr.getName());
				guiApp.getPi().getTrainerTf().setText(tr.getName());
				guiApp.getPi().getTrainerIdTf().setText(String.valueOf(tr.getId()));

				revalidate();
				repaint();
			}
		});
		scroll2 = new JScrollPane(table2);
		scroll2.setBounds(10, 80, 650, 90);
		tPnl.add(scroll2);

		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), " 회원 리스트");
		mPnl.setBorder(oneTb);

		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.gray), " 트레이너 리스트");
		tPnl.setBorder(twoTb);

		tPnl.add(tnameLbl);
		tPnl.add(tnameTf);
		tPnl.add(tsearchNameBtn);
		tPnl.add(tsearchNameAllBtn);

		allPnl.add(mPnl);
		allPnl.add(tPnl);

		add(allPnl);
		setLayout(null);
		setSize(900, 450);
		searchNameBtn.addActionListener(guiApp);
		searchNameAllbtn.addActionListener(guiApp);
		tsearchNameBtn.addActionListener(guiApp);
		tsearchNameAllBtn.addActionListener(guiApp);
	}

}
