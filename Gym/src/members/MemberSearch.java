package members;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
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

@Data
public class MemberSearch extends JPanel {
	private MemberDAOImpl dao = new MemberDAOImpl();
	private List<Member> list;
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

	public MemberSearch(MemberGUIApp guiApp) {
		memberSearchPnl = new JPanel();
		memberSearchPnl.setLayout(null);
		memberSearchPnl.setBounds(50, 50, 700, 100);

		memberSearchResult = new JPanel();
		memberSearchResult.setLayout(null);
		memberSearchResult.setBounds(50, 150, 700, 450);

		searchLbl = new JLabel("이름");
		serachTf = new JTextField();
		searchBtn = new JButton("검색");
		searchAllBtn = new JButton("전체");

		searchLbl.setBounds(0, 0, 50, 20);
		serachTf.setBounds(70, 0, 100, 20);
		searchBtn.setBounds(180, 0, 60, 20);
		searchAllBtn.setBounds(260, 0, 60, 20);

		memberSearchPnl.add(searchLbl);
		memberSearchPnl.add(serachTf);
		memberSearchPnl.add(searchBtn);
		memberSearchPnl.add(searchAllBtn);

		list = dao.memberSelectAll();

		vector = new Vector<String>();
		vector.add("번호");
		vector.addElement("이름");
		vector.add("휴대전화");
		vector.addElement("성별");
		vector.addElement("생년월일");
		vector.addElement("주소");
		vector.add("등록코드");

		// defaultTableModel 생성
		DefaultTableModel model;
		model = new DefaultTableModel(vector, 0) {
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

				Member member = dao.memberSelectCode(enroll);
				if (member != null) {
					guiApp.getMm().getRgbtn().setEnabled(false);
					guiApp.getMm().getRetouchbtn().setEnabled(true);
					guiApp.getMm().getDeletebtn().setEnabled(true);
				}

				guiApp.getMm().getNametf().setText(member.getName());
				if (member.getGender().equals("남성")) {
					guiApp.getMm().getRd().setSelected(true);
				} else {
					guiApp.getMm().getRd2().setSelected(true);
				}
				guiApp.getMm().getPhonetf().setText(String.valueOf(member.getPhone()));
				guiApp.getMm().getBirthtf().setText(member.getBirth());
				guiApp.getMm().getAdtf().setText(member.getAddress());
				guiApp.getMm().getMemberNumtf().setText(String.valueOf(member.getEnroll_code()));
				// System.out.println(member.getMember_image());
				inde = new EncodeDecode();
				if (member.getMember_image() != null) {
					guiApp.getMm().setImag(new ImageIcon(inde.decode(member.getMember_image())));
					guiApp.getMm().getImlbl().setIcon(guiApp.getMm().getImag());

				} else {
					guiApp.getMm().getImlbl().setIcon(new ImageIcon());
				}

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
		searchBtn.addActionListener(guiApp);
		searchAllBtn.addActionListener(guiApp);
	}
	
}
