import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldAddProductSuccessfully() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);

        // Добавляем два продукта
        repo.add(product1);
        repo.add(product2);

        // Проверяем результат
        Product[] expected = {product1, product2};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual, "Products should be added to the repository");
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionIfProductWithSameIdExists() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(1, "Product 2", 200); // Дублирующийся ID

        repo.add(product1);

        Exception exception = assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product2);
        });

        assertEquals("Product with id: 1 already exists", exception.getMessage());
    }

    @Test
    public void shouldRemoveProductSuccessfully() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);

        repo.add(product1);
        repo.add(product2);

        // Удаляем продукт с ID 1
        repo.remove(1);

        // Проверяем результат
        Product[] expected = {product2};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual, "Product with ID 1 should be removed from the repository");
    }

    @Test
    public void shouldThrowNotFoundExceptionIfProductToRemoveDoesNotExist() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        repo.add(product1);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            repo.remove(2); // Продукт с ID 2 не существует
        });

        assertEquals("Element with id: 2 not found", exception.getMessage());
    }
}