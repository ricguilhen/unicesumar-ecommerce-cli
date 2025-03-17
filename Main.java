import entities.Product;
import repository.InMemoryRepository;

public class Main {

    public static void main(String[] args) {
        InMemoryRepository<Product> productRepository = new InMemoryRepository<>();

        productRepository.save(new Product("Impressora", 100));
        productRepository.save(new Product("Computador", 3000));

        System.out.println("Exibindo produtos");
        productRepository.findAll().forEach(item -> System.out.println(item.getName()));
    }
}