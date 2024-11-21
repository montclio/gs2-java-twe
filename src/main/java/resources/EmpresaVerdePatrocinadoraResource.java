package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.EmpresaVerdePatrocinadoraBO;
import model.bo.UsuarioBO;
import model.vo.EmpresaVerdePatrocinadoraVO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

@Path("/empresaVerdePatrocinadora")
public class EmpresaVerdePatrocinadoraResource {
    private final EmpresaVerdePatrocinadoraBO empresaPatroBO;
    public EmpresaVerdePatrocinadoraResource() throws  ClassNotFoundException, SQLException{
        this.empresaPatroBO = new EmpresaVerdePatrocinadoraBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarEmpresaPatrocinadora(@PathParam("id") int idEmpresa) throws SQLException, ClassNotFoundException {
        try {
            EmpresaVerdePatrocinadoraVO empresaPatro = empresaPatroBO.consultarEmpresaVerdePatrocinadoraBO(idEmpresa);
            if (empresaPatro == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Empresa com o ID " + idEmpresa + "não encontrado").build();
            }
            return Response.ok(empresaPatro).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar Empresa: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarEmpresaVerdePatrocinadora(EmpresaVerdePatrocinadoraVO empresaPatro) {
        try {
            EmpresaVerdePatrocinadoraVO empresaPatroGerada = empresaPatroBO.cadastrarEmpresaVerdePatrocinadoraBO(empresaPatro);
            return Response.status(Response.Status.CREATED).entity(empresaPatro).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar Empresa: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarEmpresaVerdePatrocinadora(@PathParam("id") Integer id, EmpresaVerdePatrocinadoraVO empresaPatroAtualizado) {
        try {
            EmpresaVerdePatrocinadoraBO empresaPatroBO = new EmpresaVerdePatrocinadoraBO();

            boolean atualizado = empresaPatroBO.atualizarUsuarioBO(id, empresaPatroAtualizado);

            if (atualizado) {
                return Response.ok(empresaPatroAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Empresa não encontrado ou não atualizado.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o Empresa.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirEmpresaVerdePatrocinadora(@PathParam("id") int idEmpresa) {
        try {
            empresaPatroBO.excluirEmpresaVerdePatrocinadoraBO(idEmpresa);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir Empresa: " + e.getMessage())
                    .build();
        }
    }

}
