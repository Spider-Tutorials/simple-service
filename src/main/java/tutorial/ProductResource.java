package tutorial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public List<Product> getProducts() {
        return ProductManager.getInstance().getProducts();
    }

    @GET
    @Path("/{product}")
    public Product getProduct(@PathParam("product") String product) {
        return ProductManager.getInstance().getProduct(product);
//        return new Product("aaa", "message", "google");
    }

/*    @DELETE
    @Path("/{product}")
    public String deleteProduct(@PathParam("product") String product){
        return ProductManager.getInstance().deleteProduct(new Product(product, "", ""));
    }*/
}
