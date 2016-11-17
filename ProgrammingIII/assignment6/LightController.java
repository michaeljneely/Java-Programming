package assignment6;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 6 - October 21st, 2016
//Formatted with Eclipse ctrl+shift+f

//4 rows x 2 columns GridBagLayout JFrame
//Resizable
@SuppressWarnings("serial")
public class LightController extends JFrame implements ActionListener {

	// Status Variables
	private boolean on = false; // current status of lights
	private String operationsMode = "manual"; // current operational mode of
												// system

	// GUI Components
	private ButtonGroup modeGroup;
	private Container container;
	private GridBagLayout layout;
	private GridBagConstraints gbcLightPanel, gbcIntensityLabel, gbcOnOffButton, gbcSlider, gbcModeLabel, gbcRadioPanel,
			gbcdisplayStatusButton, gbcStatusText;
	private JButton onOffButton, displayStatusButton;
	private JLabel intensityLabel, modeLabel;
	private JPanel lightPanel, radioPanel;
	private JRadioButton manualRadioButton, timedRadioButton;
	private JSlider slider;
	private JTextArea statusText;

	public LightController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Window Setup
		container = this.getContentPane();
		this.setTitle("Light Control System");
		this.setBounds(100, 100, 600, 231);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set GridBag Layout 4xRows, 2xColumns
		layout = new GridBagLayout();
		layout.columnWeights = new double[] { 1.0, 1.0 };
		layout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		container.setLayout(layout);

		// Lights - First Row First Column
		// Background Color is Black - Indicates Lights Off
		// Background Color is Yellow - Indicates Lights On
		lightPanel = new JPanel();
		lightPanel.setBackground(Color.BLACK); // off to start
		lightPanel.setForeground(Color.BLACK);
		gbcLightPanel = new GridBagConstraints();
		gbcLightPanel.insets = new Insets(0, 0, 5, 0);
		gbcLightPanel.fill = GridBagConstraints.BOTH;
		gbcLightPanel.gridx = 0;
		gbcLightPanel.gridy = 0;
		getContentPane().add(lightPanel, gbcLightPanel);

		// Intensity Label - First Row Second Column
		intensityLabel = new JLabel("Light Intensity Level");
		intensityLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		gbcIntensityLabel = new GridBagConstraints();
		gbcIntensityLabel.insets = new Insets(0, 0, 5, 0);
		gbcIntensityLabel.gridx = 1;
		gbcIntensityLabel.gridy = 0;
		container.add(intensityLabel, gbcIntensityLabel);

		// On/Off Button - Second Row First Column
		onOffButton = new JButton("Turn On");
		onOffButton.setFont(new Font("SansSerif", Font.PLAIN, 24));
		onOffButton.addActionListener(this);
		gbcOnOffButton = new GridBagConstraints();
		gbcOnOffButton.insets = new Insets(0, 0, 5, 5);
		gbcOnOffButton.fill = GridBagConstraints.BOTH; // expand/contract both
														// ways on window resize
		gbcOnOffButton.gridx = 0;
		gbcOnOffButton.gridy = 1;
		container.add(onOffButton, gbcOnOffButton);

