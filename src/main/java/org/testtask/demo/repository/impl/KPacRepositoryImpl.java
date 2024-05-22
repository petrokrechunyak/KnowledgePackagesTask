package org.testtask.demo.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.testtask.demo.entity.KnowledgePac;
import org.testtask.demo.repository.KPacRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KPacRepositoryImpl implements KPacRepository {

    private static final String INSERT_KPAC = "INSERT INTO kpac (title, description, creation_date) VALUES (?, ?, ?)";
    private static final String SELECT_KPAC_BY_ID = "SELECT id, title, description, creation_date FROM kpac WHERE id = ?";
    private static final String SELECT_ALL_KPACS = "SELECT id, title, description, creation_date FROM kpac";
    private static final String DELETE_KPAC = "DELETE FROM kpac WHERE id = ?";
    private static final String DELETE_KPACS_FROM_KPAC_SET = "DELETE FROM kpac_set_kpac WHERE kpac_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public KPacRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(KnowledgePac kPac) {
        jdbcTemplate.update(INSERT_KPAC, kPac.getTitle(), kPac.getDescription(), kPac.getCreationDate());
    }

    @Override
    public KnowledgePac findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_KPAC_BY_ID, new Object[]{id}, new KPacRowMapper());
    }

    @Override
    public List<KnowledgePac> findAll() {
        return jdbcTemplate.query(SELECT_ALL_KPACS, new KPacRowMapper());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_KPACS_FROM_KPAC_SET, id);
        jdbcTemplate.update(DELETE_KPAC, id);
    }

    private static final class KPacRowMapper implements RowMapper<KnowledgePac> {
        @Override
        public KnowledgePac mapRow(ResultSet rs, int rowNum) throws SQLException {
            KnowledgePac kPac = new KnowledgePac();
            kPac.setId(rs.getLong("id"));
            kPac.setTitle(rs.getString("title"));
            kPac.setDescription(rs.getString("description"));
            kPac.setCreationDate(rs.getString("creation_date"));
            return kPac;
        }
    }

}
