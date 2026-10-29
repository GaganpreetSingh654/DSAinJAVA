
// public class MergeTwoSortedLists {

//     static void printList(Node head) {
//         Node current = head;
//         while (current != null) {
//             System.out.print(current.data + " ---> ");
//             current = current.next;
//         }
//         System.out.println("null");
//     }

//     static Node buildList(Node... nodes) {
//         Node dummyNode = new Node(-1);
//         Node tail = dummyNode;

//         for (Node n : nodes) {
//             tail.next = n;
//             tail = n;
//         }
//         return dummyNode.next;
//     }

//     static void deleteNthnode() {
//         int count = 0;
//         Node current = Head;
        

//     }

//     public static void main(String[] args) {
//         Node list1 = buildList(new Node(2), new Node(4), new Node(5));
//         deleteNthnode();
//     }

//     static class Node {
//         int data;
//         Node next;

//         Node(int data) {
//             this.data = data;
//             this.next = null;
//         }
//     }

// }