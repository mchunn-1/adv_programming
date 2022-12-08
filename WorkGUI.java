package lab6;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class WorkGUI extends JFrame
{
	private static final long serialVersionUID = 13L;
	//sets up filed for user input
	static JTextField input = new JTextField("");
	//sets up area for primes to print in scroll pane 
	static JTextArea output = new JTextArea("");
	private JScrollPane outScroll = new JScrollPane(output);
	//sets up label for user input 
	private JLabel directions = new JLabel("<html>Enter an integer below and press start!<html>", SwingConstants.CENTER);
	//establishes buttons
	static JButton start = new JButton("Start");
	private JButton reset = new JButton("Reset");
	static JButton cancel = new JButton("Cancel");
	//sets label for time
	static JLabel time = new JLabel("", SwingConstants.CENTER);
	//sets up field to show running progress 
	static JTextField progress = new JTextField("");
	//shows user selected number of threads
	private JLabel threads = new JLabel("", SwingConstants.CENTER);
	//sets up variable for canceling  
	static boolean isCancel = false;
	private Integer threadCount = 0;
	//initiates timer (swing)
	Timer timer = new Timer(1000, new timeActionListener());
	int timeRun = 0;
	
	//method to reset GUI
	public void reset() 
	{
		//sets time back to zero 
		timeRun = 0;
		//clears input, progress, and output
		input.setText("");
		output.setText("");
		progress.setText("");
		//puts threads back to 0
		threads.setText("Number of Threads: ");
		threadCount = 0;
		//enables start button 
		start.setEnabled(true);
	}
	
	//keeps a running time 
	private class timeActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			timeRun++;
			//shows the time as it is running 
			time.setText(String.valueOf("Overall GUI running time: " + timeRun));
			//if 2 seconds have passed, show progress
			if(timeRun % 2 ==0)
			{
				WorkNoGUI.updater();
			}
		}
	}
	
	//allows cancel button to work 
	private class CancelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//changes cancel variable to true 
			isCancel = true;
			//timer stops and is reset 
			timer.stop();
			time.setText("");
			//cancel message is given in output 
			WorkNoGUI.canceled();
		}
	}
	
	//works as manager method 
	public class ctrlSub implements Runnable
	{
		int userNum;
		int threadCount;
		//takes current userNum and threadCount 
		public ctrlSub(int userNum, int threadCount)
		{
			this.userNum = userNum;
			this.threadCount = threadCount;
		}
		public void run() 
		{
			try 
			{
				//runs control method 
				WorkNoGUI.control(userNum, threadCount);
					
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			timer.stop();
			time.setText("Scroll for overall time taken by the thread(s)");
		}
	
	}
	
	
	public void getThreads() 
	{
		//ask user to input thread number to use 
		while(threadCount == 0)
		{
			threadCount = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of threads:"));
			//if the number is less than 1, ask to input above 0
			if(threadCount < 1)
			{
				threadCount = Integer.parseInt(JOptionPane.showInputDialog("Please enter a number greater than 0!"));
			}
			threads.setText("Number of Threads: " + threadCount.toString());
		}
		//ask user for the number 
		int userNum = Integer.parseInt(input.getText());
		//start the timer
		timer.start();
		//start thread on ctrlSub() method
		new Thread(new ctrlSub(userNum, threadCount)).start();
		
	
		
	}
	
	//enables start button functions 
	private class StartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			isCancel = false;
			start.setEnabled(false);
			cancel.setEnabled(true);
			getThreads();
		}
	}
	
	//enables reset button 
	private class ResetActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			timer.stop();
			reset();
		}
	}
	
	//adds panel to show the user selected number of threads and the timer 
	private JPanel timeBar()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 0));
		panel.add(threads);
		panel.add(time);
		return panel;
	}

	//adds user input with the timeBar() panel  
	private JPanel inputTime()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 0));
		panel.add(input);
		panel.add(timeBar());
		return panel;
	}
	
	//adds the directions label to inputTime() panel
	private JPanel north()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 0));
		panel.add(directions);
		panel.add(inputTime());
		return panel;
	}
	
	//adds panel to include output scroll and progress 
	private JPanel center()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 0));
		panel.add(progress);
		progress.setEditable(false);
		panel.add(outScroll);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		return panel;
	}
	
	//adds all buttons
	private JPanel south()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 3));
		panel.add(start);
		panel.add(reset);
		panel.add(cancel);
		start.addActionListener(new StartListener());
		cancel.addActionListener(new CancelListener());
		reset.addActionListener(new ResetActionListener());
		return panel;
	}

	//establishes GUI framework 
	public WorkGUI()
	{
		super("Very slow thing");
		setLocationRelativeTo(null);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(north(), BorderLayout.NORTH);
		getContentPane().add(center(), BorderLayout.CENTER);
		getContentPane().add(south(), BorderLayout.SOUTH);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new WorkGUI();
	}
}
