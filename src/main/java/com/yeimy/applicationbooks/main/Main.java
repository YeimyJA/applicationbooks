package com.yeimy.applicationbooks.main;

import com.yeimy.applicationbooks.model.Categoria;
import com.yeimy.applicationbooks.model.InformationBook;
import com.yeimy.applicationbooks.model.Book;
import com.yeimy.applicationbooks.service.ConsumoAPI;
import com.yeimy.applicationbooks.service.ConverterData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConverterData converter = new ConverterData();
    private List<InformationBook> datosSeries = new ArrayList<>();
    private List<Book> books;
    private Optional<Book> searchBook;

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
                    getInformationBook();
                    break;
                // case 2:
                //     buscarEpisodioPorSerie();
                //     break;
                // case 3:
                //     mostrarSeriesBuscadas();
                //     break;
                // case 4:
                //     buscarSeriePorTitulo();
                //     break;
                // case 5:
                //     buscarTop5Series();
                //     break;
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
        var json = consumoApi.getInformation(URL_BASE + nameBook.replace(" ", "%20"));
        System.out.println(json);
        InformationBook datos = converter.getInformation(json, InformationBook.class);
        return datos;
    }

    //  private void searchBookApi() {
    //      InformationBook datos = getInformationBook();
    //      Book book = new Book(datos);
    //      repository.save(book);
    //      System.out.println(datos);
    //  }

    // private void buscarEpisodioPorSerie() {
    //     mostrarSeriesBuscadas();
    //     System.out.println("Escribe el nombre de la serie para mostrar sus episodios");
    //     var nombreSerie = keyboard.nextLine();

    //     Optional<Book> serie = series.stream()
    //         .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
    //         .findFirst();
    //         if (serie.isPresent()) {
    //             var serieEncontrada = serie.get();

    //             List<DatosTemporadas> temporadas = new ArrayList<>();

    //             for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
    //                 var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i );
    //                 DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
    //                 temporadas.add(datosTemporada);
    //     }
    //             temporadas.forEach(System.out::println);

    //             List<Episodio> episodios = temporadas.stream()
    //                 .flatMap(d -> d.episodios().stream()
    //                     .map(e -> new Episodio(d.numero(), e)))
    //                 .collect(Collectors.toList());
                
    //             serieEncontrada.setEpisodios(episodios);
    //             repositorio.save(serieEncontrada);
    //         }
    // }
    
    // private void mostrarSeriesBuscadas(){
    //     series = repositorio.findAll();
        
    //     series.stream()
    //         .sorted(Comparator.comparing(Book::getGenero))
    //         .forEach(System.out::print);
    // }

    // private void buscarSeriePorTitulo(){
    //     System.out.println("Escribe el nombre de la serie para que desea buscar");
    //     var nombreSerie = keyboard.nextLine();
    //     serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);

    //     if (serieBuscada.isPresent()) 
    //         System.out.println("La serie buscada es: "+ serieBuscada.get());
    //         else 
    //         System.out.println("Serie no encontrada");
    // }

    // private void buscarTop5Series(){
    //     List<Book> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
    //     topSeries.forEach(s -> System.out.println("Serie: "+ s.getTitulo()+" Evaluacion: "+s.getEvaluacion()));
    // }

    // private void buscarSeriesPorCategoria(){
    //     System.out.println("Escriba el genero/categoria que desea bsucar");
    //     var genero = keyboard.nextLine();
    //     var categoria = Categoria.fromEspanol(genero);
    //     List<Book> seriePorCategoria = repositorio.findByGenero(categoria);
    //     System.out.println("Las series de la categortia "+genero);
    //     seriePorCategoria.forEach(s -> System.out.println("Serie: "+ s.getTitulo()+" Evaluacion: "+s.getEvaluacion()));
    // }

    // private void buscarSeriesPorTemporadaYEvaluacion(){
    //     System.out.println("¿De cuantas temporadas desea la serie?");
    //     var totalTemporadas = keyboard.nextInt();
    //     keyboard.nextLine();
    //     System.out.println("¿A partir de que evaluacion");
    //     var evaluacion = keyboard.nextDouble();
    //     keyboard.nextLine();
    //     List<Book> filtroSeries = repositorio.seriesPorTemporadaYEvaluacion();
    //     System.out.println("*** Series filtradas ***");
    //     filtroSeries.forEach(s -> System.out.println(s.getTitulo()+" evaluacion: "+ s.getEvaluacion()));
    // }
    
    // private void buscarEpisodiosPorTitulo(){
    //     System.out.println("Escribe el nombre del episodio que deseas buscar");
    //     var nombreEpisodio = keyboard.nextLine();
    //     List<Episodio> episodiosEncontrados = repositorio.episodiosPorNombre(nombreEpisodio);
    //     episodiosEncontrados.forEach(e -> System.out.printf("Serie: %s Temporada %s Episodio %s Evaluacion %s",
    //         e.getSerie(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));
    // }

    // public void buscarTop5Episodios(){
    //     buscarSeriePorTitulo();
    //     if (serieBuscada.isPresent()) {
    //         Book serie = serieBuscada.get();
    //         List<Episodio> topEpisodios = repositorio.top5Episodios(serie);
    //         topEpisodios.forEach(e -> System.out.printf("Serie: %s Temporada %s Episodio %s Evaluacion %s",
    //             e.getSerie(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));
    //     }
    // }
}