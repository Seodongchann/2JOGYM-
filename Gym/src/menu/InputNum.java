package menu;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.callback.TextInputCallback;
import javax.swing.*;

import main.Admins;
import memberShip.MemberShip;
import memberShip.MemberShipDAOImpl;
import members.Member;
import members.MemberDAO;
import members.MemberDAOImpl;
import members.MemberMapper;
import members.ResultMapper;
import trainer.Trainer;
import trainer.TrainerDAO;
import trainer.TrainerDAOImpl;
import trainer.TrainerGUIApp;

import java.awt.Rectangle;

public class InputNum extends JDialog {
    private MemberDAO memberDAO = new MemberDAOImpl(); // MemberDAO 생성
    private Admins admins = new Admins(); // Admins 생성
    private MemberShipDAOImpl mem = new MemberShipDAOImpl();
    public InputNum() {
        super((JFrame) null, "회원 번호 입력", true); // 모달 다이얼로그 생성
        getContentPane().setLayout(new BorderLayout());

        // 타이틀
        JPanel pnlLeft = new JPanel();
        pnlLeft.add(new JLabel("환영합니다"));
        pnlLeft.add(new JLabel("월드클래스 GYM"));
        pnlLeft.add(new JLabel("애터미짐 입니다"));
        getContentPane().add(pnlLeft, BorderLayout.NORTH);

        // 번호 입력란
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblInput = new JLabel("회원번호를 입력하세요");
        lblInput.setBounds(149, 35, 151, 32);
        panel.add(lblInput);

        JTextField txtInputNum = new JTextField();
        txtInputNum.setBounds(130, 73, 158, 32);
        panel.add(txtInputNum);
        
        // 관리자 버튼
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JButton btnAdmins = new JButton("관리자");
        btnAdmins.setBounds(362, 10, 78, 57);
        btnAdmins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				admins.setLocationRelativeTo(null);
				admins.toFront();
				admins.setVisible(true);
				setVisible(false);
			}
		});
        panel.add(btnAdmins);
        
        // 다음으로 넘어가는 버튼
        JButton btnCheck = new JButton("확인");
        btnCheck.setBounds(104, 267, 63, 37);
        panel.add(btnCheck);
        btnCheck.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			// 멤버 등록번호
        			int enrollCode = Integer.parseInt(txtInputNum.getText());
        			Member member = memberDAO.memberSelectCode(enrollCode);
        			
        			if (member.getEnroll_code() != 0) {
        				// 회원이 존재하면 회원 정보
        				
        				MemberShip members = new MemberShip();
        				members = mem.memberShipSelect(enrollCode);
        				System.out.println(members);
        				
        				MemberMenu memberMenu = new MemberMenu(member, members);
        				memberMenu.setLocationRelativeTo(null); // 창을 화면 중앙에
        				memberMenu.toFront();
        				memberMenu.setVisible(true);
        				setVisible(false);
        			}
        			//없으면
        			else {
        				JOptionPane.showMessageDialog(null, "회원번호가 존재하지 않습니다.");
        			}
        			// 문자나 공백 입력하면
        		} catch (NumberFormatException ex) {
        			JOptionPane.showMessageDialog(null, "잘못된 입력입니다. 숫자를 입력하세요.");
        		}
        		
        	}
        });
        
        // 번호 지우는 버튼
        JButton btnFix = new JButton("수정");
        btnFix.setBounds(254, 268, 63, 37);
        panel.add(btnFix);
        btnFix.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		txtInputNum.setText("");
        	}
        });
        
        
        // 여기서부터 버튼1, 2, 3, ... 8, 9, 0
        JButton btnOne = new JButton("1");
		btnOne.setBounds(104, 127, 63, 37);
		panel.add(btnOne);
		btnOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 1);
			}
		});
		
		JButton btnTwo = new JButton("2");
		btnTwo.setBounds(179, 127, 63, 37);
		panel.add(btnTwo);
		btnTwo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 2);
			}
		});
		
		JButton btnThree = new JButton("3");
		btnThree.setBounds(254, 127, 63, 37);
		panel.add(btnThree);
		btnThree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 3);
			}
		});
		
		JButton btnFour = new JButton("4");
		btnFour.setBounds(104, 174, 63, 37);
		panel.add(btnFour);
		btnFour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 4);
			}
		});
		
		JButton btnFive = new JButton("5");
		btnFive.setBounds(179, 174, 63, 37);
		panel.add(btnFive);
		btnFive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 5);
			}
		});
		
		JButton btnSix = new JButton("6");
		btnSix.setBounds(254, 174, 63, 37);
		panel.add(btnSix);
		btnSix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 6);
			}
		});
		
		JButton btnSeven = new JButton("7");
		btnSeven.setBounds(104, 221, 63, 37);
		panel.add(btnSeven);
		btnSeven.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 7);
			}
		});
		
		JButton btnEight = new JButton("8");
		btnEight.setBounds(179, 221, 63, 37);
		panel.add(btnEight);
		btnEight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 8);
			}
		});
		
		JButton btnNine = new JButton("9");
		btnNine.setBounds(254, 221, 63, 37);
		panel.add(btnNine);
		btnNine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInputNum.setText(txtInputNum.getText() + 9);
			}
		});

        JButton btnZero = new JButton("0");
        btnZero.setBounds(179, 268, 63, 37);
        panel.add(btnZero);
        btnZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInputNum.setText(txtInputNum.getText() + 0);
            }
        });
        // 여기까지 버튼1, 2, 3, ... 8, 9, 0
        


        setSize(468, 416);
        setLocationRelativeTo(null);
    }
}