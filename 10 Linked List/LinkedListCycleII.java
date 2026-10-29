public class LinkedListCycleII {

    static Node Head;

    // ---------- Phase 1: does a cycle exist? ----------
    static boolean hasCycle() {
        Node slow = Head;
        Node fast = Head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // compare NODE identity, not data
                return true;
            }
        }
        return false; // fast reached null -> no cycle
    }

    // ---------- Phase 1 + Phase 2: find the exact meeting point,
    // then walk to find where the cycle starts ----------
    static Node detectCycleStart() {
        Node slow = Head;
        Node fast = Head;
        boolean cyclePresent = false;

        // Phase 1: find a meeting point inside the loop, if one exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cyclePresent = true;
                break;
            }
        }

        if (!cyclePresent) {
            return null; // no cycle at all
        }

        // Phase 2: one pointer from Head, one left at the meeting point,
        // both now move ONE step at a time until they meet again.
        Node finder = Head;
        while (finder != slow) {
            finder = finder.next;
            slow = slow.next;
        }

        return finder; // this is the exact node where the cycle begins
    }

    public static void main(int[] args) {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = third;
        Head = first;
        hasCycle();
        detectCycleStart();
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}