package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.UsuarioVO;

public class UsuarioDAO {
    public Connection conexao;

    public UsuarioDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }


    public int cadastrarUsuario(UsuarioVO usuario) {
        int idUsuarioGerado = -1;

        try {
            String sql = "INSERT INTO TB_TWE_USUARIO (NOME, SOBRENOME, DATA_NASCIMENTO, USUARIO_EMAIL, USUARIO_CEP, SENHA, PONTUACAO_USUARIO, SEXO)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setDate(3, new Date(usuario.getDataNascimento().getTime()));
            stmt.setString(4, usuario.getUsuarioEmail());
            stmt.setInt(5, usuario.getUsuarioCep());
            stmt.setString(6, usuario.getSenha());
            stmt.setInt(7, usuario.getPontuacaoUsuario());
            stmt.setString(8, usuario.getSexo());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idUsuarioGerado = rs.getInt(1); // O ID gerado pelo banco
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar o usuário: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return idUsuarioGerado;
    }


    public UsuarioVO atualizarUsuario(UsuarioVO usuario) {
        try {
            String sql = "UPDATE TB_TWE_USUARIO SET NOME = ?, SOBRENOME = ?, DATA_NASCIMENTO = ?, USUARIO_EMAIL = ?, "
                    + "USUARIO_CEP = ?, SENHA = ?, PONTUACAO_USUARIO = ?, SEXO = ? WHERE ID_USUARIO = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setDate(3, new Date(usuario.getDataNascimento().getTime()));
            stmt.setString(4, usuario.getUsuarioEmail());
            stmt.setInt(5, usuario.getUsuarioCep());
            stmt.setString(6, usuario.getSenha());
            stmt.setInt(7, usuario.getPontuacaoUsuario());
            stmt.setString(8, usuario.getSexo());
            stmt.setInt(9, usuario.getIdUsuario());

            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar o usuário: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return usuario;
    }


    public UsuarioVO consultarUsuario(int idUsuario) {
        UsuarioVO usuario = null;

        try {
            String sql = "SELECT * FROM TB_TWE_USUARIO" + " WHERE ID_USUARIO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setSobrenome(rs.getString("SOBRENOME"));
                usuario.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                usuario.setUsuarioEmail(rs.getString("USUARIO_EMAIL"));
                usuario.setUsuarioCep(rs.getInt("USUARIO_CEP"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setPontuacaoUsuario(rs.getInt("PONTUACAO_USUARIO"));
                usuario.setSexo(rs.getString("SEXO"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar o usuário: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return usuario;
    }

    public void deletarUsuario(int idUsuario) {
        try {
            String sql = "DELETE FROM TB_TWE_USUARIO WHERE ID_USUARIO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate(); // Executa o delete

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar o usuário: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
    }
}
