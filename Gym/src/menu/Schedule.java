package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import attend.Attend;
import attend.AttendDAOImpl;
import members.Member;

public class Schedule extends JFrame {
    private int date;
    private int space;
    private int lastday;
    private int currentYear;
    private int currentMonth;
    private JButton[] btnDay;
    private String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };
    private AttendDAOImpl attendDAO = new AttendDAOImpl(); // DAO 객체
    private Member logMember; // 로그인된 회원 정보
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // 시간 포맷터

    public Schedule(Member member) {
        super("출석부");

        this.logMember = member;
        Calendar today = Calendar.getInstance();
        currentYear = today.get(Calendar.YEAR);
        currentMonth = today.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 +1

        JPanel pnlSearch = new JPanel();
        pnlSearch.setLayout(new FlowLayout());

        JPanel pnlCalendar = new JPanel();
        pnlCalendar.setLayout(new GridLayout(7, 7));

        JLabel lblYear = new JLabel("년");
        JLabel lblMonth = new JLabel("월");

        JTextField txtYear = new JTextField("2024", 4);
        JTextField txtMonth = new JTextField("8", 2);

        JButton btnSearch = new JButton("이동");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentYear = Integer.parseInt(txtYear.getText());
                currentMonth = Integer.parseInt(txtMonth.getText());
                getCalendarDate(currentYear, currentMonth);
                setCalendarButton();
            }
        });

        JLabel[] lblDayOfWeek = new JLabel[7];
        for (int i = 0; i < 7; i++) {
            lblDayOfWeek[i] = new JLabel(dayOfWeek[i]);
            lblDayOfWeek[i].setOpaque(true);
            lblDayOfWeek[i].setHorizontalAlignment(SwingConstants.CENTER);
            pnlCalendar.add(lblDayOfWeek[i]);
        }

        btnDay = new JButton[42];
        for (int i = 0; i < 42; i++) {
            btnDay[i] = new JButton();
            btnDay[i].setText("");
            btnDay[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 선택한 날의 입장, 퇴장 시간 조회
                    JButton source = (JButton) e.getSource();
                    String text = source.getText();
                    if (!text.isEmpty() && logMember != null) {
                        LocalDate selectedDate = LocalDate.of(currentYear, currentMonth, Integer.parseInt(text));
                        int enrollCode = logMember.getEnroll_code();
                        List<Attend> logs = attendDAO.getAttendLogsByDate(enrollCode, selectedDate);

                        StringBuilder message = new StringBuilder();
                        message.append(currentYear).append("년 ").append(currentMonth).append("월 ").append(text).append("일\n");

                        for (Attend log : logs) {
                            String enterTime = log.getEnter_Time().format(timeFormatter);
                            String exitTime = (log.getExit_Time() != null) ? log.getExit_Time().format(timeFormatter) : "퇴장 없음";
                            message.append("입장: ").append(enterTime).append("       |       ");
                            message.append("퇴장: ").append(exitTime).append("\n");
                        }

                        if (logs.isEmpty()) {
                            message.append("입장 및 퇴장 기록이 없습니다.");
                        }

                        JOptionPane.showMessageDialog(Schedule.this, message.toString(), "날짜 선택", JOptionPane.INFORMATION_MESSAGE);
                    } else if (logMember == null) {
                        JOptionPane.showMessageDialog(Schedule.this, "로그인된 회원 정보가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            pnlCalendar.add(btnDay[i]);
        }

        getCalendarDate(currentYear, currentMonth);
        setCalendarButton();

        txtYear.setText(Integer.toString(currentYear));
        txtMonth.setText(Integer.toString(currentMonth));

        pnlSearch.add(txtYear);
        pnlSearch.add(lblYear);
        pnlSearch.add(txtMonth);
        pnlSearch.add(lblMonth);
        pnlSearch.add(btnSearch);

        add(pnlSearch, BorderLayout.NORTH);
        add(pnlCalendar, BorderLayout.CENTER);

        setSize(500, 500);
        setVisible(true);
    }

    public void getCalendarDate(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1); // 현재 년, 월, 일 값 설정
        date = cal.get(Calendar.DAY_OF_WEEK); // 해당 달 1일의 요일(일요일1 , 월요일2 ...)
        space = date - 1; // 첫 주 여백 버튼 갯수
        lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 달의 마지막 날짜
    }

    public void setCalendarButton() {
        // 버튼 텍스트 초기화
        for (int i = 0; i < 42; i++) {
            btnDay[i].setText("");
        }

        // 버튼 텍스트에 날짜 설정
        for (int i = 1; i <= lastday; i++) {
            btnDay[i + space - 1].setText(Integer.toString(i));
        }
    }
}