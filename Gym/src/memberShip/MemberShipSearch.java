package memberShip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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

@Data
public class MemberShipSearch extends JPanel {
	private MemberShipDAO dao = new MemberShipDAOImpl();
	private List<MembershipJoinMember> list;
	private MembershipJoinMember memberJoin;
	private JScrollPane scroll;
	private JPanel memberSearchPnl;
	private JPanel memberSearchResult;
	private JLabel searchLbl;
	private JTextField serachTf;
	private JButton searchBtn;
	private JButton searchAllBtn;
	private Vector<String> vector;
	private JTable table;
	private EncodeDecode inde;

	public MemberShipSearch(MemberShipGUIApp guiApp) {
		memberSearchPnl = new JPanel();
		memberSearchPnl.setLayout(null);
		memberSearchPnl.setBounds(50, 50, 700, 100);

		memberSearchResult = new JPanel();
		memberSearchResult.setLayout(null);
		memberSearchResult.setBounds(50, 150, 700, 450);

		searchLbl = new JLabel("등록코드");
		serachTf = new JTextField();
		searchBtn = new JButton("검색");
		searchBtn.addActionListener(guiApp);
		searchAllBtn = new JButton("전체");
		searchAllBtn.addActionListener(guiApp);

		searchLbl.setBounds(0, 0, 50, 20);
		serachTf.setBounds(70, 0, 100, 20);
		searchBtn.setBounds(180, 0, 60, 20);
		searchAllBtn.setBounds(260, 0, 60, 20);

		memberSearchPnl.add(searchLbl);
		memberSearchPnl.add(serachTf);
		memberSearchPnl.add(searchBtn);
		memberSearchPnl.add(searchAllBtn);

		list = dao.joinMembers();

		vector = new Vector<String>();
		vector.add("이름");
		vector.addElement("휴대전화");
		vector.add("성별");
		vector.addElement("생년월일");
		vector.addElement("주소");
		vector.addElement("등록코드");
		vector.add("시작일");
		vector.add("종료일");
		vector.add("등록일");

		// defaultTableModel 생성
		DefaultTableModel model;
		model = new DefaultTableModel(vector, 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (MembershipJoinMember m : list) {
			Vector<String> v = new Vector<String>();
			v.add(m.getName());
			v.add(m.getPhone());
			v.add(m.getGender());
			v.add(m.getBirth());
			v.add(m.getAddress());
			v.add(String.valueOf(m.getEnroll_code()));
			v.add(String.valueOf(m.getMembership_StartDate()));
			v.add(String.valueOf(m.getMembership_EndDate()));
			v.add(String.valueOf(m.getMembership_EnrollDate()));

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
				String enroll_code = (String) data.getValueAt(row, 5);
				Date start_date = java.sql.Date.valueOf((String) data.getValueAt(row, 6));

				int enroll = Integer.parseInt(enroll_code);

				MembershipJoinMember member = dao.selectMembership(enroll, start_date);
//				if (member != null) {
//					guiApp.getMm().getRgbtn().setEnabled(false);
//					guiApp.getMm().getRetouchbtn().setEnabled(true);
//					guiApp.getMm().getDeletebtn().setEnabled(true);
//				}

				guiApp.getMi().getNameTf().setText(member.getName());
				guiApp.getMi().getBirthTf().setText(member.getBirth());
				guiApp.getMi().getPhoneTf().setText(String.valueOf(member.getPhone()));
				guiApp.getMi().getEnroll_codeTf().setText(String.valueOf(member.getEnroll_code()));
				guiApp.getMi().getAddressTf().setText(member.getAddress());
				// System.out.println(member.getMember_image());
//				inde = new EncodeDecode();
//				if (member.getMember_image() != null) {
//					guiApp.getMm().setImag(new ImageIcon(inde.decode(member.getMember_image())));
//					guiApp.getMm().getImlbl().setIcon(guiApp.getMm().getImag());
//
//				} else {
//					guiApp.getMm().getImlbl().setIcon(new ImageIcon());
//				}

				revalidate();
				repaint();
			}
		});
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 700, 400);
		memberSearchResult.add(scroll);

		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.gray), " 사용자 리스트");
		setBorder(twoTb);

		add(memberSearchPnl);
		add(memberSearchResult);
		setLayout(null);
		setSize(800, 700);
	}

}
