# -SI_2026_lab2_243207
JOVAN MITREV 243207

Prasanje 2:
SearchBookByTitle brojkite odat spored ovaa
    public List<Book> searchBookByTitle(String title) { //1
        if (title.isEmpty()){ 		 //2
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

<img width="2112" height="1320" alt="searchbook" src="https://github.com/user-attachments/assets/bf085c34-e863-4e53-9af2-195a7a374d95" />

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
    <img width="2112" height="1320" alt="borrowbook" src="https://github.com/user-attachments/assets/0364dbae-832a-41f7-9215-c655f40acca7" />

Prasanje 3:
Za SearchBookByTitle ciklomatcka vrednost e 5 spored formulata V(G) = 16-13 + 2 = 5 (rebra - jazli + 2)
Dodeka za BorrowBook e 6 spored formulata $V(G) = 18 - 14 + 2 = 6 (isto rebra - jazli + 2)

Prasanje 6:
Test slucaj Slucaj (title)	Pokrieni jazli (Linii)	   Cel na testot
1. ""                      2, 3                      Da frli IllegalArgumentException.  
2. "Clean Code"            2, 4, 5, 6, 7, 8, 9, 11   Uspesno naogjanje i vracanje na lista.  
3. "Nepostoecka"           5, 6, 8, 9, 10            Da vrati null ako nema rezultat.  


Slucaj 1: Go aktivira throw za nevaliden izlez  

Slucaj 2: Vleguva vo ciklusot, go ispolnuva if uslovot i dodava kniga vo rezultatite.  

Slucaj 3: Go pominuva ciklusot bez da dodade nisto i ja aktivira true grankata na results.isEmpty().  

3 e potreben zatoa shto mora da se izvrsat site tri razlicni zavrsetoci na funkcijata (throw, return null и return results).

Prasanje 8:
Test slucaj      	Vlez (title, author)	   Pokrieni Granki	                 Cel na testot
1. Invalid input	("", "Author")           2 (True)                         Da frli IllegalArgumentException za prazen vlez.  

2. Not found	    ("Harry", "Rowling")	  2 (False), 4.2 (False)            Da frli RuntimeException bidejki knigata ne postoi.  

3. Successful	     ("Hobbit", "Tolkien")  2 (False), 5 (True), 6 (True)     Uspesno naogjanje i iznajmuvanje na slobodna kniga.  

4. Borrowed	       ("1984", "Orwell")	    2 (False), 5 (True), 6 (False)    Da frli RuntimeException bidejki knigata e veke iznajmena.

Slucaj 1: Go aktivira throw uslovot uste na samiot pocetok poradi nevalidni parametri.  

Slucaj 2: Go pominuva celiot ciklus (books) bez da najde sovpagjanje, po sto ja aktivira grankata nadvor od ciklusot za "Book not found".  

Slucaj 3: Ja naogja knigata (True na prviot if vo ciklusot) и ja naogja kako slobodna (True na vtoriot if), so sto uspesno ja iznajmuva.  

Slucaj 4: Ja naogja knigata (True na prviot if), no ja aktivira False grankata na vtoriot if bidejki knigata e veke prethodno iznajmena.  

Minimalniot broj na test slucaevi e 4.

Prasanje 10:

Функција / Услов                           Тест случај (Подуслови)          Комбинација       Исход
borrowBook                                 title="", author="A"             T || X            Exception  
(title.isEmpty() || author.isEmpty())      title="T", author=""             F || T            Exception  
                                           title="T", author="A"            F || F            Success  
searchBookByTitle                          Match=True, Borrowed=False       T && T            Found  
(match && !borrowed)                       Match=True, Borrowed=True        T && F            Not Found  
                                           Match=False, Borrowed=X          F && X            Not Found  

