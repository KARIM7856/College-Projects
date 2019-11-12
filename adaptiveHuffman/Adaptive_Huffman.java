package adaptiveHuffman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.awt.event.ActionEvent;

public class Adaptive_Huffman {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adaptive_Huffman window = new Adaptive_Huffman();
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
	public Adaptive_Huffman() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textCompress = new JTextArea();
		textCompress.setLineWrap(true);
		textCompress.setBounds(108, 67, 145, 118);
		frame.getContentPane().add(textCompress);
		
		JTextArea textCompressed = new JTextArea();
		textCompressed.setLineWrap(true);
		textCompressed.setBounds(439, 67, 145, 118);
		frame.getContentPane().add(textCompressed);
		
		JTextArea textDecompressed = new JTextArea();
		textDecompressed.setLineWrap(true);
		textDecompressed.setBounds(439, 265, 145, 118);
		frame.getContentPane().add(textDecompressed);
		
		JTextArea textDecompress = new JTextArea();
		textDecompress.setLineWrap(true);
		textDecompress.setBounds(108, 265, 145, 118);
		frame.getContentPane().add(textDecompress);
		
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				String content = FileOperator.readFile(file);
				
				textCompress.setText(content);
			}
		});
		btnLoadFile.setBounds(133, 33, 89, 23);
		frame.getContentPane().add(btnLoadFile);
		
		JButton btnLoadFile_1 = new JButton("Load File");
		btnLoadFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				String content = FileOperator.readFile(file);
				
				textDecompress.setText(content);
			}
		});
		btnLoadFile_1.setBounds(133, 231, 89, 23);
		frame.getContentPane().add(btnLoadFile_1);
		
		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				 if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					
					String content = textCompressed.getText();
					
					FileOperator.saveFile(file, content);
				 }
			}
		});
		btnSaveFile.setBounds(470, 33, 89, 23);
		frame.getContentPane().add(btnSaveFile);
		
		JButton btnSaveFile_1 = new JButton("Save File");
		btnSaveFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				 if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					
					String content = textDecompressed.getText();
					
					FileOperator.saveFile(file, content);
				 }
			}
		});
		btnSaveFile_1.setBounds(470, 231, 89, 23);
		frame.getContentPane().add(btnSaveFile_1);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textCompress.getText();
				Huffman huf = new Huffman();
				textCompress.setText("");
				textCompressed.setText(huf.compress(message));
			}
		});
		btnCompress.setBounds(293, 112, 110, 23);
		frame.getContentPane().add(btnCompress);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textDecompress.getText();
				textDecompress.setText("");
				
				Huffman huf = new Huffman();
				
				textDecompressed.setText(huf.deCompress(message));
				
			}
		});
		btnDecompress.setBounds(293, 317, 110, 23);
		frame.getContentPane().add(btnDecompress);
		
		JLabel lblAdaptiveHuffman = new JLabel("Adaptive Huffman");
		lblAdaptiveHuffman.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdaptiveHuffman.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdaptiveHuffman.setBounds(232, 11, 205, 30);
		frame.getContentPane().add(lblAdaptiveHuffman);
	}
}
