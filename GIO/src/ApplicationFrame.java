import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationFrame extends JFrame{
	
	JPanel mainPanel;
	
	public ApplicationFrame() {
		super("GIO");
		this.setSize(new Dimension(1280,720));
		this.setContentPane(createAppPanel());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private Container createAppPanel() {
		mainPanel = new JPanel(new BorderLayout());
		JPanel subPanel = new JPanel();
		BoxLayout b = new BoxLayout(subPanel, BoxLayout.X_AXIS);
		subPanel.setLayout(b);
		subPanel.add(new JButton("Button"));
		subPanel.add(Box.createHorizontalGlue());
		subPanel.add(new JLabel("Texto"));
		mainPanel.add(subPanel, BorderLayout.NORTH);
		mainPanel.add(new JLabel("Hello"), BorderLayout.CENTER);
		return mainPanel;
	}

	public void setPanel(JPanel panel) {
		BorderLayout layout = (BorderLayout) mainPanel.getLayout();
		mainPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.validate();
	}
}
