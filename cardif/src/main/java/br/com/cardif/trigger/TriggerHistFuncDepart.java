package br.com.cardif.trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.h2.tools.TriggerAdapter;

public class TriggerHistFuncDepart extends TriggerAdapter {

	@Override
	public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {

		try (PreparedStatement stmt = conn.prepareStatement(criarQuery())) {
			int index = 0;
			stmt.setObject(++index, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			stmt.setObject(++index, oldRow.getLong("DEPARTAMENTO_ID"));
			stmt.setObject(++index, oldRow.getLong("FUNCIONARIO_ID"));
			stmt.execute();
		}
	}

	private String criarQuery() {
		StringBuilder insert = new StringBuilder("INSERT INTO HISTORICO_FUNC_DEPART ");
		insert.append("(HIST_FUNC_DEPT_ID , DATA_CRIACAO , DEPARTAMENTO_ID , FUNCIONARIO_ID )");
		insert.append(" VALUES ");
		insert.append("(SEQ_HISTORICO_FUNC_DEPART.NEXTVAL, ?, ?, ?)");
		return insert.toString();
	}

}