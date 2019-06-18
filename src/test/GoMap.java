package test;

public class GoMap extends CreatMap{
	private int bx=-1;
	private int by=-1;
	private int ex=-1;
	private int ey=-1;
	
	GoMap(){
		
	}
	
	void setPoint(int bx,int by,int ex,int ey) {
		this.bx=bx;
		this.by=by;
		this.ex=ex;
		this.ey=ey;
		map[this.ex][this.ey]=3;
	}
	
	private boolean pathMapMethod(int x,int y) {
//		System.out.println(x+" "+y);
//		this.draw(x,y);
		if(x==ex&&y==ey) {
			this.print();
		}
		if(map[x][y]==8) {
			map[x][y]=1;
			if(pathMapMethod(x,y+1)) {
				if(pathMapMethod(x-1,y)) {
					if(pathMapMethod(x,y-1)) {
						pathMapMethod(x+1,y);
					}
				}
			}
			map[x][y]=8;
		}
		
		return true;
	}
	
	void pathmap() {
		pathMapMethod(bx,by);
//		this.print();
	}
}