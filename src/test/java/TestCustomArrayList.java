import com.homework.CustomArrayList;

import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomArrayList {

    private CustomArrayList<String> testStringList;
    private CustomArrayList<Integer> testIntegerList;

    @BeforeEach
    void initList() {
        testStringList = new CustomArrayList<>();
        testIntegerList = new CustomArrayList<>();
    }

    @AfterEach
    void destroyList() {
        testStringList = null;
        testIntegerList = null;
    }

    @DisplayName("Добавляем элементы в лист")
    @Test
    public void testAdd() {

        // Добавляем 1000 цифр в список
        for (int i = 0; i < 1000; i++) {
            testIntegerList.add(i);
        }
        // Добавляем 1000 строк в список
        for (int i = 0; i < 1000; i++) {
            testStringList.add(String.valueOf(i));
        }
        assertEquals(1000, testIntegerList.size());
        assertEquals(1000, testStringList.size());
    }

    @DisplayName("Добавляем элементы в лист по индексу")
    @Test
    public void testAddWithIndex() {

        // Добавляем 1000 цифр в список
        for (int i = 0; i < 1000; i++) {
            testIntegerList.add(i, i);
        }
        // Добавляем 1000 строк в список
        for (int i = 0; i < 1000; i++) {
            testStringList.add(i, String.valueOf(i));
        }
        // Проверяем, что элементы успешно добавлены
        assertEquals(1000, testIntegerList.size());
        assertEquals(1000, testStringList.size());

        // Проверяем выбрасывание исключения при попытке добавить элемент с недопустимым индексом
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.add(-1, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.add(1001, 6));
    }

    @DisplayName("Заменяем элементы в списке")
    @Test
    public void testSet() {
        // Добавляем элементы в список
        testIntegerList.add(1);
        testIntegerList.add(2);
        testIntegerList.add(3);

        // Заменяем элемент с индексом 1 на новое значение
        int oldElement = testIntegerList.set(1, 5);

        // Проверяем, что возвращенное значение соответствует старому элементу
        assertEquals(2, oldElement);

        // Проверяем, что элемент успешно заменен
        assertEquals(5, testIntegerList.get(1));
        // Проверяем выбрасывание исключения при попытке заменить элемент с недопустимым индексом
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.set(3, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.set(-3, 6));
    }

    @DisplayName("Получаем элемент из списка")
    @Test
    public void testGet() {

        // Добавляем элементы в список
        testIntegerList.add(1);
        testIntegerList.add(2);
        testIntegerList.add(3);

        // Проверяем получение элементов по индексу
        assertEquals(1, testIntegerList.get(0));
        assertEquals(2, testIntegerList.get(1));
        assertEquals(3, testIntegerList.get(2));

        // Проверяем выбрасывание исключения при попытке получить элемент с недопустимым индексом
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.get(-3));
    }

    @DisplayName("Удаляем элементы из списка по индексу")
    @Test
    public void testDelete() {

        // Добавляем элементы в список
        testIntegerList.add(1);
        testIntegerList.add(2);
        testIntegerList.add(3);

        // Удаляем элемент по индексу 1 (значение 2)
        int deletedElement = testIntegerList.delete(1);

        // Проверяем, что возвращенное значение соответствует удаленному элементу
        assertEquals(2, deletedElement);

        // Проверяем, что Размер списка уменьшился на 1
        assertEquals(2, testIntegerList.size());

        // Проверяем, что элемент 3 теперь имеет индекс 1
        assertEquals(3, testIntegerList.get(1));

        // Пытаемся удалить элемент с недопустимым индексом
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.delete(2));
        assertThrows(IndexOutOfBoundsException.class, () -> testIntegerList.delete(-1));
    }

    @DisplayName("Удаляем элементы из списка по значению")
    @Test
    public void testDeleteByValue() {


        // Добавляем элементы в список
        testStringList.add("1");
        testStringList.add("2");
        testStringList.add("3");

        // Удаляем элемент со значением 2
        boolean isDeleted = testStringList.delete("2");

        // Проверяем, что элемент успешно удален
        assertTrue(isDeleted);

        // Проверяем размер списка после удаления
        assertEquals(2, testStringList.size());

        // Пытаемся удалить элемент, которого нет в списке
        boolean isDeletedNonExisting = testStringList.delete("32");

        // Проверяем, что удаление элемента, которого нет в списке, вернуло false
        assertFalse(isDeletedNonExisting);

    }

    @DisplayName("Очищаем список")
    @Test
    public void testClear() {

        // Добавляем элементы в список
        testIntegerList.add(1);
        testIntegerList.add(2);
        testIntegerList.add(3);

        // Очищаем список
        testIntegerList.clear();

        // Проверяем, что список пустой после очистки
        assertTrue(testIntegerList.isEmpty());

        // Проверяем, что размер списка равен 0 после очистки
        assertEquals(0, testIntegerList.size());

    }

    @DisplayName("Получаем размер списка")
    @Test
    public void testSize() {

        // Добавляем элементы в список
        testIntegerList.add(1);
        testIntegerList.add(2);
        testIntegerList.add(3);

        // Получаем размер списка
        int size = testIntegerList.size();

        // Проверяем, что размер списка равен ожидаемому значению
        assertEquals(3, size);
    }

    @DisplayName("Проверяем пустой ли список")
    @Test
    public void testIsEmpty() {

        // Проверяем, что список пустой в начале
        assertTrue(testIntegerList.isEmpty());

        // Добавляем элемент в список
        testIntegerList.add(1);

        // Проверяем, что список больше не пустой
        assertFalse(testIntegerList.isEmpty());
    }

    @DisplayName("Проверяем порядок элементов")
    @Test
    public void testSort() {

        testIntegerList.add(2);
        testIntegerList.add(3);
        testIntegerList.add(1);

        // Создаем компаратор для сравнения элементов списка
        Comparator<Integer> comparator = Comparator.naturalOrder();

        // Сортируем список
        testIntegerList.sort(comparator);

        // Проверяем, что список отсортирован в правильном порядке
        assertEquals(1, testIntegerList.get(0));
        assertEquals(2, testIntegerList.get(1));
        assertEquals(3, testIntegerList.get(2));
    }

}
