package myArrayList;

import java.util.Arrays;
import java.util.StringJoiner;

public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData; // Масив для зберігання елементів
    private int size = 0;

    /**
     * Конструктор, що створює список з початковою ємністю за замовчуванням.
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Повертає кількість елементів у списку.
     */
    public int size() {
        return size;
    }

    /**
     * Додає елемент у кінець списку.
     */
//    public void add(E element) {
//        // Перевіряємо, чи не заповнений наш внутрішній масив
//        if (size == elementData.length) {
//            grow(); // Якщо так, розширюємо його
//        }
//        elementData[size] = element;
//        size++;
//    }
    public void add(E element) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index); // Перевіряємо, чи індекс коректний
        return (E) elementData[index];
    }

    /**
     * Видаляє елемент за індексом.
     */
    public E remove(int index) {
        checkIndex(index);

        E oldValue = get(index);
        //
        int numMoved = size - index - 1;

        // Якщо видаляємо не останній елемент, потрібно зсунути частину масиву вліво
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        // Зменшуємо розмір і "затираємо" останній елемент, щоб збирач сміття міг його видалити
        size--;
        elementData[size] = null;

        return oldValue;
    }

    /**
     * Внутрішній метод для розширення масиву.
     */
    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity + oldCapacity * 1.5D);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * Внутрішній метод для перевірки коректності індексу.
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Індекс: " + index + ", Розмір: " + size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(elementData[i]));
        }
        return joiner.toString();
    }

}
