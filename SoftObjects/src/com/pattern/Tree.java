package com.pattern;

import com.datatype.Point;
import com.primitive2d.Line;

public class Tree extends Pattern{
	private double limit = 2;//minimum length of a branch before the fractal terminates
	private double growthRate= 0.66;//fraction by which the branch is decreased each recursion
	private float theta = 60;//starting angle of the tree
	private float startingHeight = 200;//starting height of the tree

	private Point origin = new Point(0,0);//starting angle of the tree
	public Tree(){
		super();
		
	}
	
	public void setLimit(double limit){
		this.limit = limit;
	}
	
	public void setGrowthRate(double growthRate){
		this.growthRate = growthRate;
	}
	
	public void setTheta(float theta){
		this.theta = theta;
	}
	
	public void setOrigin(double x,double y){
		this.origin = new Point(x,y);
	}
	
	public void setStartingHeight(float height){
		this.startingHeight = height;
	}
	
	public void generate(){
		Line line = new Line(origin.getX(),origin.getY(),startingHeight,theta);
		this.addLine(line);
		this.branch(startingHeight,line.end,theta,20);
		
	}
	
	private void branch(float height, Point origin, float startingTheta, float thetaChange){
		height*= growthRate;
		
		//exit function
		if(height >limit){
			float rightTheta = startingTheta + thetaChange;
			Line rightLine = new Line(origin.getX(),origin.getY(),height,rightTheta);
			this.addLine(rightLine);
			this.branch(height,rightLine.end,rightTheta,thetaChange);
			
			float leftTheta = startingTheta - thetaChange;
			Line leftLine = new Line(origin.getX(),origin.getY(),height,leftTheta);
			this.addLine(leftLine);
			this.branch(height,leftLine.end,leftTheta,thetaChange);
			
		}
	}
}
