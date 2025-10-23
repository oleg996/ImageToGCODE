public class config {

    static String directory = "/home/oleg/Pictures/tux.png";

    //image generation
    static int div = 1; // generate point every nth pixel
    static int threshold = 50;// no commentary

    static int [] patt = {200,100,200,50,200,100,200,50};

    //point generation
    static int merge_dist = 5; //distance for merge
    static boolean limit = false; // limits merge (don't merge a point inside the image) WARNING SLOW!!
    static int limit_size = 300; // how many points to limit

    //path generation

    static int min_dist = 10; // min distance to generate path between two points
    static int max_length = 100; // max length of one path

}
