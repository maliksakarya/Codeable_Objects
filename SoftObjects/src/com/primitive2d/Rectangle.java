package com.primitive2d;

import com.datatype.*;

public class Rectangle extends Polygon {
	private double width;
	private double height;
	private Line top;
	private Line left;
	private Line bottom;
	private Line right;

	
	public Rectangle(double startX, double startY, double width, double height){
		this.origin = new Point(startX,startY);
		
		Point topLeft = origin.copy();
		Point topRight = new Point(origin.getX()+width,origin.getY());
		Point bottomLeft = new Point(origin.getX(),origin.getY()+height);
		Point bottomRight = new Point(origin.getX()+width,origin.getY()+height);
		
		top = new Line(topRight.copy(),topLeft.copy());
		left = new Line(topLeft.copy(),bottomLeft.copy());
		bottom = new Line(bottomLeft.copy(),bottomRight.copy());
		right = new Line(bottomRight.copy(), topRight.copy());
		
		addLine(top);
		addLine(left);
		addLine(bottom);
		addLine(right);
	}
	


}
