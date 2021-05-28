package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V2021052711191622168370__tabela_veiculos extends BaseJavaMigration {

    public void migrate(Context context) throws Exception {
        var dsl = using(context.getConnection());
        dsl.transaction(configuration -> {
            using(configuration)
                .createTable("veiculo")
                    .column("id", BIGINT.identity(true))
                    .column("placa", VARCHAR(255).nullable(true))
                    .column("quilometragem", INTEGER.nullable(true))
                    .column("cor", VARCHAR(255).nullable(true))
                    .column("preco", DECIMAL(10, 2).nullable(true))
                    .column("quantidade_portas", INTEGER.nullable(true))
                    .column("ano", INTEGER.nullable(true))
                .constraints(
                    primaryKey("id"))
                .execute();
        });
    }
}
