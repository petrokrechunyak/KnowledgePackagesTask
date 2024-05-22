package org.testtask.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.testtask.demo.entity.KnowledgePac;
import org.testtask.demo.entity.KnowledgePacSet;
import org.testtask.demo.repository.KPacSetRepository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class KPacSetRepositoryImpl implements KPacSetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_KPAC_SET = "INSERT INTO kpac_set (title) VALUES (?)";
    private static final String SELECT_KPAC_SET_BY_ID = "SELECT id, title FROM kpac_set WHERE id = ?";
    private static final String SELECT_ALL_KPAC_SETS = "SELECT id, title FROM kpac_set";
    private static final String DELETE_KPAC_SET = "DELETE FROM kpac_set WHERE id = ?";
    private static final String INSERT_KPAC_SET_KPAC = "INSERT INTO kpac_set_kpac (kpac_set_id, kpac_id) VALUES (?, ?)";
    private static final String DELETE_KPACS_FROM_KPAC_SET = "DELETE FROM kpac_set_kpac WHERE kpac_set_id = ?";

    public void create(KnowledgePacSet knowledgePacSet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(INSERT_KPAC_SET, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, knowledgePacSet.getTitle());
            return ps;
        }, keyHolder);

        BigInteger KnowledgePacSetId = (BigInteger) keyHolder.getKey();

        for (KnowledgePac kPac : knowledgePacSet.getKPacs()) {
            linkKnowledgePacSetToKPac(KnowledgePacSetId.longValue(), kPac.getId());
        }
    }

    public KnowledgePacSet findById(Long id) {
        KnowledgePacSet KnowledgePacSet = jdbcTemplate.queryForObject(SELECT_KPAC_SET_BY_ID, new Object[]{id}, new KnowledgePacSetRowMapper());
        KnowledgePacSet.setKPacs(findKPacsByKnowledgePacSetId(id));
        return KnowledgePacSet;
    }

    public List<KnowledgePacSet> findAll() {
        List<KnowledgePacSet> KnowledgePacSets = jdbcTemplate.query(SELECT_ALL_KPAC_SETS, new KnowledgePacSetRowMapper());
        KnowledgePacSets.forEach(KnowledgePacSet -> KnowledgePacSet.setKPacs(findKPacsByKnowledgePacSetId(KnowledgePacSet.getId())));
        return KnowledgePacSets;
    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_KPACS_FROM_KPAC_SET, id);
        jdbcTemplate.update(DELETE_KPAC_SET, id);
    }

    private void linkKnowledgePacSetToKPac(Long KnowledgePacSetId, Long kPacId) {
        jdbcTemplate.update(INSERT_KPAC_SET_KPAC, KnowledgePacSetId, kPacId);
    }

    private List<KnowledgePac> findKPacsByKnowledgePacSetId(Long KnowledgePacSetId) {
        return jdbcTemplate.query(
                "SELECT k.id, k.title, k.description, k.creation_date " +
                        "FROM kpac k " +
                        "JOIN kpac_set_kpac ks ON k.id = ks.kpac_id " +
                        "WHERE ks.kpac_set_id = ?",
                new Object[]{KnowledgePacSetId},
                new KPacRowMapper());
    }

    private static final class KnowledgePacSetRowMapper implements RowMapper<KnowledgePacSet> {
        @Override
        public KnowledgePacSet mapRow(ResultSet rs, int rowNum) throws SQLException {
            KnowledgePacSet KnowledgePacSet = new KnowledgePacSet();
            KnowledgePacSet.setId(rs.getLong("id"));
            KnowledgePacSet.setTitle(rs.getString("title"));
            return KnowledgePacSet;
        }
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
