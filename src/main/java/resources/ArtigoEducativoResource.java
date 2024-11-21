package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.ArtigoEducativoBO;
import model.vo.ArtigoEducativoVO;


import java.sql.SQLException;

@Path("/artigo")
public class ArtigoEducativoResource {
    private final ArtigoEducativoBO artigoBO;
    public ArtigoEducativoResource() throws  ClassNotFoundException, SQLException{
        this.artigoBO = new ArtigoEducativoBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarArtigoEducativo(@PathParam("id") int idArtigo) throws SQLException, ClassNotFoundException {
        try {
            ArtigoEducativoVO artigo = artigoBO.consultarUArtigoEducativoBO(idArtigo);
            if (artigo == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Artigo com o ID " + idArtigo + "não encontrado").build();
            }
            return Response.ok(artigo).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar usuário: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarArtigoEducativo(ArtigoEducativoVO artigo) {
        try {
            ArtigoEducativoVO artigoGerado = artigoBO.cadastrarArtigoEducativoBO(artigo);
            return Response.status(Response.Status.CREATED).entity(artigo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar artigo: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarArtigoEducativo(@PathParam("id") int idArtigo, ArtigoEducativoVO artigoAtualizado) {
        try {
            ArtigoEducativoBO artigoBO = new ArtigoEducativoBO();

            boolean atualizado = artigoBO.atualizarArtigoEducativoBO(idArtigo, artigoAtualizado);

            if (atualizado) {
                return Response.ok(artigoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Artigo não encontrado ou não atualizado.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o Artigo.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirArtigoEducativo(@PathParam("id") int idArtigo) {
        try {
            artigoBO.excluirArtigoEducativoBO(idArtigo);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir Artigo: " + e.getMessage())
                    .build();
        }
    }

}
