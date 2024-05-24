package com.homework;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements;
    private int size;

    /**
     * Создает пустой список с начальной емкостью DEFAULT_SIZE.
     */
    public CustomArrayList() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Создает пустой список с заданной емкостью specifiedSize.
     *
     * @param specifiedSize - начальная емкость списка
     * @throws IllegalArgumentException - если заданная емкость < 0
     */
    public CustomArrayList(int specifiedSize) {
        if (specifiedSize > 0) {
            this.elements = (T[]) new Object[specifiedSize];
            this.size = 0;
        } else if (specifiedSize == 0) {
            this.elements = (T[]) new Object[0];
        } else {
            throw new IllegalArgumentException("specifiedSize должен быть >= 0");
        }
    }

    /**
     * Добавляет указанный элемент в конец списка.
     *
     * @param element - добавляется в список.
     */
    public void add(T element) {
        if (isFullArray()) {
            resize();
        }
        elements[size] = element;
        size++;
    }

    /**
     * Проверяет индекс на нахождение в диапазоне от 0 до последнего элемента в списке
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this);
    }

    /**
     * Вставляет элемент в указанную позицию в списке.
     * Сдвигает элемент, который в данный момент находится в этой позиции,
     * а также другие элементы, находящиеся правее - вправо.
     * Если вставить в последнюю позицию списка, элемент добавится в конец списка.
     *
     * @param index   - позиция для вставки
     * @param element - подлежащий вставке
     * @throws IndexOutOfBoundsException - если индекс выходит за пределы списка
     */
    public void add(int index, T element) {
        checkIndexForAdd(index);
        if (isFullArray()) {
            resize();
        }
        if (size - index >= 0) System.arraycopy(elements, index, elements,
                index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Заменяет элемент в позиции в этом списке на новый элемент.
     *
     * @param index   - позиция элемента
     * @param element - который должен быть сохранен
     * @return возвращает элемент, который ранее находился в указанной позиции
     * @throws IndexOutOfBoundsException - если индекс выходит за пределы списка
     */
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldElement = elements[index];
        elements[index] = element;
        return oldElement;

    }

    /**
     * Возвращает элемент в заданной позиции в этом списке.
     *
     * @param index- позиция элемента
     * @return - элемент в заданной позиции в этом списке
     * @throws IndexOutOfBoundsException - если индекс выходит за пределы списка
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elements[index];
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     * Сдвигает все последующие элементы влево.
     *
     * @param index - для элемента, который нужно удалить
     * @return элемент, который был удален из списка
     * @throws IndexOutOfBoundsException - если индекс неверен
     */
    public T delete(int index) {
        Objects.checkIndex(index, size);
        T oldElement = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldElement;
    }

    /**
     * Удаляет первое появление элемента из этого списка.
     *
     * @param element - элемент, который должен быть удален из этого списка, если он присутствует;
     * @return - {@code true}, если этот список содержал указанный элемент, {@code false} - если не содержал
     */
    //            System.out.println("Элемента '" + element + "' нет в списке");
    public boolean delete(T element) {
        int index = findIndex(element);
        if (index < 0) {

            return false;
        }
        delete(index);
        return true;
    }

    /**
     * Находит индекс элемента в списке по значению
     *
     * @param element - элемент, индекс которого нужно найти
     */
    private int findIndex(T element) {
        if (element == null)
            return -1;
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i]))
                return i;
        }
        return -1;
    }

    /**
     * Удаляет все элементы списка.
     */
    public void clear() {
        int rightBound = size;
        for (int i = 0; i < rightBound; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return - количество элементов в этом списке.
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает значение true, если список не содержит элементов, иначе false
     *
     * @return - {@code true} если список не содержит элементов, иначе - {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }


    private boolean isFullArray() {
        return size == elements.length;
    }

    /**
     * Увеличивает размер базового массива в 1,5 раза.
     * Создает новый массив с новой емкостью. Копирует все элементы в новый массив.
     */
    private void resize() {
        T[] oldElements = this.elements;
        this.elements = (T[]) new Object[oldElements.length + oldElements.length / 2];
        for (int i = 0; i < size; i++) {
            this.elements[i] = oldElements[i];
        }
    }

    /**
     * Сортирует список в соответствии с порядком, заданным компаратором.
     *
     * @param comparator - используется для сравнения элементов списка
     * @throws NullPointerException - если список содержит null элементы
     */
    public void sort(Comparator<T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Реализация быстрой сортировки.
     *
     * @param low        - нижняя граница массива
     * @param high       - верхняя граница массива
     * @param comparator - используется для сравнения элементов списка
     * @throws NullPointerException - если список содержит null элементы
     */
    private void quickSort(int low, int high, Comparator <T> comparator) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        T pivot = elements[middle];

        int leftBound = low;
        int rightBound = high;
        while (leftBound <= rightBound) {
            while (comparator.compare(elements[leftBound], pivot) < 0) {
                leftBound++;
            }
            while (comparator.compare(elements[rightBound], pivot) > 0) {
                rightBound--;
            }
            if (leftBound <= rightBound) {
                T temp = elements[leftBound];
                elements[leftBound] = elements[rightBound];
                elements[rightBound] = temp;
                leftBound++;
                rightBound--;
            }
        }
        if (low < rightBound) {
            quickSort(low, rightBound, comparator);
        }
        if (high > leftBound) {
            quickSort(leftBound, high, comparator);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

}