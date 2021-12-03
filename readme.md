# Migrações de banco de dados baseadas em arquivos java
Sempre me senti incomodado com o fato de ser muito mais legal nas comunidades de javascript, CSharp, php e outras a criação das migrações de banco de dados em arquivos específicos (builders) no lugar de scripts SQL.

Então fui atras de tentar trazer a mesma experiencia no javinha ❤️.

E eu consegui um resultado legal. A ideia por traz disso é bem simples na realidade eu juntei duas bibliotecas extremamente populares do mundo java e criei um script sh pra mais conveniência.

o script [migration_create](https://github.com/Throyer/java-based-flyway-migrations/blob/master/migartion_create.sh) é basicamente um comando de terminal que cria um arquivo de migração com os timestamps, dentro desse arquivo que é uma migração do flyway eu crio uma querydsl utilizando o jooq e ta feito o sorvetinho kkkk. 

> 🚨 **eu já criei uma biblioteca pra auxilio na criação das migrações**
>
>> acessa lá 👀 [![Release](https://jitpack.io/v/throyer/migration-maven-plugin.svg)](https://jitpack.io/#throyer/migration-maven-plugin)

## um exemplo comparando uma migração feita com `typeorm` vs minha migração (`flyway + jooq`):

### Java
```java
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
                .constraints(
                    primaryKey("id"))
                .execute();
        });
    }
}
```


### Typescript
```ts
import { MigrationInterface, QueryRunner, Table, TableColumn } from "typeorm";

export class tabelaVeiculos1622166625338 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(new Table({
            name: 'veiculo',
            columns: [
                new TableColumn({
                    name: 'id',
                    type: 'bigint',
                    generationStrategy: 'increment',
                    isPrimary: true,
                    isGenerated: true,
                }),
                new TableColumn({
                    name: 'placa',
                    type: 'varchar',
                    isNullable: true,
                }),
                new TableColumn({
                    name: 'quilometragem',
                    type: 'varchar',
                    isNullable: true,
                }),
                new TableColumn({
                    name: 'cor',
                    type: 'varchar',
                    isNullable: true,
                }),
                new TableColumn({
                    name: 'preco',
                    type: 'decimal',
                    precision: 10,
                    scale: 2,
                    default: 0,
                }),
                new TableColumn({
                    name: 'quantidade_portas',
                    type: 'int',
                    isNullable: true,
                }),
            ]
        }))
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropTable('veiculo')
    }

}
```

As migrações mais completas, com chave estrangeira e etc tambem são bem mais simples de se fazer com o jooq [link da pasta das migrações](https://github.com/Throyer/java-based-flyway-migrations/tree/master/src/main/java/db/migration).

Futuramente eu pretendo fazer uma biblioteca pro maven e deixar esse comando que cria as migrações mais esperto e quem sabe fazer umas opção de rollback e drop tambem...

# Spring boot
link das configurações do spring initializ que eu usei: https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.0.RELEASE&packaging=jar&jvmVersion=16&groupId=com.github&artifactId=concessionaria&name=concessionaria&description=concession%C3%A1ria%20api&packageName=com.github.concessionaria&dependencies=data-jpa,mysql,web,devtools,flyway,validation

