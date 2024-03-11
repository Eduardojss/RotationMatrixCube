package com.example.test;

import org.ejml.simple.SimpleMatrix;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Square Drawing");
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setBackground(Color.BLACK);
			frame.add(new SquarePanel("#"));
			frame.setVisible(true);
		});

		//double[][] result = multiplyMatrices(projection, rotation);
	}

	static class SquarePanel extends JPanel {
		public SquarePanel(String c) {
			this.caracter = c;
		}
		float angle = 0f;

		String caracter;

		SimpleMatrix projection = new SimpleMatrix( 
			new double[][] {
				new double[]{1, 0, 0},
				new double[]{0, 1, 0},
			}
		);
		double[][] rotation = {
				{Math.cos(angle), -Math.sin(angle), 0},
				{Math.sin(angle), Math.cos(angle), 0},
		};

		@Override
		protected void paintComponent(Graphics g) {
			SimpleMatrix points =  new SimpleMatrix(
				new double[][]{
					new double[]{50, -50, 0},
					new double[]{50, 50, 0},
					new double[]{-50, 50, 0},
					new double[]{-50, -50, 0},
				}
			);

			SimpleMatrix projection2d = points.mult(projection);

			System.out.println(projection2d);
			return;
			// super.paintComponent(g);
			// g.setColor(Color.black);
			// g.fillRect(100, 100, 400, 400);
			// for (double[] side : points) {
			// 	double x = side[0];
			// 	double y = side[1];
			// 	//double[][] projection2d = multiplyMatrices(projection, points);
			// 	g.setColor(Color.white);
			// 	System.out.println(projection2d);
			// 	g.drawString(this.caracter, (int) Math.round(projection2d[0][0]) + 300, (int) Math.round(projection2d[1][1]) + 300);
			// }
		}


		// @Override
		// protected void paintComponent(Graphics g) {
		// 	super.paintComponent(g);
		// 	g.setColor(Color.black);
		// 	g.fillRect(100, 100, 400, 400);
		// 	for (int i = 0; i < 200; i += 10) {
		// 		for (int j = 10; j <= 200; j += 10) {
		// 			g.setColor(Color.white);
		// 			g.drawString(this.caracter, 150 + i, 150 + j);
		// 		}
		// 	}
		// }

	}
}
