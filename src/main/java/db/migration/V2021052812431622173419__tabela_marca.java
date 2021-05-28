package db.migration;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
public class V2021052812431622173419__tabela_marca extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {

            using(configuration)
                .createTable("marca")
                    .column("id", BIGINT.identity(true))
                    .column("nome", VARCHAR(255).nullable(false))
                .constraints(
                    primaryKey("id"),
                    unique("nome")
                )
            .execute();

            using(configuration)
                .alterTable("modelo")
                    .addColumn("marca_id", BIGINT.nullable(true))
                .execute();

            using(configuration)
                .alterTable("modelo")
                    .add(foreignKey("marca_id").references("marca", "id"))
                .execute();
        });
    }
}
