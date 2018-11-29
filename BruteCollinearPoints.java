import java.util.Arrays;
public class BruteCollinearPoints {

    private int count;
    private LineSegment[] lineArrayList;

    private class Node
    {
        LineSegment value;
        Node next;
    }

    public BruteCollinearPoints(Point[] points)
    {
        Node Head;
        Node Tail;
        Head = new Node();
        Tail = Head;
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
                if (points[i].compareTo(points[j]) == 0)
                {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        for (Point p: points)
        {
            for (Point q: points)
            {
                for (Point r: points)
                {
                    for (Point s: points)
                    {
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s))
                        {
                            Point[] pointArray = new Point[4];
                            pointArray[0] = p;
                            pointArray[1] = q;
                            pointArray[2] = r;
                            pointArray[3] = s;
                            Arrays.sort(pointArray);
                            Tail.value = new LineSegment(pointArray[0], pointArray[3]);
                            Tail.next = new Node();
                            Tail = Tail.next;
                            count++;

                        }
                    }
                }
            }
        }
        lineArrayList = new LineSegment[count];
        Node current = Head;
        for (int i = 0; i < count; i++)
        {
            lineArrayList[i] = current.value;
            current = current.next;

        }

    }

    public int numberOfSegments()
    {
        return count;
    }

    public LineSegment[] segments()
    {
        return lineArrayList;
    }
}
