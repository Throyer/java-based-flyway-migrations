package db.migration;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
public class V2021052812111622171493__tabela_modelo extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        var dsl = using(context.getConnection());
        dsl.transaction(configuration -> {

            using(configuration)
                .createTable("modelo")
                    .column("id", BIGINT.identity(true))
                    .column("nome", VARCHAR(255).nullable(false))
                .constraints(
                    primaryKey("id"),
                    unique("nome")
                )
            .execute();

            using(configuration)
                .alterTable("veiculo")
                    .addColumn("modelo_id", BIGINT.nullable(true))
                .execute();

            using(configuration)
                .alterTable("veiculo")
                    .add(foreignKey("modelo_id").references("modelo", "id"))
                .execute();
        });
    }
}
