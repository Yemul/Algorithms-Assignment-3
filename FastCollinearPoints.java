import java.util.Arrays;

public class FastCollinearPoints {

    private Point referencePoint;
    private int count;
    private LineSegment[] lineArrayList;

    private class Node
    {
        LineSegment value;
        Node next;
    }


    public FastCollinearPoints(Point[] points)
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

        for (int i = 0; i < points.length; i++)
        {
            referencePoint = points[i];
            Point[] pointArray = new Point[points.length - i - 1];
            int currentIndex = 0;
            for (int j = i + 1; j < points.length; j++)
            {
                pointArray[currentIndex] = points[j];
                currentIndex++;
            }
            mergeSort(pointArray);
            // Below is trying to find whether a line exists
            int pointCount = 0;
            int index = 0;
            if (pointArray.length > 0)
            {
                double desiredSlope = pointArray[0].slopeTo(referencePoint);
                while ((index < pointArray.length) && pointArray[index].slopeTo(referencePoint) == desiredSlope)
                {
                    pointCount++;
                    index++;
                }
            }
            if (pointCount >= 3)
            {
                Point[] lastPointArray = new Point[pointCount];
                lastPointArray[0] = referencePoint;
                int lastIndex = 1;
                int originalIndex = 0;
                while (lastIndex < lastPointArray.length)
                {
                    lastPointArray[lastIndex] = pointArray[originalIndex];
                    lastIndex++;
                    originalIndex++;
                }
                Arrays.sort(lastPointArray);
                Tail.value = new LineSegment(pointArray[0], pointArray[3]);
                Tail.next = new Node();
                Tail = Tail.next;
                count++;


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

    private boolean comparePoints(Point point1, Point point2)
    {
        double slope1 = referencePoint.slopeTo(point1);
        double slope2 = referencePoint.slopeTo(point2);
        if (slope1 < slope2)
        {
            return true;
        }
        if (slope1 > slope2)
        {
            return false;
        }
        else
        {
            return point1.compareTo(point2) < 0;

        }
    }

    private void merge(Point[] a, int low, int mid, int high)
    {
        // Create an auxiliary array to save the passed-in array.
        Point[] copy = new Point[a.length];
        for (int i = 0; i < a.length; i++)
        {// Copying all elements in a to copy.
            copy[i] = a[i];
        }
        int i = low;
        int j = mid + 1;
        int current_index = 0;
        while ((i <= mid) && (j <= high))
        {
            if (comparePoints(copy[i], copy[j]))
            {
                a[current_index] = copy[i];
                i++;
            }

            else
            {
                a[current_index] = copy[j];
                j++;
            }
        }
        while (i <= mid)
        {
            a[current_index] = copy[i];
            i++;
        }
        while (j <= high)
        {
            a[current_index] = copy[j];
            j++;
        }

    }

    private void mergeSort(Point[] a)
    {
        mergeSort(a, 0, a.length - 1);
    }

    private void mergeSort(Point[] a, int low, int high)
    {
        if (high <= low)
        {
            return;
        }
        int mid = low + (high - low)/2;
        mergeSort(a, low, mid);
        mergeSort(a, mid+1, high);
        merge(a, low, mid, high);
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
