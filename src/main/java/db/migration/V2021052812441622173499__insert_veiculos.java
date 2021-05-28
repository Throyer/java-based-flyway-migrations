package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V2021052812441622173499__insert_veiculos extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        var dsl = using(context.getConnection());

        dsl.insertInto(table("marca"), field("nome"))
            .values("Toyota")
            .values("Ford")
        .execute();

        dsl.insertInto(table("modelo"), field("nome"), field("marca_id"))
            .values("Corolla", 1)
            .values("Fiesta", 2)
        .execute();

        dsl.insertInto(
                table("veiculo"),
                field("placa"),
                field("quilometragem"),
                field("cor"),
                field("preco"),
                field("quantidade_portas"),
                field("modelo_id")
            )
            .values("ABC-1234", 120000, "Prata", 45000d, 4, 1)
            .values("DCE-5577", 250000, "Branco", 25000, 2, 2)
        .execute();
    }
}
