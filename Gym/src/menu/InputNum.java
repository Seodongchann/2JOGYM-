package menu;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Rectangle;

public class InputNum extends JDialog {
    private JTextField textField;

    public InputNum() {
    	
        super((JFrame) null, "회원 번호 입력", true); // 모달 다이얼로그 생성
        getContentPane().setLayout(new BorderLayout());

        // 왼쪽 글자
        JPanel pnlLeft = new JPanel();
        pnlLeft.add(new JLabel("환영합니다"));
        pnlLeft.add(new JLabel("월드클래스 GYM"));
        pnlLeft.add(new JLabel("애터미짐 입니다"));
        getContentPane().add(pnlLeft, BorderLayout.NORTH);

        // 오른쪽 패널
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblInput = new JLabel("회원번호를 입력하세요");
        lblInput.setBounds(149, 35, 151, 32);
        panel.add(lblInput);

        textField = new JTextField();
        textField.setBounds(130, 73, 158, 32);
        panel.add(textField);

        JButton btnOne = new JButton("1");
		btnOne.setBounds(104, 127, 63, 37);
		panel.add(btnOne);
		btnOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 1);
			}
		});
		
		JButton btnTwo = new JButton("2");
		btnTwo.setBounds(179, 127, 63, 37);
		panel.add(btnTwo);
		btnTwo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 2);
			}
		});
		
		JButton btnThree = new JButton("3");
		btnThree.setBounds(254, 127, 63, 37);
		panel.add(btnThree);
		btnThree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 3);
			}
		});
		
		JButton btnFour = new JButton("4");
		btnFour.setBounds(104, 174, 63, 37);
		panel.add(btnFour);
		btnFour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 4);
			}
		});
		
		JButton btnFive = new JButton("5");
		btnFive.setBounds(179, 174, 63, 37);
		panel.add(btnFive);
		btnFive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 5);
			}
		});
		
		JButton btnSix = new JButton("6");
		btnSix.setBounds(254, 174, 63, 37);
		panel.add(btnSix);
		btnSix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 6);
			}
		});
		
		JButton btnSeven = new JButton("7");
		btnSeven.setBounds(104, 221, 63, 37);
		panel.add(btnSeven);
		btnSeven.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 7);
			}
		});
		
		JButton btnEight = new JButton("8");
		btnEight.setBounds(179, 221, 63, 37);
		panel.add(btnEight);
		btnEight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 8);
			}
		});
		
		JButton btnNine = new JButton("9");
		btnNine.setBounds(254, 221, 63, 37);
		panel.add(btnNine);
		btnNine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + 8);
			}
		});

        JButton btnZero = new JButton("0");
        btnZero.setBounds(179, 268, 63, 37);
        panel.add(btnZero);
        btnZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + 0);
            }
        });

        JButton btnCheck = new JButton("확인");
        btnCheck.setBounds(104, 267, 63, 37);
        panel.add(btnCheck);
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MemberMenu memberMenu = new MemberMenu();
            	memberMenu.setLocationRelativeTo(null); // 창을 화면 중앙에 배치
                memberMenu.setVisible(true);
                memberMenu.toFront();
                dispose();
                 
            }
        });

        JButton btnFix = new JButton("수정");
        btnFix.setBounds(254, 268, 63, 37);
        panel.add(btnFix);
        btnFix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);

        setSize(468, 416);
        setLocationRelativeTo(null);
    }
//    public static void main(String[] args) {
//        new InputName.setVisible(true);
//    }
}