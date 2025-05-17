package mk.ukim.fikni.labs.config;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewCreator {

    private final JdbcTemplate jdbcTemplate;

    public MaterializedViewCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createViews() {
        jdbcTemplate.execute("""
            CREATE MATERIALIZED VIEW IF NOT EXISTS accommodation_count_by_host AS
            SELECT
                h.id AS host_id,
                h.name AS host_name,
                h.surname AS host_surname,
                COUNT(b.id) AS accommodation_count
            FROM host h
            LEFT JOIN booking b ON h.id = b.host_id
            GROUP BY h.id, h.name, h.surname;
        """);

        jdbcTemplate.execute("""
            CREATE MATERIALIZED VIEW IF NOT EXISTS host_count_by_country AS
            SELECT
                c.id AS country_id,
                c.name AS country_name,
                COUNT(h.id) AS host_count
            FROM country c
            LEFT JOIN host h ON c.id = h.country_id
            GROUP BY c.id, c.name;
        """);
    }
}