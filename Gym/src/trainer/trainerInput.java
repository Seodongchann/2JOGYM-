package trainer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class trainerInput extends JFrame {
	private void list2(List<JPanel> list, JPanel listpnl, TrainerDB db) {
		List<Trainer> list2 = new ArrayList<>();
		list2 = db.selectAll();
		// 리스트에 리스트2를 넣어라.
		int a = 250;
		int b = 50;
		for (int i = 0; i < list2.size(); i++) {
			JLabel listlblnum2 = new JLabel(String.valueOf(i + 1));
			listlblnum2.setBounds(400, a += b, 50, 50);
			JLabel listlblname2 = new JLabel(list2.get(i).getName());
			listlblname2.setBounds(450, a += b, 50, 50);
			JLabel listlblPhone2 = new JLabel(list2.get(i).getPhone());
			listlblPhone2.setBounds(500, a += b, 100, 50);
			JLabel listlblgender2 = new JLabel(list2.get(i).getGender());
			listlblgender2.setBounds(550, a += b, 50, 50);
			JLabel listlblbirth2 = new JLabel(list2.get(i).getBirth());
			listlblbirth2.setBounds(600, a += b, 50, 50);
			JLabel listlblStart_date2 = new JLabel(String.valueOf(list2.get(i).getStart_date()));
			listlblStart_date2.setBounds(700, a += b, 100, 50);
			JLabel listlbladdress2 = new JLabel(list2.get(i).getAddress());
			listlbladdress2.setBounds(800, a += b, 100, 50);

			listpnl.add(listlblnum2);
			listpnl.add(listlblname2);
			listpnl.add(listlblPhone2);
			listpnl.add(listlblbirth2);
			listpnl.add(listlblgender2);
			listpnl.add(listlblStart_date2);
			listpnl.add(listlbladdress2);
			list.add(listpnl);
		}
		
	}

	// 트레이너 정보입력
	public trainerInput() {
		// 이름, 남성 여성 라벨
		JPanel Inputpnl = new JPanel(); // 전체페널
		JPanel TrainerInputpnl = new JPanel(); // 트레이너정보입력패널
		JPanel TrainerListpnl = new JPanel();
		JPanel TrainerListButtonpnl = new JPanel(); // 트레이너 버튼패널
		JPanel TrainerListSQLpnl = new JPanel();
		JLabel lbl = new JLabel("이름");
		JTextField nametf = new JTextField(5);
		JRadioButton rd = new JRadioButton("남성");
		JRadioButton rd2 = new JRadioButton("여성");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rd);
		bg.add(rd2);
		// Inputpnl.setBounds(100,100,100,100);
		lbl.setBounds(50, 100, 50, 50);
		nametf.setBounds(100, 115, 90, 20);
		rd.setBounds(200, 115, 60, 20);
		rd2.setBounds(260, 115, 60, 20);
		TrainerInputpnl.add(lbl);
		TrainerInputpnl.add(nametf);
		TrainerInputpnl.add(rd);
		TrainerInputpnl.add(rd2);

		// 생년월일
		JLabel birthlbl = new JLabel("생년월일");
		JTextField birthtf = new JTextField(3);
		birthlbl.setBounds(50, 150, 100, 50);
		birthtf.setBounds(110, 165, 60, 20);
		TrainerInputpnl.add(birthlbl);
		TrainerInputpnl.add(birthtf);
		// 전화번호
		JLabel phonelbl = new JLabel("전화번호");
		JTextField phonetf = new JTextField(12);
		phonelbl.setBounds(180, 149, 70, 50);
		phonetf.setBounds(240, 165, 90, 20);
		TrainerInputpnl.add(phonelbl);
		TrainerInputpnl.add(phonetf);
		// 트레이너번호
		JLabel trainerNumlbl = new JLabel("트레이너 번호");
		JTextField trainerNumtf = new JTextField(20);
		trainerNumlbl.setBounds(50, 200, 100, 50);
		trainerNumtf.setBounds(150, 216, 180, 20);
		TrainerInputpnl.add(trainerNumlbl);
		TrainerInputpnl.add(trainerNumtf);

		// 주소
		JLabel adlbl = new JLabel("주소");
		JTextField adtf = new JTextField(30);
		adlbl.setBounds(50, 250, 70, 50);
		adtf.setBounds(100, 267, 230, 20);
		TrainerInputpnl.add(adlbl);
		TrainerInputpnl.add(adtf);
		// 이미지 선택
		JLabel imlbl = new JLabel("이미지 선택", SwingConstants.CENTER);
		imlbl.setBounds(100, 300, 200, 200);
		imlbl.setOpaque(true); // 배경색을 불투명하게 만들어서 색 넣을 수 있께함.
		imlbl.setBackground(Color.WHITE);
		TrainerInputpnl.add(imlbl);

		JButton imbtn = new JButton("이미지 파일 등록");
		imbtn.setBounds(100, 520, 200, 25);
		TrainerInputpnl.add(imbtn);
		imbtn.addActionListener(new ActionListener() {
			// 이미지 파일 넣는 버튼리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setBounds(100, 520, 200, 25);
				Inputpnl.add(filechooser);
				int result = filechooser.showOpenDialog(filechooser);
				if (result == filechooser.APPROVE_OPTION) {
					File selectedFile = filechooser.getSelectedFile();

				}
			}
		});
		// 수정 버튼
		JButton retouchbtn = new JButton("수정");
		retouchbtn.setBounds(120, 570, 60, 25);
		TrainerInputpnl.add(retouchbtn);
		// 삭제 버튼
		JButton deletebtn = new JButton("삭제");
		deletebtn.setBounds(190, 570, 60, 25);
		TrainerInputpnl.add(deletebtn);
		// 초기화 버튼
		JButton resetbtn = new JButton("초기화");
		resetbtn.setBounds(260, 570, 75, 25);
		TrainerInputpnl.add(resetbtn);

		// 트레이너 정보입력 라인보더
		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), "트레이너 정보입력");

		TrainerInputpnl.setBorder(oneTb);
		TrainerInputpnl.setBounds(30, 50, 100, 100);

		TrainerListpnl.setLayout(null);
		TrainerListpnl.setBounds(500, 50, 900, 610);

		// 트레이너 리스트 라인보더
		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.gray), "트레이너 리스트");
		TrainerListpnl.setBorder(twoTb);
		TrainerListpnl.add(TrainerListButtonpnl);

		TrainerListButtonpnl.setLayout(null);
		TrainerListButtonpnl.setBounds(50, 30, 800, 150);
		// 트레이너 리스트 이름라벨
		JLabel listnamelbl = new JLabel("이름");
		listnamelbl.setText("이름");
		listnamelbl.setBounds(10, 0, 50, 20);
		TrainerListButtonpnl.add(listnamelbl);
		// 트레이너 리스트 이름텍스트 필드
		JTextField listnametf = new JTextField(5);
		listnametf.setBounds(100, 0, 90, 20);
		TrainerListButtonpnl.add(listnametf);
		// 트레이너 리스트 검색버튼
		JButton listsearchbtn = new JButton("검색");
		listsearchbtn.setBounds(240, 0, 60, 20);
		TrainerListButtonpnl.add(listsearchbtn);

		// 트레이너 리스트 전체버튼
		JButton listallbtn = new JButton("전체");
		listallbtn.setBounds(310, 0, 60, 20);
		TrainerListButtonpnl.add(listallbtn);

		// 트레이너 리스트
		List<JPanel> list = new ArrayList<JPanel>();
		JPanel listpnl = new JPanel();
		JLabel listlblnum = new JLabel("번호");
		JLabel listlblname = new JLabel("이름");
		JLabel listlblphone = new JLabel("전화번호");
		JLabel listlblgender = new JLabel("성별");
		JLabel listlblbirth = new JLabel("생년월일");
		JLabel listlblStart_date = new JLabel("임관일");
		JLabel listlbladdress = new JLabel("주소");

		listpnl.setLayout(new GridLayout(0, 7));
		listpnl.add(listlblnum);
		listlblnum.setBounds(400, 50, 100, 30);
		listpnl.add(listlblname);
		listlblname.setBounds(450, 50, 50, 30);
		listpnl.add(listlblbirth);
		listlblphone.setBounds(500, 50, 100, 30);
		listpnl.add(listlbladdress);
		listlblgender.setBounds(550, 50, 50, 30);
		listpnl.add(listlblphone);
		listlblbirth.setBounds(600, 50, 50, 30);
		listpnl.add(listlblgender);
		listlblStart_date.setBounds(700, 50, 100, 30);
		listpnl.add(listlblStart_date);
		listlbladdress.setBounds(800, 50, 100, 30);
		listpnl.setBounds(400, 100, 900, 550);

		listsearchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Trainer> list2 = new ArrayList<>();
				TrainerDB db = new TrainerDB();
				list2 = db.selectAll();
				// 리스트에 리스트2를 넣어라.
				int a = 250;
				int b = 50;
				for (int i = 0; i < list2.size(); i++) {
					JLabel listlblnum2 = new JLabel(String.valueOf(i + 1));
					listlblnum2.setBounds(400, a += b, 50, 50);
					JLabel listlblname2 = new JLabel(list2.get(i).getName());
					listlblname2.setBounds(450, a += b, 50, 50);
					JLabel listlblPhone2 = new JLabel(list2.get(i).getPhone());
					listlblPhone2.setBounds(500, a += b, 100, 50);
					JLabel listlblgender2 = new JLabel(list2.get(i).getGender());
					listlblgender2.setBounds(550, a += b, 50, 50);
					JLabel listlblbirth2 = new JLabel(list2.get(i).getBirth());
					listlblbirth2.setBounds(600, a += b, 50, 50);
					JLabel listlblStart_date2 = new JLabel(String.valueOf(list2.get(i).getStart_date()));
					listlblStart_date2.setBounds(700, a += b, 100, 50);
					JLabel listlbladdress2 = new JLabel(list2.get(i).getAddress());
					listlbladdress2.setBounds(800, a += b, 100, 50);

					listpnl.add(listlblnum2);
					listpnl.add(listlblname2);
					listpnl.add(listlblPhone2);
					listpnl.add(listlblbirth2);
					listpnl.add(listlblgender2);
					listpnl.add(listlblStart_date2);
					listpnl.add(listlbladdress2);
					list.add(listpnl);
				}
				listpnl.revalidate();
				listpnl.repaint();
			}
		});

		// 등록 버튼
		JButton rgbtn = new JButton("등록");
		rgbtn.setBounds(50, 570, 60, 25);
		TrainerInputpnl.add(rgbtn);
		rgbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result = 0;
				int trainer = 0;
				String name = nametf.getText();
				String gender = "남";
				String birth = birthtf.getText();
				String phone = phonetf.getText();
				String address = adtf.getText();

				Trainer tr = new Trainer(name, phone, gender, birth, address);
				TrainerDB db = new TrainerDB();
				result = db.insert(tr);
				System.out.println(result);

				list2(list, listpnl, db);
				listpnl.revalidate();
				listpnl.repaint();
			}

		});
		
		TrainerDB db = new TrainerDB();
		TrainerListSQLpnl.setBounds(50, 180, 800, 400);
		TrainerListSQLpnl.setLayout(null);
		
		
		
