package members;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import files.EncodeDecode;
import lombok.Data;

@Data
public class MemberInput extends JPanel {
	private JTextField nametf;
	private ButtonGroup bg;
	private JTextField birthtf;
	private JTextField phonetf;
	private JTextField memberNumtf;
	private JTextField adtf;
	private JLabel imlbl;
	private EncodeDecode inde;
	private File file;
	private ImageIcon imag;
	private MemberInput input;
	private JLabel birthlbl;
	private JLabel phonelbl;
	private JLabel memberNumlbl;
	private JLabel adlbl;
	private JButton imbtn;
	private JButton rgbtn;
	private JButton retouchbtn;
	private JButton deletebtn;
	private JButton resetbtn;
	private MemberDAO mDAO = new MemberDAOImpl();
	private JRadioButton rd;
	private JRadioButton rd2;

	public MemberInput(MemberGUIApp guiApp) {
		JLabel namelbl = new JLabel("이름");
		nametf = new JTextField(5);
		rd = new JRadioButton("남성", true);
		rd2 = new JRadioButton("여성");
		bg = new ButtonGroup();
		bg.add(rd);
		bg.add(rd2);

		namelbl.setBounds(50, 50, 50, 20);
		nametf.setBounds(100, 50, 100, 20);
		rd.setBounds(220, 50, 60, 20);
		rd2.setBounds(280, 50, 60, 20);

		birthlbl = new JLabel("생년월일");
		birthtf = new JTextField(3);
		birthlbl.setBounds(50, 100, 100, 20);
		birthtf.setBounds(110, 100, 60, 20);
		phonelbl = new JLabel("전화번호");
		phonetf = new JTextField(12);
		phonelbl.setBounds(180, 100, 70, 20);
		phonetf.setBounds(240, 100, 90, 20);
		memberNumlbl = new JLabel("등록 번호");
		memberNumtf = new JTextField(20);
		memberNumlbl.setBounds(50, 150, 100, 20);
		memberNumtf.setBounds(130, 150, 200, 20);

		adlbl = new JLabel("주소");
		adtf = new JTextField(30);
		adlbl.setBounds(50, 200, 70, 20);
		adtf.setBounds(100, 200, 230, 20);
		imlbl = new JLabel("이미지 선택", SwingConstants.CENTER);
		imlbl.setBounds(80, 250, 250, 250);
		imlbl.setOpaque(true); // 배경색을 불투명하게 만들어서 색 넣을 수 있께함.
		imlbl.setBackground(Color.WHITE);

		imbtn = new JButton("이미지 파일 등록");
		imbtn.setBounds(100, 520, 200, 25);
		imbtn.addActionListener(new ActionListener() {
			// 이미지 파일 넣는 버튼리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setBounds(100, 520, 200, 25);
				add(filechooser);
				int result = filechooser.showOpenDialog(filechooser);
				if (result == filechooser.APPROVE_OPTION) {
					file = filechooser.getSelectedFile();
					imag = new ImageIcon(file.getPath());
					imlbl.setIcon(imag);
				}
			}
		});

		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), "사용자 정보입력");

		setBorder(oneTb);
		setBounds(30, 50, 100, 100);

		rgbtn = new JButton("등록");
		rgbtn.setBounds(50, 570, 60, 25);
		rgbtn.addActionListener(guiApp);

		retouchbtn = new JButton("수정");
		retouchbtn.setBounds(110, 570, 60, 25);
		retouchbtn.addActionListener(guiApp);
	//	retouchbtn.setEnabled(false);
		deletebtn = new JButton("삭제");
		deletebtn.setBounds(170, 570, 60, 25);
		deletebtn.setEnabled(false);
		resetbtn = new JButton("초기화");
		resetbtn.setBounds(230, 570, 100, 25);
		add(namelbl);
		add(nametf);
		add(rd);
		add(rd2);
		add(birthlbl);
		add(birthtf);
		add(phonelbl);
		add(phonetf);
		add(memberNumlbl);
		add(memberNumtf);
		add(adlbl);
		add(adtf);
		add(imlbl);
		add(imbtn);
		add(rgbtn);
		add(retouchbtn);
		add(deletebtn);
		add(resetbtn);

		setLayout(null);
		setSize(400, 700);

	}
}
