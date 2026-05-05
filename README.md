# -SI_2026_lab2_243207
# Втора лабораториска вежба по Софтверско инженерство

**Јован Митрев, Индекс: 243207**

---

## 2. Control Flow Graph

### Функција `searchBookByTitle`
```java
public List<Book> searchBookByTitle(String title) { //1
    if (title.isEmpty()){        //2
        throw new IllegalArgumentException("Invalid title"); //3 
    }
    List<Book> results = new ArrayList<Book>(); //4
    for (Book book : books) { //5
        if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) { //6
            results.add(book); //7
        }
    } //8
    if (results.isEmpty()) { //9
        return null; //10
    }
    return results; //11
} //12
```
<img width="2112" height="1320" alt="searchbook" src="https://github.com/user-attachments/assets/bf085c34-e863-4e53-9af2-195a7a374d95" />

---

### Функција `borrowBook`
```java
borrowBook e spored ovaa
    public void borrowBook(String title, String author) { // 1
        if (title.isEmpty() || author.isEmpty()){ //2
            throw new IllegalArgumentException("Invalid search query"); //3
        }
        for (Book book : books) { //4
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) { //5
                if (!book.isBorrowed()) { //6
                    book.setBorrowed(true); //7
                    System.out.println("Borrowed successfully"); //8
                } else {
                    throw new RuntimeException("Book is already borrowed."); //10
                }
                return; //11
            }
        }
        throw new RuntimeException("Book not found"); //12
    } // 13
```

<img width="2112" height="1320" alt="borrowbook" src="https://github.com/user-attachments/assets/0364dbae-832a-41f7-9215-c655f40acca7" />

## Прашање 3: Цикломатска комплексност

Цикломатската комплексност е пресметана со користење на формулата $V(G) = E - N + 2$:

*   **SearchBookByTitle**: Вредноста е **5**, бидејќи $V(G) = 16 - 13 + 2 = 5$ (ребра - јазли + 2).
*   **BorrowBook**: Вредноста е **6**, бидејќи $V(G) = 18 - 14 + 2 = 6$ (ребра - јазли + 2).

---

## Прашање 6: Every Statement (searchBookByTitle)

| Тест случај | Влез (`title`) | Покриени јазли (линии) | Цел на тестот |
| :--- | :--- | :--- | :--- |
| **1. ""** | `""` | 2, 3 | Da frli `IllegalArgumentException`. |
| **2. "Clean Code"** | `"Clean Code"` | 2, 4, 5, 6, 7, 8, 9, 11 | Uspesno naogjanje i vracanje na lista. |
| **3. "Nepostoecka"** | `"Nepostoecka"` | 5, 6, 8, 9, 10 | Da vrati `null` ako nema rezultat. |

### Објаснување:
*   **Slucaj 1**: Go aktivira `throw` за невалиден излез.
*   **Slucaj 2**: Vleguva vo ciklusot, go ispolnuva `if` uslovot i dodava kniga vo rezultatite.
*   **Slucaj 3**: Go pominuva ciklusot bez da dodade nisto i ja aktivira `true` grankata na `results.isEmpty()`.

**Минималниот број на тест случаи е 3**, бидејќи мора да се извршат сите три различни завршетоци на функцијата (`throw`, `return null` и `return results`).

---

## Прашање 8: Every Branch (borrowBook)

| Тест случај | Влез (`title`, `author`) | Покриени Гранки | Цел на тестот |
| :--- | :--- | :--- | :--- |
| **1. Invalid input** | `("", "Author")` | 2 (True) | Da frli `IllegalArgumentException` za prazen vlez. |
| **2. Not found** | `("Harry", "Rowling")` | 2 (False), 4.2 (False) | Da frli `RuntimeException` bidejki knigata ne postoi. |
| **3. Successful** | `("Hobbit", "Tolkien")` | 2 (False), 5 (True), 6 (True) | Uspesno naogjanje i iznajmuvanje na slobodna kniga. |
| **4. Borrowed** | `("1984", "Orwell")` | 2 (False), 5 (True), 6 (False) | Da frli `RuntimeException` bidejki knigata e veke iznajmena. |

