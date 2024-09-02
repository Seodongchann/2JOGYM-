package menu;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame {

    public Menu() {
        super("환영합니다. 월드클래스 GYM 애터미짐 입니다.");

        // 메인 패널
        JPanel pnl = new JPanel(new BorderLayout());
        JPanel pnlSouth = new JPanel();
        
        JButton btnLogin = new JButton("로그인");
        btnLogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		InputNum inputNum = new InputNum();
        		inputNum.setLocationRelativeTo(Menu.this); 
        		inputNum.setVisible(true);
        		
        	}
        });

        // 로고 이미지
        JLabel lblBackground = new JLabel();
        lblBackground.setIcon(new ImageIcon(Menu.class.getResource("/image/로고.png")));

        pnlSouth.add(btnLogin);
        pnl.add(lblBackground, BorderLayout.CENTER);
        pnl.add(pnlSouth, BorderLayout.SOUTH);
        add(pnl);
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}