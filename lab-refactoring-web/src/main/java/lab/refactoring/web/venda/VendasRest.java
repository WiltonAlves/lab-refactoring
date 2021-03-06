package lab.refactoring.web.venda;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/vendas")
public class VendasRest {

  @Inject
  private VendaRepository vendaRepository;

  @POST
  @Consumes(APPLICATION_JSON)
  public Response realizarVenda(Venda novaVenda) {
    vendaRepository.save(novaVenda);
    return ok().build();
  }

  @Path("{codigo}")
  @DELETE
  public Response excluirVenda(@PathParam("codigo") Integer codigo) {
    vendaRepository.delete(codigo);
    return ok().build();
  }

  @DELETE
  public Response excluirVendas() {
    vendaRepository.deleteAll();
    return ok().build();
  }

  @Path("{codigo}")
  @GET
  @Produces(APPLICATION_JSON)
  public Response obterVenda(@PathParam("codigo") Integer codigo) {
    Venda venda = vendaRepository.findOne(codigo);
    return ok().entity(venda).build();
  }

  @GET
  @Produces(APPLICATION_JSON)
  public Response obterVendas() {
    Iterable<Venda> vendas = vendaRepository.findAll();
    return ok().entity(vendas).build();
  }

  @Path("/recentes")
  @GET
  @Produces(APPLICATION_JSON)
  public Response obterVendasRecentes() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, -7);
    Date d = c.getTime();
    Iterable<Venda> vendas = vendaRepository.findByDataGreaterThan(d);
    return ok().entity(vendas).build();
  }
}
