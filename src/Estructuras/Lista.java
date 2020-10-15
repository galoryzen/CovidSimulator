package Estructuras;

import java.util.Iterator;


public class Lista<Item> implements Iterable<Item> {

    private Node PTR;

    @Override
    public Iterator<Item> iterator() {
        return new ListaIterador<Item>(this);
    }

    public class ListaIterador<T> implements Iterator<T> {

        Node actual;

        public ListaIterador(Lista<T> lista) {
            this.actual = (Node) lista.getPTR();
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            T dato = (T) actual.getDato();
            actual = actual.getLink();
            return dato;
        }
    }

    public class Node {

        private Object Dato;
        private Node Link;

        public Node() {
            this.Dato = null;
            this.Link = null;
        }

        public Object getDato() {
            return Dato;
        }

        public Node getLink() {
            return Link;
        }

    }

    public Lista() {
        PTR = null;
    }

    public void add(Object item) {
        Node P = PTR;
        Node Q = new Node();
        if (PTR == null) {
            PTR = new Node();
            PTR.Dato = item;
        } else {
            while (P.Link != null) {
                P = P.Link;
            }
            P.Link = Q;
            Q.Dato = item;
        }
    }

    public Item get(int index) {
        int cont = 0;
        Node P = PTR;
        while (P != null) {
            if (cont == index) {
                return (Item) P.Dato;
            }
            cont++;
            P = P.Link;
        }
        return null;
    }


    public Node getPTR() {
        return PTR;
    }
    
    public Object getLast(){
        Node p = PTR;
        while (p.Link!= null) {
            p = p.Link;
        }
        return p.Dato;
    }
    

    public int size() {
        int c = 0;
        Node p = PTR;
        while (p != null) {
            c++;
            p = p.Link;
        }
        return c;
    }
    
    public boolean isEmpty(){
        return PTR == null;
    }
    
    public boolean contains(Object o){
        Node p = PTR;
        while (p!=null) {
            if (o == p.Dato) return true;
            p = p.Link;
        }
        return false;
    }
}
