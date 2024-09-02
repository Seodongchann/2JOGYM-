package trainer;

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
public class TrainerSearch extends JPanel {
	private TrainerDAOImpl dao = new TrainerDAOImpl();
	private List<Trainer> list;
	private JScrollPane scroll;
	private JPanel trainerSearchPnl;
	private JPanel trainerSearchResult;
	private JLabel searchLbl;
	private JTextField serachTf;
	private JButton searchBtn;
	private JButton searchAllBtn;
	private Vector<String> vector;
	private JTable table;
	private EncodeDecode inde;

	public TrainerSearch(TrainerGUIApp guiApp) {
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

		
		list = dao.TrainerselectAll();

		vector = new Vector<String>();
		vector.add("번호");
		vector.addElement("이름");
		vector.add("휴대전화");
		vector.addElement("성별");
		vector.addElement("생년월일");
		vector.addElement("주소");
		// vector.add("등록코드");

		// defaultTableModel 생성
		DefaultTableModel model;
		model = new DefaultTableModel(vector, 0) {
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
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				TableModel data = table.getModel();
				String trainer_code = (String) data.getValueAt(row, 0);
				int trainer_id = Integer.parseInt(trainer_code);
				Trainer tr = dao.trainerSelectId(trainer_id);
				if (tr != null) {
					guiApp.getTi().getRgbtn().setEnabled(false);
					guiApp.getTi().getRetouchbtn().setEnabled(true);
					guiApp.getTi().getDeletebtn().setEnabled(true);
				}
				guiApp.getTi().getNametf().setText(tr.getName());
				if (tr.getGender().equals("남성")) {
					guiApp.getTi().getRd().setSelected(true);
				} else {
					guiApp.getTi().getRd2().setSelected(true);
				}
				guiApp.getTi().getTrNumlbl().setVisible(true);
				guiApp.getTi().getTrNumtf().setVisible(true);
				guiApp.getTi().getTrNumtf().setText(String.valueOf(tr.getId()));
				guiApp.getTi().getTrNumtf().setEnabled(false);
				guiApp.getTi().getPhonetf().setText(tr.getPhone());
				guiApp.getTi().getBirthtf().setText(tr.getBirth());
				guiApp.getTi().getAdtf().setText(tr.getAddress());
				inde = new EncodeDecode();
				if (tr.getTrainer_image() != null) {
					guiApp.getTi().setImag(new ImageIcon(inde.decode(tr.getTrainer_image())));
					guiApp.getTi().getImlbl().setIcon(guiApp.getTi().getImag());

				} else {
					guiApp.getTi().getImlbl().setIcon(new ImageIcon());
				}

				revalidate();
				repaint();
			}
		});
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 700, 400);
		trainerSearchResult.add(scroll);

		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.gray), " 사용자 리스트");

		setBorder(twoTb);

		add(trainerSearchPnl);
		add(trainerSearchResult);
		setLayout(null);
		setSize(800, 700);
		searchBtn.addActionListener(guiApp);
		searchAllBtn.addActionListener(guiApp);
	}

}
