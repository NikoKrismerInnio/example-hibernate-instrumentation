package io.myplant.example.hibernate;

import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import io.myplant.example.hibernate.repository.AssetDetailRepository;
import io.myplant.example.hibernate.repository.AssetRepository;
import io.myplant.example.hibernate.repository.DataItemRepository;
import io.myplant.example.hibernate.repository.PropertyRepository;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@ComponentScan("io.myplant.example.hibernate")
public class BaseTestWithPostgres {
    @ClassRule
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer =
            PostgresContainer.getInstance();

    @Autowired protected AssetRepository assetRepository;
    @Autowired protected AssetDetailRepository assetDetailRepository;
    @Autowired protected DataItemRepository dataItemRepository;
    @Autowired protected PropertyRepository propertyRepository;

    @Autowired JdbcTemplate jdbcTemplate;

    protected void truncateTables() {
        deleteFromTables(
                jdbcTemplate,
                "asset",
                "asset_detail",
                "data_item",
                "property"
        );
    }
}
