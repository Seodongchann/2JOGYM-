package pt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

import lombok.Data;
import trainer.TrainerGUIApp;


@Data
public class PTProfileSearch extends JPanel {
	private PTDAOImpl dao = new PTDAOImpl();
	private List<PT> list;
	private JScrollPane scroll;
	private JPanel trainerSearchPnl;
	private JPanel trainerSearchResult;
	private JLabel searchLbl;
	private JTextField serachTf;
	private JButton searchBtn;
	private JButton searchAllBtn;

	public PTProfileSearch(TrainerGUIApp guiApp) {
		trainerSearchPnl = new JPanel();
		trainerSearchPnl.setLayout(null);
		trainerSearchPnl.setBounds(50, 50, 700, 100);

		trainerSearchResult = new JPanel();
		trainerSearchResult.setLayout(null);
		trainerSearchResult.setBounds(50, 150, 700, 450);

		searchLbl = new JLabel("이름");
		serachTf = new JTextField();
		searchBtn = new JButton("검색");
		searchAllBtn = new JButton("전체");

		searchLbl.setBounds(0, 0, 50, 20);
		serachTf.setBounds(70, 0, 100, 20);
		searchBtn.setBounds(180, 0, 60, 20);
		searchAllBtn.setBounds(260, 0, 60, 20);

		trainerSearchPnl.add(searchLbl);
		trainerSearchPnl.add(serachTf);
		trainerSearchPnl.add(searchBtn);
		trainerSearchPnl.add(searchAllBtn);

//		list = dao.trainerSelectAll();
		List<PT> list = new ArrayList<>();
		Vector<String> vector;

		vector = new Vector<String>();
		vector.add("번호");
		vector.addElement("이름");
		vector.add("전화번호");
		vector.addElement("성별");
		vector.addElement("생년월일");
		vector.addElement("주소");
		vector.add("시작일");

		// defaultTableModel 생성
		DefaultTableModel model;
		model = new DefaultTableModel(vector, 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};


		JTable table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 700, 500);
		trainerSearchResult.add(scroll);

		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.gray), " 트레이너 리스트");
		setBorder(twoTb);

		add(trainerSearchPnl);
		add(trainerSearchResult);
		setLayout(null);
		setSize(800, 700);


		searchAllBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<PT> list = dao.selectAll();

				DefaultTableModel model;
				model = new DefaultTableModel(vector, 0) {
					public boolean isCellEditable(int r, int c) {
						return (c != 0) ? true : false;
					}
				};


				JTable table = new JTable(model);

				scroll.setViewportView(table);

				revalidate();
				repaint();
			}
		});

	}

}
