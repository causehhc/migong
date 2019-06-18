package test;

public class Main {
	
	public static void main(String[] args) {
		GoMap map=new GoMap();
		map.setMap(17, 67);
		map.stack();
		map.setPoint(2, 2, 14, 64);
		map.pathmap();
	}
}