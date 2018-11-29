import java.util.Arrays;
import java.util.ArrayList;
public class BruteCollinearPoints {

    private int count;
    private LineSegment[] lineArrayList;



    public BruteCollinearPoints(Point[] points)
    {
        ArrayList<LineSegment> lines = new ArrayList<>();
        if (points == null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null)
            {
                throw new java.lang.IllegalArgumentException();
            }
            for (int j = i+1; j < points.length; j++)
            {
                if (points[j] == null)
                {
                    throw new java.lang.IllegalArgumentException();
                }
                if (points[i].compareTo(points[j]) == 0)
                {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        for (int i = 0; i < points.length; i++)
        {
            for (int j = i + 1; j < points.length; j++)
            {
                for (int k = j + 1; k < points.length; k++)
                {
                    for (int l = k + 1; l < points.length; l++)
                    {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s))
                        {
                            Point[] pointArray = new Point[4];
                            pointArray[0] = p;
                            pointArray[1] = q;
                            pointArray[2] = r;
                            pointArray[3] = s;
                            Arrays.sort(pointArray);
                            lines.add(new LineSegment(pointArray[0], pointArray[3]));
                            count++;

                        }
                    }
                }
            }
        }
        lineArrayList = new LineSegment[count];
        for (int i = 0; i < count; i++)
        {
            lineArrayList[i] = lines.get(i);
        }

    }

    public int numberOfSegments()
    {
        return count;
    }

    public LineSegment[] segments()
    {
        LineSegment[] to_return = new LineSegment[count];
        for (int i = 0; i < count; i++)
        {
            to_return[i] = lineArrayList[i];
        }
        return to_return;
    }

    public static void main(String[] args)
    {

    }
}
