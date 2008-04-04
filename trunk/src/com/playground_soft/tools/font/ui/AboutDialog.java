/* Playground Soft -- Font Converter
 * (c)2008 Wutipong Wongsakuldej aka 'mr_tawan'.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.playground_soft.tools.font.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class AboutDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -158608940307774754L;
	private JButton okBtn;
	private JTextArea copyrightTxa;
	private JTextArea licenseTxa;
	public AboutDialog(JFrame parent){
		super(parent,true);
		this.setTitle("About...");
		copyrightTxa = new JTextArea("Playground Soft -- Font Converter\n"+
				"(c)2008 Wutipong Wongsakuldej aka 'mr_tawan'.\n" +
				"Licensed under GNU General Public License version 2. ");
		copyrightTxa.setEditable(false);
		
		if(gplv2 == null){
			StringBuilder sb = new StringBuilder();
			try {
				BufferedReader reader = 
					new BufferedReader(new FileReader("gpl-2.0.txt"));
				String line = null;
				while((line = reader.readLine())!=null){
					sb.append(line+"\n");
				}
				gplv2 = sb.toString();
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		licenseTxa = new JTextArea(gplv2);
		licenseTxa.setFont(Font.getFont(Font.MONOSPACED));
		okBtn = new JButton("OK");
		this.setResizable(false);
		this.getContentPane().setLayout( new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		this.getContentPane().add(copyrightTxa,constraints);
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JScrollPane scroller = new JScrollPane(licenseTxa);
		scroller.setPreferredSize(new Dimension(500,500));
		this.getContentPane().add(scroller,constraints);
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.NONE;
		this.getContentPane().add(okBtn,constraints);
		okBtn.addActionListener(this);
		this.pack();
	}

	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
	
	private static String gplv2 = null;
}
