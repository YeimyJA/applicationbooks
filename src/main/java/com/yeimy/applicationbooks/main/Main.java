package com.yeimy.applicationbooks.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.yeimy.applicationbooks.model.Book;
import com.yeimy.applicationbooks.model.InformationBook;
import com.yeimy.applicationbooks.repository.BookRepository;
import com.yeimy.applicationbooks.service.ConsumoAPI;
import com.yeimy.applicationbooks.service.ConverterData;

public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConverterData converter = new ConverterData();
    private List<Book> books  = new ArrayList<>();;
    private BookRepository repository;

    public Main(BookRepository repository2) {
         this.repository = repository2;
     }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """

                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcion) {
                case 1:
                    searchBookApi();
                    break;
                case 2:
                    showBookSearch();
                    break;
                case 3:
                    listBooks();
                    break;
                case 4:
                    listAuthors();
                    break;
                case 5:
                    listForLanguages();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private InformationBook getInformationBook() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nameBook = keyboard.nextLine();
        var json = consumoApi.getInformation(URL_BASE + "search=" + nameBook.replace(" ", "%20"));
        System.out.println(json.get("results").get(0).toString());
        InformationBook datos = converter.getInformation(json.get("results").get(0).toString(), InformationBook.class);
        return datos;
    }

       private void searchBookApi() {
           InformationBook datos = getInformationBook();
           Book book = new Book(datos);
           repository.save(book);
           System.out.println(book.getId());
           System.out.println(datos);
     }
    
      private void showBookSearch(){
          books = repository.findAll();  
          books.stream()
             .sorted(Comparator.comparing(Book::getDownload_count))
          .forEach(System.out::print);
      } 

     private void listBooks(){
        books = repository.findAll();  
        books.stream()
           .sorted(Comparator.comparing(Book::getDownload_count))
        .forEach(System.out::print);
     }
     
     private void listAuthors(){
        books = repository.findAll();  
        books.stream()
           .sorted(Comparator.comparing(Book::getDownload_count))
        .forEach(System.out::print);
    }

     private void listForLanguages(){
         System.out.println("Escribe el lenguaje del libro que desea ver");
         var userLenguage = keyboard.nextLine();
         var json = consumoApi.getInformation(URL_BASE + "languages=" + userLenguage);
        System.out.println(json.get("results").toString());   
    }
}