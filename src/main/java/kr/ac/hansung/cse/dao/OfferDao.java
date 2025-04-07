package kr.ac.hansung.cse.dao;

import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OfferDao {

    // JdbcTemplate: Spring의 JDBC 추상화 도구, 데이터베이스 작업을 간소화함
    private JdbcTemplate jdbcTemplate;  // psa(portable service abstraction)

    // DataSource를 주입받아 JdbcTemplate 초기화
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 전체 레코드 수를 반환하는 메소드
    public int getRowCount() {
        String sqlStatement= "select count(*) from offers";
        return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
    }

    // 이름으로 단일 Offer 객체를 조회하는 메소드 (Read)
    public Offer getOffer(String name) {
        String sqlStatement= "select * from offers where name=?";
        return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name},
                new RowMapper<Offer>() {

                    // ResultSet의 각 행을 Offer 객체로 변환하는 매퍼
                    @Override
                    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Offer offer= new Offer();

                        offer.setId(rs.getInt("id"));
                        offer.setName(rs.getString("name"));
                        offer.setEmail(rs.getString("email"));
                        offer.setText(rs.getString("text"));

                        return offer;
                    }
                });
    }

    // 모든 Offer 객체를 조회하는 메소드 (Read)
    public List<Offer> getOffers() {
        String sqlStatement= "select * from offers";
        return jdbcTemplate.query(sqlStatement, new RowMapper<Offer>() {

            // ResultSet의 각 행을 Offer 객체로 변환하는 매퍼
            @Override
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer= new Offer();

                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setText(rs.getString("text"));

                return offer;
            }
        });
    }

    // 새로운 Offer 객체를 삽입하는 메소드 (Create)
    public boolean insert(Offer offer) {
        String name= offer.getName();
        String email= offer.getEmail();
        String text = offer.getText();

        String sqlStatement= "insert into offers (name, email, text) values (?,?,?)";

        // update 메소드는 영향받은 행의 수를 반환, 1이면 성공
        return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text}) == 1);
    }

    // 기존 Offer 객체를 수정하는 메소드 (Update)
    public boolean update(Offer offer) {
        int id = offer.getId();
        String name= offer.getName();
        String email= offer.getEmail();
        String text = offer.getText();

        String sqlStatement= "update offers set name=?, email=?, text=? where id=?";

        // 영향받은 행이 1개이면 성공
        return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text, id}) == 1);
    }

    // ID로 Offer 객체를 삭제하는 메소드 (Delete)
    public boolean delete(int id) {
        String sqlStatement= "delete from offers where id=?";
        // 영향받은 행이 1개이면 성공
        return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1);
    }
}
