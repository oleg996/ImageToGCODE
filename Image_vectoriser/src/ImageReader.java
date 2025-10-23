import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.ietf.jgss.Oid;




public class ImageReader {

	public static BufferedImage readIMG(String name ) {
	   try {
		   BufferedImage imgBufferedImage = ImageIO.read(new File(name));

		  return imgBufferedImage;
		   
		} catch (Exception e) {

		}
	   return null;
}

	public static BufferedImage to_gr(BufferedImage in){
		BufferedImage out = new BufferedImage(in.getHeight(), in.getWidth(), in.getType());
		for (int x = 1; x < in.getHeight()-1; x++) {
			for (int y = 1; y < in.getWidth() - 1; y++) {

				Color c = new Color(in.getRGB(x,y));

				int gc = (c.getGreen() + c.getBlue() + c.getRed()) /3;
				out.setRGB(x,y,new Color(gc,gc,gc).getRGB());
			}
		}
		return  out;



	}
	
	
	public static BufferedImage edgeDET(BufferedImage in) {
		BufferedImage out = new BufferedImage(in.getHeight(), in.getWidth(), in.getType());
		int avg = 0;
		for (int x = 1; x < in.getHeight()-1; x++) {
			   for (int y = 1; y < in.getWidth()-1; y++) {
				   avg = 0;
				   avg += (new Color(in.getRGB(y, x)).getGreen())*4	;
				   avg -= (new Color(in.getRGB(y+1, x)).getGreen());
				   avg -= (new Color(in.getRGB(y-1, x)).getGreen());
				   avg -= (new Color(in.getRGB(y, x+1)).getGreen());
				   avg -= (new Color(in.getRGB(y, x-1)).getGreen());
				   avg = avg > -10 ? 255:0;
				   out.setRGB(y, x, new Color(avg, avg, avg).getRGB());
			   }
		   }
		return out;
	}
	public static BufferedImage test(BufferedImage in) {
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
		int avg = 0;
		int outv = 0;
		for (int x = 1; x < in.getHeight()-1; x++) {
			   for (int y = 1; y < in.getWidth()-1; y++) {
				   avg = 0;
				   avg += (new Color(in.getRGB(y-1, x-1)).getGreen());
				   avg += (new Color(in.getRGB(y, x-1)).getGreen());
				   avg += (new Color(in.getRGB(y+1, x-1)).getGreen());
				   
				   avg -= (new Color(in.getRGB(y-1, x+1)).getGreen());
				   avg -= (new Color(in.getRGB(y, x+1)).getGreen());
				   avg -= (new Color(in.getRGB(y+1, x+1)).getGreen());
				   
				   
				   outv = Math.abs(avg) > 100 ? 255:0;
				   
				   avg = 0;
				   avg += (new Color(in.getRGB(y-1, x+1)).getGreen());
				   avg += (new Color(in.getRGB(y-1, x)).getGreen());
				   avg += (new Color(in.getRGB(y-1, x-1)).getGreen());
				   
				   avg -= (new Color(in.getRGB(y+1, x+1)).getGreen());
				   avg -= (new Color(in.getRGB(y+1, x)).getGreen());
				   avg -= (new Color(in.getRGB(y+1, x-1)).getGreen());
				   
				   
				   outv += Math.abs(avg) > 100 ? 255:0;
				   
				   outv = Math.abs(outv) > 255 ? 255:outv;
				   
				   out.setRGB(y, x, new Color(outv, outv, outv).getRGB());
			   }
		   }
		return out;
	}
	
	
	
	public static BufferedImage preprocessimple(BufferedImage in) {
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
		int outv = 0;
		for (int x = 0; x < in.getHeight(); x++) {
			   for (int y = 0; y < in.getWidth(); y++) {
		
				   
				   outv = Math.abs((new Color(in.getRGB(y, x)).getGreen())) > config.threshold? 0:255;
				   
				   
				   out.setRGB(y, x, new Color(outv, outv, outv).getRGB());
			   }
		   }
		return out;
	}
	public static int getavg(BufferedImage in) {
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
		int outv = 0;
		for (int x = 0; x < in.getHeight(); x++) {
			   for (int y = 0; y < in.getWidth(); y++) {
		
				   
				   outv =+ Math.abs((new Color(in.getRGB(y, x)).getGreen()));
				   
				   
			   }
		   }
		return outv/(in.getWidth()*in.getHeight());
	}
	public static ArrayList<Point2d> to_pointarr(BufferedImage in) {
		ArrayList<Point2d> out = new ArrayList<Point2d>();
		try {
		for (int x = 1; x < in.getHeight()-1; x+= config.div) {
			   for (int y = 1; y < in.getWidth()-1; y+= config.div) {
				   	if((new Color(in.getRGB(x, y)).getGreen())>0)
				   		out.add(new Point2d(x,y));
			   }
		}
		
	} catch (Exception e) {
		System.out.println(e);
	}
		return out;
		
	}
	
	