//		TrainerListSQLpnl.add(list2(list, listpnl, db));
//		TrainerListSQLpnl.add(list2(list, listpnl, db));
		TrainerListpnl.add(TrainerListSQLpnl);
//		String[] columns;
//		columns = new String[] { "번호", "이름", "나이", "성별", "전화번호", "메일", "주소" };
//		List<String[]> rows;
//		rows = dao.getListById(id); // 데이터 튜플(ArrayList)
//		JScrollPane scrollpane = new JScrollPane();
		// jTable1 생성
//		JTable jtable = new JTable();
//		model = new DefaultTableModel(data, columns); // 테이블에 붙일 모델 객체 생성(데이터, 컬럼)
//		jtable = new javax.swing.JTable(model);	// 테이블 객체 생성
//		scrollpane.setViewportView(jtable); // 패널에 테이블 붙이기

		// jTable1 컬럼 별 정렬 기능 추가
//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jtable.getModel());
//		jtable.setRowSorter(sorter);

		// 스크롤바
//		Frame frame = new Frame("테스트");
//		JScrollPane scrollpane = new JScrollPane();
//		frame.add(scrollpane);
		Inputpnl.setLayout(null);
		Inputpnl.setSize(1500, 700);

		TrainerInputpnl.setLayout(null);
		TrainerInputpnl.setBounds(30, 50, 400, 610);
		Inputpnl.add(TrainerInputpnl);

		Inputpnl.add(TrainerInputpnl);
		Inputpnl.add(TrainerListpnl);

		// 남은거 하나 = 50 200 1000 500
		// TrainerListButtonpnl = 50,50 1000 100
		// TrainerListpnl = 500,50, 1000, 700
		// trainerinput =50,50 400, 700
		// inputpnl = 전체 페널 크기 =1500,700
//		getContentPane().add(Inputpnl);

		add(Inputpnl);
		setSize(1500, 730);
	}

	public static void main(String[] args) {
		new trainerInput().setVisible(true);
	}

}
