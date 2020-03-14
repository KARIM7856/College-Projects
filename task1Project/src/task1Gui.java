import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.ctc.wstx.cfg.OutputConfigFlags;
import com.sun.javafx.runtime.eula.Eula;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.util.*;

public class task1Gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					task1Gui window = new task1Gui();
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
	public task1Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1288, 773);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea inputTextArea = new JTextArea();
		inputTextArea.setText("example input:\r\n3 2\r\n0 1\r\n2 1\r\nwhere 3 is number of vertices and 2 is number of edges.\r\npress enter button to enter the input.");
		inputTextArea.setBounds(776, 35, 416, 182);
		frame.getContentPane().add(inputTextArea);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setBounds(666, 38, 56, 16);
		frame.getContentPane().add(lblInput);
		
		JButton inputButton = new JButton("Enter");
		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setText("output will be here , also a frame that shows the graph will show.");

		inputButton.addActionListener(new ActionListener() {
			
			// code start win this button is pressed here.
			public void actionPerformed(ActionEvent arg0) {
				Scanner sc = new Scanner(inputTextArea.getText());// take input from text  area.
			
				mGraph g = new mGraph(sc);
				
				ArrayList<Integer> path = g.fleurys();
				
				String AdjMatrixoutput = "";
				//formating adj matrix in a string.
				for (int i = 0; i < g.adjMat.length; i++) {
					for (int j = 0; j < g.adjMat[i].length; j++) {// all rows has same size
						AdjMatrixoutput += g.adjMat[i][j]+ " ";
					}
					AdjMatrixoutput += "\n";
					
				}
				
				// formatting incidence matrix to string.
				String IncidMatOutput = "";
				for (int i = 0; i < g.incMat.length; i++) {
					for (int j = 0; j < g.incMat[i].length; j++) {
						IncidMatOutput += g.incMat[i][j]+ " ";
					}
					IncidMatOutput += "\n";
					
				}
				
				String eulerPath = "";
				for(int i = 0; i < path.size(); i++) {
					eulerPath += String.valueOf(path.get(i));
				}
				eulerPath += "\n";
				
				outputTextArea.setText("adjacency list:\n" +  g.toString() +
								"\n\n" + "adjacency matrix:\n" + AdjMatrixoutput +"\n\n"+"incident matrix:\n" + IncidMatOutput +
								 "\n\n" + "euler path:\n" + "\n\n" + eulerPath);
				
				// graph class in jung library to draw.
				Graph<Integer, String> guiGraph;
				guiGraph = new SparseMultigraph<Integer,String>();// spares means efficient with big graph, multi means support parallel edges.s
				
				// add vertex to graph gui
				for (int i = 0; i < g.V; i++) {
					guiGraph.addVertex(i);
				}
				// add edges to the gui graph class, using adj list.
				
				// note that adj[i] is a linked list, no direct access.
				for (int i = 0; i < g.adj.length; i++) {
					for (int j : g.adj[i]) {// all rows has same size
						
						guiGraph.addEdge(""+i +" " + j, i,j,EdgeType.UNDIRECTED);// first string just a name for the edge.
					}
				}
				
				// The Layout takes the graph and associate with each vertex a coordinate (2d point).
				Layout<Integer, String> layout = new CircleLayout<Integer,String>(guiGraph);
				layout.setSize(new Dimension(500,500)); // sets the space on screen of the graph will take
				
				// The canvas the graph will be put on.
				BasicVisualizationServer<Integer,String> vs =
				new BasicVisualizationServer<Integer,String>(layout);
				
				// imagine the graph in a frame, setsize is how much space it will take in it, setPreffered is the fram size,
				// i will this frame on the main gui frame
				vs.setPreferredSize(new Dimension(600,600)); 
				
				// label each vertex with the to string of that vertex, in our case the integer number.
				vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
					
				JFrame gframe = new JFrame("Simple Graph View");
				gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// this line to exist when you press red button.
				gframe.getContentPane().add(vs);
				gframe.pack();// idk what it does :/, but it was there on the pdf that explained the library, commenting it mess things up.
				gframe.setVisible(true);
			

				
			}
			
			
		});
		inputButton.setBounds(939, 230, 97, 25);
		frame.getContentPane().add(inputButton);
		
		outputTextArea.setBounds(776, 268, 416, 405);
		frame.getContentPane().add(outputTextArea);
		
		JLabel lblOutput = new JLabel("output");
		lblOutput.setBounds(666, 268, 56, 16);
		frame.getContentPane().add(lblOutput);
	}
}
