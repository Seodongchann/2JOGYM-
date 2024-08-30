package memberShip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import lombok.Data;
import main.Admins;
import members.Member;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@Data
public class MemberShipGUIApp extends JPanel implements ActionListener {
	private List<MembershipJoinMember> members;
	private MemberShipSearch mss;
	private MemberShipInput mi;
	private MemberShipDAO dao = new MemberShipDAOImpl();

	public MemberShipGUIApp(Admins ad) {
		setLayout(null);
		mi = new MemberShipInput(this);
		mss = new MemberShipSearch(this);
		mi.setLocation(50, 50);
		mss.setLocation(500, 50);
		add(mi);
		add(mss);
		setSize(1350, 800);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commend = e.getActionCommand();
		int membership_month = 0;

		if (commend.equals("등록")) {
			int enroll_code = Integer.parseInt(mi.getEnroll_codeTf().getText());
			if (mi.getRb1().isSelected()) {
				membership_month = 1;
			} else if (mi.getRb2().isSelected()) {
				membership_month = 3;
			} else if (mi.getRb3().isSelected()) {
				membership_month = 6;
			} else if (mi.getRb4().isSelected()) {
				membership_month = 12;
			}
			int membership_price = membership_month * 100000;
			String str = mi.getModel().getYear() + "-" + (mi.getModel().getMonth() + 1) + "-" + mi.getModel().getDay();

			System.out.println(str);//
			int result = 0;
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date membership_startDate = format.parse(str);
				Date membership_start_dat = format.parse(str);

				Calendar cal = Calendar.getInstance();
				cal.setTime(membership_start_dat);
				cal.add(Calendar.MONTH, membership_month);
				Date membership_endDate = cal.getTime();

				System.out.println(membership_startDate);
				MemberShip m = new MemberShip(enroll_code, membership_month, membership_price, membership_startDate,
						membership_endDate);

				result = dao.membershipInsert(m);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			textClear();
		} else if (commend.equals("초기화")) {
			textClear();
		} else if (commend.equals("검색")) {
			searchEnroll();
			revalidate();
			repaint();
		} else if (commend.equals("전체")) {
			searchAll();
			revalidate();
			repaint();
		}
//		else if(commend.equals("수정")) {
//			int enroll_code = Integer.parseInt(mi.getEnroll_codeTf().getText());
//			Date startDate = mi.getMemberShip().getMembership_StartDate();// 이전 시작일
//			
//			
//		
//		}

	}

	public void textClear() {
		mi.getNameTf().setText("");
		mi.getBirthTf().setText("");
		mi.getPhoneTf().setText("");
		mi.getEnroll_codeTf().setText("");
		mi.getAddressTf().setText("");
		if (!mi.getRb1().isSelected()) {
			mi.getRb1().setSelected(true);
		}

	}

	private void searchEnroll() {
		int enroll_code = Integer.parseInt(mss.getSerachTf().getText());
		members = dao.selectMembership(enroll_code);

		DefaultTableModel model;
		model = new DefaultTableModel(mss.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (MembershipJoinMember m : members) {
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

		mss.getTable().setModel(model);
		mss.getScroll().setViewportView(mss.getTable());

		mss.getSerachTf().setText("");
	}

	private void searchAll() {
		members = dao.joinMembers();

		DefaultTableModel model;
		model = new DefaultTableModel(mss.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (MembershipJoinMember m : members) {
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

		mss.getTable().setModel(model);
		mss.getScroll().setViewportView(mss.getTable());
	}
}
