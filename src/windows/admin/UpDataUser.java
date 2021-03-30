package windows.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.AddUserDAO;
import sql.UpDataUserDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpDataUser extends JFrame {

	private JPanel contentPane;
	private JTextField user_text;
	private JTextField pass_text;
	private JTextField name_text;
	private JComboBox comboBox;


	/**
	 * Create the frame.
	 */
	public UpDataUser(String user,String pass,String name,String type,AdminMain mainWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				mainWindow.showTableData();
			}
		});
		setTitle("\u4FEE\u6539\u5E10\u53F7");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpDataUser.class.getResource("/windows/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5E10\u53F7\uFF1A");
		
		user_text = new JTextField();
		user_text.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		
		pass_text = new JTextField();
		pass_text.setEditable(false);
		pass_text.setColumns(10);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		
		name_text = new JTextField();
		name_text.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7C7B\u578B\uFF1A");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u8001\u5E08", "\u7BA1\u7406\u5458"}));
		
		JButton upData = new JButton("\u4FEE\u6539\u5E10\u53F7");
		upData.addActionListener(new ActionListener() {//updata事件
			public void actionPerformed(ActionEvent e) {
				if (!(user_text.getText().equals(user) && name_text.getText().equals(name) 
						&& new String((String) comboBox.getSelectedItem()).equals(type))) {
					if (!user_text.getText().equals("")) {
						int upType = 2;
						switch ((String)comboBox.getSelectedItem()) {
						case "老师":
							upType = 1;
							break;
						case "管理员":
							upType = 0;
							break;
						}
						if (new UpDataUserDAO().upDataUser(user_text.getText(), name_text.getText(), upType,user)) {
							JOptionPane.showMessageDialog(null, "修改成功！","成功",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "修改失败！请检查帐号是否重复！","错误",JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "帐号不能为空！","错误",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,"没有修改任何信息!", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton re = new JButton("\u91CD\u7F6E");
		re.addActionListener(new ActionListener() {//重置事件
			public void actionPerformed(ActionEvent e) {
				showData(user, pass, name, type);
			}
		});
		
		JButton rePass = new JButton("\u91CD\u7F6E\u5BC6\u7801");
		rePass.addActionListener(new ActionListener() {//重置密码事件
			public void actionPerformed(ActionEvent e) {
				if (new UpDataUserDAO().rePass(user_text.getText())) {
					JOptionPane.showMessageDialog(null, "重置密码成功！","成功",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "重置密码失败！","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(upData))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(name_text, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(user_text, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(pass_text, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(re, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rePass)))))))
					.addGap(194))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(user_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pass_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(name_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rePass))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(upData)
						.addComponent(re))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		loginThing(user, pass, name, type);
	}

	private void loginThing(String user,String pass,String name,String type) {
		showData(user, pass, name, type);
		setResizable(false);//不能最大化
		setLocationRelativeTo(this);//设置当前窗口居中
		setVisible(true);
	}
	
	private void showData(String user,String pass,String name,String type) {
		user_text.setText(user);
		pass_text.setText(pass);
		name_text.setText(name);
		switch (type) {
		case "学生":
			comboBox.setSelectedIndex(0);
			break;
		case "老师":
			comboBox.setSelectedIndex(1);
			break;
		case "管理员":
			comboBox.setSelectedIndex(2);
			break;
		}
	}
	
}
