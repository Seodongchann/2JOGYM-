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
        
        // 버튼 패널
        JPanel pnlSouth = new JPanel();

        // 버튼 생성
        JButton btnLogin = new JButton("로그인");
        
        // 버튼 추가
        pnlSouth.add(btnLogin);

        // 로고 이미지
        JLabel lblBackground = new JLabel();
        lblBackground.setIcon(new ImageIcon(Menu.class.getResource("/image/로고.png")));

        // 추가
        pnl.add(lblBackground, BorderLayout.CENTER);
        pnl.add(pnlSouth, BorderLayout.SOUTH);

        // 버튼 클릭 이벤트 설정
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                InputNum inputNum = new InputNum();
                inputNum.setLocationRelativeTo(Menu.this); 
                inputNum.setVisible(true);
            }
        });

        add(pnl);
        setSize(750, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Menu().setVisible(true);
    }
}