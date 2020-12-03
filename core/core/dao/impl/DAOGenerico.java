package core.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.aplicacao.Resultado;
import core.util.db.DB;
import dominio.EntidadeDominio;

public class DAOGenerico {
	
	public ResultadoDAO salvar(String sqlInsert) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String msg = null;
		Integer idGerado = null;

		try {
			conn = DB.getConnection();
			
			int linhasAfetadas = 0;
			
			ps = conn.prepareStatement(sqlInsert);
			
			linhasAfetadas += ps.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    idGerado = rs.getInt(1);
			}
			
			if (linhasAfetadas != 0) {
				msg = "Cadastro realizado com sucesso. Linhas afetadas: " + linhasAfetadas + ".";
			} else {
				msg = "Falha ao salvar o cadastro. Contate o administrador do sistema.";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}

		ResultadoDAO rDAO = new ResultadoDAO(msg, idGerado);
		return rDAO;
	}
	
	public ResultadoDAO alterar(String sqlUpdate) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String msg = null;
		Integer idGerado = null;

		try {
			conn = DB.getConnection();
			
			int linhasAfetadas = 0;
			
			ps = conn.prepareStatement(sqlUpdate);
			
			linhasAfetadas += ps.executeUpdate(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    idGerado = rs.getInt(1);
			}
			
			if (linhasAfetadas != 0) {
				msg = "Alteração realizada com sucesso. Linhas afetadas: " + linhasAfetadas + ".";
			} else {
				msg = "Falha ao salvar o cadastro. Contate o administrador do sistema.";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}

		ResultadoDAO rDAO = new ResultadoDAO(msg, idGerado);
		return rDAO;
	}
	
	public ResultadoDAO excluir(String sqlDelete) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String msg = null;
		Integer idGerado = null;

		try {
			conn = DB.getConnection();
			
			int linhasAfetadas = 0;
			
			ps = conn.prepareStatement(sqlDelete);
			
			linhasAfetadas += ps.executeUpdate(sqlDelete, Statement.RETURN_GENERATED_KEYS);
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    idGerado = rs.getInt(1);
			}
			
			if (linhasAfetadas != 0) {
				msg = "Exclusão realizada com sucesso. Linhas afetadas: " + linhasAfetadas + ".";
			} else {
				msg = "Falha ao salvar o cadastro. Contate o administrador do sistema.";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}

		ResultadoDAO rDAO = new ResultadoDAO(msg, idGerado);
		return rDAO;
	}
	
	public Resultado consultar(String sqlSelect, ContrutorEntidade construtorEntidade) {
		Resultado resultado = new Resultado();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultadoSelect = null;
		List<EntidadeDominio> listaEntidadesResultadoSelect = new ArrayList<EntidadeDominio>();

		try {			
			conn = DB.getConnection();

			ps = conn.prepareStatement(sqlSelect);
			
			resultadoSelect = ps.executeQuery();	
			
			listaEntidadesResultadoSelect = construtorEntidade.construirEntidades(resultadoSelect);
			
			resultado.setResultadoLista(listaEntidadesResultadoSelect);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(resultadoSelect);
			DB.closeStatement(ps);
			//DB.closeConnection();
		}
		return resultado;
	}

}
