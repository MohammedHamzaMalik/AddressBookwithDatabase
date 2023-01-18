import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    //an example connection to H2 DB
    val url = "jdbc:mysql://localhost:3306/addressbook_db"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "hamza"
    val password = "password"
    Database.connect(url, driver, username, password)


    transaction {
        // print sql to std-out
        addLogger(StdOutSqlLogger)

        SchemaUtils.create (Cities)

        // insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
        val stPeteId = Cities.insert {
            it[name] = "St. Petersburg"
        } get Cities.id

        // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
        println("Cities: ${Cities.selectAll()}")
    }
}

object Cities: IntIdTable() {
    val name = varchar("name", 50)
}
