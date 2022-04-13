import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI implements ActionListener{
	private JTextField textField;
	private int n;
	ArrayList<Integer> list;
	
	
	public GUI() {
		JFrame frame = new JFrame("Algorithms");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)screenSize.getWidth()/2, (int) screenSize.getHeight()/2);
		frame.setLocation((int) screenSize.getWidth()/4, (int) screenSize.getHeight()/4);
		
		JButton quickSort = new JButton("Quick Sort");
		JButton mergeSort = new JButton("Merge Sort");
		JButton bubbleSort = new JButton("Bubble Sort");
		JButton insertionSort = new JButton("Insertion Sort");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel();
		JPanel graph = new JPanel();
		JPanel number = new JPanel();
		
		JLabel label = new JLabel("Enter a Number");
		textField = new JTextField(20);
		JButton submit = new JButton("Submit");
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list = new ArrayList<Integer>();
				textArea.setText("");
				String str = textField.getText();
				String regex = "[0-9]+";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(str);
				if(m.matches()) n = Integer.parseInt(str);
				else System.out.println("Not All Number");
				
				for(int i = 0; i < n; i++) {
					list.add(new Random().nextInt(100)+1);
				}

				for(int i: list) {
					for(int j = 0; j < i; j++) {
						textArea.append("X");
					}
					textArea.append("\n");
				}
			}
		});
		quickSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				quickSort(list, 0, n-1);
			}
			
			private void swap(ArrayList<Integer> list, int i, int j) {
				int temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
				
				textArea.setText("");
				
				for(int num: list) {
					for(int start = 0; start < num; start++) {
						textArea.append("X");
					}
					textArea.append("\n");
				}
			}
			
			private int partition(ArrayList<Integer> list, int low, int high) {
				int pivot = list.get(high);
				int i = (low-1);
				
				for(int j = low; j <= high -1; j++) {
					if(list.get(j) < pivot) {
						i++;
						swap(list, i, j);
					}
				}
				
				swap(list, i+1, high);
				return (i+1);
			}

			private void quickSort(ArrayList<Integer> list, int low, int high) {
				if( low < high) {
					int pi = partition(list, low, high);
					
					quickSort(list, low, pi-1);
					quickSort(list, pi+1, high);
				}
			}
		});
		
		buttons.setBackground(Color.yellow);
		number.setBackground(Color.cyan);
		
		buttons.add(quickSort);
		buttons.add(mergeSort);
		buttons.add(bubbleSort);
		buttons.add(insertionSort);
		
		graph.add(textArea);
		
		number.add(label);
		number.add(textField);
		number.add(submit);
		
		mainPanel.add(buttons, BorderLayout.PAGE_START);
		mainPanel.add(graph, BorderLayout.CENTER);
		mainPanel.add(number, BorderLayout.SOUTH);
		

		frame.add(mainPanel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Am I here?");
		
	}

}
