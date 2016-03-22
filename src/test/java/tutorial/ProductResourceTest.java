package tutorial;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --

//        c.getConfiguration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetSingleProduct() {
        Product responseMsg = target.path("product/iphone6").request().get(Product.class);
        assertEquals("iphone6", responseMsg.getName());
        assertEquals("apples mobile phone", responseMsg.getDescription());
        assertEquals("apple", responseMsg.getProducer());
    }

    @Test
    public void testGetallProducts() {
        List<Product> products = target.path("product").request().get(List.class);
        assertEquals(2, products.size());
    }
}
