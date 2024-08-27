package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Color;

public class MemberMenu extends JFrame {
    private JLabel lblEnterTime;
    private JLabel lblExitTime;
    private JButton btnEnter;
    private JButton btnExit;

    public MemberMenu() {
        super("회원 메뉴 입니다");
        
        JPanel pnl = new JPanel();
        
        btnEnter = new JButton("출석");
        
        btnEnter.setBounds(275, 220, 85, 60);
        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblEnterTime.setText(getCurrentTime());
                btnEnter.setEnabled(false);
                btnExit.setEnabled(true);
            }
        });
        
        btnExit = new JButton("퇴장");
        btnExit.setEnabled(false);
        btnExit.setBounds(new Rectangle(372, 220, 85, 60));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	lblExitTime.setText(getCurrentTime());
                btnEnter.setEnabled(true);
            	btnExit.setEnabled(false);
            }
        });
        
        JButton btnSchedule = new JButton("스케줄");
        btnSchedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Schedule().setVisible(true);
            }
        });
        btnSchedule.setBounds(319, 377, 97, 60);
        pnl.setLayout(null);
        
        pnl.add(btnEnter);
        pnl.add(btnExit);
        pnl.add(btnSchedule);
        
        getContentPane().add(pnl);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 263, 461);
        pnl.add(panel);
        panel.setLayout(null);
        
        JLabel lblImage = new JLabel("이미지", SwingConstants.CENTER);
        lblImage.setBounds(44, 27, 175, 155);
        lblImage.setBackground(Color.WHITE);
        lblImage.setOpaque(true);
        
        panel.add(lblImage);
        
        JLabel lblName = new JLabel("이름 : ");
        lblName.setBounds(54, 201, 99, 27);
        panel.add(lblName);
        
        JLabel lblGender = new JLabel("성별 : ");
        lblGender.setBounds(54, 238, 72, 27);
        panel.add(lblGender);
        
        JLabel lblPhone = new JLabel("전화번호: ");
        lblPhone.setBounds(54, 275, 175, 27);
        panel.add(lblPhone);
        
        JLabel lblBirth = new JLabel("생년월일: ");
        lblBirth.setBounds(54, 312, 194, 27);
        panel.add(lblBirth);
        
        JLabel lblAddress = new JLabel("주소: ");
        lblAddress.setBounds(54, 349, 194, 27);
        panel.add(lblAddress);
        
        JLabel lblMembership = new JLabel("회원권: ");
        lblMembership.setBounds(54, 386, 194, 27);
        panel.add(lblMembership);
        
        JLabel lblPT = new JLabel("PT: ");
        lblPT.setBounds(54, 424, 194, 27);
        panel.add(lblPT);
        
        lblEnterTime = new JLabel("");
        lblEnterTime.setForeground(Color.RED);
        lblEnterTime.setBounds(299, 290, 57, 15);
        pnl.add(lblEnterTime);
        
        lblExitTime = new JLabel("");
        lblExitTime.setForeground(Color.BLUE);
        lblExitTime.setBounds(396, 290, 57, 15);
        pnl.add(lblExitTime);
        
        JLabel lblMemberNum = new JLabel("회원번호 : ");
        lblMemberNum.setBounds(309, 143, 119, 37);
        pnl.add(lblMemberNum);
        
        JButton btnGoMenu = new JButton("나가기");
        btnGoMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnGoMenu.setBounds(387, 10, 85, 23);
        pnl.add(btnGoMenu);
        
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private String getCurrentTime() {
        SimpleDateFormat NowTime = new SimpleDateFormat("HH:mm");
        return NowTime.format(new Date());
    }

//    public static void main(String[] args) {
//        new MemberMenu().setVisible(true);
//    }
}