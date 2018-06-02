package OverloapInterval;


class Interval implements Comparable<Interval>
{
    int start;
    int end;

    public Interval() {
        start = 0;
        end = 0;
    }

	public Interval(int s, int e) {
        start = s;
        end = e;
    }
	
    public int compareTo(Interval o) {
        return  start-o.start;
    }

	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}
    
    
}
