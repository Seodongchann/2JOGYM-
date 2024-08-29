package trainer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pt.PT;
import pt.PTDAOImpl;

public class TrainerSchedule extends JFrame {
	PTDAOImpl pt = new PTDAOImpl();
	
//	public void list(PTDAOImpl pt) {
//	List<PT> list2 = pt.selectDate(LocalDateTime.of(2024, 8, 29, 16, 30));
//	
//	
//	}
//	PTDAOImpl pt = new PTDAOImpl();
	
	private int date;
	private int space;
	private int lastday;
	private int currentYear;
	private int currentMonth;
	private JButton[] btnDay;
	private String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };

	public TrainerSchedule() {
		super("트레이너 스케줄");

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
					LocalDate dd = LocalDate.of(currentYear, currentMonth,Integer.parseInt(text));
					java.sql.Date date = java.sql.Date.valueOf(dd);
					
					List<PT> list2 = pt.selectDate(date);
					if (!text.isEmpty()) {
						JOptionPane.showMessageDialog(TrainerSchedule.this,
								currentYear + "년 " + currentMonth + "월 " + text + "일\n "+list2 , "날짜 선택",
								JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
			});
			pnlCalendar.add(btnDay[i]);
		}

		for (int i = 1; i <= lastday; i++) {
			btnDay[i + space - 1].setText(Integer.toString(i));
		}

		// 날짜 계산 및 버튼 초기화
		getCalendarDate(currentYear, currentMonth);
		setCalendarButton();

		// 현재 연도와 월을 JTextField에 설정
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

	public static void main(String[] args) {
		new TrainerSchedule().setVisible(true);
	}
}