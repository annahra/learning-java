import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point currPt : s.getPoints()){
            //For each point in s, increment numPoints
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double avgLength = getPerimeter(s)/getNumPoints(s);
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = -9999999;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            
            if(currDist > largestSide){
                largestSide = currDist;
            }
        }
        
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = -99999999;
        
        for(Point currPt : s.getPoints()){
            double currX = currPt.getX();
            
            if (currX > largestX){
                largestX = currX;
            }
        }
        
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        //create directory resource to iterate over multiple files
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = -1;
        
        for(File f : dr.selectedFiles()){
            //create file resource
            FileResource fr = new FileResource(f);
            //create shape from file resource
            Shape s = new Shape(fr);
            //get perimeter of shape
            double currPerim = getPerimeter(s);
            
            if (currPerim > largestPerim){
                largestPerim = currPerim;
            }
        }
        
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = -1;
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            
            if (currPerim > largestPerim){
                largestPerim = currPerim;
                temp = f;
            }
        }
        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);
        
        double avgLength = getAverageLength(s);
        System.out.println("average length = " + avgLength);
        
        double largestSide = getLargestSide(s);
        System.out.println("largest side = " + largestSide);
        
        double largestX = getLargestX(s);
        System.out.println("largest x = " + largestX);
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        //get largest perimeter of multiple files
        double largestPerimMF = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter from multiple files = " + largestPerimMF);
    }
    

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter name = " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testFileWithLargestPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}
