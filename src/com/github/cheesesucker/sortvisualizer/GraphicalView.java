package com.github.cheesesucker.sortvisualizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.IdentityHashMap;

public class GraphicalView {
	private static final int WindowWidth = 500;
	private static final int WindowHeight = 200;
	private static final int WindowsPerRow = 3;
	private static final int WindowOffsetY = 200;
	private static AbstractMap<Object, GraphicalView> instances = new IdentityHashMap<Object, GraphicalView>();
	
	public static GraphicalView get(double[] input) {
		return get(input, "");
	}
	
	public static GraphicalView get(double[] input, String name) {
		if (!instances.containsKey(input)) {
			GraphicalView g = new GraphicalView(name);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int unusedSpaceX = screenSize.width - WindowWidth * WindowsPerRow;
			int x = unusedSpaceX / 2 + (instances.size() % WindowsPerRow) * WindowWidth;
			int y = WindowOffsetY + instances.size() / WindowsPerRow * WindowHeight;
			g.draw.setLocationOnScreen(x, y);
			instances.put(input, g);
		}
		return instances.get(input);
	}
	
	public String name;
	public Drawer draw;
	public ArrayList<Integer> exch = new ArrayList<Integer>();
	public ArrayList<Integer> less = new ArrayList<Integer>();
	public int compares = 0;
	public int exchs = 0;
	
	public GraphicalView(String name) {
		this.name = name;
		draw = new Drawer(name);
		draw.setCanvasSize(WindowWidth, WindowHeight);
	}
	
	public void draw(double[] input) {
		draw.show(0);
		draw.clear(Drawer.BLACK);
		
		double barWidth = 1.0 / input.length;
		double width = 0.90 * barWidth;
		for (int i = 0; i < input.length; i++) {
			if (exch.contains(i)) {
				draw.setPenColor(Drawer.RED);
			} else if (less.contains(i)) {
				draw.setPenColor(Drawer.ORANGE);
			} else {
				draw.setPenColor(Drawer.GRAY);
			}
			
			double x = barWidth * (i + 0.5);
			double y = 0.5;
			double halfWidth = width * 0.5;
			double halfHeight = input[i] * 0.45;
			draw.filledRectangle(x, y, halfWidth, halfHeight);
		}
		
		draw.setPenColor(new Color(222, 222, 222));
		draw.setFont(draw.getFont().deriveFont(16.0f));
		draw.textLeft(0, 1, this.name);
		
		draw.setFont(draw.getFont().deriveFont(10.0f));
		draw.textLeft(0, 0, "E: " + this.exchs);
		draw.textLeft(0.1, 0, "C: " + this.compares);
		draw.textLeft(0.8, 0, "T: " + (this.compares + this.exchs));
		
		draw.show(33);
	}
}
