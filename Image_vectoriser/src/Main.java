import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Main {
		public static void main(String[] args) {
		BufferedImage raw =	ImageReader.readIMG(config.directory);

		raw =ImageReader.to_gr(raw);

		raw = ImageReader.adg_con(raw);

		//raw = ImageReader.gen_htones(raw);

		//raw = ImageReader.edgeDET(raw);


		ImageReader.prec(raw, "Raw");
		BufferedImage edge = ImageReader.preprocessimple(raw);


		ImageReader.prec(edge, "proccessed");
		ArrayList<Point2d> points = ImageReader.to_pointarr(edge);

		ArrayList<Point2d> merged = ImageReader.mergrgeBysidt(points);
		ImageReader.prec(ImageReader.toim(merged), "merged");





		ArrayList<ArrayList<Point2d>> img = ImageReader.shapify(merged);

		ImageReader.prec(ImageReader.toimb(img),"final");

		ImageReader.togcode(img);
		
		}
}
