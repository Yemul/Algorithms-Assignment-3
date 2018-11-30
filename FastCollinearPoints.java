import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {

    private int count;
    private LineSegment[] lineArrayList;

    public FastCollinearPoints(Point[] points)
    {
        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        count = 0;

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
        Point[] arrayCopy = Arrays.copyOf(points, points.length);
        for (Point point: points)
        {
            Arrays.sort(arrayCopy, point.slopeOrder());
            if (arrayCopy.length > 2)
            {
                int innerIndex = 1;
                int innerCount = 0;
                double currentSlope = point.slopeTo(arrayCopy[innerIndex]);
                while (innerIndex < arrayCopy.length)
                {
                    if (currentSlope == point.slopeTo(arrayCopy[innerIndex]))
                    {
                        innerCount++;
                    }
                    else
                    {
                        if (innerCount >= 3)
                        {
                            Arrays.sort(arrayCopy, innerIndex-innerCount, innerIndex);
                            if (point.compareTo(arrayCopy[innerIndex-1]) < 0)
                            {
                                lines.add(new LineSegment(point, arrayCopy[innerIndex-1]));
                                count++;
                            }

                        }
                        innerCount = 1;
                        currentSlope = point.slopeTo(arrayCopy[innerIndex]);
                    }
                    innerIndex++;
                }
                if (innerCount >= 3)
                {
                    Arrays.sort(arrayCopy, innerIndex-innerCount, innerIndex);
                    if (point.compareTo(arrayCopy[innerIndex-1]) < 0)
                    {
                        lines.add(new LineSegment(point, arrayCopy[innerIndex-1]));
                        count++;
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
}
