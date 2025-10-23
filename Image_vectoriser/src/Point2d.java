public class Point2d {
	int x,y;
	public Point2d(int x, int y) {
	this.x = x;
	this.y = y;
	}
	public double dist(Point2d o) {
		return Math.sqrt(Math.pow(x-o.x,2)+Math.pow(y-o.y,2));
		
		//return Math.abs(x-o.x)+Math.abs(y-o.y);
	}
	public double distn(Point2d o) {

//		if(y-o.y < 0) {
//			return Double.POSITIVE_INFINITY;
//		}

		//return Math.sqrt(Math.pow(x-o.x,2)+Math.pow(y-o.y,2));
		
		return Math.abs(x-o.x)+Math.abs(y-o.y);
//
//		
//		
//		return Math.abs(x-o.x)*3+y-o.y;
		
		
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return x == ((Point2d)obj).x && y == ((Point2d)obj).y;
	}
}
