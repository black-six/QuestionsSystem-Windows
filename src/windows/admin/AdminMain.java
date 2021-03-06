package windows.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import sql.AdminMainDAO;
import sql.UpDataUserDAO;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class AdminMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private AdminMain main;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMain() {
		setTitle("\u7BA1\u7406\u5458");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMain.class.getResource("/windows/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				null,
			new String[] {
				"\u5E10\u53F7", "\u5BC6\u7801", "\u59D3\u540D", "\u7C7B\u578B"
			}
		));
		contentPane.setLayout(gl_contentPane);
		
		loginThing();
		main = this;
	}

	private void loginThing() {
		setResizable(false);//???????????????
		setLocationRelativeTo(this);//????????????????????????
		showTableData();
		setVisible(true);
	}
	
	public void showTableData() {
		try {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setRowCount(0);//?????????
    		ResultSet rs = new AdminMainDAO().getAllUser();
            if (rs.next()) {
                do {
                    Vector v = new Vector();
                    v.add(rs.getString(1));
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < rs.getString(2).length(); i++) {
                    	sb.append("*");
					}
                    v.add(sb.toString());
                    v.add(rs.getString(3));
                    switch (rs.getInt(4)) {
						case 0:
							v.add("?????????");
							break;
						case 1:
							v.add("??????");
							break;
						case 2:
							v.add("??????");
							break;
					}
                    dtm.addRow(v);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "??????????????????????????????");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminMain.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	private void tableMouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu popMenu = new JPopupMenu();
            JMenuItem addMenu = new JMenuItem();
            JMenuItem updateMenu = new JMenuItem();
            JMenuItem deleteMenu = new JMenuItem();  
            JMenuItem refreshMenu = new JMenuItem();            

            int focusedRowIndex = table.rowAtPoint(e.getPoint());//???????????????????????????
            //????????????????????????????????????????????????  
            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
            
            addMenu.setText("????????????");
            addMenu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent aevt) {
                    new AddUser(main);
                }
            });
            
            updateMenu.setText("????????????");
            updateMenu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent aevt) {
                    int row = table.getSelectedRow();//??????????????????????????????????????????
                    new UpDataUser((String) table.getValueAt(row, 0), (String) table.getValueAt(row, 1),
                    				(String) table.getValueAt(row, 2),(String)table.getValueAt(row, 3),main);
                }
            });
            
            deleteMenu.setText("????????????");
            deleteMenu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent aevt) {
                	if (new AdminMainDAO().deleteUser((String) table.getValueAt(table.getSelectedRow(), 0))) {
						JOptionPane.showMessageDialog(null, "???????????????","??????",JOptionPane.INFORMATION_MESSAGE);
						showTableData();
					} else {
						JOptionPane.showMessageDialog(null, "???????????????","??????",JOptionPane.ERROR_MESSAGE);
					}
                }
            });
            
            refreshMenu.setText("??????");
            refreshMenu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent aevt) {
                    showTableData();
                }
            });
            
            popMenu.add(addMenu);
            popMenu.add(updateMenu);
            popMenu.add(deleteMenu);
            popMenu.add(refreshMenu);
            popMenu.show(table, e.getX(), e.getY());
        }
	}
}
