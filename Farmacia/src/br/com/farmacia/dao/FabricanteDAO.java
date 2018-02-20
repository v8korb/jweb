package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fabricante;
import br.com.farmacia.factory.ConectionFactory;

public class FabricanteDAO {
	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConectionFactory.connect();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConectionFactory.connect();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		comando.executeUpdate();
	}

	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConectionFactory.connect();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();
	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConectionFactory.connect();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;
		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConectionFactory.connect();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			lista.add(f);	
		}
		return lista;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConectionFactory.connect();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%");	
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()) {
			Fabricante f2 = new Fabricante();
			f2.setCodigo(resultado.getLong("codigo"));
			f2.setDescricao(resultado.getString("descricao"));
			lista.add(f2);	
		}
		return lista;
	}
	
	public static void main(String[] args) {
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("Descricao A"); Fabricante
		 * f2 = new Fabricante(); f2.setDescricao("Descricao B");
		 */

		/*
		 * Fabricante f3 = new Fabricante(); f3.setCodigo(1L); Fabricante f4 = new
		 * Fabricante(); f4.setCodigo(2L);
		 */

		/*
		 * Fabricante f5 = new Fabricante(); f5.setCodigo(12L);
		 * f5.setDescricao("DESCRICAO C");
		 */

/*		Fabricante f6 = new Fabricante();
		f6.setCodigo(12L);

		Fabricante f7 = new Fabricante();
		f7.setCodigo(13L);*/
		
		Fabricante f8 = new Fabricante();
		f8.setDescricao("ao C");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			// fdao.salvar(f1);
			// fdao.salvar(f2);
			// fdao.excluir(f3);
			// fdao.excluir(f4);
			// fdao.editar(f5);
			//Fabricante FRes1 = fdao.buscarPorCodigo(f6);
			//Fabricante FRes2 = fdao.buscarPorCodigo(f7);
			//ArrayList<Fabricante> lista = fdao.listar();		
			//for(Fabricante f : lista) {
			//	System.out.println(f);
			//}
			ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f8);	
			//System.out.println(f8);
			for(Fabricante ff : lista) {
				System.out.println(ff.toString());
			}					
			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println("Not OK");
			System.out.println(e);
		}
	}
}
