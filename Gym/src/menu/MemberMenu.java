package menu;

import javax.swing.*;

import attend.Attend;
import attend.AttendDAOImpl;
import files.EncodeDecode;
import memberShip.MemberShip;
import memberShip.MemberShipDAO;
import memberShip.MemberShipDAOImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import members.Member;
import members.MemberDAO;
import members.MemberDAOImpl;

public class MemberMenu extends JFrame {
	private EncodeDecode ende = new EncodeDecode();
	private MemberDAO memberDAO = new MemberDAOImpl();
	private AttendDAOImpl attendDAO = new AttendDAOImpl();
	private MemberShipDAOImpl mem = new MemberShipDAOImpl();
	
	private Member member;
	private JLabel lblEnterTime;
	private JLabel lblExitTime;
	private JButton btnEnter;
	private JButton btnExit;

	public MemberMenu(Member member, MemberShip memberShip) {
		super("회원 메뉴 입니다");
		this.member = member;
		
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		

		// 출석 버튼
		btnEnter = new JButton("출석");
		btnEnter.setBounds(275, 220, 85, 60);
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblEnterTime.setText(getCurrentTime());
				btnEnter.setEnabled(false);
				btnExit.setEnabled(true);

				LocalDate date = LocalDate.now();
				LocalDateTime time = LocalDateTime.now();
				Attend enter = new Attend(member.getEnroll_code(), date, time);
				attendDAO.insertEnter(enter);
			}
		});
		pnl.add(btnEnter);

		// 퇴장 버튼
		btnExit = new JButton("퇴장");
		btnExit.setEnabled(false);
		btnExit.setBounds(new Rectangle(372, 220, 85, 60));
		btnExit.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        lblExitTime.setText(getCurrentTime());
		        btnEnter.setEnabled(true);
		        btnExit.setEnabled(false);
		        
		        LocalDate date = LocalDate.now();
		        LocalDateTime enterTime = LocalDateTime.now();
		        LocalDateTime exitTime = LocalDateTime.now();

		        Attend exit = new Attend(member.getEnroll_code(), date, enterTime, exitTime);
		        attendDAO.insertExit(exit);
		    }
		});
		pnl.add(btnExit);

		// 스케줄 버튼
		JButton btnSchedule = new JButton("스케줄");
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Schedule(member).setVisible(true);
			}
		});
		btnSchedule.setBounds(319, 377, 97, 60);
		pnl.add(btnSchedule);

		// 왼쪽 회원 정보 패널
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 263, 461);
		pnl.add(panel);
		panel.setLayout(null);

		// 이미지 라벨
		JLabel lblImage = new JLabel("이미지");
		lblImage.setBounds(44, 27, 175, 155);
		lblImage.setBackground(Color.WHITE);
		lblImage.setOpaque(true);
		panel.add(lblImage);
		ImageIcon icon = new ImageIcon(ende.decode(member.getMember_image()));
		
		lblImage.setIcon(icon);		

		// 회원 정보 라벨
		JLabel lblName = new JLabel("이름: " + member.getName());
		lblName.setBounds(54, 201, 200, 27);
		panel.add(lblName);

		JLabel lblGender = new JLabel("성별: " + member.getGender());
		lblGender.setBounds(54, 238, 200, 27);
		panel.add(lblGender);

		JLabel lblPhone = new JLabel("전화번호: " + member.getPhone());
		lblPhone.setBounds(54, 275, 200, 27);
		panel.add(lblPhone);

		JLabel lblBirth = new JLabel("생년월일: " + member.getBirth());
		lblBirth.setBounds(54, 312, 200, 27);
		panel.add(lblBirth);

		JLabel lblAddress = new JLabel("주소: " + member.getAddress());
		lblAddress.setBounds(54, 349, 200, 27);
		panel.add(lblAddress);

		// 회원권 및 PT 정보 (아직 추가 안됨)
		JLabel lblMembership = new JLabel("회원권: " + memberShip.getMembership_StartDate() + " ~ " + memberShip.getMembership_EndDate());
		lblMembership.setBounds(54, 386, 200, 27);
		panel.add(lblMembership);

		JLabel lblPT = new JLabel("PT: 없음");
		lblPT.setBounds(54, 424, 200, 27);
		panel.add(lblPT);

		// 출석 및 퇴장 시간 라벨
		lblEnterTime = new JLabel("");
		lblEnterTime.setForeground(Color.RED);
		lblEnterTime.setBounds(299, 290, 57, 15);
		pnl.add(lblEnterTime);

		lblExitTime = new JLabel("");
		lblExitTime.setForeground(Color.BLUE);
		lblExitTime.setBounds(396, 290, 57, 15);
		pnl.add(lblExitTime);

		// 회원번호 라벨
		JLabel lblMemberNum = new JLabel("회원번호: " + member.getEnroll_code());
		lblMemberNum.setBounds(309, 143, 200, 37);
		pnl.add(lblMemberNum);

		// 나가기 버튼
		JButton btnGoMenu = new JButton("나가기");
		btnGoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnGoMenu.setBounds(387, 10, 85, 23);
		pnl.add(btnGoMenu);
		getContentPane().add(pnl);
		setSize(500, 500);
	}

	

	private String getCurrentTime() {
		SimpleDateFormat NowTime = new SimpleDateFormat("HH:mm");
		return NowTime.format(new Date());
	}
}