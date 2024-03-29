package br.com.duosdevelop.ecommerce.services;

import br.com.duosdevelop.ecommerce.domain.*;
import br.com.duosdevelop.ecommerce.domain.enums.Perfil;
import br.com.duosdevelop.ecommerce.domain.enums.StatePayment;
import br.com.duosdevelop.ecommerce.domain.enums.TypeCustomer;
import br.com.duosdevelop.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder be;

    @Autowired
    private CategoryRepository repositoryCategory;

    @Autowired
    private ProductRepository repositoryProduct;

    @Autowired
    private StateRepository repositoryState;

    @Autowired
    private CityRepository repositoryCity;

    @Autowired
    private CustomerRepository repositoryCustomer;

    @Autowired
    private AddressRepository repositoryAddress;

    @Autowired
    private PedidoRepository repositoryPedido;

    @Autowired
    private PaymentRepository repositoryPayment;

    @Autowired
    private ItemPedidoRepository repositoryItemPedido;

    public void instantiateTestDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletrônicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product prod1 = new Product(null, "Computador", 2000.00);
        Product prod2 = new Product(null, "Impressora", 800.00);
        Product prod3 = new Product(null, "Mouse", 80.00);
        Product prod4 = new Product(null, "Mesa de Escritório", 300.00);
        Product prod5 = new Product(null, "Toalha", 50.00);
        Product prod6 = new Product(null, "Colcha", 200.00);
        Product prod7 = new Product(null, "TV true color", 1200.00);
        Product prod8 = new Product(null, "Roçadeira", 800.00);
        Product prod9 = new Product(null, "Abajour", 100.00);
        Product prod10 = new Product(null, "Pendente", 180.00);
        Product prod11 = new Product(null, "Shampoo", 90.00);

        State mg = new State(null, "Minas Gerais");
        State sp = new State(null, "São Paulo");

        City ubCity = new City(null, "Uberlândia", mg);
        City spCity = new City(null, "São Paulo", sp);
        City cmCity = new City(null, "Campinas", sp);

        Customer customer1 = new Customer(null, "Maria das Dores", "gersonpssesi123@gmail.com", "20164050078", TypeCustomer.PESSOA_FISICA, be.encode("123"));
        customer1.getTel().addAll(Arrays.asList("24351435", "53126355"));
        Customer customer2 = new Customer(null, "Ana Maria", "anaMaria@gmail.com", "47265810074", TypeCustomer.PESSOA_FISICA, be.encode("321"));
        customer2.addPerfil(Perfil.ADMIN);
        customer2.getTel().addAll(Arrays.asList("24351436"));

        Address address1 = new Address(null, "Rua 1", "300", "casa", "Jardim", "03297266", customer1, ubCity);
        Address address2 = new Address(null, "Rua 2", "400", "casa 3", "Jardim", "63429628", customer1, spCity);
        Address address3 = new Address(null, "Rua 3", "500", "casa 2", "Jardim", "63429628", customer2, cmCity);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), customer1, address1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), customer1, address2);

        Payment payment1 = new CardPayment(null, StatePayment.QUITADO, ped1, 6);
        ped1.setPayment(payment1);

        Payment payment2 = new PaymentSlip(null, StatePayment.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPayment(payment2);

        ItemPedido item1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);

        ItemPedido item2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);

        ItemPedido item3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);

        customer1.getAddress().addAll(Arrays.asList(address1, address2));
        customer2.getAddress().addAll(Arrays.asList(address3));

        cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProducts().addAll(Arrays.asList(prod2, prod4));
        cat3.getProducts().addAll(Arrays.asList(prod5, prod6));
        cat4.getProducts().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
        cat5.getProducts().addAll(Arrays.asList(prod8));
        cat6.getProducts().addAll(Arrays.asList(prod9, prod10));
        cat7.getProducts().addAll(Arrays.asList(prod11));

        prod1.getCategories().addAll(Arrays.asList(cat1, cat4));
        prod2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        prod3.getCategories().addAll(Arrays.asList(cat1, cat4));
        prod4.getCategories().addAll(Arrays.asList(cat2));
        prod5.getCategories().addAll(Arrays.asList(cat3));
        prod6.getCategories().addAll(Arrays.asList(cat3));
        prod7.getCategories().addAll(Arrays.asList(cat4));
        prod8.getCategories().addAll(Arrays.asList(cat5));
        prod9.getCategories().addAll(Arrays.asList(cat6));
        prod10.getCategories().addAll(Arrays.asList(cat6));
        prod11.getCategories().addAll(Arrays.asList(cat7));

        mg.getCities().addAll(Arrays.asList(ubCity));
        sp.getCities().addAll(Arrays.asList(spCity, cmCity));

        customer1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        ped1.getItens().addAll(Arrays.asList(item1, item2));
        ped2.getItens().addAll(Arrays.asList(item3));

        prod1.getItens().addAll(Arrays.asList(item1));
        prod2.getItens().addAll(Arrays.asList(item3));
        prod3.getItens().addAll(Arrays.asList(item2));

        repositoryCategory.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        repositoryProduct.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));
        repositoryState.saveAll(Arrays.asList(mg, sp));
        repositoryCity.saveAll(Arrays.asList(ubCity, spCity, cmCity));
        repositoryCustomer.saveAll(Arrays.asList(customer1, customer2));
        repositoryAddress.saveAll(Arrays.asList(address1, address2, address3));

        repositoryPedido.saveAll(Arrays.asList(ped1, ped2));
        repositoryPayment.saveAll(Arrays.asList(payment1, payment2));

        repositoryItemPedido.saveAll(Arrays.asList(item1, item2, item3));
    }
}
