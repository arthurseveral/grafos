package mestrado;

import java.util.Objects;

public class Vertex<T> {
    
    private T item;
    
    public Vertex(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.item);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<?> other = (Vertex<?>) obj;
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return getItem().toString();
    }
    
}
