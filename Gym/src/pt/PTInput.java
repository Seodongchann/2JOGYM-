package pt;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Data;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@Data
public class PTInput extends JPanel {
	private JLabel nameLbl;
	private JTextField nameTf;
	private JLabel birthLbl;
	private JTextField birthTf;
	private JLabel enrollLbl;
	private JTextField enrollTf;
	private JLabel ptNumLbl;
	private JTextField ptNumTf;
	private JLabel trainerLbl;
	private JTextField trainerTf;
	private JLabel trainerIdLbl;
	private JTextField trainerIdTf;
	private JButton addBtn;
	private JButton upBtn;
	private JButton delBtn;
	private JButton reBtn;
	private JLabel timeLbl;
	private JTextField timeTf;
	private JLabel ptlbl;
	private JPanel pnls;
	private JTextField idTf;
	private JLabel idlbl;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	public PTInput(PTGUIApp guiapp) {
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(20, 0, 330, 400);

		nameLbl = new JLabel("이름");
		nameTf = new JTextField();
		birthLbl = new JLabel("생년월일");
		birthTf = new JTextField();
		enrollLbl = new JLabel("등록 코드");
		enrollTf = new JTextField();

		ptNumLbl = new JLabel("횟수");
		ptNumTf = new JTextField();
		ptNumTf.setText(String.valueOf(1));
		trainerLbl = new JLabel("트레이너");
		trainerTf = new JTextField();
		trainerIdLbl = new JLabel("트레이너 번호");
		trainerIdTf = new JTextField();
		ptlbl = new JLabel("날짜");
		pnls = new JPanel();
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		pnls.add(datePicker);
		timeLbl = new JLabel("시간");
		timeTf = new JTextField(2);
		idlbl = new JLabel("코드");
		idTf = new JTextField(2);
		ptlbl.setVisible(false);
		pnls.setVisible(false);
		timeLbl.setVisible(false);
		timeTf.setVisible(false);
		idlbl.setVisible(false);
		idTf.setVisible(false);

		addBtn = new JButton("등록");
		upBtn = new JButton("수정");
		upBtn.setEnabled(false);
		delBtn = new JButton("삭제");
		delBtn.setEnabled(false);
		reBtn = new JButton("초기화");

		nameLbl.setBounds(10, 50, 60, 20);
		nameTf.setBounds(80, 50, 60, 20);
		birthLbl.setBounds(10, 80, 60, 20);
		birthTf.setBounds(80, 80, 60, 20);
		enrollLbl.setBounds(10, 120, 60, 20);
		enrollTf.setBounds(80, 120, 60, 20);
		ptNumLbl.setBounds(10, 150, 60, 20);
		ptNumTf.setBounds(80, 150, 60, 20);
		trainerLbl.setBounds(10, 190, 60, 20);
		trainerTf.setBounds(80, 190, 60, 20);
		trainerIdLbl.setBounds(10, 220, 60, 20);
		trainerIdTf.setBounds(80, 220, 60, 20);
		ptlbl.setBounds(10, 250, 60, 30);
		pnls.setBounds(80, 250, 200, 30);
		timeLbl.setBounds(10, 290, 60, 20);
		timeTf.setBounds(80, 290, 60, 20);
		idlbl.setBounds(210, 290, 40, 20);
		idTf.setBounds(260, 290, 60, 20);

		addBtn.setBounds(10, 350, 60, 20);
		upBtn.setBounds(70, 350, 60, 20);
		delBtn.setBounds(130, 350, 60, 20);
		reBtn.setBounds(190, 350, 80, 20);

		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.gray), "PT권 구매");
		pnl.setBorder(oneTb);

		pnl.add(nameLbl);
		pnl.add(nameTf);
		pnl.add(birthLbl);
		pnl.add(birthTf);
		pnl.add(enrollLbl);
		pnl.add(enrollTf);
		pnl.add(ptNumLbl);
		pnl.add(ptNumTf);
		pnl.add(trainerLbl);
		pnl.add(trainerTf);
		pnl.add(trainerIdLbl);
		pnl.add(trainerIdTf);
		pnl.add(ptlbl);
		pnl.add(pnls);
		pnl.add(timeLbl);
		pnl.add(timeTf);
		pnl.add(idlbl);
		pnl.add(idTf);
		pnl.add(addBtn);
		pnl.add(upBtn);
		pnl.add(delBtn);
		pnl.add(reBtn);

		add(pnl);
		setLayout(null);
		setSize(400, 400);

		addBtn.addActionListener(guiapp);
		upBtn.addActionListener(guiapp);
		delBtn.addActionListener(guiapp);
		reBtn.addActionListener(guiapp);
	}

}
