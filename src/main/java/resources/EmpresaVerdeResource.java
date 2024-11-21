package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.EmpresaVerdeBO;
import model.bo.UsuarioBO;
import model.vo.EmpresaVerdeVO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

@Path("/empresaVerde")
public class EmpresaVerdeResource {
    private final EmpresaVerdeBO empresaVerdeBO;
    public EmpresaVerdeResource() throws  ClassNotFoundException, SQLException{
        this.empresaVerdeBO = new EmpresaVerdeBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarEmpresaVerde(@PathParam("id") int idEmpresa) throws SQLException, ClassNotFoundException {
        try {
            EmpresaVerdeVO empresaVerde = empresaVerdeBO.consultarEmpresaVerdeBO(idEmpresa);
            if (empresaVerde == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Empresa com o ID " + idEmpresa + "não encontrado").build();
            }
            return Response.ok(empresaVerde).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar Empresa: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarEmpresaVerde(EmpresaVerdeVO empresaVerde) {
        try {
            EmpresaVerdeVO empresaVerdeGerada = empresaVerdeBO.cadastrarEmpresaVerdeBO(empresaVerde);
            return Response.status(Response.Status.CREATED).entity(empresaVerde).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar sua Empresa: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarEmpresaVerde(@PathParam("id") int idEmpresa, EmpresaVerdeVO empresaVerdeAtualizada) {
        try {
            EmpresaVerdeBO empresaVerdeBO = new EmpresaVerdeBO();

            boolean atualizado = empresaVerdeBO.atualizarEmpresaVerdBO(idEmpresa, empresaVerdeAtualizada);

            if (atualizado) {
                return Response.ok(empresaVerdeAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Empresa não encontrada ou não atualizada.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar a Empresa.")
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirEmpresaVerde(@PathParam("id") int idEmpresa) {
        try {
            empresaVerdeBO.excluirEmpresaVerdeBO(idEmpresa);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir a Empresa: " + e.getMessage())
                    .build();
        }
    }

}
