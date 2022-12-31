package Login;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLogo extends JPanel {
		
		public PanelLogo() {
			
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();
			setBackground(new Color (0,0,0,0));
			
		
			ImageIcon Icon=new ImageIcon("Immagini/logo2.png");
			Image image = Icon.getImage();
			Image newimg = image.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);  
			Icon = new ImageIcon(newimg);
			
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel l= new JLabel(Icon);
			c.insets= new Insets (20,40,0,0);
			c.weightx=0.5;
			c.gridx=0;
			c.gridy=0;
			add(l,c);
			
			
		}

	}