	public static BufferedImage gen_htones(BufferedImage in){
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
		for (int x = 0; x < in.getHeight(); x++) {
			for (int y = 0; y < in.getWidth(); y++) {

				int i = ((x + y) / 5) % (config.patt.length);

				i = config.patt[i];




				if((in.getRGB(x,y) & 255) < i)
					out.setRGB(x,y,Color.black.getRGB());
				else
					out.setRGB(x,y,Color.white.getRGB());
//				int pix = i;
//				out.setRGB(x,y,new Color(pix,pix,pix).getRGB());

			}
		}
		return  out;
	}


	public static BufferedImage adg_con(BufferedImage in){
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
		int max =0;
		int min = 255;
		for (int x = 0; x < in.getHeight(); x++) {
			for (int y = 0; y < in.getWidth(); y++) {

				int pix = in.getRGB(x,y)&255;

				max = Math.max(max,pix);
				min = Math.min(min,pix);


			}
		}
		for (int x = 0; x < in.getHeight(); x++) {
			for (int y = 0; y < in.getWidth(); y++) {
				int pix = in.getRGB(x,y)&255;
				pix = pix - min *255 /(max - min);
				out.setRGB(x,y,new Color(pix,pix,pix).getRGB());
			}
		}


		return  out;
	}

	public static ArrayList<Point2d> mergrgeBysidt(ArrayList<Point2d>arr) {
		Random ran = new Random();
		ArrayList<Point2d> buf = new ArrayList<Point2d>(arr);
		ArrayList<Point2d> out = new ArrayList<Point2d>();
		ArrayList<Point2d> speed = new ArrayList<Point2d>();;
		while (!buf.isEmpty()) {
		Point2d cent =	buf.get(ran.nextInt(buf.size()));
		speed.clear();
		for ( Point2d p : buf)
			if(cent.dist(p)<=config.merge_dist)
				speed.add(p);

			if(!config.limit || speed.size() < config.limit_size) {

				for (Point2d point2d : speed) {

					buf.remove(point2d);
				}
				out.add(cent);
			}
				debug_pr("Merge"+buf.size());
		
		}
		
		return out;
	}
	

	public static ArrayList<ArrayList<Point2d>> shapify(ArrayList<Point2d>arr) {
		ArrayList<ArrayList<Point2d>> out = new ArrayList<ArrayList<Point2d>>();
		
		ArrayList<Point2d> buf = new ArrayList<Point2d>(arr);
		
		Random ran = new Random();
		Point2d start;
		
		
		ArrayList<Point2d> shape = new ArrayList<Point2d>();
		while ( !buf.isEmpty()){
		shape.clear();
		start = buf.get(ran.nextInt(buf.size()));
		debug_pr("Shapify"+buf.size());
		for(int i = 0;i < config.max_length ;i ++) {
			Point2d tmp = find_closest(buf, config.min_dist, start);
			shape.add(start);
			buf.remove(start);
			if(tmp == null) {break;}
			start=tmp;

		}
		//if(shape.size() > 10)
		out.add(new ArrayList<Point2d>(shape));

		}
		return out;
	}
	public static Point2d find_closest( ArrayList<Point2d> arr,int d,Point2d p) {
		ArrayList<Point2d> buf = new ArrayList<Point2d>(arr);
		
		buf.removeIf((a)->a.distn(p) > d);
		buf.remove(p);
		if(buf.size() >0) {
		Point2d min = buf.get(0);
		for (Point2d point2d : buf) {
			
				if(p.distn(point2d)<p.distn(min)) {
				min = point2d;
				}
			
			
		}
		return min;
		}
		return null;
		
	}
	
