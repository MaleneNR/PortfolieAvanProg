package app.algoritmik.circularlinkedlistsolution;

public class Main {

    public static void main(String[] args) {
        Node list = ListFactory.buildList(1, 2, 3, 4, 5);
        if(!hasCircle(list))
            System.out.println(list);

        Node circularlist = ListFactory.buildListWithCycle();
        if(!hasCircle(circularlist))
            System.out.println(circularlist);
    }

    private static boolean hasCircle(Node head){
        Node slow = head;
        Node fast = head;

        while (fast !=  null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}
