package io.myplant.example.hibernate;

import static org.assertj.core.api.Assertions.assertThat;
import static java.util.stream.Collectors.toList;

import io.myplant.example.hibernate.model.Asset;
import io.myplant.example.hibernate.model.AssetDetail;
import io.myplant.example.hibernate.model.DataItem;
import io.myplant.example.hibernate.model.Property;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = NONE)
public class AssetTest extends BaseTestWithPostgres {

    private final static String[] REGIONS = new String[] { "US", "DE", "NL", "IT", "AT" };
    private final int NR_ASSETS = 9;
    private final int NR_PROPERTIES_PER_ASSETS = 100;
    private final int NR_DATAITEMS_PER_ASSETS = 1_000;

    /* NOTE: if using test containers, remove the disabled annotation here, active the beforeEach
     * annotation and truncate the tables at the beginning of the method (by uncommenting the line).
     * Also make sure to look at the application-test.properties file and activate the corresponding
     * configuration.
     */
    @Test
    @Rollback(false)
    @Disabled
    // @BeforeEach
    public void setup() {
        // super.truncateTables();

        final var random = new SecureRandom();
        final var results = IntStream.rangeClosed(1, NR_ASSETS).mapToObj(i -> {
            final var d = new AssetDetail();
            d.setLocation("Location #" + i);
            d.setCustomer("Customer #" + i % 100);
            d.setRegion(REGIONS[i % REGIONS.length]);

            final var a = new Asset();
            a.setName("Asset #" + i);
            a.setDetails(d);
            a.setProperties(IntStream.rangeClosed(1, NR_PROPERTIES_PER_ASSETS).mapToObj(idx -> new Property("Property #" + idx, String.valueOf(random.nextDouble()))).collect(toList()));
            a.setDataItems(IntStream.rangeClosed(1, NR_DATAITEMS_PER_ASSETS).mapToObj(idx -> new DataItem("DataItem #" + idx, String.valueOf(random.nextDouble()))).collect(toList()));

            return a;
        }).collect(toList());

        assetRepository.saveAll(results);
        log.info("Test entries saved.");
        final var min = results.stream().collect(Collectors.minBy(Comparator.comparing(Asset::getId))).get();
        final var max = results.stream().collect(Collectors.maxBy(Comparator.comparing(Asset::getId))).get();
        log.info("  - min asset id: {}", min);
        log.info("  - max asset id: {}", max);
    }

    @Test
    @Rollback(false)
    public void printAsset() {
        final var sRandom = new SecureRandom();
        final var idRandom = sRandom.nextInt(NR_ASSETS) + 1;
        final var name = "Asset #" + idRandom;

        log.info("Querying for random asset by name: {}", name);
        final var asset = assetRepository.findByName(name).orElse(null);
        assertThat(asset).isNotNull();
        log.info("Asset: {}", asset);
        log.info("Details: {}", asset.getDetails());
        final var diIdRandom = sRandom.nextInt(NR_DATAITEMS_PER_ASSETS);
        final var di = asset.getDataItems().get(diIdRandom);
        assertThat(di).isNotNull();
        assertThat(di.getId()).isNotNull();
        log.info("Random data item: {}", di);

        final var pIdRandom = sRandom.nextInt(NR_PROPERTIES_PER_ASSETS);
        final var p = asset.getProperties().get(pIdRandom);
        log.info("Random property: {}", p);
        assertThat(p).isNotNull();
        assertThat(p.getId()).isNotNull();
    }

    @Test
    @Rollback(false)
    public void printAllAsset() {
        final var assets = assetRepository.findAll(Sort.by("id").descending());
        assets.stream().map(a -> a.getName()).forEach(log::info);
    }

    @Test
    @Rollback(false)
    public void printAssetImage() {
        final var name = "Asset #10" ;

        log.info("Querying for asset (with image) by name: {}", name);
        final var asset = assetRepository.findByName(name).orElse(null);
        assertThat(asset).isNotNull();
        log.info("Asset: {}", asset);
        final var blob = asset.getBlob();
        assertThat(blob).isNotNull();
        log.info("Asset's blob: {}", blob);
    }
}
