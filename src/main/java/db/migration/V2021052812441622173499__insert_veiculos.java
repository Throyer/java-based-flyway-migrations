package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.jooq.DSLContext;

public class V2021052812441622173499__insert_veiculos extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {
            var dsl = using(configuration);

            dsl.insertInto(table("marca"), field("nome"))
                .values("Toyota")
                .values("Ford")
                .values("volkswagen")
            .execute();
    
            dsl.insertInto(table("modelo"), field("nome"), field("marca_id"))
                .values("Corolla", findIdByNome("toyota", "marca", dsl))
                .values("Fiesta", findIdByNome("Ford", "marca", dsl))
                .values("Gol", findIdByNome("volkswagen", "marca", dsl))
            .execute();
    
            dsl.insertInto(
                    table("veiculo"),
                    field("placa"),
                    field("quilometragem"),
                    field("cor"),
                    field("preco"),
                    field("quantidade_portas"),
                    field("ano"),
                    field("modelo_id")
                )
                .values("ABC-1234", 120000, "Prata", 45000d, 4, 2015, findIdByNome("Corolla", "modelo", dsl))
                .values("GFD-2266", 126500, "Preto", 69800d, 4, 2020, findIdByNome("Corolla", "modelo", dsl))
                .values("DCE-5577", 250000, "Branco", 25000, 2, 2013, findIdByNome("Fiesta", "modelo", dsl))
                .values("BBF-7856", 150000, "Preto metalizado", 360000, 2, 2017, findIdByNome("Gol", "modelo", dsl))
            .execute();
        });
    }

    private Long findIdByNome(String nome, String tableName, DSLContext dsl) {
        return dsl.select(field("id"))
        .from(table(tableName))
            .where(field("nome").equal(nome))
        .limit(1).fetch()
            .getValues(field("id"), Long.class)
        .stream()
            .findFirst()
        .orElseThrow();
    }
}
