package com.primitive2d;

import java.util.Vector;

import processing.core.PApplet;

import com.datatype.DCFace;
import com.datatype.DCHalfEdge;
import com.datatype.Point;
import com.math.Geom;

public class Polygon extends DCFace implements Drawable, Turtle{
	protected Vector<Line> lines;
	protected Vector<Point> points;
	
	
	public Polygon(){
		this.origin = new Point(0,0);
		this.lines = new Vector<Line>();
		this.points = new Vector<Point>();
	}
	
	//sets a new origin for translations and rotations
	public void setOrigin(double x, double y){
	    	this.setOrigin(new Point(x,y));
	    }
	
	//adds in a new point and automatically creates a new line if 1 or more points exist already
	public void addPoint(double x,double y){
		Point point = new Point(x,y);
		
		if(lines.size()>0){
			Point start = lines.get(lines.size()-1).end;
			Line line = new Line(start.copy(),point.copy());
			addLine(line);
		}
		else if(points.size()>0){
			Line line = new Line(points.get(0).copy(),point.copy());
			addLine(line);
		}
		
		points.add(point);
	}
	//adds a line in cart mode by specifying 4 coordinates
	public void addLine(double startX, double startY, double endX, double endY) {
		Line line = new Line(startX,startY,endX,endY);
		lines.add(line);
		
			
	}
	//adds a line in polar mode by specifying an origin, radius and angle
	public void addLine(Point origin, float radius, float theta) {
		Line line = new Line(origin,radius, theta);
		lines.add(line);
	}
	
	//adds a line by passing in a line
	public void addLine(Line line) {
		lines.add(line);
		if(!points.contains(line.start)){
			points.add(line.start);
		}
		
		if(!points.contains(line.end)){
			points.add(line.end);
		}
	}
	
	 //translates all lines to a new point;
    public void moveTo(double x, double y) {
        for (int i = 0; i < lines.size(); i++) {
            Line currentLine = lines.get(i);
            currentLine.moveTo(x, y,this.origin);
        }
        this.origin = new Point(x,y);
        
    }
    
    //translates all lines to a new point;
    @Override
    public void moveTo(double x, double y, Point focus) {
       for (int i = 0; i < lines.size(); i++) {
            Line currentLine = lines.get(i);
            currentLine.moveTo(x, y, focus);
        }
        
        
    }
    
	@Override
    public void moveBy(double x, double y) {
		 for (int i = 0; i < lines.size(); i++) {
	            Line currentLine = lines.get(i);
	            currentLine.moveBy(x, y);
	        }
        
    }
	
	@Override
	  //rotates all lines around the origin by an increment of theta;
    public void rotate(double theta) {
        this.rotate(theta,origin);
    }
	
	  //rotates all lines around the focus by an increment of theta;
    public void rotate(double theta, Point _focus) {
        for (int i = 0; i < lines.size(); i++) {
            Line currentLine = lines.get(i);
            currentLine.rotate(theta, _focus);
        }
    }
	
	public void draw(PApplet parent, float strokeWeight){
		for(int i=0;i<lines.size();i++){
    		lines.get(i).draw(parent, strokeWeight);
    		
    		
    	}
	}
	
	public void print(PApplet parent){
		//TODO:implement print method
	}

	@Override
	public void left(double angle) {
		TurtleStruct.angle-=angle;
		/*if(TurtleStruct.angle<0){
			TurtleStruct.angle = 360;
		}*/
		
	}

	@Override
	public void right(double angle) {
		// TODO Auto-generated method stub
		TurtleStruct.angle+=angle;
		/*if(TurtleStruct.angle>360){
			TurtleStruct.angle = 0;
		}*/
	}

	@Override
	public void forward(double dist) {
		Line newLine = new Line(TurtleStruct.location, dist, TurtleStruct.angle);
		if(TurtleStruct.pen){
			
			this.addLine(newLine);
		}
		TurtleStruct.location = newLine.end;
		
	}
	
	

	@Override
	public void back(double dist) {
		Line newLine = new Line(TurtleStruct.location, -dist, TurtleStruct.angle);
		if(TurtleStruct.pen){
			
			this.addLine(newLine);
		}
		TurtleStruct.location = newLine.end;
		
	}

	@Override
	public void penUp() {
		TurtleStruct.pen=false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penDown() {
		TurtleStruct.pen=true;
		// TODO Auto-generated method stub
		
	}

}
