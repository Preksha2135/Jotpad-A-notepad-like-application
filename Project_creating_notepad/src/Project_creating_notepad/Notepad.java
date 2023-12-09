package Project_creating_notepad;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notepad extends JFrame implements ActionListener{
	
	public static final String FILE_NAME= "myfile.txt";
	JTextArea text = null;
	
	JPanel mainPanel, buttonsPanel;
	JScrollPane scrollPane;
	//Constructor
	public Notepad() {
		super();
		initializeComponents(); //user function
		setSize(1000,1000); //built-in
		setTitle("JOTPAD"); //built-in
		setVisible(true); //built-in
	}

	private void initializeComponents() {
		this.setLayout(new GridLayout(2,2,3,4));
		
		//main panel
		mainPanel=new JPanel(new BorderLayout());
		text = new JTextArea(50,80);
		scrollPane=new JScrollPane(text);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(new Label(""), BorderLayout.NORTH);
		mainPanel.add(new Label(""), BorderLayout.SOUTH);
		mainPanel.add(new Label(""), BorderLayout.EAST);
		mainPanel.add(new Label(""), BorderLayout.WEST);
		
		//buttons panel
		buttonsPanel = new JPanel();
		JButton btnSave=new JButton("Save File");
		JButton btnOpen=new JButton("Open File");
		JButton btnExit=new JButton("Exit");
		btnSave.addActionListener(this);
		btnOpen.addActionListener(this);
		btnExit.addActionListener(this);
		buttonsPanel.add(btnSave);
		buttonsPanel.add(btnOpen);
		buttonsPanel.add(btnExit);
		
		//adding the 2 panels to the frame
		add(mainPanel);
		add(buttonsPanel);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String strAction=e.getActionCommand();
		if(strAction.equalsIgnoreCase("Exit")) {
			this.dispose();	
		}
		if(strAction.equalsIgnoreCase("Save File")) {
			this.saveFile();
		}
		if(strAction.equalsIgnoreCase("Open File")) {
			this.openFile();	
		}
		
	}

	private void saveFile() {
		try {
			FileOutputStream fos=new FileOutputStream(FILE_NAME, true);
			String strContent = text.getText();
			char ch;
			for(int i=0;i<strContent.length(); i++) {
				ch=strContent.charAt(i);
				fos.write(ch);
			}
			fos.close();
			text.setText("");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void dispose() {
		super.dispose();
		System.exit(0);
	}

	private void openFile() {
		try {
			FileInputStream fis=new FileInputStream(FILE_NAME);
			StringBuffer strContent=new StringBuffer("");
			char ch;
			while(fis.available()>0) {
				ch=(char)fis.read();
				strContent.append(ch);
			}
			text.setText(strContent.toString());
			fis.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new Notepad();
	}
}
