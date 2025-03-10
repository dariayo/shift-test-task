# File Filter Utility

**File Filter Utility** — это утилита командной строки на Java, которая фильтрует содержимое входных файлов, разделяя их на три отдельных файла: целые числа, числа с плавающей точкой и строки. Утилита также собирает статистику по каждому типу данных.

---

## 🚀 Функционал

1. **Фильтрация данных:**
   - Входные файлы читаются в порядке их перечисления в командной строке.
   - Данные разделяются по типам:
     - **Целые числа** сохраняются в файл `sample-integers.txt`.
     - **Вещественные числа** сохраняются в файл `sample-floats.txt`.
     - **Строки** сохраняются в файл `sample-strings.txt`.

2. **Настраиваемые параметры:**
   - `-o <path>` — задаёт путь для выходных файлов.
   - `-p <prefix>` — добавляет префикс к именам выходных файлов.
   - `-a` — включает режим добавления данных к существующим файлам.

3. **Статистика:**
   - Краткая (`-s`): количество записанных элементов для каждого типа.
   - Полная (`-f`):
     - Для чисел: минимальное, максимальное значение, сумма, среднее.
     - Для строк: длина самой короткой и самой длинной строки.

4. **Обработка ошибок:**
   - Программа не "падает" при ошибках, а информирует пользователя.
   - Частичная обработка входных файлов предпочтительна.

---

## 🛠 Сборка и запуск

### Зависимости

- **Java**: JDK 17+
- **Maven**: 3.6.0+

### Сборка проекта

1. Клонируйте репозиторий:

   ```bash
   git clone <URL>
   ```

2. Соберите проект:

   ```bash
   mvn clean package
   ```

3. Исполняемый файл будет создан в папке `target`:

   ```bash
   target/file-filter-util-1.0.jar
   ```

### Запуск утилиты

Пример запуска утилиты:

```bash
java -jar target/file-filter-util-1.0.jar -s -a -p sample- input/in1.txt input/in2.txt
```

---

## 📂 Пример использования

### Входные файлы

**in1.txt**

```
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500
```

**in2.txt**

```
Нормальная форма числа с плавающей запятой
1.528535047E-25
Long
1234567890123456789
```

### Команда

```bash
java -jar target/file-filter-util-1.0.jar -s -a -p sample- input/in1.txt input/in2.txt
```

### Выходные файлы

**sample-integers.txt**

```
45
1234567890123456789
100500
```

**sample-floats.txt**

```
1.528535047E-25
3.1415
-0.001
```

**sample-strings.txt**

```
Lorem ipsum dolor sit amet
Нормальная форма числа с плавающей запятой
Пример
Long
consectetur adipiscing
тестовое задание
```

### Вывод статистики

```plaintext
=== Statistics ===
[Integers]
Count: 3

[Floats]
Count: 3

[Strings]
Count: 6
```

---

## 💡 Особенности

1. **Создание файлов по мере необходимости:** выходные файлы создаются только при наличии данных соответствующего типа.
2. **Удобный режим добавления:** флаг `-a` позволяет сохранять предыдущие результаты.
3. **Гибкая конфигурация вывода:** можно настроить путь и префикс для выходных файлов.
4. **Обработка ошибок:** даже если один из файлов повреждён, остальные обрабатываются корректно.