	public static BufferedImage toim(ArrayList<Point2d>arr) {
		BufferedImage out = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
		for (Point2d point2d : arr) {
			System.out.println(point2d.x+" "+point2d.y);
			out.setRGB(point2d.x, point2d.y, Color.white.getRGB());
		}
		return out;
	}
	public static BufferedImage toimb(ArrayList<ArrayList<Point2d>>arr) {
		BufferedImage out = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
		Color dr = new Color(0);
		Random r = new Random();
		Graphics gr = out.createGraphics();
		for (ArrayList<Point2d> arrayList : arr) {
			dr = new Color(r.nextInt());
			dr = Color.white;
		for (int i = 1; i < arrayList.size(); i++) {
			gr.setColor(dr);
			gr.drawLine(arrayList.get(i-1).x, arrayList.get(i-1).y,arrayList.get(i).x, arrayList.get(i).y);
			
			
		}
		}
		return out;
	}
	public static void toimbt(ArrayList<ArrayList<Point2d>>arr) {
		
		
		
		
		
		BufferedImage out = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
		
		Graphics gr = out.createGraphics();
		
		
		
		
		
		
		
		JFrame wind = new JFrame("Animation");
		
		ImageIcon icon = new ImageIcon(out);
		JLabel jLabel = new JLabel(icon);
		wind.add(jLabel);
		wind.setVisible(true);
		wind.setSize(200, 200);
		wind.setDefaultCloseOperation(wind.EXIT_ON_CLOSE);
		
		
		
		
		
		
		
		Random r = new Random();
		
		Thread th = new Thread(()->{
			
			Color dr = new Color(0);
		for (ArrayList<Point2d> arrayList : arr) {
			dr = new Color(r.nextInt());
			
		for (int i = 1; i < arrayList.size(); i++) {
			gr.setColor(dr);
			gr.drawLine(arrayList.get(i-1).y, arrayList.get(i-1).x,arrayList.get(i).y, arrayList.get(i).x);
			wind.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
		});
		th.start();
		
	}
	public static void togcode(ArrayList<ArrayList<Point2d>>arr) {
		ArrayList<ArrayList<Point2d>> buf = new ArrayList(arr);
		
		try {
			buf.removeIf((e)->e.size()==0);
		FileWriter fileWriter = new FileWriter("/home/oleg/test.gcode");
		StringBuilder sb = new StringBuilder();
		
		sb.append("G28 \n");
		ArrayList<Point2d> shape = buf.get(0);
		Point2d last = null;
		while (buf.size() > 0) {
			shape = buf.get(0);
			buf.remove(shape);
			if(last == null || last.dist(shape.get(0)) > 10) {
			sb.append( "G0 Z12 F5000\n");
			}
			System.out.println("Shape size " + shape.size()+" buf size "+ buf.size());
			sb.append("G0 X"+((float)shape.get(0).x/10.+50)+" Y"+((float)shape.get(0).y/10.+50)+"F10000\n");
			sb.append("G0 Z10 F5000\n");
			for (Point2d p : shape) {
				sb.append("G0 X"+((float)p.x/10.+50)+" Y"+((float)p.y/10.+50)+"F3000\n");
			}
			 last = shape.get(shape.size()-1);
			 Point2d ls = last;
			buf.sort((a,b)->{
				double dist = a.get(0).dist(ls) - b.get(0).dist(ls);
				dist *= 10000;
				return (int)dist;
				
			});
		}
		System.out.println("Writing to a file");
		fileWriter.write(sb.toString());
		fileWriter.close();
		System.out.println("DONE" + "Shapes "+arr.size());
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		
	
	public static void prec(BufferedImage img,String name) {
		JFrame wind = new JFrame(name);
		
		ImageIcon icon = new ImageIcon(img);
				
		wind.add(new JLabel(icon));
		wind.setVisible(true);
		wind.setSize(200, 200);
		wind.setDefaultCloseOperation(wind.EXIT_ON_CLOSE);
		
	}
	static int n =0 ;
	private static void debug_pr(String val) {
		n++;
		if (n>100) {
			System.out.println("Processing:"+val);
			n=0;
		}
		
	}
}
