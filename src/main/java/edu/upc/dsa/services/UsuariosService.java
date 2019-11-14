package edu.upc.dsa.services;

import edu.upc.dsa.UsuariosManager;
import edu.upc.dsa.UsuariosManagerImp;
import edu.upc.dsa.ProductsManager;
import edu.upc.dsa.ProductsManagerImp;
import edu.upc.dsa.TracksManagerImpl;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Usuario;

import edu.upc.dsa.models.Track;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/usuarios", description = "Endpoint to Usuario Service")
@Path("/usuarios")
public class UsuariosService {

    private UsuariosManager um;

    public UsuariosService() {
        this.um = new UsuariosManagerImp().getInstance();
        if (um.size()==0) {
            this.um.addUsuario("1", "yoel", "cruz_torres");
            this.um.addUsuario("2", "miriam", "cruz_torres");
            this.um.addUsuario("3", "jose", "cruz_sierra");
        }
    }

    @GET
    @ApiOperation(value = "get all usuarios", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/ordenadoAlfabeticamente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {

        List<Usuario> usuarios = this.um.usuariosOrdenadosAlfabeticamente();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Create a new Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getId()==null)  return Response.status(500).entity(usuario).build();
        this.um.addUsuario(usuario);
        return Response.status(201).entity(usuario).build();
    }

    @PUT
    @ApiOperation(value = "Update a Usuario with the id that you write", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateUsuarioById(Usuario usuario) {

        Usuario u = this.um.updateUsuarioById(usuario);

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Get a Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") String id) {
        Usuario u = this.um.getUsuarioById(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

//    @POST
//    @ApiOperation(value = "Create a new Objeto", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
//            @ApiResponse(code = 500, message = "Validation Error")
//
//    })
//
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response newObjeto(@PathParam("id"+ "objeto") String id, String name) {
//        Usuario u = this.um.getUsuarioById(id);
//        if (u == null) return Response.status(404).build();
//        else  {
//            u.addObjeto(name);
//            return Response.status(201).entity(u).build();
//        }
//    }

//    @GET
//    @ApiOperation(value = "get num usuarios", notes = "asdasd")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
//    })
//    @Path("/ordenadoAlfabeticamente")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getNumUsuarios() {
//        List<Usuario> usuarios = this.um.usuariosOrdenadosAlfabeticamente();
//        int numUsuarios = this.um.size();
//
//        return Response.status(201).entity(numUsuarios).build();
//    }






}
