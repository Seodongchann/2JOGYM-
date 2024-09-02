package pt;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

import lombok.Data;
import trainer.Trainer;

@Data
public class PTSearch extends JPanel {
	private PTDAOImpl pdao = new PTDAOImpl();
	private JScrollPane scroll;
	private List<PTMemberTrainer> pmt = new ArrayList<PTMemberTrainer>();
	private JPanel pnl;
	private JLabel nameLbl;
	private JTextField nameTf;
	private JButton searchBtn;
	private JTable table;
	private DefaultTableModel model;
	private PTMemberTrainer spmt = null;
	private PTInput input;
	private Vector<String> vector;

	public PTSearch(PTGUIApp guiApp) {
		pnl = new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(10, 50, 1220, 200);

		nameLbl = new JLabel("이름");
		nameTf = new JTextField();
		searchBtn = new JButton("PT검색");
		nameLbl.setBounds(10, 10, 60, 20);
		nameTf.setBounds(80, 10, 100, 20);
		searchBtn.setBounds(200, 10, 100, 20);

		pmt = pdao.selectJoinAll();
		vector = new Vector<String>();
		vector.add("번호");
		vector.addElement("회원이름");
		vector.add("회원 전화번호");
		vector.addElement("회원 성별");
		vector.addElement("회원생년월일");
		vector.addElement("회원 등록코드");
		vector.add("트레이너번호");
		vector.add("트레이너 이름");
		vector.add("트레이너 휴대전화");
		vector.add("트레이너 성별");
		vector.add("트레이너 생년월일");
		vector.add("pt 시간");

		model = new DefaultTableModel(vector, 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (PTMemberTrainer p : pmt) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(p.getPt_code()));
			v.add(p.getName());
			v.add(p.getMPhone());
			v.add(p.getGender());
			v.add(p.getBrith());
			v.add(String.valueOf(p.getEnroll_code()));
			v.add(String.valueOf(p.getT_id()));
			v.add(p.getTName());
			v.add(p.getTPhone());
			v.add(p.getTGender());
			v.add(p.getTBirth());
			v.add(String.valueOf(p.getPt_time()));
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
				int row = table.getSelectedRow();
				TableModel data = table.getModel();
				int id = Integer.parseInt((String) data.getValueAt(row, 0));
				spmt = pdao.selelctJoinID(id);
				guiApp.getPi().getNameTf().setText(spmt.getName());
				guiApp.getPi().getBirthTf().setText(spmt.getBrith());
				guiApp.getPi().getEnrollTf().setText(String.valueOf(spmt.getEnroll_code()));
				guiApp.getPi().getTrainerTf().setText(spmt.getTName());
				guiApp.getPi().getTrainerIdTf().setText(String.valueOf(spmt.getT_id()));
				guiApp.getPi().getPtlbl().setVisible(true);
				guiApp.getPi().getPnls().setVisible(true);
				guiApp.getPi().getTimeLbl().setVisible(true);
				guiApp.getPi().getTimeTf().setVisible(true);
				guiApp.getPi().getIdlbl().setVisible(true);
				guiApp.getPi().getIdTf().setVisible(true);
				guiApp.getPi().getIdTf().setText(String.valueOf(spmt.getPt_code()));
				guiApp.getPi().getModel().setValue(spmt.getPt_date());
				Time time = spmt.getPt_time();
				if (time == null) {
					guiApp.getPi().getTimeTf().setText("");
				} else {
					guiApp.getPi().getTimeTf().setText(time.toString());
				}
				guiApp.getPi().getAddBtn().setEnabled(false);
				guiApp.getPi().getUpBtn().setEnabled(true);
				guiApp.getPi().getDelBtn().setEnabled(true);

			}
		});

		scroll = new JScrollPane(table);
		scroll.setBounds(10, 50, 1200, 150);
		pnl.add(scroll);

		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), "PT 조회");
		pnl.setBorder(oneTb);

		pnl.add(nameLbl);
		pnl.add(nameTf);
		pnl.add(searchBtn);

		add(pnl);
		setLayout(null);
		setSize(1350, 300);
		searchBtn.addActionListener(guiApp);
	}

}
