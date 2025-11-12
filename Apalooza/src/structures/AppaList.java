package structures;

import java.util.ArrayList;
import java.util.List;

public class AppaList {
    private final List<Object> elements;

    public AppaList() {
        this.elements = new ArrayList<>();
    }

    public AppaList(List<Object> elements) {
        this.elements = new ArrayList<>(elements);
    }

    // Get element at index
    public Object get(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new RuntimeException("List index out of bounds: " + index);
        }
        return elements.get(index);
    }

    // Set element at index
    public void set(int index, Object value) {
        if (index < 0 || index >= elements.size()) {
            throw new RuntimeException("List index out of bounds: " + index);
        }
        elements.set(index, value);
    }

    // Add element to end of list (like JavaScript push)
    public void push(Object value) {
        elements.add(value);
    }

    // Remove and return last element (like JavaScript pop)
    public Object pop() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.remove(elements.size() - 1);
    }

    // Get length of list
    public int length() {
        return elements.size();
    }

    // Check if list contains element
    public boolean contains(Object value) {
        return elements.contains(value);
    }

    // Get underlying list (for iteration)
    public List<Object> getElements() {
        return new ArrayList<>(elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            Object elem = elements.get(i);
            if (elem instanceof String) {
                sb.append("\"").append(elem).append("\"");
            } else {
                sb.append(elem);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AppaList)) return false;
        AppaList other = (AppaList) obj;
        return elements.equals(other.elements);
    }
}
