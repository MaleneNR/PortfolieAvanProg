package app.algoritmik.circularlinkedlist;


public class Main {

    public static void main(String[] args) {
        Node list = ListFactory.buildList(1, 2, 3, 4, 5);
      if(!hasCycle(list))
        System.out.println(list);

      Node circularList = ListFactory.buildListWithCycle();
      if(!hasCycle(circularList))
        System.out.println(circularList);
    }

    public static boolean hasCycle (Node head){
        Node skildpadde = head;
        Node hare = head;

                while(true){
                    if (skildpadde == null || skildpadde.next == null) return false;
                    skildpadde = skildpadde.next;

                    if (hare == null || hare.next == null) return false;
                    hare = hare.next;

                    if (hare == null || hare.next == null) return false;
                    hare = hare.next;

                    if (skildpadde == hare) return true;
                }


    }
}
