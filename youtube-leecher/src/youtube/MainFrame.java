package youtube;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JList;

public class MainFrame implements ActionListener {

	private JFrame frame;
	private JButton btnNewButton;
	private JTextField txtHttpwwwtabnakir;
	private TextArea textArea;
	private JLabel lblResult;
	private JPanel panel;
	private JLabel label;
	private JCheckBox chckbxShowPreview;
	private JPanel panel_1;
	private JCheckBox chckbxGetVideoSize;
	private JList<String> list;
    private DefaultListModel<String> model;
    
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1131, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("Get Link");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 158, 113, 23);
		
		frame.getContentPane().add(btnNewButton);

		txtHttpwwwtabnakir = new JTextField();
		txtHttpwwwtabnakir.setText("https://www.youtube.com/watch?v=wAmq8eIkdI8&amp");
		txtHttpwwwtabnakir.setBounds(10, 11, 655, 20);
		frame.getContentPane().add(txtHttpwwwtabnakir);
		txtHttpwwwtabnakir.setColumns(10);

		textArea = new TextArea();
		textArea.setBounds(10, 384, 1103, 195);
		frame.getContentPane().add(textArea);

		lblResult = new JLabel("Result");
		lblResult.setBounds(10, 364, 73, 14);
		frame.getContentPane().add(lblResult);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(292, 55, 373, 303);
		frame.getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBorder((Border) new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 55, 241, 92);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		chckbxShowPreview = new JCheckBox("Show Preview");
		chckbxShowPreview.setBounds(6, 20, 143, 23);
		panel_1.add(chckbxShowPreview);
		
		chckbxGetVideoSize = new JCheckBox("Get Video Size");
		chckbxGetVideoSize.setSelected(true);
		chckbxGetVideoSize.setBounds(6, 46, 143, 23);
		panel_1.add(chckbxGetVideoSize);
		
	    model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setBounds(10, 201, 241, 152);
		frame.getContentPane().add(list);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		
		case "Get Link":
			
			//ساخته شده youtube_search در اینجا یک متغیر از نوع کلاس   
			youtube_source youtube_source;
			
			try {
				
				//در اینجا آبجکت بالایی نمونه سازی شده و تکست داخل تکست باکس به عنوان ارگومان به کانستراکتور کلاس فرستاده شده 
			    youtube_source = new youtube_source((txtHttpwwwtabnakir.getText()));
				
			    // در اینجا تابع دیتکیت سیستم پروکسیز فراخانی شده
			    youtube_source.detect_system_proxies();
			    
			    
			    // در اینجا تابع اکسترکت یو آر الز فراخانی شده و یو ار ال های استخراج شده در یک ارایه از نوع رشته قرار میکیرن
			    // دقت کنید که یو ار ال های استخراج شده باید دیکد بشن
				
			    ArrayList<String> urlsss = new ArrayList<String>();
			    urlsss = youtube_source.extract_urls();
				
				int i = 1;
				for (String string : urlsss) {
					
                    int size = 0;
					string = URLDecoder.decode(string, "UTF-8");
				    
					textArea.append("Link" + i + "\r\n");
					textArea.append(string + "\r\n" + "-------------------------------------" + "\r\n");
					
					if (chckbxGetVideoSize.isSelected() == true) {
						
						size =  youtube_source.Get_File_Size(string)/1048576;
						
						model.addElement("link" + i + " " + size + " MB");
						
					} else {
						
						model.addElement("link" + i + " Size not known" );
					}
					
					i++;
					
				}
				
				if (chckbxShowPreview.isSelected() == true) {
					
				
				/*
				در بخش زیر هم ادرس عکس فیلمی که میخاد
				دانلود بشه توسط تابع گت پیک پیدا میشه 
				و روی پنل نمایش داده میشه
				 */
				Image img = youtube_source.get_pic();
				ImageIcon icon = new ImageIcon(img);
			    img = icon.getImage();
				Image newimg = img.getScaledInstance(panel.getWidth(), panel.getHeight(),  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newicon = new ImageIcon(newimg);
				if (label == null) {
					
					    label = new JLabel("", newicon, JLabel.CENTER);
						label.setSize(panel.getWidth(), panel.getHeight());
						panel.add( label, BorderLayout.CENTER );
						panel.revalidate();
						panel.repaint();
					
				} else {
					label.setIcon(newicon);
					panel.revalidate();
					panel.repaint();
					
				   }
				}
				
			} catch (Exception e2) {

				System.out.println(e2.getMessage());
			}
			break;

		default:
			break;
		}
	}
}
