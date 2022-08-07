package notepad2;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Notepad2 extends JFrame implements ActionListener  
{
	static int count =2;
	JTextArea t;
        JLabel l=new JLabel();

	
	JFrame f;

	// Constructor
	Notepad2(String fname)
	{
		f = new JFrame(fname);
		

		try {
			//String s = javax.swing.plaf.metal.icons;
			// Set metal look and feel 
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

			// Set theme to ocean
			//MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
		}

		// Text component
		 

		// Create a menubar
		JMenuBar mb = new JMenuBar();

		// Create amenu for menu
		JMenu m1 = new JMenu("File");

		// Create menu items
		JMenuItem mi1 = new JMenuItem("New");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		JMenuItem mi9 = new JMenuItem("Print");
       
		// Add action listener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);

		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);

		// Create amenu for menu
		JMenu m2 = new JMenu("Edit");

		// Create menu items
		JMenuItem mi4 = new JMenuItem("cut");
		JMenuItem mi5 = new JMenuItem("copy");
		JMenuItem mi6 = new JMenuItem("paste");
                JMenuItem mi7=new JMenuItem("Insert");

		// Add action listener
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
                mi7.addActionListener(this);

		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);
                m2.add(mi7);

		JMenuItem mc = new JMenuItem("About");

		mc.addActionListener(this);

		mb.add(m1);
		mb.add(m2);
		mb.add(mc);
		
		JScrollPane js = new JScrollPane(t);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		f.getContentPane().add(js);

		f.setJMenuBar(mb);
		
		//f.add(t);
	
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);// Place at centre
	
		f.setVisible(true);
	}
        public ImageIcon ResizeImage(String ImagePath)
        {
            ImageIcon MyImage=new ImageIcon(ImagePath);
            
            Image img=MyImage.getImage();
            Image newImg=img.getScaledInstance(t.getWidth(),t.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon image=new ImageIcon(newImg);
            return image;
                
            
        }

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();

		if (s.equals("cut")) {
			t.cut();
		}
                else if(s.equals("Insert"))
                    
                {
                    try
                    {
                        JFileChooser j = new JFileChooser();
                        j.setCurrentDirectory(new File(System.getProperty("user.home")));
                        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.Images","jpg","gif","png");
                        j.addChoosableFileFilter(filter);
                        int result =j.showSaveDialog(null);
                        if(result==JFileChooser.APPROVE_OPTION)
                        {
                            File selectedFile=j.getSelectedFile();
                            String path=selectedFile.getAbsolutePath();
                            l.setIcon(ResizeImage(path));
                            t.add(l);
                        
                        }
                        else if(result==JFileChooser.CANCEL_OPTION)
                        {
                            System.out.println("No file Choosen");
                        }
                        
                    }
                    catch(Exception evt)
                        {
                              JOptionPane.showMessageDialog(f, evt.getMessage());  
                        }
                }
		else if (s.equals("copy")) {
			t.copy();
		}
		else if (s.equals("paste")) {
			t.paste();
		}
		else if (s.equals("Save")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsSaveDialog function to show the save dialog
			int r = j.showSaveDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {

				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					//  file writer
					FileWriter wr = new FileWriter(fi, false);

					// buffered writer to write
					BufferedWriter w = new BufferedWriter(wr);

					// Write
					w.write(t.getText());

					w.flush();
					w.close();
					
					f.setTitle((j.getSelectedFile()).getName() );
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			
			else
				JOptionPane.showMessageDialog(f, "Do you don't want to save changes in "+f.getTitle()+" ?");
		}
		else if (s.equals("Print")) {
			try {
				// print the file
				t.print();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		else if (s.equals("Open")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsOpenDialog function to show the save dialog
			int r = j.showOpenDialog(null);

			// If the user selects a file
			if (r == JFileChooser.APPROVE_OPTION) {
				// Set the label to the path of the selected directory
				String foname = (j.getSelectedFile()).getName();
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// String
					String s1 = "", sl = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					// Initialize sl
					sl = br.readLine();

					// Take the input from the file
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}

					// Set the text
					Notepad2 on = new Notepad2(foname);
					on.t.setText(sl);
					on.f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
		}
		else if (s.equals("New")) 
		{
		    String s2 = "Untitled"+ Integer.toString(count)+".txt";
		    count+=1;
		    Notepad2 n = new Notepad2(s2);
		}
		else if (s.equals("About")) 
		{
			JFrame fab = new JFrame("About");
			JTextArea jab  = new JTextArea();
			jab.setText("Updated Notepad...\n\nApplication devoloped by Gnanagurvignesh & Balamuralikrishna R N");
			fab.add(jab);
			fab.setSize(400,200);
			fab.setLocationRelativeTo(null);
			fab.setVisible(true); 
            
		}
	}
	
	public static void main(String[] args) 
	{
		Notepad2 n2 = new Notepad2("Untitled.txt");
		n2.f.dispose();
		Notepad2 n3 = new Notepad2("Untitled.txt");
		n3.f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		while(true)
		{
			int fno=0;
			Frame[] allframe = Frame.getFrames();
			for(Frame fr : allframe)
			{
				if(fr.isShowing())
				{
					fno+=1;
					//System.out.println(fr.getTitle()+"\n");
					//fr.dispose();
				}
				
				
			}
			if(fno==0)
				System.exit(0);	
		}
	}
}


