package com.yeimy.applicationbooks.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.yeimy.applicationbooks.model.Author;
import com.yeimy.applicationbooks.model.Book;
import com.yeimy.applicationbooks.model.InformationBook;
import com.yeimy.applicationbooks.repository.AuthorRepository;
import com.yeimy.applicationbooks.repository.BookRepository;
import com.yeimy.applicationbooks.service.ConsumoAPI;
import com.yeimy.applicationbooks.service.ConverterData;


public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConverterData converter = new ConverterData();
    private BookRepository repository;
    @Autowired
    private AuthorRepository authorRepository;

    public Main(BookRepository repository2) {
         this.repository = repository2;
     }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija ula opción a través del número:

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
                    listAuthors();
                    break;
                case 4:
                    authorsLiveInYear();
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
        InformationBook datos = converter.getInformation(json.get("results").get(0).toString(), InformationBook.class);
        return datos;
    }

       private void searchBookApi() {
           InformationBook datos = getInformationBook();
           Book book = new Book(datos);
           repository.save(book);
           System.out.println("****LIBRO****\n"+ "Titulo: "+datos.title()+"\nAutor: "+datos.authors()+"\nIdioma: "+datos.languages()+"\nNumero de descargas: "+datos.download_count()+"\n*************");
     }
    
      private void showBookSearch(){
        List<Book> books = repository.findAll();  
        for (Book book : books) {
            System.out.println("****LIBRO****\n"+ "Titulo: "+book.getTitle()+"\nIdioma: "+book.getLanguages()+"\nNumero de descargas: "+book.getDownload_count()+"\n*************\n" + //
                                "");  
        }
      } 

     private void listAuthors(){
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            System.out.println("Nombre: " + author.getName() + 
                               ", Año de nacimiento: " + author.getBirth_year() + 
                               ", Año de fallecimiento: " + author.getDeath_year());
        }
    }

    private void authorsLiveInYear(){
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var userYear = keyboard.nextInt();
        List<Author> authors = authorRepository.findAll();

        List<Author> filteredYear = authors.stream()
                .filter(author -> author.getBirth_year() != null)
                .filter(author -> author.getBirth_year() >= userYear && author.getDeath_year() <= userYear)
                .collect(Collectors.toList());
        filteredYear.forEach(author -> System.out.println("Autor: " + author.getName()));
   }

     private void listForLanguages(){
         System.out.println("Escribe el lenguaje del libro que desea ver");
         var userLenguage = keyboard.nextLine();
         List<Book> books = repository.findAll();
         List<Book> filteredBooks = books.stream()
            .filter(book ->book.getLanguages().contains(userLenguage))
            .collect(Collectors.toList());
            if (filteredBooks.isEmpty()) {
                System.out.println("No se encontraron libros para el lenguaje: " + userLenguage);
            } else {
                filteredBooks.forEach(book -> System.out.println("Título: " + book.getTitle()));
            }
    }
}