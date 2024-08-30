package pt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import members.Member;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PTProfile extends JFrame {

	private JTable tablemonth;
	private PTDAOImpl dao = new PTDAOImpl();
	private List<PT> list;
	private JScrollPane scrollBarday;
	private JTable tableday;

	public PTProfile() {
		JPanel pnl = new JPanel();
		JPanel panel = new JPanel();
		JButton daybtn = new JButton("조회");
		JButton monthbtn = new JButton("조회");

		daybtn.setBounds(220, 10, 60, 30);
		monthbtn.setBounds(220, 10, 60, 30);

		panel.setLayout(null);
		panel.setBounds(0, 0, 1334, 300);
		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), "일간 매출");
		panel.setBorder(oneTb);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 350, 1334, 50);
		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.gray), "월간 매출");
		panel2.setBorder(twoTb);

		pnl.setLayout(null);

		JLabel lblmonth = new JLabel("날짜 검색");
		lblmonth.setBounds(10, 10, 131, 40);

		JLabel lblday = new JLabel("날짜 검색");
		lblday.setBounds(10, 10, 131, 40);



		JScrollPane scrollBarmonth = new JScrollPane(tablemonth, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBarmonth.setBounds(1317, 410, 17, 280);

		UtilDateModel modelday = new UtilDateModel();
		JDatePanelImpl datePanelday = new JDatePanelImpl(modelday);
		JDatePickerImpl datePickerday = new JDatePickerImpl(datePanelday);

		datePickerday.setBounds(71, 10, 131, 30);

		UtilDateModel modelmonth = new UtilDateModel();
		JDatePanelImpl datePanelmonth = new JDatePanelImpl(modelmonth);
		JDatePickerImpl datePickermonth = new JDatePickerImpl(datePanelmonth);
		datePickermonth.setBounds(71, 10, 131, 30);

		Vector<String> vector;

		vector = new Vector<String>();
		vector.add("번호");
		vector.addElement("회원 ID");
		vector.addElement("트레이너 ID");
		vector.addElement("PT날짜");
		vector.addElement("등록일");
		vector.add("가격");
		vector.add("시간");
		
		
		DefaultTableModel model;
		model = new DefaultTableModel(vector, 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		list = dao.selectAll();
		for (PT p : list) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(p.getPT_Member_ID()));
			v.add(String.valueOf(p.getPT_Trainer_ID()));
			v.add(String.valueOf(p.getPT_Date()));
			v.add(String.valueOf(p.getPT_EnrollDate()));
			v.add(String.valueOf(p.getPT_Price()));
			v.add(String.valueOf(p.getPT_Time()));
			model.addRow(v);
		}
		tableday = new JTable(model);
		tableday.setBounds(0, 60, 1334, 280);

//		tablemonth = new JTable(model);
//		tablemonth.setBounds(0, 410, 1334, 280);
		
		scrollBarday = new JScrollPane(tableday, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		scrollBarday.setBounds(0, 60, 1300, 250);

		scrollBarday.add(tableday);
		scrollBarday.setLayout(null);
//		add(scrollBarday);
//		add(scrollBarmonth);

//		//add(tableday);
//		add(tablemonth);

		panel.add(daybtn);
		panel.add(lblday);
		panel.add(datePickerday);
		panel.add(scrollBarday);
		panel2.add(monthbtn);
		panel2.add(lblmonth);
		panel2.add(datePickermonth);
		pnl.add(panel2);
		pnl.add(panel);
//		pnl.add(scrollBarday);
//		pnl.add(tablemonth);

		daybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel models;
				List<PT> ls = new ArrayList<PT>();
				java.util.Date date = modelday.getValue();
				long date2 = date.getTime();

				Date da = new java.sql.Date(date2);

				ls = dao.selectDate(da);
			
				models = new DefaultTableModel(vector, 0) {
					public boolean isCellEditable(int r, int c) {
						return (c != 0) ? true : false;
					}
				};

				for (PT p : ls) {
					Vector<String> v = new Vector<String>();
					v.add(String.valueOf(p.getPT_Trainer_ID()));
					v.add(String.valueOf(p.getPT_Member_ID()));
					v.add(String.valueOf(p.getPT_Time()));
					v.add(String.valueOf(p.getPT_Price()));
					v.add(String.valueOf(p.getPT_EnrollDate()));
					v.add(String.valueOf(p.getPT_Date()));
					models.addRow(v);
				}
				
				tableday.setModel(models);
				scrollBarday.setViewportView(tableday);
			}

		});

		add(pnl);
		setSize(1350, 800);

	}

	public static void main(String[] args) {
		new PTProfile().setVisible(true);
	}
}