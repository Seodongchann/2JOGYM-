package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Admins;

public class Loginwindow extends JFrame {
	Admins a = new Admins();
	public Loginwindow() {
		
		JLabel idlbl = new JLabel("사용자 : ");
		idlbl.setBounds(20,20,80,30);
		JTextField tf = new JTextField();
		tf.setBounds(100,20,100,30);
		JLabel pwlbl = new JLabel("비밀번호 : ");
		pwlbl.setBounds(20,75,80,30);
		JPasswordField pwfd = new JPasswordField(1234);
		pwfd.setBounds(100,75,100,30);
		
		
		
		JButton btn = new JButton("로그인");
		btn.setBounds(100,120,80,30);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Integer.parseInt(pwfd.getText())==1234 && 
						tf.getText().equals("admin")) {
					a.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요."
							, "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		add(idlbl);
		add(tf);
		add(pwlbl);
		add(pwfd);
		add(btn);
		
		
		setLayout(null);
		setSize(250,300);
		
		
		
	}
	public static void main(String[] args) {
		new Loginwindow().setVisible(true);
	}
}
