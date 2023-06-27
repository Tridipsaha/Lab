

import java.util.PriorityQueue;

class Detail implements Comparable<Detail> {
    int pri, AT, BT;

    public Detail(int p, int arr, int time) {
        this.pri = p;
        this.AT = arr;
        this.BT = time;
    }

    @Override
    public int compareTo(Detail other) {
        if (this.pri == other.pri) {
            return 0;
        } else if (this.pri < other.pri) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Priority: " + pri + ", Arrival Time: " + AT + ", Burst Time: " + BT;
    }
}

public class Sort {
    public static void main(String[] args) {
        PriorityQueue<Detail> pq = new PriorityQueue<>();
        pq.add(new Detail(4, 1, 5));
        pq.add(new Detail(7, 2, 5));
        pq.add(new Detail(1, 7, 5));
        pq.add(new Detail(3, 2, 5));
        pq.add(new Detail(5, 4, 5));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
