package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class DriverFrame extends JFrame implements ActionListener {
	private final int WIDTH = 200;
	private final int HEIGHT = 300;
	private final String[] BUTTON_LABELS = { "Add a Student",
			"Delete a Student", "Find a Student", "List the Students" };
	private final String[] ORDER_RADIO_LABELS = { "Ascending", "Descending" };
	private final String[] TYPE_RADIO_LABELS = { "ID", "First Name",
			"Last Name" };

	private JRadioButton[] orderRadio;
	private JRadioButton[] typeRadio;
	private JPanel bDiv;
	private JPanel tDiv;

	private ButtonGroup oRadio;
	private ButtonGroup tRadio;

	private DataStructure myStructure;
	
	public static void main(String[] args) {
		new DriverFrame().setVisible(true);

	}

	public DriverFrame() {
		super("311");

		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.GRAY);
		this.setLayout(new BorderLayout(10, 10));

		this.bDiv = new JPanel();
		this.tDiv = new JPanel();

		this.add(this.bDiv, BorderLayout.SOUTH);
		this.add(this.tDiv, BorderLayout.NORTH);

		this.tDiv.add(new JLabel("COSC 311 - Driver Program"));

		this.bDiv.setLayout(new BoxLayout(this.bDiv, BoxLayout.Y_AXIS));

		this.orderRadio = new JRadioButton[this.ORDER_RADIO_LABELS.length];
		this.typeRadio = new JRadioButton[this.TYPE_RADIO_LABELS.length];
		
		for (int i = 0; i < this.BUTTON_LABELS.length; i++) {
			if (i == this.BUTTON_LABELS.length - 1) {
				this.oRadio = new ButtonGroup();
				for (int j = 0; j < this.ORDER_RADIO_LABELS.length; j++) {
					this.orderRadio[j] = new JRadioButton(
							this.ORDER_RADIO_LABELS[j]);
					this.orderRadio[j].addActionListener(this);
					this.oRadio.add(this.orderRadio[j]);
					this.bDiv.add(this.orderRadio[j]);
				}
				this.orderRadio[0].setSelected(true);
				
				JSeparator sep = new JSeparator();
				sep.setMaximumSize(new Dimension(this.WIDTH * 2, 5));
				this.bDiv.add(sep);

				this.tRadio = new ButtonGroup();
				for (int j = 0; j < this.TYPE_RADIO_LABELS.length; j++) {
					this.typeRadio[j] = new JRadioButton(
							this.TYPE_RADIO_LABELS[j]);
					this.typeRadio[j].addActionListener(this);
					this.tRadio.add(this.typeRadio[j]);
					this.bDiv.add(this.typeRadio[j]);
				}
				this.typeRadio[0].setSelected(true);
			}
			JButton btn = new JButton(this.BUTTON_LABELS[i]);
			btn.addActionListener(this);
			btn.setMaximumSize(new Dimension(this.WIDTH, 25));
			this.bDiv.add(btn);
			
			this.myStructure = new DataStructure();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		switch (action) {
		case "Add a Student":
			break;
		case "Delete Student":
			break;
		case "Find a Student":
			break;
		case "List the Students":
			int o, t;
			for (o = 0; o < this.oRadio.getButtonCount(); o++) {
				if (this.orderRadio[o].equals(this.oRadio.getSelection())) {
					break;
				}
			}
			for (t = 0; t < this.tRadio.getButtonCount(); t++) {
				if (this.typeRadio[t].equals(this.tRadio.getSelection())) {
					break;
				}
			}
			this.myStructure.listIt(t, o == 0);
			break;
		}

	}
}
