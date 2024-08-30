package memberShip;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Data;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@Data
public class MemberShipInput extends JPanel {
	private MemberShip memberShip = new MemberShip();
	private JLabel nameLbl;
	private JTextField nameTf;
	private JLabel birthLbl;
	private JTextField birthTf;
	private JLabel phoneLbl;
	private JPanel memberPnl;
	private JPanel memberShipPnl;
	private JTextField phoneTf;
	private JLabel enroll_code;
	private JTextField enroll_codeTf;
	private JLabel addressLbl;
	private JTextField addressTf;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JRadioButton rb4;
	private ButtonGroup bg;
//	private JLabel priceLbl;
//	private JTextField priceTf;
	private JButton rigiBtn;
//	private JButton corrBtn;
//	private JButton dropBtn;
	private JButton resetBtn;
	private UtilDateModel model;
	private JDatePickerImpl datePicker;
	private JDatePanelImpl datePanel;

	public MemberShipInput(MemberShipGUIApp guiapp) {
		memberPnl = new JPanel();
		memberShipPnl = new JPanel();
		memberPnl.setLayout(null);
		memberShipPnl.setLayout(null);

		nameLbl = new JLabel("이름");
		nameTf = new JTextField(10);
		birthLbl = new JLabel("생년월일");
		birthTf = new JTextField(10);
		phoneLbl = new JLabel("휴대전화");
		phoneTf = new JTextField(15);
		enroll_code = new JLabel("등록코드");
		enroll_codeTf = new JTextField(10);
		addressLbl = new JLabel("주소");
		addressTf = new JTextField(30);

		nameLbl.setBounds(20, 50, 50, 20);
		nameTf.setBounds(70, 50, 100, 20);
		birthLbl.setBounds(20, 100, 100, 20);
		birthTf.setBounds(80, 100, 60, 20);
		phoneLbl.setBounds(150, 100, 70, 20);
		phoneTf.setBounds(210, 100, 90, 20);
		enroll_code.setBounds(20, 150, 100, 20);
		enroll_codeTf.setBounds(100, 150, 200, 20);
		addressLbl.setBounds(20, 200, 70, 20);
		addressTf.setBounds(70, 200, 230, 20);

		memberPnl.add(nameLbl);
		memberPnl.add(nameTf);
		memberPnl.add(birthLbl);
		memberPnl.add(birthTf);
		memberPnl.add(phoneLbl);
		memberPnl.add(phoneTf);
		memberPnl.add(enroll_code);
		memberPnl.add(enroll_codeTf);
		memberPnl.add(addressLbl);
		memberPnl.add(addressTf);

		JLabel rigiLbl = new JLabel("약정");
		rb1 = new JRadioButton("1개월", true);
		rb2 = new JRadioButton("3개월");
		rb3 = new JRadioButton("6개월");
		rb4 = new JRadioButton("12개월");
		bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);

//		priceLbl = new JLabel("결제금액");
//		priceTf = new JTextField(10);

		JPanel pnl = new JPanel();
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		pnl.add(datePicker);

		JLabel startLbl = new JLabel("시작일");
		rigiBtn = new JButton("등록");
		rigiBtn.addActionListener(guiapp);
//		corrBtn = new JButton("수정");
//	//	corrBtn.setEnabled(false);
//		dropBtn = new JButton("삭제");
//	//	dropBtn.setEnabled(false);
		resetBtn = new JButton("초기화");
		resetBtn.addActionListener(guiapp);

		rigiLbl.setBounds(30, 50, 60, 20);
//		priceLbl.setBounds(110, 50, 60, 20);
//		priceTf.setBounds(180, 50, 100, 20);
		rb1.setBounds(30, 90, 60, 20);
		rb2.setBounds(100, 90, 60, 20);
		rb3.setBounds(30, 140, 60, 20);
		rb4.setBounds(100, 140, 100, 20);
		rigiBtn.setBounds(30, 240, 60, 25);
//		corrBtn.setBounds(90, 240, 60, 25);
//		dropBtn.setBounds(150, 240, 60, 25);
		resetBtn.setBounds(210, 240, 80, 25);
		startLbl.setBounds(30, 180, 50, 30);
		pnl.setBounds(90, 180, 200, 30);

		memberShipPnl.add(rigiLbl);
//		memberShipPnl.add(priceLbl);
//		memberShipPnl.add(priceTf);
		memberShipPnl.add(rb1);
		memberShipPnl.add(rb2);
		memberShipPnl.add(rb3);
		memberShipPnl.add(rb4);
		memberShipPnl.add(startLbl);
		memberShipPnl.add(pnl);
		memberShipPnl.add(rigiBtn);
//		memberShipPnl.add(corrBtn);
//		memberShipPnl.add(dropBtn);
		memberShipPnl.add(resetBtn);

		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), "회원권 구매");
		TitledBorder oneTb2 = new TitledBorder(new LineBorder(Color.gray), "회원 정보");
		TitledBorder oneTb3 = new TitledBorder(new LineBorder(Color.gray), "회원권 정보");

		memberPnl.setBorder(oneTb2);
		memberShipPnl.setBorder(oneTb3);

		memberPnl.setBounds(40, 50, 320, 300);
		memberShipPnl.setBounds(40, 370, 320, 300);
		setBorder(oneTb);

		add(memberPnl);
		add(memberShipPnl);
		setLayout(null);
		setSize(400, 700);
	}
}
