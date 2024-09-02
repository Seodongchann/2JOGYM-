package pt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import lombok.Data;
import main.Admins;
import memberShip.MemberShipDAOImpl;
import memberShip.MembershipJoinMember;
import members.Member;
import members.MemberDAOImpl;
import trainer.Trainer;
import trainer.TrainerDAOImpl;

@Data
public class PTGUIApp extends JPanel implements ActionListener {
	private PTDAO dao = new PTDAOImpl();
	private MemberShipDAOImpl msdao = new MemberShipDAOImpl();
	private MemberDAOImpl mdao = new MemberDAOImpl();
	private TrainerDAOImpl tdao = new TrainerDAOImpl();
	private PTInput pi;
	private MemberAndTrainerSearch mts;
	private PTSearch pts;
	private List<PTMemberTrainer> pmt;
	private List<Member> listm;
	private List<Trainer> listt;

	public PTGUIApp(Admins admin) {
		pi = new PTInput(this);
		mts = new MemberAndTrainerSearch(this);
		pts = new PTSearch(this);
		add(pi);
		add(mts);
		add(pts);

		pi.setLocation(50, 50);
		mts.setLocation(450, 50);
		pts.setLocation(40, 450);
		setLayout(null);
		setSize(1350, 800);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String commend = e.getActionCommand();
		if (commend.equals("등록")) {
			int trainer_id = Integer.parseInt(pi.getTrainerIdTf().getText());
			int member_id = Integer.parseInt(pi.getEnrollTf().getText());
			int ptNum = Integer.parseInt(pi.getPtNumTf().getText());
			PT pt = new PT(trainer_id, member_id);
			int result = dao.insert(pt, ptNum);
			searchAll();
			clearText(result);
		} else if (commend.equals("수정")) {
			int pt_code = Integer.parseInt(pi.getIdTf().getText());
			int trainer_id = Integer.parseInt(pi.getTrainerIdTf().getText());
			int member_id = Integer.parseInt(pi.getEnrollTf().getText());
			int ptNum = Integer.parseInt(pi.getPtNumTf().getText());
			String str = pi.getModel().getYear() + "-" + (pi.getModel().getMonth() + 1) + "-" + pi.getModel().getDay();

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date pt_date;
			Date ptDate = null;
			try {
				pt_date = format.parse(str);
				long milliSeconds = pt_date.getTime();
				ptDate = new Date(milliSeconds);

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Time pt_time = Time.valueOf(pi.getTimeTf().getText());

			System.out.println("_________________________");
			PT pt = new PT(pt_code, trainer_id, member_id, pt_time, ptDate);
			int result = dao.updatePT(pt);
			searchAll();
			clearText(result);
		} else if (commend.equals("삭제")) {
			int pt_code = Integer.parseInt(pi.getIdTf().getText());
			int result = dao.deletePt(pt_code);
			searchAll();
			clearText(result);
		} else if (commend.equals("초기화")) {
			clearText(1);
		} else if (commend.equals("회원검색")) {
			searchMember();
			revalidate();
			repaint();
		} else if (commend.equals("회원전체")) {
			resetMember();
			revalidate();
			repaint();

		} else if (commend.equals("트레이너검색")) {
			searchTrainer();
			revalidate();
			repaint();

		} else if (commend.equals("트레이너전체")) {
			resetTrainer();
			revalidate();
			repaint();
		} else if (commend.equals("PT검색")) {
			searchPT();
			revalidate();
			repaint();
		} else if (commend.equals("PT전체")) {
			resetPt();
			revalidate();
			repaint();
		}

	}

	private void searchMember() {
		String name = mts.getNameTf().getText();
		listm = mdao.memberSelectName(name);

		DefaultTableModel model;
		model = new DefaultTableModel(mts.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Member m : listm) {
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

		mts.getTable().setModel(model);
		mts.getScroll().setViewportView(mts.getTable());

		mts.getNameTf().setText("");
	}

	private void searchTrainer() {
		String name = mts.getTnameTf().getText();
		listt = tdao.trainerSelectName(name);

		DefaultTableModel model2;
		model2 = new DefaultTableModel(mts.getVector2(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Trainer t : listt) {
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
		mts.getTable2().setModel(model2);
		mts.getScroll2().setViewportView(mts.getTable2());

		mts.getTnameTf().setText("");
	}

	private void searchPT() {
		String name = pts.getNameTf().getText();
		pmt = dao.selectjoinName(name);

		DefaultTableModel model;
		model = new DefaultTableModel(pts.getVector(), 0) {
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

		pts.getTable().setModel(model);
		pts.getScroll().setViewportView(pts.getTable());

		pts.getNameTf().setText("");
	}

	private void searchAll() {
		pmt = dao.selectJoinAll();
		DefaultTableModel model;
		model = new DefaultTableModel(pts.getVector(), 0) {
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
		pts.getTable().setModel(model);
		pts.getScroll().setViewportView(pts.getTable());

		pts.getNameTf().setText("");

	}

	public void resetMember() {
		listm = mdao.memberSelectAll();
		DefaultTableModel model;
		model = new DefaultTableModel(mts.getVector(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Member m : listm) {
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
		mts.getTable().setModel(model);
		mts.getScroll().setViewportView(mts.getTable());

		mts.getNameTf().setText("");

	};

	public void resetTrainer() {
		listt = tdao.TrainerselectAll();
		DefaultTableModel model;
		model = new DefaultTableModel(mts.getVector2(), 0) {
			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		for (Trainer t : listt) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(t.getId()));
			v.add(t.getName());
			v.add(String.valueOf(t.getPhone()));
			v.add(t.getGender());
			v.add(t.getBirth());
			v.add(t.getAddress());
			v.add(String.valueOf(t.getStart_date()));
			model.addRow(v);
		}
		mts.getTable2().setModel(model);
		mts.getScroll2().setViewportView(mts.getTable2());

		mts.getTnameTf().setText("");
	};

	public void resetPt() {
		pmt = dao.selectJoinAll();
		DefaultTableModel model;
		model = new DefaultTableModel(pts.getVector(), 0) {
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
		pts.getTable().setModel(model);
		pts.getScroll().setViewportView(pts.getTable());

		pts.getNameTf().setText("");
	};

	public void clearText(int result) {
		if (result >= 1) {
			pi.getNameTf().setText("");
			pi.getBirthTf().setText("");
			pi.getEnrollTf().setText("");
			pi.getPtNumTf().setText("");
			pi.getTrainerTf().setText("");
			pi.getTrainerIdTf().setText("");

			pi.getPtlbl().setVisible(false);
			pi.getPnls().setVisible(false);
			pi.getTimeLbl().setVisible(false);
			pi.getTimeTf().setVisible(false);
			pi.getIdlbl().setVisible(false);
			pi.getIdTf().setVisible(false);

			pi.getAddBtn().setEnabled(true);
			pi.getUpBtn().setEnabled(false);
			pi.getDelBtn().setEnabled(false);
		}
	}
}