		// Light Intensity Slider -> 1 to 5 with ticks, labels and snapping
		// Second Row Second Column
		slider = new JSlider();
		slider.setValue(1);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setMinimum(1);
		slider.setMaximum(5);
		// Add Change Listener to Print Out Light Intensity Changes
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// Only Print Change Once Per Adjustment
				if (!slider.getValueIsAdjusting()) {
					System.out.println("Light Intensity Slider Action Event Handler Called. "
							+ "Light Intensity Level changed to " + slider.getValue());
				}

			}
		});
		gbcSlider = new GridBagConstraints();
		gbcSlider.insets = new Insets(0, 0, 5, 0);
		gbcSlider.fill = GridBagConstraints.BOTH;
		gbcSlider.gridx = 1;
		gbcSlider.gridy = 1;
		container.add(slider, gbcSlider);

		// Mode of Operations Label - Third Row First Column
		modeLabel = new JLabel("Mode");
		modeLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		gbcModeLabel = new GridBagConstraints();
		gbcModeLabel.insets = new Insets(0, 0, 5, 5);
		gbcModeLabel.gridx = 0;
		gbcModeLabel.gridy = 2;
		container.add(modeLabel, gbcModeLabel);

		// JPanel to Hold Radio Buttons - Third Row Second Column
		radioPanel = new JPanel();
		gbcRadioPanel = new GridBagConstraints();
		gbcRadioPanel.insets = new Insets(0, 0, 5, 0);
		gbcRadioPanel.fill = GridBagConstraints.HORIZONTAL; // only expand
															// horizontally on
															// window resize
		gbcRadioPanel.gridx = 1;
		gbcRadioPanel.gridy = 2;
		container.add(radioPanel, gbcRadioPanel);
		// Add Radio Buttons to Radio Panel
		manualRadioButton = new JRadioButton("Manual", true); // Have manual
																// selected as
																// default
		manualRadioButton.addActionListener(this);
		radioPanel.add(manualRadioButton);
		timedRadioButton = new JRadioButton("Timed");
		timedRadioButton.addActionListener(this);
		radioPanel.add(timedRadioButton);
		// Put into a Button Group so only one can be selected at a time.
		modeGroup = new ButtonGroup();
		modeGroup.add(manualRadioButton);
		modeGroup.add(timedRadioButton);

		// Display Status Button - Fourth Row First Column
		displayStatusButton = new JButton("Display Status");
		displayStatusButton.setFont(new Font("SansSerif", Font.PLAIN, 24));
		displayStatusButton.addActionListener(this);
		gbcdisplayStatusButton = new GridBagConstraints();
		gbcdisplayStatusButton.insets = new Insets(0, 0, 0, 5);
		gbcdisplayStatusButton.fill = GridBagConstraints.BOTH;
		gbcdisplayStatusButton.gridx = 0;
		gbcdisplayStatusButton.gridy = 3;
		container.add(displayStatusButton, gbcdisplayStatusButton);

		// Status Text Area - Fourth Row Second Column
		statusText = new JTextArea();
		statusText.setText("Click 'Display Status' to See the System Status");
		statusText.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gbcStatusText = new GridBagConstraints();
		gbcStatusText.fill = GridBagConstraints.HORIZONTAL;
		gbcStatusText.gridx = 1;
		gbcStatusText.gridy = 3;
		container.add(statusText, gbcStatusText);
	}

	// Action Event Handler
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == onOffButton) {
			if (on) {
				on = false;
				onOffButton.setText("Turn On");
				lightPanel.setBackground(Color.BLACK);
				System.out.println("On/Off Button Action Event Handler Called. The Lights have been turned off");

			} else {
				on = true;
				onOffButton.setText("Turn Off");
				lightPanel.setBackground(Color.YELLOW);
				System.out.println("On/Off Button Action Event Handler Called. The Lights have been turned on");
			}
		} else if (e.getSource() == manualRadioButton) {
			// Only change to manual if it isn't in manual already
			if (!operationsMode.equals("manual")) {
				operationsMode = "manual";
				System.out
						.println("Manual Radio Button Action Event Handler Called. Operational Mode Changed to Manual");
			}
			// If already in manual mode, say so
			else {
				System.out
						.println("Manual Radio Button Action Event Handler Called. Operational Mode Already is Manual");
			}

		} else if (e.getSource() == timedRadioButton) {
			// Only change to timed if it isn't in timed already
			if (!operationsMode.equals("timed")) {
				operationsMode = "timed";
				System.out.println("Timed Radio Button Action Event Handler Called. Operational Mode Changed to Timed");
			}
			// If already in timed mode, say so
			else {
				System.out.println("Timed Radio Button Action Event Handler Called. Operational Mode Already is Timed");
			}
		} else if (e.getSource() == displayStatusButton) {
			String powerStatus = (on) ? "on" : "off"; // get power status as
														// string
			System.out.println("Display Status Button Action Event Handler Called \nLights Status: " + powerStatus
					+ "\nLight Intensity is: " + slider.getValue() + "\nMode is: " + operationsMode);
			statusText.setText("Lights Status: " + powerStatus + "\nLight Intensity is: " + slider.getValue()
					+ "\nMode is: " + operationsMode);
		}

	}

	public static void main(String[] Args) {
		LightController l = new LightController();
		l.setVisible(true);
	}
}
