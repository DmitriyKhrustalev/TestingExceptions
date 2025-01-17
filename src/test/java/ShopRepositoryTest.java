import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveProductIfExists() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);
        repo.add(product1);
        repo.add(product2);

        repo.remove(1);

        Product[] expected = {product2};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionIfProductDoesNotExist() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        repo.add(product1);

        Exception exception = assertThrows(NotFoundException.class, () -> repo.remove(2));

        assertEquals("Element with id: 2 not found", exception.getMessage());
    }
}