### Објаснување:SI_2026_lab2_243207Јован Митрев 243207Prasanje 2: Control Flow GraphSearchBookByTitle brojkite odat spored ovaa:Javapublic List<Book> searchBookByTitle(String title) { //1
    if (title.isEmpty()){        //2
        throw new IllegalArgumentException("Invalid title"); //3 
    }
    List<Book> results = new ArrayList<Book>(); //4
    for (Book book : books) { //5
        if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) { //6
            results.add(book); //7
        }
    } //8
    if (results.isEmpty()) { //9
        return null; //10
    }
    return results; //11
} //12
borrowBook e spored ovaa:Javapublic void borrowBook(String title, String author) { // 1
    if (title.isEmpty() || author.isEmpty()){ //2
        throw new IllegalArgumentException("Invalid search query"); //3
    }
    for (Book book : books) { //4
        if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) { //5
            if (!book.isBorrowed()) { //6
                book.setBorrowed(true); //7
                System.out.println("Borrowed successfully"); //8
            } else {
                throw new RuntimeException("Book is already borrowed."); //10
            }
            return; //11
        }
    }
    throw new RuntimeException("Book not found"); //12
} // 13
Prasanje 3: Ciklomatcka kompleksnostZa SearchBookByTitle ciklomatcka vrednost e 5 spored formulata V(G) = 16-13 + 2 = 5 (rebra - jazli + 2).Dodeka za BorrowBook e 6 spored formulata V(G) = 18 - 14 + 2 = 6 (isto rebra - jazli + 2).Prasanje 6: Every Statement (za searchBookByTitle)Test slucaj (title)Pokrieni jazli (Linii)Cel na testot1. ""2, 3Da frli IllegalArgumentException.2. "Clean Code"2, 4, 5, 6, 7, 8, 9, 11Uspesno naogjanje i vracanje na lista.3. "Nepostoecka"5, 6, 8, 9, 10Da vrati null ako nema rezultat.Slucaj 1: Go aktivira throw za nevaliden izlez.Slucaj 2: Vleguva vo ciklusot, go ispolnuva if uslovot i dodava kniga vo rezultatite.Slucaj 3: Go pominuva ciklusot bez da dodade nisto i ja aktivira true grankata na results.isEmpty().3 e potreben zatoa shto mora da se izvrsat site tri razlicni zavrsetoci na funkcijata (throw, return null и return results).Prasanje 8: Every Branch (za borrowBook)Invalid input: ("", "Author") -> Pokriva 2 (True). Da frli IllegalArgumentException za prazen vlez.Not found: ("Harry", "Rowling") -> Pokriva 2 (False), 4.2 (False). Da frli RuntimeException bidejki knigata ne postoi.Successful: ("Hobbit", "Tolkien") -> Pokriva 2 (False), 5 (True), 6 (True). Uspesno naogjanje i iznajmuvanje na slobodna kniga.Borrowed: ("1984", "Orwell") -> Pokriva 2 (False), 5 (True), 6 (False). Da frli RuntimeException bidejki knigata e veke iznajmena.Slucaj 1 aktivira throw uste na pocetok. Slucaj 2 go pominuva celiot ciklus bez da najde nisto i frla Book not found. Slucaj 3 ja naogja knigata i ja iznajmuva. Slucaj 4 ja naogja ama e veke zafatena.Minimalniot broj na test slucaevi e 4.Prasanje 10: Multiple ConditionZa borrowBook (title.isEmpty() || author.isEmpty()):title="", author="A" -> T || X (Exception)title="T", author="" -> F || T (Exception)title="T", author="A" -> F || F (Success)Za searchBookByTitle (match && !borrowed):Match=True, Borrowed=False -> T && T (Found)Match=True, Borrowed=True -> T && F (Not Found)Match=False, Borrowed=X -> F && X (Not Found)
*   **Slucaj 1**: Go aktivira `throw` uslovot uste na samiot pocetok poradi nevalidni parametri.
*   **Slucaj 2**: Go pominuva celiot ciklus (`books`) bez da najde sovpagjanje, po sto ja aktivira grankata nadvor od ciklusot za "Book not found".
*   **Slucaj 3**: Ja naogja knigata (True na prviot `if` vo ciklusot) и ja naogja kako slobodna (True na vtoriot `if`), so sto uspesno ja iznajmuva.
*   **Slucaj 4**: Ja naogja knigata (True na prviot `if`), no ja aktivira `False` гранката на вториот `if` бидејќи книгата е веќе претходно изнајмена.

**Минималниот број на тест случаи е 4.**

---

## Прашање 10: Multiple Condition

| Функција / Услов | Тест случај (Подуслови) | Комбинација | Исход |
| :--- | :--- | :--- | :--- |
| **borrowBook** | `title=""`, `author="A"` | **T \|\| X** | Exception |
| `(title.isEmpty() \|\| author.isEmpty())` | `title="T"`, `author=""` | **F \|\| T** | Exception |
| | `title="T"`, `author="A"` | **F \|\| F** | Success |
| **searchBookByTitle** | `Match=True`, `Borrowed=False` | **T && T** | Found |
| `(match && !borrowed)` | `Match=True`, `Borrowed=True` | **T && F** | Not Found |
| | `Match=False`, `Borrowed=X` | **F && X** | Not Found |
