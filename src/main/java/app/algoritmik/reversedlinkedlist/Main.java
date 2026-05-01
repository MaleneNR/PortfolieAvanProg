package app.algoritmik.reversedlinkedlist;


public class Main {

    public static void main(String[] args) {

      Node head = ListFactory.buildList(1, 2, 3, 4, 5);
        System.out.println("LinkedList: " + head.toString());
        head = reverseList(head);
        System.out.println("LinkedList reversed: " + head.toString());
    }

    public static Node reverseList(Node head){
        Node previous = null; //Når vi arbejder med previous, er den den del af listen vi HAR vendt
        Node current = head;  //current er den vi arbejder på at vende
        Node next;            //resten af listen, som vi ikke har rørt og ikke vil miste.

        while (current != null){
            next = current.next; //Vi gemmer referencen til næste node, så vi ikke mister resten af listen


            current.next = previous; //vi vender retningen, så null er for enden
            previous = current; //så flytter vi previous en frem, så den nu er vores nye forrige
            current = next; //current flyttes også én frem i den originale liste
        }
        return previous;


    }

}
