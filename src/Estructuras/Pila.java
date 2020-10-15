package Estructuras;


import java.util.Iterator;

/**
 * Representa una estructura LIFO(Ultimo en entrar, primero en salir) de items genericos.
 *
 * @author Raul
 * @param <Item> El tipo de los datos a guardar en esta pila.
 */
public class Pila<Item> implements Iterable<Item> {

    private int n;
    private Nodo first;

    public Pila() {
        first = null;
        n = 0;
    }

    private class Nodo {

        private Item item;
        private Nodo next;
    }

    public void push(Item item) {
        Nodo anterior = first;
        first = new Nodo();
        first.item = item;
        first.next = anterior;
        n++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public Item pop() {
        if (!isEmpty()) {
            Item item = first.item; //Guarda el item a devolver
            first = first.next;     //Borra el ptr
            n--;                    //Le baja el tamaño a la pila
            return item;            //Retorna el item guardado anteriormente
        }
        return null;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    /**
     * El iterador de la pila.
     */
    private class ListIterator implements Iterator<Item> {

        private Nodo current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            return null;
        }
    }
}
