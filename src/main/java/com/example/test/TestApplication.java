package com.example.test;

import processing.core.*;

public class TestApplication extends PApplet {

	public static void main(String... args) {
		PApplet.runSketch(new String[] { "com.example.test.TestApplication" }, new TestApplication());
	}

	PVector[] points = new PVector[8];

	MatrixOperations mo = new MatrixOperations();

	public void settings() {
		size(600, 400);
	}

	public void setup() {
		points[0] = new PVector(50, -50, -50);
		points[1] = new PVector(50, 50, -50);
		points[2] = new PVector(-50, 50, -50);
		points[3] = new PVector(-50, -50, -50);
		points[4] = new PVector(50, -50, 50);
		points[5] = new PVector(50, 50, 50);
		points[6] = new PVector(-50, 50, 50);
		points[7] = new PVector(-50, -50, 50);
	}

	float angle = 0f;

	float[][] projection = {
			{ 1, 0, 0 },
			{ 0, 1, 0 },
	};

	
	public void draw() {
		background(0);
		translate(width / 2, height / 2);
		noFill();
		
		float[][] rotationZ = {
			{ cos(angle), -sin(angle), 0 },
			{ sin(angle), cos(angle), 0 },
			{ 0, 0, 1 },
		};

		float[][] rotationX = {
			{1, 0, 0},	
			{0, cos(angle), -sin(angle) },
			{0, sin(angle), cos(angle) },
		};

		float[][] rotationY = {
			{ cos(angle), 0, -sin(angle) },
			{ 0, 1, 0 },
			{ sin(angle), 0, cos(angle) },
		};

		PVector[] projected = new PVector[8];

		int index = 0;
		for (PVector v : points) {
			PVector rotated = mo.matmulv(rotationX, v);
			rotated = mo.matmulv(rotationY, rotated);
			rotated = mo.matmulv(rotationZ, rotated);
			PVector vector2d = mo.matmulv(projection, rotated);
			projected[index] = vector2d;
			point(vector2d.x, vector2d.y);
			index++;
		}

		for (PVector v : projected){
			stroke(255);
			strokeWeight(3);
		}

		connect(0, 1, projected);
		connect(1, 2, projected);
		connect(2, 3, projected);
		connect(3, 0, projected);
		connect(4, 5, projected);
		connect(5, 6, projected);
		connect(6, 7, projected);
		connect(7, 4, projected);
		connect(0, 4, projected);
		connect(1, 5, projected);
		connect(2, 6, projected);
		connect(3, 7, projected);

		angle += 0.03;
	}

	void connect(int i, int j, PVector[] points) {
		PVector v1 = points[i];
		PVector v2 = points[j];
		line(v1.x, v1.y, v2.x, v2.y);
	}
}
