package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.LoginDAO;
import windows.admin.AdminMain;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/windows/img/logo.png")));
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 269);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u767B\u5F55\u8BD5\u9898\u5E93\u7BA1\u7406\u7CFB\u7EDF\uFF01");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/windows/img/modify.png")));
		
		JLabel label = new JLabel("\u5E10\u53F7\uFF1A");
		label.setIcon(new ImageIcon(Login.class.getResource("/windows/img/userName.png")));
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/windows/img/password.png")));
		
		user = new JTextField();
		user.setColumns(10);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {//登录事件
			public void actionPerformed(ActionEvent e) {
				loginClike();
			}
		});
		button.setIcon(new ImageIcon(Login.class.getResource("/windows/img/bookTypeManager.png")));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {//重置事件
			public void actionPerformed(ActionEvent e) {
				user.setText("");
				pass.setText("");
			}
		});
		button_1.setIcon(new ImageIcon(Login.class.getResource("/windows/img/reset.png")));
		
		pass = new JPasswordField();
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n') {//当按下的值等于回车时调用登录方法
					loginClike();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(user, Alignment.TRAILING)
								.addComponent(pass, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))))
					.addGap(56))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(41))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		loginThing();
	}
	
	private void loginThing() {
		setResizable(false);//不能最大化
		setLocationRelativeTo(this);//设置当前窗口居中
	}
	
	private void loginClike() {
		if (!(user.getText().equals("") || pass.getText().equals(""))) {
			LoginDAO dao = new LoginDAO();
			boolean flag = true;
			switch (dao.findUser(user.getText(),pass.getText())) {
			case 1:
				new AdminMain();
				dispose();
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "帐号或密码错误！","错误",JOptionPane.ERROR_MESSAGE);
				break;
			case -1:
				JOptionPane.showMessageDialog(null,"非管理员不能在windows客户端登录!", "提示", JOptionPane.WARNING_MESSAGE);
				break;
			}
		} else {
			JOptionPane.showMessageDialog(null,"帐号或密码为空!", "提示", JOptionPane.WARNING_MESSAGE);
		}
	}
}
