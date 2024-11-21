package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.connection.ConexaoFactory;
import model.vo.ArtigoEducativoVO;

public class ArtigoEducativoDAO {
    public Connection conexao;

    public ArtigoEducativoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArtigoEducativoVO cadastrarArtigoEducativo(ArtigoEducativoVO artigo) {
        ArtigoEducativoVO artigoGerado = null;

        try {
            String sql = "INSERT INTO tb_twe_artigos_educativos (tema, autor, link, texto)"
                    + " VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, artigo.getTema());
            stmt.setString(2, artigo.getAutor());
            stmt.setString(3, artigo.getLink());
            stmt.setString(4, artigo.getConteudoTexto());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                artigoGerado = new ArtigoEducativoVO();
                artigoGerado.setTema(artigo.getAutor());
                artigoGerado.setAutor(artigo.getAutor());
                artigoGerado.setLink(artigo.getLink());
                artigoGerado.setConteudoTexto(artigo.getConteudoTexto());
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao cadastrar o Artigo: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return artigoGerado;
    }

    public boolean atualizarArtigoEducativo(int idArtigo, ArtigoEducativoVO artigo) {
        try {
            String sql = "UPDATE tb_twe_artigos_educativos SET tema = ?, autor = ?, link = ?, texto = ?, "
                    + "WHERE id_artigo = ?";

            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, artigo.getTema());
            stmt.setString(2, artigo.getAutor());
            stmt.setString(4, artigo.getLink());
            stmt.setString(5, artigo.getConteudoTexto());

            int linhasAfetadas = stmt.executeUpdate();


            return linhasAfetadas > 0;

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao atualizar o Artigo: " + err.getMessage(), err);
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

    public ArtigoEducativoVO consultarArtigoEducativo(int idArtigo) {
        ArtigoEducativoVO artigo = null;

        try {
            String sql = "SELECT * FROM tb_twe_artigos_educativos" + " WHERE id_artigo = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idArtigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                artigo = new ArtigoEducativoVO();
                artigo.setIdArtigo(rs.getInt("id_artigo"));
                artigo.setTema(rs.getString("tema"));
                artigo.setAutor(rs.getString("autor"));
                artigo.setConteudoTexto(rs.getString("link"));
            }
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao consultar o Artigo: " + err.getMessage(), err);
        } finally {
            try {
                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão: " + e.getMessage(), e);
            }
        }
        return artigo;
    }

    public void deletarArtigoEducativ(int idArtigo) {
        try {
            String sql = "DELETE FROM tb_twe_artigos_educativos WHERE id_artigo = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idArtigo);
            stmt.executeUpdate();

        } catch (SQLException err) {
            throw new RuntimeException("Erro ao deletar o Artigo: " + err.getMessage(), err);
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
