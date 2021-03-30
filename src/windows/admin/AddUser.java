package windows.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.AddUserDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JTextField name;


	/**
	 * Create the frame.
	 */
	public AddUser(AdminMain mainWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				mainWindow.showTableData();
			}
		});
		setTitle("\u6DFB\u52A0\u5E10\u53F7");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddUser.class.getResource("/windows/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 321, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5E10\u53F7\uFF1A");
		
		user = new JTextField();
		user.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		
		JLabel label_1 = new JLabel("\u7C7B\u578B\uFF1A");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u8001\u5E08", "\u7BA1\u7406\u5458"}));
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//注册按钮点击事件
				if (!user.getText().equals("") && !pass.getText().equals("")) {
					int type = 2;
					switch ((String) comboBox.getSelectedItem()) {
					case "老师":
						type = 1;
						break;
					case "管理员":
						type = 0;
						break;
					}
					if (new AddUserDAO().addUser(user.getText(),new String(pass.getPassword()),name.getText(), type)) {
						JOptionPane.showMessageDialog(null, "创建新用户成功！","成功",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "创建新用户失败！","错误",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "帐号或密码不能为空！","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {//重置按钮事件
			public void actionPerformed(ActionEvent e) {
				user.setText("");
				pass.setText("");
				name.setText("");
				comboBox.setSelectedIndex(0);
			}
		});
		
		pass = new JPasswordField();
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		
		name = new JTextField();
		name.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(user, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pass, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(name, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(button_1)
									.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
									.addComponent(button)))))
					.addGap(68))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(16))
		);
		contentPane.setLayout(gl_contentPane);
		loginThing();
	}

	private void loginThing() {
		setResizable(false);//不能最大化
		setLocationRelativeTo(this);//设置当前窗口居中
		setVisible(true);
	}
}
